package com.nhndooray.samplespringbatch.stats;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

import java.util.Objects;

public class WeeklyStatJobValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {

        // TODO - 02
        //    parameter 에 beginDate 과 endDate 값이 없으면 예외를 발생합니다.
        //    필수 파라미터인 beginDate 과 endDate 값 유무를 검증합니다.
    }
}
