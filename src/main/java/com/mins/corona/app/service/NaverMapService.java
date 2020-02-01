package com.mins.corona.app.service;

import com.mins.corona.app.dto.SearchAddrDTO;

public interface NaverMapService {

    SearchAddrDTO.Res getAddrDetailInfoByAddr(SearchAddrDTO.Req param);
}
