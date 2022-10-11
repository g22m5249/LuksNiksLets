package com.example.clickonxno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Multiplayer extends AppCompatActivity implements View.OnClickListener {

    // initialise the buttons
    private Button[][] buttons = new Button[3][3];
    // a boolean to check for player turns
    private boolean playerXTurn = true;
    // counters
    private int roundCount;
    private int playerXScore;
    private int playerOScore;
    // text views
    private TextView textViewPlayerX;
    private TextView textViewPlayerO;

    // main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // locating the text views by id
        textViewPlayerX = findViewById(R.id.text_view_pX);
        textViewPlayerO = findViewById(R.id.text_view_pO);

        //for loop to be able to click all buttons
        for(int i = 0;i < 3 ; i++) {
            for(int j = 0;j < 3 ; j++) {
                String buttonID= "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }


        Button buttonMenu = findViewById(R.id.button_main);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Multiplayer.this, MainMenu.class);
                startActivity(intent);
            }
        });

        // restart button to restart the game
        Button buttonRestart = findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
    }
    // method to play with x's and o's
    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        // when it is player x's turn and o when it is player o's turn
        ((Button) view).setText("X");
        // increment no. of rounds
        roundCount++;

        if (checkForWin()) {
            if (playerXTurn) {
                playerXWins();
            } else {
                playerOWins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            playerXTurn = !playerXTurn;
        }
        //call randomise method

        cpuPlay();
    }
    // button will play
    private void cpuPlay() {
        Random rand = new Random();

        int i = rand.nextInt(3);
        int j = rand.nextInt(3);

        String[][] field = new String[3][3];
        field[i][j] = buttons[i][j].getText().toString();

        while (!((buttons[i][j].getText().toString()).equals(""))) {
            i = rand.nextInt(3);
            j = rand.nextInt(3);
        }

        buttons[i][j].setText("O");
    }

    // method to check which player wins
    private boolean checkForWin() {
        String[][] field = new String[3][3];
        // for loop to get the plays played by the players
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        // lukhanyo should explain
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    // if player X wins and update score then restart game
    private void playerXWins() {
        playerXScore++;
        Toast.makeText(this, "Player X has Won!", Toast.LENGTH_SHORT).show();
        updateScoreText();
        restartGame();
    }

    // if player O wins and update score then restart game
    private void playerOWins() {
        playerOScore++;
        Toast.makeText(this, "CPU has Won!", Toast.LENGTH_SHORT).show();
        updateScoreText();
        restartGame();
    }
    // if it is a tie indicate and restart the game
    private void draw() {
        Toast.makeText(this, "Tie/Draw!", Toast.LENGTH_SHORT).show();
        restartGame();
    }

    // update the scores as the players play
    private void updateScoreText() {
        textViewPlayerX.setText("Player X:" + playerXScore);
        textViewPlayerO.setText("Player O:" + playerOScore);

    }

    // restart the whole game
    private void restartGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // make the grids empty
                buttons[i][j].setText("");
            }
        }

        // make round count 0 and playerX's turn
        roundCount = 0;
        playerXTurn = true;
    }

}
