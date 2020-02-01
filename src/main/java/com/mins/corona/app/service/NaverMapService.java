package com.mins.corona.app.service;

import com.mins.corona.app.dto.SearchAddrDTO;
import com.mins.corona.app.entity.Infectee;

import java.util.List;

public interface NaverMapService {

    SearchAddrDTO.Res getAddrDetailInfoByAddr(SearchAddrDTO.Req param);

    List<Infectee> getAllInfecteeList(String cacheKey);
}
