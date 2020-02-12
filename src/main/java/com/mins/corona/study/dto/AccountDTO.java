package com.mins.corona.study.dto;

import com.mins.corona.study.annotation.AccountValid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 회원 정보 DTO 정의
 *
 * @author minssogi
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AccountValid
public class AccountDTO {

    @NotBlank
    private String name;

    private int age;

    private String address;

    private String adultYn;
}
