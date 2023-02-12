package com.nhndooray.samplespringbatch.stats;

import org.springframework.batch.item.ItemProcessor;

// TODO - 03
//   - 입력받은 WeeklyStat 을 가공하여 다시 WeeklyStat 으로 응답한다.
public class CtrProcessor implements ItemProcessor<WeeklyStat, WeeklyStat> {
    @Override
    public WeeklyStat process(WeeklyStat item) throws Exception {
        item.calculateCtr();
        System.out.println("raw : " + item);
        return item;
    }
}
