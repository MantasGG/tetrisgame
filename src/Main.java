import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ScreenRenderer screenRenderer = new ScreenRenderer();
        GameRules gameRules = new GameRules();

        List<Block> blocks = new ArrayList();
        blocks.add(new Block());
        int currentBlock = 0;
        Block block = blocks.get(currentBlock);

        boolean gameOver = false;

        while (!gameOver) {

            screenRenderer.renderScreen(block);

            if (gameRules.isColliding(block)) {

                block.savePos();

                gameRules.clearFullLines();

                blocks.add(new Block());
                currentBlock++;
                block = blocks.get(currentBlock);

                gameOver = gameRules.isGameOver(block);

            }

            gameRules.getKeyDown(block);
        }
    }
}


