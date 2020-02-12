package com.mins.corona.study.dto;

import com.mins.corona.study.annotation.AccountValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AccountValid
public class AccountDTO {
    private String name;
    private int age;
    private String address;
    private String adultYn;
}
