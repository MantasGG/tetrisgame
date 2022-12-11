import java.util.Scanner;

public class GameRules {
    private final int[][] screen;
    Scanner scanner = new Scanner(System.in);
    String input;

    public GameRules() {
        screen = Map.getMap();
    }

    public void clearFullLines(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (screen[i][j] == 0) {
                    break;
                }

                if (j == 9) {
                    for (int x = 0; x < 10; x++) {
                        screen[i][x] = 0;
                    }
                    for (int z = i; z > 0; z--) {
                        for (int x = 0; x < 10; x++) {
                            screen[z][x] = screen[z-1][x];
                            screen[z-1][x] = 0;
                        }
                    }
                }
            }
        }
    }

    public Boolean isGameOver(Block block){
        if (((block.getX1() == 0) && (screen[block.getX1() + 1][block.getY1()] == '#')) ||
                ((block.getX2() == 0) && (screen[block.getX2() + 1][block.getY2()] == '#')) ||
                ((block.getX3() == 0) && (screen[block.getX3() + 1][block.getY3()] == '#')) ||
                ((block.getX4() == 0) && (screen[block.getX4() + 1][block.getY4()] == '#'))) {
            System.out.println("\nGame over!");
            return true;
        }
        return false;
    }

    public void getKeyDown(Block block){
        input = getInput();
        switch (input) {
            case ("w"):
                block.rotate();
                if ((block.getY1() < 0) || (block.getY2() < 0) || (block.getY3() < 0) || (block.getY4() < 0) ||
                        (block.getY1() > 9) || (block.getY2() > 9) || (block.getY3() > 9) || (block.getY4() > 9) ||
                        (block.getX1() > 19) || (block.getX2() > 19) || (block.getX3() > 19) || (block.getX4() > 19)) {
                    block.unrotate();
                }
                break;
            case ("a"):
                if ((block.getY1() != 0) && (block.getY2() != 0) && (block.getY3() != 0) && (block.getY4() != 0)) {
                    block.moveLeft();
                }
                break;
            case ("d"):
                if ((block.getY1() != 9) && (block.getY2() != 9) && (block.getY3() != 9) && (block.getY4() != 9)) {
                    block.moveRight();
                }
                break;
        }

        block.moveDown();
    }

    public String getInput(){
        return scanner.nextLine();
    }

    public boolean isColliding(Block block){
        return (block.getX1() == 19) || (block.getX2() == 19) || (block.getX3() == 19) || (block.getX4() == 19) ||
                (screen[block.getX1() + 1][block.getY1()] == '#') ||
                (screen[block.getX2() + 1][block.getY2()] == '#') ||
                (screen[block.getX3() + 1][block.getY3()] == '#') ||
                (screen[block.getX4() + 1][block.getY4()] == '#');
    }
}
