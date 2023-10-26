package com.teknos.goosegameui;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Party implements Game{
    private final ArrayList<Player> players;
    private boolean isFinished;
    private final Random random;
    private final Board board;

    public Party() {
        this.players = new ArrayList<>();
        this.random = new Random();
        this.isFinished = false;
        this.board = new Board();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public boolean isFinished() {
        return isFinished;
    }
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    private int rollDice(ImageButton dice) {
        int randomNumber = random.nextInt(6) + 1;
        switch (randomNumber) {
            case 1:
                dice.setImageResource(R.drawable.dice_six_faces_one_39564);
                break;
            case 2:
                dice.setImageResource(R.drawable.dice_six_faces_two_38562);
                break;
            case 3:
                dice.setImageResource(R.drawable.dice_six_faces_three_39562);
                break;
            case 4:
                dice.setImageResource(R.drawable.dice_six_faces_four_38563);
                break;
            case 5:
                dice.setImageResource(R.drawable.dice_six_faces_five_39565);
                break;
            case 6:
                dice.setImageResource(R.drawable.dice_six_faces_six_39563);
        }

        return randomNumber;
    }

    public String nextRound(Player player, String wayToRefer, ImageButton dice) {
        String message = "";
        int diceResult = rollDice(dice);
        if ((player.getPosition() + diceResult) > 63) {
            player.setPosition(63 - ((player.getPosition() + diceResult) - 63));
        } else {
            player.setPosition(player.getPosition() + diceResult);
        }
        message += "Player " + wayToRefer + " scored a " + diceResult + ", he's now in the square " + player.getPosition();

        if (player.getPosition() == board.getDeath()) {
            player.setPosition(0);
            message += "\nOh no, you have fallen in the death, you are now in the square 0";
        }
        if (player.getPosition() == board.getWell()) {
            player.setTrapped(true);
            player.setTrappedTurns(2);
            message += "\nOh no, you have fallen in the well, 2 turns trapped";
        }
        if (player.getPosition() == board.getMaze()) {
            player.setInMaze(true);
            player.setPosition(30);
            message += "\nOh no, you have fallen in the maze, returned to square 30";
        }

        int gooseIndex = 0;
        while (player.getPosition() != board.getGoose()[gooseIndex] && gooseIndex < 14) {
            gooseIndex++;
        }
        if (gooseIndex < 14) {
            player.setPosition(board.getGoose()[gooseIndex + 1]);
            player.setThrowAgain(true);
            message += "\nYes! you have fallen in a goose and you advance to the next goose, you are now in the square " + board.getGoose()[gooseIndex + 1] + " and you can throw the dice again";
        }

        if (player.getPosition() == board.getBridge()[0]) {
            player.setPosition(board.getBridge()[1]);
            player.setThrowAgain(true);
            message += "\nYes! you have fallen in the first bride and you advance to the next one, you are now in the square 12 and you can throw again";
        } else if (player.getPosition() == board.getBridge()[1]) {
            player.setPosition(board.getBridge()[0]);
            player.setThrowAgain(true);
            message += "\nOh no, you have fallen in the second bridge and you return to the first one, you are now in the square 6 and you can throw again";
        }

        if (player.getPosition() == board.getPrision()) {
            player.setTrapped(true);
            player.setTrappedTurns(3);
            message += "\nOh no, you have fallen in the prison, 3 turns trapped";
        }

        if (player.getPosition() == 63) {
            this.isFinished = true;
        }
        return message;
    }

    @Override
    public Player winner() {
        for (Player player : players){
            if (player.getPosition() == 63) {
                return player;
            }
        }
        return null;
    }
}
