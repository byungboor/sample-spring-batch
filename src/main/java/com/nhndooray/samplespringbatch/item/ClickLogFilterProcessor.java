package com.nhndooray.samplespringbatch.item;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.http.HttpMethod;

public class ClickLogFilterProcessor implements ItemProcessor<AccessLog, ItemClickLog> {

    @Override
    public ItemClickLog process(AccessLog accessLog) throws Exception {


        if (HttpMethod.GET == accessLog.getHttpMethod()
            && accessLog.getUri().startsWith("/items/")
            && accessLog.getUri().endsWith(".png")) {

            String[] tokens = accessLog.getUri().trim().split("\\/");
            if (tokens.length != 3)
                return null;

            return new ItemClickLog(Long.valueOf(tokens[2].substring(0, tokens[2].indexOf("."))));
        }
        return null;
    }
}
