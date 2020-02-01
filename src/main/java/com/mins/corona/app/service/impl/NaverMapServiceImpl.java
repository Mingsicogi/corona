package com.mins.corona.app.service.impl;

import com.mins.corona.app.dto.SearchAddrDTO;
import com.mins.corona.app.service.AbstractNaverCloudService;
import com.mins.corona.app.service.NaverMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NaverMapServiceImpl extends AbstractNaverCloudService implements NaverMapService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${naver.maps.url}")
    private String naverMapApiUrl;

    @Override
    public SearchAddrDTO.Res getAddrDetailInfoByAddr(SearchAddrDTO.Req param) {
        String url = String.format(naverMapApiUrl, param.getQuery());

        ResponseEntity<SearchAddrDTO.Res> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(super.setHeaderData()), SearchAddrDTO.Res.class);

        return responseEntity.getBody();
    }
}
