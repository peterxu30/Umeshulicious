/**
 * @author Peter Xu
 * Controls: W A S D
 * Press the corresponding key to the arrow on the screen.
 * Do not let Umesh get into his zone!
 * The more keys you get right, the faster it gets. 
 * This is the main file. Compile and run this to play!
 *
 * Special thanks to Princeton Standard Library.
 * I used its StdDraw class to draw my game and
 * its StdAudio class to play sound. 
 */
public class UmeshuliciousMain {
	
	/**
	 * Main method that runs the game.
	 */
	public static void main(String[] args) {
		StdDraw.setCanvasSize(800, 500);
		StdDraw.setXscale(0, 5);
        StdDraw.setYscale(0, 3);
		GameEngine umeshEngine = new GameEngine();
		umeshEngine.drawGame();
		StdDraw.show(5);
		long maxTime = 20000000;
		StdAudio.loop("sounds/dontletme.wav");

		while (!umeshEngine.isOver()) {
			umeshEngine.gameStart();
			umeshEngine.keyChooser();
			StdDraw.clear();
			umeshEngine.drawGame();
			StdDraw.show(5);
			int currentKey = umeshEngine.getKey();
			long time = 0;
			int i = 0;
			long keyPressed = 0;
			String timeStr = time + " / " + maxTime;
			for (time = 0; time < maxTime; time++) {
				for (i = 0; i < 4; i++) {
					if (currentKey == i && StdDraw.isKeyPressed(umeshEngine.getKey(i))) {
						StdDraw.clear();
						umeshEngine.setOuterLoop(true);
						umeshEngine.setUmeshWin(false);
						umeshEngine.setMoved(false);
						if (maxTime > 9500000) {
							maxTime = maxTime - 3000000;
						}
						for (keyPressed = 0; keyPressed < 100000000; keyPressed++) {
							if (!StdDraw.isKeyPressed(umeshEngine.getKey(i))) {
								break;
							}
						}
						i = 4;
					} else if (currentKey != i && StdDraw.isKeyPressed(umeshEngine.getKey(i))) {
						umeshEngine.setOuterLoop(true);
						umeshEngine.setUmeshWin(true);
						umeshEngine.setMoved(true);
						for (keyPressed = 0; keyPressed < 100000000; keyPressed++) {
							if (!StdDraw.isKeyPressed(umeshEngine.getKey(i))) {
								break;
							}
						}
						i = 4;
					}
				}
				if (umeshEngine.getOuterLoop()) {
					umeshEngine.setOuterLoop(false);
					break;
				}
			}
			if (umeshEngine.getUmeshWin()) {
				umeshEngine.moveForward();
			} else {
				umeshEngine.setUmeshWin(true);
			}
		}
		StdAudio.close();
		StdAudio.loop("sounds/definitelyin.wav");
		umeshEngine.setGameOver();
		StdDraw.clear(StdDraw.BLUE);
		umeshEngine.drawGame();
		StdDraw.show(5);
	}
}
