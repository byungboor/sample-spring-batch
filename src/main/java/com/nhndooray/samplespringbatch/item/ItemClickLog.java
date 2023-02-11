package com.nhndooray.samplespringbatch.item;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ItemClickLog {

    private Long itemId;

    public ItemClickLog(Long itemId) {
        this.itemId = itemId;
    }
}
