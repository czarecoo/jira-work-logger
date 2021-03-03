package com.czareg;

import org.junit.jupiter.api.Test;

public class WorkLogControllerTest {
    @Test
    void testGet() {
        WorkLogController workLogController = new WorkLogController();
        workLogController.get();
    }
}