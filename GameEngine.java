import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.Font;

/**
 * @author Peter Xu
 * This is the game engine.
 * UmeshuliciousMain runs an instance of this in its
 * main method to run the game.
 */
public class GameEngine {
    /* 0 - w, 1 - a, 2 - s, 3 - d */
    private int key;
    private String[] arrows;
    private String[] words;
    private Random rando;
    private boolean outerLoop;
    private boolean umeshWin;
    private Umeshulicious game;
    private boolean gameOver;
    private boolean moved;
    private boolean started;

    /**
     * GameEngine constructor.
     */
    public GameEngine() {
        game = new Umeshulicious();
        rando = new Random();
        outerLoop = false;
        umeshWin = true;
        arrows = new String[4];
        arrows[0] = "images/up.png";
        arrows[1] = "images/left.png";
        arrows[2] = "images/down.png";
        arrows[3] = "images/right.png";
        words = new String[5];
        words[0] = "START";
        words[1] = "STABLE MARRIAGE";
        words[2] = "RSA";
        words[3] = "PROBABILITY";
        gameOver = false;
        moved = false;
        started = false;
    }

    /**
     * Randomly chooses the next key to be pressed.
     * Assigns instance variable key to that int value.
     */
    public void keyChooser() {
        key = rando.nextInt(4);
    }

    /**
     * Returns the unicode for the corresponding key.
     * @param i The integer that corresponds to a key.
     * @param The key's unicode.
     */
    public int getKey(int i) {
        if (i == 0) {
            return KeyEvent.VK_W;
        } else if (i == 1) {
            return KeyEvent.VK_A;
        } else if (i == 2) {
            return KeyEvent.VK_S;
        } else {
            return KeyEvent.VK_D;
        }
    }

    /**
     * Draws the title/main message at the top of the screen.
     */
    private void drawTitle() {
        String title = "DON'T LET UMESH INTO HIS ZONE!";
        if (gameOver) {
            title = "YOU LET UMESH INTO HIS ZONE!";
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        StdDraw.text(2.5, 2.6, title);
    }

    /**
     * Draws the current status in the middle of the screen,
     * below the game field.
     */
    private void drawCurrentStatus() {
        String status = "NICE";
        if (gameOver) {
            status = "YOU ARE NOW WATCHING THE THRONE.";
        } else if (moved) {
            status = "Umesh is closer to his zone!";
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        StdDraw.text(2.5, 1, status);
    }

    /**
     * Main draw method, responsible for drawing the game.
     */
    public void drawGame() {
        drawTitle();
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        for (double i = 0.0; i < 4.0; i++) {
            StdDraw.square(i + 0.5, 1.8, 0.45);
            if (i <= 3.0) {
                StdDraw.text(i + 0.5, 2.1, words[(int) i]);
            }
        }
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.square(4.5, 1.8, 0.45);
        StdDraw.text(4.5, 2.1, "UMESH'S ZONE");
        StdDraw.picture(game.getCurrentLocationUmesh() + 0.5, 1.8, "images/umesh.png", 0.85, 0.85);
        if (started) {
            StdDraw.picture(2.5, 0.5, arrows[key], 0.7, 0.7);
        }
        drawCurrentStatus();
        if (game.getCurrentLocationUmesh() == 4) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledSquare(4.5, 1.8, 0.45);
            StdDraw.picture(game.getCurrentLocationUmesh() + 0.5, 1.8, "images/crownumesh.png", 0.9, 0.9);
        }
    }

    public void gameStart() {
        started = true;
    }
    /**
     * Returns if game has ended or not.
     * @return Whether game is over.
     */
    public boolean isOver() {
        return game.isOver();
    }

    /**
     * Sets the value of moved instance variable.
     * @param move New boolean value for moved.
     */
    public void setMoved(boolean move) {
        moved = move;
    }

    /**
     * Returns the current key to be pressed.
     * @return Key corresponding integer (from 0 to 3).
     */
    public int getKey() {
        return key;
    }

    /**
     * Returns outerLoop instance variable.
     * Responsible for breaking a loop in the game.
     * @return The outerLoop instance variable.
     */
    public boolean getOuterLoop() {
        return outerLoop;
    }

    /**
     * Sets the outerLoop instance variable to
     * new value.
     * @param loop New value.
     */
    public void setOuterLoop(boolean loop) {
        outerLoop = loop;
    }

    /**
     * Returns umeshWin instance variable.
     * Variable is true if player failed to press
     * correct key. Umesh wins that round.
     * @return If Umesh won or not.
     */
    public boolean getUmeshWin() {
        return umeshWin;
    }

    /**
     * Sets the value of umeshWin instance
     * variable.
     * @param win New value.
     */
    public void setUmeshWin(boolean win) {
        umeshWin = win;
    }

    /**
     * Moves Umesh forward if
     * player loses the round.
     */
    public void moveForward() {
        game.moveUmeshForward();
    }

    /**
     * Sets the status of gameOver
     * instance variable to true.
     * No point setting it to false as 
     * game will have ended already.
     */
    public void setGameOver() {
        gameOver = true;
    }
}
