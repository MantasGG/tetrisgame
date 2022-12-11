import java.util.concurrent.TimeUnit;

public class ScreenRenderer extends Block{

    private int[][] screen;

    public ScreenRenderer() {
        super();
        screen = Map.getMap();
    }

    public void renderScreen(Block block) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(200);

        System.out.println("  Tetris");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (
                        (i == block.getX1() && j == block.getY1()) ||
                                (i == block.getX2() && j == block.getY2()) ||
                                (i == block.getX3() && j == block.getY3()) ||
                                (i == block.getX4() && j == block.getY4())
                ) {
                    System.out.print("#");
                } else if (screen[i][j] == 0)
                    System.out.print((char) 0x2022);
                else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
            System.out.println();
    }
}
