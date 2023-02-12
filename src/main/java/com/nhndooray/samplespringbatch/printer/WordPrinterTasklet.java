package com.nhndooray.samplespringbatch.printer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WordPrinterTasklet implements Tasklet {

    private int offset = 0;
    private List<String> lines = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n");

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String letter = this.readLetter();
        if (letter == null)
            return null;

        String upperCaseLetter = letter.toUpperCase();
        System.out.println(upperCaseLetter);

        return null;
    }

    private String readLetter() {
        if (offset >= lines.size())
            return null;

        return null;
    }

}
