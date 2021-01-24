package com.bmjline.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;

@Service("userDetailsServiceConfig")
public class UserDetailsServiceConfig implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("admin")) {
            throw new AcceptPendingException();
        }
        return new User(username, passwordEncoder.encode("111111"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));

    }
}
