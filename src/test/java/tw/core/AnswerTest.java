package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by jxzhong on 2017/9/23.
 */
public class AnswerTest {
    private Answer actualAnswer;

    @BeforeEach
    public void setUp() {
        actualAnswer = Answer.createAnswer("1 2 3 4");
    }

    @Test
    public void should_not_throw_the_exception_when_call_validate(){
        try {
            actualAnswer.validate();
        } catch (AnswerFormatIncorrectException answerFormatIncorrectException){
            fail("should not throw the exception");
        }
    }

    @Test
    public void should_throw_the_exception_when_call_validate_given_repeated_numbers(){
        Answer answer = Answer.createAnswer("1 3 3 5");
        try {
            answer.validate();
            fail("should throw the exception");
        } catch (AnswerFormatIncorrectException answerFormatIncorrectException){
        }
    }
}