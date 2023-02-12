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
// TODO - 01
//    - Tasklet 인터페이스를 사용하는 방법은 Tasklet 구현.
public class WordPrinterTasklet implements Tasklet {

    private int offset = 0;
    private List<String> lines = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n");

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String letter = this.readLetter();
        // TODO-02 - 탈출조건 확인 해보자.
        //    - readLetter() 가 null 을 리턴하면 종료 아니면 계속 실행이다.
        //    - 다음 return 구문에 적절한 값을 넣어보자.
        if (letter == null)
            return RepeatStatus.FINISHED;

        String upperCaseLetter = letter.toUpperCase();
        System.out.println(upperCaseLetter);

        // TODO-03 - 탈출조건 확인 해보자.
        //    - readLetter() 가 null 을 리턴하면 종료 아니면 계속 실행이다.
        //    - 다음 return 구문에 적절한 값을 넣어보자.
        return RepeatStatus.CONTINUABLE;
    }

    private String readLetter() {
        // TODO-04 - 탈출조건을 고려해서 lines 리스트의 한 글자씩 리턴한다. TODO -02 와 함께 어떤 값을 응답해야할지 고민해보자.
        if (offset >= lines.size())
            return null;

        return lines.get(offset++);
    }

}
