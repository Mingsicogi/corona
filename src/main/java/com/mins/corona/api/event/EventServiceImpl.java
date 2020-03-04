package com.mins.corona.api.event;

import com.mins.corona.common.exception.BadParameterException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Override
    public boolean parameterValidationCheck(Object param) {

        Event castParam = (Event) param;
        boolean check1 = this.priceValidationCheckAndUpdateFree(castParam);
        boolean check2 = this.locationValidationCheckAndUpdateOffline(castParam);

        return check1 && check2;
    }

    private boolean priceValidationCheckAndUpdateFree(Event event) {
        if (event.getBasePrice() == event.getMaxPrice() && (event.getBasePrice() != 0 || event.getMaxPrice() != 0)) {
            throw new BadParameterException("기본 가격과 최고 가격이 같다면 반드시 둘다 0이어야합니다.(무료)");

        } else if (event.getBasePrice() == 0 && event.getMaxPrice() == 0) {
            log.debug("FREE");
            event.setFree(true);
            return true;
        }

        if (event.getBasePrice() > event.getMaxPrice()) {
            throw new BadParameterException("기본 가격이 최고 가격보다 높습니다.");
        } else if (event.getBasePrice() > event.getMaxPrice() && event.getMaxPrice() == 0) {
            log.debug("무제한 경매");
            event.setFree(false);
        } else {
            log.debug("선착순 등록");
            event.setFree(false);
        }

        return true;
    }

    private boolean locationValidationCheckAndUpdateOffline(Event event) {
        if (StringUtils.isAnyBlank(event.getLocation())) {
            event.setOffline(false);
        } else {
            event.setOffline(true);
        }

        return true;
    }
}
