import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int[][] screen = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Hello world");
        List<Block> blocks = new ArrayList();
        blocks.add(new Block());
        int currentBlock = 0;
        Block block = blocks.get(currentBlock);


        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!gameOver) {
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

            input = scanner.nextLine();
            if ((block.getX1() == 19) || (block.getX2() == 19) || (block.getX3() == 19) || (block.getX4() == 19) ||
                    (screen[block.getX1() + 1][block.getY1()] == '#') ||
                    (screen[block.getX2() + 1][block.getY2()] == '#') ||
                    (screen[block.getX3() + 1][block.getY3()] == '#') ||
                    (screen[block.getX4() + 1][block.getY4()] == '#')) {

                block.savePos();

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

                blocks.add(new Block());
                currentBlock++;
                block = blocks.get(currentBlock);

                if (((block.getX1() == 0) && (screen[block.getX1() + 1][block.getY1()] == '#')) ||
                        ((block.getX2() == 0) && (screen[block.getX2() + 1][block.getY2()] == '#')) ||
                        ((block.getX3() == 0) && (screen[block.getX3() + 1][block.getY3()] == '#')) ||
                        ((block.getX4() == 0) && (screen[block.getX4() + 1][block.getY4()] == '#'))) {
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
                    System.out.println("\nGame over!");
                    gameOver = true;
                    break;
                }

            }
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
    }

    // create list of positions
    public static class Block {
        private final int[][] O_COORDINATES = {{0, 0, 0, 1, 1, 0, 1, 1}};
        private final int[][] I_COORDINATES = {{0, 0, 0, 1, 0, 2, 0, 3}, {0, 0, 1, 0, 2, 0, 3, 0}};
        private final int[][] Z_COORDINATES = {{0, 0, 0, 1, 1, 1, 1, 2}, {0, 1, 1, 0, 1, 1, 2, 0}};
        private final int[][] S_COORDINATES = {{0, 1, 0, 2, 1, 0, 1, 1}, {1, 1, 2, 1, 0, 0, 1, 0}};
        private final int[][] J_COORDINATES = {{0, 0, 1, 0, 1, 1, 1, 2}, {0, 1, 0, 0, 1, 0, 2, 0}, {1, 2, 0, 2, 0, 1, 0, 0}, {2, 0, 2, 1, 1, 1, 0, 1}};
        private final int[][] L_COORDINATES = {{0, 2, 1, 0, 1, 1, 1, 2}, {2, 1, 0, 0, 1, 0, 2, 0}, {1, 0, 0, 2, 0, 1, 0, 0}, {0, 0, 2, 1, 1, 1, 0, 1}};
        private final int[][] T_COORDINATES = {{0, 0, 0, 1, 0, 2, 1, 1}, {0, 1, 1, 1, 2, 1, 1, 0}, {1, 2, 1, 1, 1, 0, 0, 1}, {2, 0, 1, 0, 0, 0, 1, 1}};
        private final int[][][] ALL_BLOCKS_COORDINATES = {O_COORDINATES, I_COORDINATES, Z_COORDINATES, S_COORDINATES, J_COORDINATES, L_COORDINATES, T_COORDINATES};

        int[][] posOld = new int[4][2];
        int state = 0;
        int blockType = 0;
        final int min = 0;
        final int max = 7;
        int x = 0;
        int y = 0;
        int[][] pos;
        int orientation;
        private int[][] currentBlock;

        public Block() {
            blockType = ThreadLocalRandom.current().nextInt(min, max);
            //this.blockType = 1;
            this.currentBlock = ALL_BLOCKS_COORDINATES[blockType];
            this.orientation = 0;
        }

        public void savePos() {
            screen[getX1()][getY1()] = '#';
            screen[getX2()][getY2()] = '#';
            screen[getX3()][getY3()] = '#';
            screen[getX4()][getY4()] = '#';
        }

        public void reset() {
            pos[0][0] = 0 - 1;
            pos[0][1] = 3;

            pos[1][0] = 0 - 1;
            pos[1][1] = 4;

            pos[2][0] = 0 - 1;
            pos[2][1] = 5;

            pos[3][0] = 1 - 1;
            pos[3][1] = 4;
        }

        public void moveDown() {


            x++;

            this.currentBlock[orientation][0] += 1;

            this.currentBlock[orientation][2] += 1;

            this.currentBlock[orientation][4] += 1;

            this.currentBlock[orientation][6] += 1;
        }

        public void moveRight() {

            y++;

            this.currentBlock[orientation][1] += 1;

            this.currentBlock[orientation][3] += 1;

            this.currentBlock[orientation][5] += 1;

            this.currentBlock[orientation][7] += 1;
        }

        public void moveLeft() {

            y--;

            this.currentBlock[orientation][1] -= 1;

            this.currentBlock[orientation][3] -= 1;

            this.currentBlock[orientation][5] -= 1;

            this.currentBlock[orientation][7] -= 1;
        }

        public void rotate() {
            if (orientation < currentBlock.length - 1) {
                orientation++;

                this.currentBlock[orientation][1] += y;

                this.currentBlock[orientation][3] += y;

                this.currentBlock[orientation][5] += y;

                this.currentBlock[orientation][7] += y;

                this.currentBlock[orientation][0] += x;

                this.currentBlock[orientation][2] += x;

                this.currentBlock[orientation][4] += x;

                this.currentBlock[orientation][6] += x;

                y = 0;
                x = 0;
            } else {
                orientation = 0;

                this.currentBlock[orientation][1] += y;

                this.currentBlock[orientation][3] += y;

                this.currentBlock[orientation][5] += y;

                this.currentBlock[orientation][7] += y;

                this.currentBlock[orientation][0] += x;

                this.currentBlock[orientation][2] += x;

                this.currentBlock[orientation][4] += x;

                this.currentBlock[orientation][6] += x;

                y = 0;
                x = 0;

            }
        }

        public void unrotate() {
            orientation--;
            if (orientation < 0) {
                orientation = currentBlock.length - 1;
            }
        }

        public int getX1() {
            return this.currentBlock[orientation][0];
        }

        public int getX2() {
            return this.currentBlock[orientation][2];
        }

        public int getX3() {
            return this.currentBlock[orientation][4];
        }

        public int getX4() {
            return this.currentBlock[orientation][6];
        }

        public int getY1() {
            return this.currentBlock[orientation][1];
        }

        public int getY2() {
            return this.currentBlock[orientation][3];
        }

        public int getY3() {
            return this.currentBlock[orientation][5];
        }

        public int getY4() {
            return this.currentBlock[orientation][7];
        }
    }
}


