package com.mins.corona.app.service.impl;

import com.mins.corona.app.dto.SearchAddrDTO;
import com.mins.corona.app.entity.Infectee;
import com.mins.corona.app.repository.InfecteeRepository;
import com.mins.corona.app.service.AbstractNaverCloudService;
import com.mins.corona.app.service.NaverMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NaverMapServiceImpl extends AbstractNaverCloudService implements NaverMapService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InfecteeRepository infecteeRepository;

    @Value("${naver.maps.url}")
    private String naverMapApiUrl;

    @Override
    public SearchAddrDTO.Res getAddrDetailInfoByAddr(SearchAddrDTO.Req param) {
        String url = String.format(naverMapApiUrl, param.getQuery());

        ResponseEntity<SearchAddrDTO.Res> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(super.setHeaderData()), SearchAddrDTO.Res.class);

        return responseEntity.getBody();
    }

    @Override
    @Cacheable(cacheNames = "commonCache", key = "#cacheKey")
    public List<Infectee> getAllInfecteeList(String cacheKey) {
        return infecteeRepository.findAll();
    }
}
