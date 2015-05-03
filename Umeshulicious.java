/**
 * @author Peter Xu
 * The playing field object. 
 */

public class Umeshulicious {
    private Space[] playField;
    private int currentLocationUmesh;

    /**
     * Umeshulicious constructor.
     */
    public Umeshulicious() {
        playField = new Space[5];
        playField[0] = new Space(true);
        for (int i = 1; i < 5; i++) {
            playField[i] = new Space(false);
        }
        currentLocationUmesh = 0;
    }

    /**
     * Moves Umesh forward on the play field.
     */
    public void moveUmeshForward() {
        if (currentLocationUmesh == 4) {
            return;
        }
        Space temp = playField[currentLocationUmesh + 1];
        playField[currentLocationUmesh + 1] = playField[currentLocationUmesh];
        playField[currentLocationUmesh] = temp;
        currentLocationUmesh += 1;
    }

    /**
     * Returns the current location of Umesh as
     * an integer from 0 to 4.
     * @return The integer corresponding to Umesh's location.
     */
    public int getCurrentLocationUmesh() {
        return currentLocationUmesh;
    }

    /**
     * Returns if the game is over.
     * Game ends when Umesh reaches the end.
     * @return If game is over.
     */
    public boolean isOver() {
        return (currentLocationUmesh == 4);
    }

    /**
     * Nested class that represents 
     * a tile of the play field.
     */
    public class Space {
        private boolean isUmesh;

        /**
         * Space constructor.
         */
        public Space(boolean umesh) {
            isUmesh = umesh;
        }
    }
}