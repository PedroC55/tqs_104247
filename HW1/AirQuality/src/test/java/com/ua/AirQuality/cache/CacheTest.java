package com.ua.AirQuality.cache;

import org.awaitility.Awaitility;
import org.awaitility.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ua.AirQuality.cache.Cache;
import com.ua.AirQuality.model.Data;

import static org.assertj.core.api.Assertions.assertThat;


public class CacheTest {
    private Cache ca;
    private Data request;

    @BeforeEach
    void setUp() {
        request = new Data();
        this.ca = new Cache(1);
    }

    @Test
    public void whenNoCachedRequest_thenCacheRequest_andIncNumMisses() {
        assertThat(this.ca.getCachedRequest("?")).isNull();
        assertThat(this.ca.getNumRequests()).isEqualTo(1);
        assertThat(this.ca.getNumMisses()).isEqualTo(1);
        assertThat(this.ca.getNumHits()).isEqualTo(0);
    }

    @Test
    public void whenExpiredCachedRequest_thenIncNumMisses() throws InterruptedException {
        String key = "key";
        this.ca.cacheRequest(key, request);
        Thread.sleep(2000);
//        Awaitility.setDefaultTimeout(Duration.FIVE_SECONDS);

        assertThat(this.ca.getCachedRequest(key)).isNull();
        assertThat(this.ca.getNumRequests()).isEqualTo(1);
        assertThat(this.ca.getNumMisses()).isEqualTo(1);
        assertThat(this.ca.getNumHits()).isEqualTo(0);
    }

    @Test
    public void whenCachedRequest_thenReturnRequest_andIncNumHits() {
        String key = "key";
        this.ca.cacheRequest(key, request);

        assertThat(this.ca.getCachedRequest(key)).isEqualTo(request);
        assertThat(this.ca.getNumRequests()).isEqualTo(1);
        assertThat(this.ca.getNumMisses()).isEqualTo(0);
        assertThat(this.ca.getNumHits()).isEqualTo(1);
    }
}