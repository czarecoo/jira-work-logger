package com.czareg.controller;

import com.czareg.request.Request;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@Disabled
public class WorkLogControllerTest {
    private static final String URL = "https://czarecoo.atlassian.net/";
    private static final String EMAIL = "czarecoo@o2.pl";
    private static final String TOKEN = "Wo9lwEbIlX3eHEr0zsR82839";
    private static final String ISSUE_KEY = "WL-2";

    @Test
    void testLogEightHoursEveryWorkdayThisWeek() throws IOException {
        Request request = new Request(URL, EMAIL, TOKEN, ISSUE_KEY);
        WorkLogController workLogController = new WorkLogController();
        workLogController.logEightHoursEveryWorkdayThisWeek(request);
    }
}