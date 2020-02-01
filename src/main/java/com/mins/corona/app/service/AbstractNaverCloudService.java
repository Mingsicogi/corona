package com.mins.corona.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

public abstract class AbstractNaverCloudService {

    @Value("${naver.maps.client.id}")
    private String clientId;

    @Value("${naver.maps.client.secret}")
    private String clientSecret;

    public HttpHeaders setHeaderData() {
        HttpHeaders headers = new HttpHeaders();

        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);

        return headers;
    }
}
