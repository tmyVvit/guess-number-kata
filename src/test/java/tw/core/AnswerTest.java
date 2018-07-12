package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;
import tw.core.model.Record;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void should_throw_the_exception_when_call_validate_given_input_numbers_bigger_than_10(){
        Answer answer = Answer.createAnswer("1 3 11 5");
        try {
            answer.validate();
            fail("should throw the exception");
        } catch (AnswerFormatIncorrectException answerFormatIncorrectException){
        }
    }

    @Test
    public void should_return_1A0B_when_call_check_given_input_1567(){
        Answer inputAnswer = Answer.createAnswer("1 5 6 7");

        Record result = actualAnswer.check(inputAnswer);

        assertEquals("1A0B", result.getValue());
    }

    @Test
    public void should_get_string_when_call_toString(){
        String str = actualAnswer.toString();

        assertEquals("1 2 3 4", str);
    }
}