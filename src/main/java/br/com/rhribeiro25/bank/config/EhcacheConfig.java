package br.com.rhribeiro25.bank.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Renan Ribeiro
 * @date 12/11/2020.
 */

@Configuration
@EnableCaching
public class EhcacheConfig{

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("userCache"),
                new ConcurrentMapCache("userActiveCache"),
                new ConcurrentMapCache("usersCache"),
                new ConcurrentMapCache("transactionsCache")));
        return cacheManager;
    }
}