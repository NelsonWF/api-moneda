package com.api.nawf.infrastructure.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarHelperTest {

    private CalendarHelper calendarHelper;

    @BeforeEach
    public void antesDe() {
        calendarHelper = new CalendarHelper();
    }

    @Test
    void debeCalcularLaFechaConElTimestamp() {
        Date dateExpect = new Timestamp(1616821200L * 1000);
        Long time = 1616821200L;
        assertEquals(dateExpect, this.calendarHelper.toDate(time));
    }
}
