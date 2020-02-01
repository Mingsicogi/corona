package com.mins.corona.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class SearchAddrDTO {

    @Data
    public static class Req {
        private String query;
    }

    @Data
    public static class Res {
        private String status;
        private Meta meta;
        private List<Address> addresses;
        private String errorMessage;
    }

    @Data
    public static class Meta {
        private Integer totalCount;
        private Integer page;
        private Integer count;

    }

    @Data
    public static class Address {
        private String roadAddress;
        private String jibunAddress;
        private String englishAddress;
        private List<AddressElement> addressElements;
        private String x;
        private String y;
        private String distance;

    }

    @Data
    public static class AddressElement {
        private List<String> types;
        private String longName;
        private String shortName;
        private String code;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Location {
        private String x;
        private String y;
    }
}
