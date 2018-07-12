package tw.core;/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.OutOfGuessCountException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tw.core.GameStatus.CONTINUE;
import static tw.core.GameStatus.FAIL;
import static tw.core.GameStatus.SUCCESS;

public class GameTest {

    private final Answer actualAnswer = Answer.createAnswer("1 2 3 4");
    private Game game;

    @BeforeEach
    public void setUp() throws Exception {
        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        game = new Game(answerGenerator);
    }


    @Test
    public void should_get_the_success_status_when_guess_input_is_correct() throws Exception {

        //given
//        excuteSuccessGuess();
        GuessResult guess = game.guess(Answer.createAnswer("1 2 3 4"));
        //when
        //then
        assertThat(guess.getResult(), is("4A0B"));

    }

    @Test
    public void should_not_get_the_success_status_when_guess_input_is_wrong() throws Exception {

        //given
//        excuteSuccessGuess();
        GuessResult guess = game.guess(Answer.createAnswer("1 2 5 6"));
        //when
        String result = guess.getResult();
        //then
        assertThat(result, not("4A0B"));
    }

    @Test
    public void should_get_the_history_guesses_when_call_getHistory() throws OutOfGuessCountException {
        game.guess(Answer.createAnswer("1 4 3 6"));
        game.guess(Answer.createAnswer("1 5 6 7"));

        List<GuessResult> results = game.guessHistory();

        assertEquals("2A1B", results.get(0).getResult());
        assertEquals("1A0B", results.get(1).getResult());
    }
    
    @Test
    public void should_get_FAIL_when_call_checkStatus_given_6_times_wrong_guess() throws OutOfGuessCountException{
    // given
        game.guess(Answer.createAnswer("1 4 3 5"));
        game.guess(Answer.createAnswer("7 9 6 5"));
        game.guess(Answer.createAnswer("1 4 6 5"));
        game.guess(Answer.createAnswer("7 1 3 5"));
        game.guess(Answer.createAnswer("7 9 3 4"));
        game.guess(Answer.createAnswer("7 9 6 2"));
    // when
        String result = game.checkStatus();
    // then
        assertEquals(FAIL, result);
    }

    @Test
    public void should_get_SUCCESS_when_call_checkStatus_given_correct_guess() throws OutOfGuessCountException {
    // given
        game.guess(Answer.createAnswer("1 2 3 4"));
    // when
        String result = game.checkStatus();
    // then
        assertEquals(SUCCESS, result);
    }

    @Test
    public void should_get_CONTINUE_when_call_checkStatus_fiven_guess_not_correct_and_less_than_6_times() throws OutOfGuessCountException {
    // given
        game.guess(Answer.createAnswer("1 2 3 5"));
    // when
        String result = game.checkStatus();
    // then
        assertEquals(CONTINUE, result);
    }
}
