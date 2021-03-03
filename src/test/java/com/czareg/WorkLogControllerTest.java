package com.czareg;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

public class WorkLogControllerTest {
    @Test
    void name() throws URISyntaxException {
        WorkLogController workLogController = new WorkLogController();
        workLogController.get();
    }
}
