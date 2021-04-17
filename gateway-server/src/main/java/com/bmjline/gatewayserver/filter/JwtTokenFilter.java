package com.bmjline.gatewayserver.filter;

import com.alibaba.fastjson.JSON;
import com.bmjline.authserver.exception.TokenAuthenticationException;
import com.bmjline.authserver.util.JwtUtil;
import com.bmjline.common.api.CommonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author bmj
 */
@Component
@ConfigurationProperties("bmjline.jwt")
public class JwtTokenFilter implements GlobalFilter, Ordered {

    private final StringRedisTemplate stringRedisTemplate;

    private List<String> skipAuthUrls;

    public List<String> getSkipAuthUrls() {
        return skipAuthUrls;
    }

    public void setSkipAuthUrls(List<String> skipAuthUrls) {
        this.skipAuthUrls = skipAuthUrls;
    }

    public JwtTokenFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        ServerHttpResponse response = exchange.getResponse();

        //跳过不需要验证的路径
        if (null != skipAuthUrls && isSkipUrl(url)) {
            return chain.filter(exchange);
        }

        //从请求头中取得token
        String xToken = exchange.getRequest().getHeaders().getFirst("X-Token");
        if (StringUtils.isEmpty(xToken)) {
            return authErrorResponse(response, "token is empty, please check");
        }
        String token = (String) stringRedisTemplate.opsForHash().get(xToken, "token");
        if (StringUtils.isEmpty(token)) {
            return authErrorResponse(response, "invalid token");
        }

        String userId = JwtUtil.getUserId(token);
        //请求中的token是否有效，判断是否需要刷新token
        try {
            JwtUtil.verifyToken(token, userId);
            stringRedisTemplate.opsForHash().put(xToken, "token", JwtUtil.generateToken(userId, userId));
        } catch (TokenAuthenticationException e) {
            return authErrorResponse(response, e.getMessage());
        }

        //如果各种判断都通过，执行chain上的其他业务逻辑
        return chain.filter(exchange);
    }

    /**
     * 判断当前访问的url是否开头URI是在配置的忽略url列表中
     *
     * @param url
     * @return
     */
    public boolean isSkipUrl(String url) {
        for (String skipAuthUrl : skipAuthUrls) {
            if (url.startsWith(skipAuthUrl)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 认证错误输出
     *
     * @param response 响应对象
     * @param message  错误信息
     * @return
     */
    private Mono<Void> authErrorResponse(ServerHttpResponse response, String message) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        byte[] responseByte = JSON.toJSON(CommonResult.unauthorized(message)).toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(responseByte);
        return response.writeWith(Flux.just(buffer));
    }
}
