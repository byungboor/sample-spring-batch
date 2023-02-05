package com.nhndooray.samplespringbatch.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class MemberProcessor implements ItemProcessor<Member, Member> {
    @Override
    public Member process(Member member) throws Exception {
        log.warn("Processing Member : {}", member);
        return member;
    }
}
