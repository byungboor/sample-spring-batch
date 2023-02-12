package com.nhndooray.samplespringbatch.stats;

import org.springframework.batch.item.ItemProcessor;

public class CtrProcessor implements ItemProcessor<WeeklyStat, WeeklyStat> {
    @Override
    public WeeklyStat process(WeeklyStat item) throws Exception {
        item.calculateCtr();
        System.out.println("raw : " + item);
        return item;
    }
}
