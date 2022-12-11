import java.util.concurrent.ThreadLocalRandom;

public class Block implements BlockInterface{
    private final int[][] O_COORDINATES = {{0, 0, 0, 1, 1, 0, 1, 1}};
    private final int[][] I_COORDINATES = {{0, 0, 0, 1, 0, 2, 0, 3}, {0, 0, 1, 0, 2, 0, 3, 0}};
    private final int[][] Z_COORDINATES = {{0, 0, 0, 1, 1, 1, 1, 2}, {0, 1, 1, 0, 1, 1, 2, 0}};
    private final int[][] S_COORDINATES = {{0, 1, 0, 2, 1, 0, 1, 1}, {1, 1, 2, 1, 0, 0, 1, 0}};
    private final int[][] J_COORDINATES = {{0, 0, 1, 0, 1, 1, 1, 2}, {0, 1, 0, 0, 1, 0, 2, 0}, {1, 2, 0, 2, 0, 1, 0, 0}, {2, 0, 2, 1, 1, 1, 0, 1}};
    private final int[][] L_COORDINATES = {{0, 2, 1, 0, 1, 1, 1, 2}, {2, 1, 0, 0, 1, 0, 2, 0}, {1, 0, 0, 2, 0, 1, 0, 0}, {0, 0, 2, 1, 1, 1, 0, 1}};
    private final int[][] T_COORDINATES = {{0, 0, 0, 1, 0, 2, 1, 1}, {0, 1, 1, 1, 2, 1, 1, 0}, {1, 2, 1, 1, 1, 0, 0, 1}, {2, 0, 1, 0, 0, 0, 1, 1}};
    private final int[][][] ALL_BLOCKS_COORDINATES = {O_COORDINATES, I_COORDINATES, Z_COORDINATES, S_COORDINATES, J_COORDINATES, L_COORDINATES, T_COORDINATES};

    int blockType = 0;
    final int min = 0;
    final int max = 7;
    int x = 0;
    int y = 0;
    int orientation;
    private final int[][] currentBlock;
    private final int[][] screen;

    public Block() {
        blockType = ThreadLocalRandom.current().nextInt(min, max);
        this.currentBlock = ALL_BLOCKS_COORDINATES[blockType];
        this.orientation = 0;
        screen = Map.getMap();
    }

    public void savePos() {
        screen[getX1()][getY1()] = '#';
        screen[getX2()][getY2()] = '#';
        screen[getX3()][getY3()] = '#';
        screen[getX4()][getY4()] = '#';
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

    public void setX1(Integer value) {
        this.currentBlock[orientation][0] = value;
    }

}
