package com.murphy.ybrain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author Murphy
 * @date 2020-12-09 18:00
 */
@Configuration
public class TokenConfig {

    /**
     * 使用内存存储令牌
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }


}
