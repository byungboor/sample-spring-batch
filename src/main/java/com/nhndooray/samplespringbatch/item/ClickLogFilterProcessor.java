package com.nhndooray.samplespringbatch.item;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.http.HttpMethod;

public class ClickLogFilterProcessor implements ItemProcessor<AccessLog, ItemClickLog> {


    public static final String pattern = "^/items/*.png$";


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

    public static void main(String[] args) throws Exception {
        String line = "10.244.1.1 - http-nio-10830-exec-5 [08/Feb/2023:22:09:37 +0900] \"GET /items/111.png\" 200 60";
        AccessLog accessLog = new AccessLog(line);
        ClickLogFilterProcessor processor = new ClickLogFilterProcessor();
        ItemClickLog itemClickLog = processor.process(accessLog);
        System.out.println(itemClickLog.getItemId());

    }
}
