package com.mins.corona.app.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    @DisplayName("도메인 테스")
    void builder() {
        Event event = Event.builder()
                .name("Mins Drinks")
                .description("Mins Drinks Sale")
                .build();
        Assertions.assertNotNull(event);
    }

    @Test
    void javaBean() {
        Event event = new Event();
        String test = "Test";
        event.setName(test);

        Assertions.assertNotNull(event);
        Assertions.assertEquals(test, event.getName());
    }
}