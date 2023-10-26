package com.teknos.goosegameui;

public class Board {
    private final int[] goose = {1, 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59, 63};
    private final int[] bridge = {6, 12};
    private final int death = 58;
    private final int well = 31;
    private final int maze = 42;
    private final int prision = 53;

    public static boolean inMaze(Player player) {
        return player.getPosition() == 42;
    }

    public int[] getGoose() {
        return goose;
    }

    public int[] getBridge() {
        return bridge;
    }

    public int getDeath() {
        return death;
    }

    public int getWell() {
        return well;
    }

    public int getMaze() {
        return maze;
    }

    public int getPrision() {
        return prision;
    }
}
