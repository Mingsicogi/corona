package com.mins.corona.study.annotation.validator;

import com.mins.corona.study.annotation.AccountValid;
import com.mins.corona.study.dto.AccountDTO;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountValidator implements ConstraintValidator<AccountValid, AccountDTO> {

    @Override
    public boolean isValid(AccountDTO accountDTO, ConstraintValidatorContext constraintValidatorContext) {

        if(StringUtils.isAnyBlank(accountDTO.getAddress(), accountDTO.getAdultYn(), accountDTO.getName())) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("address, adultYn, name 필드는 null이 될 수 없습니다.");
            return false;
        }

        if(StringUtils.equals("Y", accountDTO.getAdultYn()) && accountDTO.getAge() < 19) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("성인은 반드시 19세 이상입니다.");
            return false;
        }

        return true;
    }
}
