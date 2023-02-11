package com.nhndooray.samplespringbatch.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClickLogFilterProcessorTest {

    @Test
    public void test() throws Exception {

        String line = "10.244.1.1 - http-nio-10830-exec-5 [08/Feb/2023:22:09:37 +0900] \"GET /items/111.png\" 200 60";
        AccessLog accessLog = new AccessLog(line);
        ClickLogFilterProcessor processor = new ClickLogFilterProcessor();
        ItemClickLog itemClickLog = processor.process(accessLog);

        Assertions.assertEquals(111L, itemClickLog.getItemId());
    }

}