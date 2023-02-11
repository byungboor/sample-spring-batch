package com.nhndooray.samplespringbatch.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.*;

class AccessLogTest {

    @Test
    public void testConstructor(){
        String line = "10.244.1.1 - http-nio-10830-exec-5 [08/Feb/2023:22:09:37 +0900] \"GET /items/111\" 200 60";
        AccessLog accessLog = new AccessLog(line);

        Assertions.assertEquals("/items/111", accessLog.getUri());
        Assertions.assertEquals("[08/Feb/2023:22:09:37 +0900]", accessLog.getDate());
        Assertions.assertEquals(60, accessLog.getSize());
        Assertions.assertEquals(HttpMethod.GET, accessLog.getHttpMethod());
        Assertions.assertEquals(HttpStatusCode.valueOf(200), accessLog.getHttpStatus());
    }

}