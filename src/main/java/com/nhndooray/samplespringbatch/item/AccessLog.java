package com.nhndooray.samplespringbatch.item;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;

@ToString
@Getter
public class AccessLog {

    private String clientIpAddress;
    private String threadName;
    private String date;
    private HttpMethod httpMethod;
    private String uri;
    private HttpStatusCode httpStatus;
    private Integer size;

    public AccessLog(String line) {
        String[] tokens = line.split("\\s+");
        this.clientIpAddress = tokens[0];
        this.threadName = tokens[2];
        this.date = tokens[3] + " " + tokens[4];
        this.httpMethod = HttpMethod.valueOf(tokens[5].substring(1));
        this.uri =  tokens[6].substring(0, tokens[6].length() - 1);
        this.httpStatus = HttpStatusCode.valueOf(Integer.valueOf(tokens[7]));
        this.size = Integer.valueOf(tokens[8]);
    }

    public static void main(String[] args){
        String line = "10.244.1.1 - http-nio-10830-exec-5 [08/Feb/2023:22:09:37 +0900] \"GET /items/111\" 200 60";
        AccessLog accessLog = new AccessLog(line);
        System.out.println(accessLog);
    }

}
