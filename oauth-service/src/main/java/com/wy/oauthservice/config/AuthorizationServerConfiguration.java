package com.wy.oauthservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 授权配置
 *
 * @author wangye
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，
     * 可以把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息；
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String password = passwordEncoder.encode("123456");
        clients
                .inMemory()
                //客户端ID
                .withClient("producer-gateway-service")
                //5.0以上版本需要加密密码
                .secret(password)
                .scopes("WRIGTH", "read")
                .autoApprove(true)
                .authorities("WRIGTH_READ", "WRIGTH_WRITE")
                .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code");
    }


    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     * 还有token的存储方式(tokenStore)
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(jwtTokenStore())
                .tokenEnhancer(jwtTokenConverter())
                .authenticationManager(authenticationManager);
    }

    /**
     * 用jwt管理token
     *
     * @return
     */
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenConverter());

//        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("wangye10");
        return converter;
    }
}
