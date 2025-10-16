package wordle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

public class wordleTest {
    @Test
    void testHints1() {
        Wordle game = new Wordle();
        String target = "abbey";
        String guess = "keeps";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[100m_\u001B[0m, \u001B[43mo\u001B[0m, \u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m]", Arrays.toString(hints));
    }

    @Test
    void testHints2() {
        Wordle game = new Wordle();
        String target = "abbey";
        String guess = "kebab";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[100m_\u001B[0m, \u001B[43mo\u001B[0m, \u001B[42m+\u001B[0m, \u001B[43mo\u001B[0m, \u001B[43mo\u001B[0m]", Arrays.toString(hints));
    }

    @Test
    void testHints3() {
        Wordle game = new Wordle();
        String target = "abbey";
        String guess = "babes";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[43mo\u001B[0m, \u001B[43mo\u001B[0m, \u001B[42m+\u001B[0m, \u001B[42m+\u001B[0m, \u001B[100m_\u001B[0m]", Arrays.toString(hints));
    }

    @Test
    void testHints4() {
        Wordle game = new Wordle();
        String target = "lobby";
        String guess = "table";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[42m+\u001B[0m, \u001B[43mo\u001B[0m, \u001B[100m_\u001B[0m]", Arrays.toString(hints));
    }

    @Test
    void testHints5() {
        Wordle game = new Wordle();
        String target = "ghost";
        String guess = "pious";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[42m+\u001B[0m, \u001B[100m_\u001B[0m, \u001B[43mo\u001B[0m]", Arrays.toString(hints));
    }

    @Test
    void testHints6() {
        Wordle game = new Wordle();
        String target = "ghost";
        String guess = "slosh";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[42m+\u001B[0m, \u001B[42m+\u001B[0m, \u001B[43mo\u001B[0m]", Arrays.toString(hints));
    }

    @Test
    void testHints7() {
        Wordle game = new Wordle();
        String target = "kayak";
        String guess = "aorta";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[43mo\u001B[0m, \u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[43mo\u001B[0m]", Arrays.toString(hints));
    }

    @Test
    void testHints8() {
        Wordle game = new Wordle();
        String target = "kayak";
        String guess = "kayak";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[42m+\u001B[0m, \u001B[42m+\u001B[0m, \u001B[42m+\u001B[0m, \u001B[42m+\u001B[0m, \u001B[42m+\u001B[0m]", Arrays.toString(hints));
    }

    @Test
    void testHints9() {
        Wordle game = new Wordle();
        String target = "kayak";
        String guess = "fungi";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m]", Arrays.toString(hints));
    } 

    @Test
    void testHints10() {
        Wordle game = new Wordle();
        String target = "ledge";
        String guess = "hedge";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[100m_\u001B[0m, \u001B[42m+\u001B[0m, \u001B[42m+\u001B[0m, \u001B[42m+\u001B[0m, \u001B[42m+\u001B[0m]", Arrays.toString(hints));
    }     

    @Test
    void testHints11() {
        Wordle game = new Wordle();
        String target = "acrid";
        String guess = "rural";
        String[] hints = game.getHints(target, guess);
        assertEquals("[\u001B[100m_\u001B[0m, \u001B[100m_\u001B[0m, \u001B[42m+\u001B[0m, \u001B[43mo\u001B[0m, \u001B[100m_\u001B[0m]", Arrays.toString(hints));
    } 
}
