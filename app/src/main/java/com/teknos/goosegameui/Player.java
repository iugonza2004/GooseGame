package com.teknos.goosegameui;

public class Player {
    private String nickname;
    private int playerNumber;
    private int position;
    private boolean throwAgain;
    private boolean trapped;
    private int trappedTurns;
    private boolean inMaze;

    public Player(String nickname, int playerNumber) {
        this.nickname = nickname;
        this.playerNumber = playerNumber;
        this.position = 0;
        this.throwAgain = false;
        this.trapped = false;
        this.trappedTurns = 0;
        this.inMaze = false;
    }

    public boolean isInMaze() {
        return inMaze;
    }

    public void setInMaze(boolean inMaze) {
        this.inMaze = inMaze;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public int getPlayerNumber() {
        return playerNumber;
    }
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public boolean throwAgain() {
        return throwAgain;
    }

    public void setThrowAgain(boolean trhowAgain) {
        this.throwAgain = trhowAgain;
    }

    public boolean isTrapped() {
        return trapped;
    }

    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }

    public int getTrappedTurns() {
        return trappedTurns;
    }

    public void setTrappedTurns(int trappedTurns) {
        this.trappedTurns = trappedTurns;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                ", playerNumber=" + playerNumber +
                ", position=" + position +
                ", throwAgain=" + throwAgain +
                ", trapped=" + trapped +
                ", trappedTurns=" + trappedTurns +
                '}';
    }
}
