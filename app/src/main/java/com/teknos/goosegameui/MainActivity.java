package com.teknos.goosegameui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private static Party party;
    private static int indexPlayerTurn;
    private Board board;


    public void createParty(View view) {
        TextView player1View = findViewById(R.id.player1Name);
        TextView player2View = findViewById(R.id.player2Name);
        TextView errorTextView = findViewById(R.id.errorMessage);

        String player1 = player1View.getText().toString();
        String player2 = player2View.getText().toString();

        if (player1.isEmpty() || player2.isEmpty()) {
            String errorText = "All fields must be filled to continue";
            errorTextView.setText(errorText);

        } else {

            board = new Board();
            indexPlayerTurn = 0;

            party = new Party();
            party.addPlayer(new Player(player1, 1));
            party.addPlayer(new Player(player2, 2));

            player1View.setVisibility(View.GONE);
            player2View.setVisibility(View.GONE);
            errorTextView.setVisibility(View.GONE);
            findViewById(R.id.createParty).setVisibility(View.GONE);
            findViewById(R.id.createPartyLayout).setVisibility(View.GONE);
            findViewById(R.id.playersInput).setVisibility(View.GONE);

            findViewById(R.id.gameTextLayout).setVisibility(View.VISIBLE);
            findViewById(R.id.gameText).setVisibility(View.VISIBLE);
            findViewById(R.id.partyLayout).setVisibility(View.VISIBLE);
            findViewById(R.id.dice).setVisibility(View.VISIBLE);
            findViewById(R.id.player1Position).setVisibility(View.VISIBLE);
            findViewById(R.id.player2Position).setVisibility(View.VISIBLE);

            TextView player1indicator = findViewById(R.id.player1indicator);
            player1indicator.setVisibility(View.VISIBLE);
            player1indicator.setText(player1);
            TextView player2indicator = findViewById(R.id.player2indicator);
            player2indicator.setVisibility(View.VISIBLE);
            player2indicator.setText(player2);

            findViewById(R.id.positionImage1).setVisibility(View.VISIBLE);
            findViewById(R.id.positionImage2).setVisibility(View.VISIBLE);

            String text = "PLAYER " + party.getPlayers().get(indexPlayerTurn).getPlayerNumber() + " TURN\nYou are in the square " + party.getPlayers().get(indexPlayerTurn).getPosition() + ".\nTHROW THE DICE";
            TextView gameText = findViewById(R.id.gameText);
            gameText.setText(text);
            player1indicator = findViewById(R.id.player1indicator);
            player1indicator.setTextColor(Color.parseColor("#00FF1A"));
        }
    }

    public void playerThrow(View view) {

        TextView gameText = findViewById(R.id.gameText);
        String text = "";

        Player playerTurn = party.getPlayers().get(indexPlayerTurn);
        if (!playerTurn.isTrapped()) {
            playerTurn.setThrowAgain(false);
            playerTurn.setInMaze(false);
            String wayToRefer = String.valueOf(playerTurn.getPlayerNumber());


            ImageButton dice = findViewById(R.id.dice);
            text = party.nextRound(playerTurn, wayToRefer, dice);
            gameText.setText(text);

            if (playerTurn.getPlayerNumber() == 1) {
                ImageView imageView = findViewById(R.id.positionImage1);
                if (playerTurn.getPosition() == 0) {
                    imageView.setImageResource(R.drawable.death);
                } else if (playerTurn.getPosition() == board.getPrision()) {
                    imageView.setImageResource(R.drawable.prison);
                } else if (playerTurn.getPosition() == board.getWell()) {
                    imageView.setImageResource(R.drawable.well);
                } else if (playerTurn.isInMaze()) {
                    imageView.setImageResource(R.drawable.maze);
                } else if (positionIsGoose(playerTurn.getPosition())) {
                    imageView.setImageResource(R.drawable.goose);
                } else if (positionIsBridge(playerTurn.getPosition())) {
                    imageView.setImageResource(R.drawable.bridge);
                } else {
                    imageView.setImageResource(R.drawable.pngtree_footprint_foot_icon_vector_illustration_png_image_4661336);
                }
            } else {
                ImageView imageView = findViewById(R.id.positionImage2);
                if (playerTurn.getPosition() == 0) {
                    imageView.setImageResource(R.drawable.death);
                } else if (playerTurn.getPosition() == board.getPrision()) {
                    imageView.setImageResource(R.drawable.prison);
                } else if (playerTurn.getPosition() == board.getWell()) {
                    imageView.setImageResource(R.drawable.well);
                } else if (playerTurn.isInMaze()) {
                    imageView.setImageResource(R.drawable.maze);
                } else if (positionIsGoose(playerTurn.getPosition())) {
                    imageView.setImageResource(R.drawable.goose);
                } else if (positionIsBridge(playerTurn.getPosition())) {
                    imageView.setImageResource(R.drawable.bridge);
                } else {
                    imageView.setImageResource(R.drawable.pngtree_footprint_foot_icon_vector_illustration_png_image_4661336);
                }
            }

            if (playerTurn.getPlayerNumber() == 1) {
                TextView puntuation = findViewById(R.id.player1Position);
                puntuation.setText(String.valueOf(playerTurn.getPosition()));
            } else {
                TextView puntuation = findViewById(R.id.player2Position);
                puntuation.setText(String.valueOf(playerTurn.getPosition()));
            }

            if (!playerTurn.throwAgain()) {
                indexPlayerTurn++;
                if (indexPlayerTurn == 2) {
                    indexPlayerTurn = 0;
                }
                if (!party.getPlayers().get(indexPlayerTurn).isTrapped()) {
                    text = "\n\nPLAYER " + party.getPlayers().get(indexPlayerTurn).getPlayerNumber() + " TURN\nYou are in the square " + party.getPlayers().get(indexPlayerTurn).getPosition() + ".\nTHROW THE DICE";
                    gameText.setText(gameText.getText().toString() + text);

                }
                if (party.getPlayers().get(indexPlayerTurn).getPlayerNumber() == 1) {
                    TextView player1indicator = findViewById(R.id.player1indicator);
                    player1indicator.setTextColor(Color.parseColor("#00FF1A"));

                    TextView player2indicator = findViewById(R.id.player2indicator);
                    player2indicator.setTextColor(Color.parseColor("#000000"));
                } else {
                    TextView player2indicator = findViewById(R.id.player2indicator);
                    player2indicator.setTextColor(Color.parseColor("#00FF1A"));

                    TextView player1indicator = findViewById(R.id.player1indicator);
                    player1indicator.setTextColor(Color.parseColor("#000000"));
                }
            }
        } else {
            playerTurn.setTrappedTurns(playerTurn.getTrappedTurns() - 1);
            if (playerTurn.getTrappedTurns() == 0) {
                playerTurn.setTrapped(false);
                text = "Player " + playerTurn.getPlayerNumber() + " is no longer trapped";
                gameText.setText(text);
            } else {
                text = "Player " + playerTurn.getPlayerNumber() + " is trapped for " + playerTurn.getTrappedTurns() + " turns";
                gameText.setText(text);
            }
            indexPlayerTurn++;
            if (indexPlayerTurn == 2) {
                indexPlayerTurn = 0;
            }
        }

        if(party.isFinished()) {
            showWinner();
        }
    }

    private boolean positionIsGoose(int position) {
        int gooseIndex = 0;
        while (position != board.getGoose()[gooseIndex] && gooseIndex < 14) {
            gooseIndex++;
        }
        return gooseIndex != 14;
    }
    private boolean positionIsBridge(int position) {
        return position == board.getBridge()[0] || position == board.getBridge()[1];

    }

    private void showWinner() {
        findViewById(R.id.gameTextLayout).setVisibility(View.GONE);
        findViewById(R.id.gameText).setVisibility(View.GONE);
        findViewById(R.id.partyLayout).setVisibility(View.GONE);
        findViewById(R.id.dice).setVisibility(View.GONE);
        findViewById(R.id.player1Position).setVisibility(View.GONE);
        findViewById(R.id.player2Position).setVisibility(View.GONE);
        findViewById(R.id.player1indicator).setVisibility(View.GONE);
        findViewById(R.id.player2indicator).setVisibility(View.GONE);
        findViewById(R.id.positionImage1).setVisibility(View.GONE);
        findViewById(R.id.positionImage2).setVisibility(View.GONE);

        findViewById(R.id.winnerLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.winnerTextLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.winnerText).setVisibility(View.VISIBLE);
        findViewById(R.id.copa).setVisibility(View.VISIBLE);

        Player winner = party.winner();
        TextView winnerTextView = findViewById(R.id.winnerText);
        String winnerText = "AND THE WINNER IS.... \nPLAYER " + winner.getPlayerNumber() + "\n" + winner.getNickname();
        winnerTextView.setText((CharSequence) winnerText);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}