package com.bmjline.common.constant;

/**
 * @author bmj
 */
public final class AuthConstant {

    private AuthConstant() {
        throw new IllegalStateException("Auth constant class");
    }

    /**
     * 认证信息Http请求头
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * JWT载体key
     */
    public static final String JWT_PAYLOAD_KEY = "payload";

    /**
     * Redis缓存权限规则key
     */
    public static final String PERMISSION_RULES_KEY = "auth:permission:rules";

    /**
     * 黑名单token前缀
     */
    public static final String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";

    public static final String CLIENT_DETAILS_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    public static final String BASE_CLIENT_DETAILS_SQL = "select " + CLIENT_DETAILS_FIELDS + " from oauth_client_details";

    public static final String FIND_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " order by client_id";

    public static final String SELECT_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " where client_id = ?";

    /**
     * 密码加密方式
     */
    public static final String BCRYPT = "{bcrypt}";

    public static final String JWT_USER_ID_KEY = "id";

    public static final String JWT_CLIENT_ID_KEY = "client_id";

    public static final String JWT_JTI_KEY = "client_id";

    /**
     * JWT存储权限前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    public static final String JWT_AUTHORITIES_KEY = "authorities";


    /**
     * BMJLINE ADMIN ID
     */
    public static final String ADMIN_CLIENT_ID = "bmjline-admin-vue";


    /**
     * BMJLINE WEAPP ID
     */
    public static final String WEAPP_CLIENT_ID = "bmjline-weapp";

    /**
     * 后台管理接口路径匹配
     */
    public static final String ADMIN_URL_PATTERN = "/admin/**";
}