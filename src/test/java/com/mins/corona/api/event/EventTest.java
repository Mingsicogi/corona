package com.mins.corona.api.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    @DisplayName("도메인 테스")
    void builder() {
        com.mins.corona.api.event.Event event = com.mins.corona.api.event.Event.builder()
                .name("Mins Drinks")
                .description("Mins Drinks Sale")
                .build();
        Assertions.assertNotNull(event);
    }

    @Test
    void javaBean() {
        com.mins.corona.api.event.Event event = new com.mins.corona.api.event.Event();
        String test = "Test";
        event.setName(test);

        Assertions.assertNotNull(event);
        Assertions.assertEquals(test, event.getName());
    }
}