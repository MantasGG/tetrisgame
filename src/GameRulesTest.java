import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameRulesTest {

    private final Block block = new Block();
    private final GameRules gameRules = new GameRules();

    @Test
    void isGameOver_ShouldReturn_False() {
        int screen[][] = Map.getMap();

        block.setX1(0);

        screen[1][0] = (char) 0x2022;
        screen[1][1] = (char) 0x2022;
        screen[1][2] = (char) 0x2022;
        screen[1][3] = (char) 0x2022;
        screen[1][4] = (char) 0x2022;
        screen[1][5] = (char) 0x2022;
        screen[1][6] = (char) 0x2022;
        screen[1][7] = (char) 0x2022;
        screen[1][8] = (char) 0x2022;
        screen[1][9] = (char) 0x2022;

        Boolean actual = gameRules.isGameOver(block);

        Assertions.assertEquals(false, actual);
    }

    @Test
    void isGameOver_ShouldReturn_True() {

        int screen[][] = Map.getMap();
        block.setX1(0);

        screen[1][0] = '#';
        screen[1][1] = '#';
        screen[1][2] = '#';
        screen[1][3] = '#';
        screen[1][4] = '#';
        screen[1][5] = '#';
        screen[1][6] = '#';
        screen[1][7] = '#';
        screen[1][8] = '#';
        screen[1][9] = '#';

        Boolean actual = gameRules.isGameOver(block);

        Assertions.assertEquals(true, actual);
    }

    @Test
    void isColliding_ShouldReturn_True() {
        block.setX1(19);

        Boolean actual = gameRules.isColliding(block);

        Assertions.assertEquals(true, actual);
    }
}