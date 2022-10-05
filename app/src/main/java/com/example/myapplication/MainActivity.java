package com.example.tictactoe10;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // variable of the main activity
    // text view variables
    private TextView playerOscore, playerXscore, playerStatus;
    // buttons variables
    private Button restartButton, mainMenuButton;
    // counters init
    private int CountPlayerXScore, CountPlayerOScore, countRound;
    // Boolean to switch players
    boolean player;
    // state of the game
    int[][] wins = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    int[] boardState = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    // buttons
    private Button[] buttons = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // text views
        playerOscore = (TextView) findViewById(R.id.playerOscore);
        playerXscore = (TextView) findViewById(R.id.playerXscore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);
        // buttons

        restartButton = (Button) findViewById(R.id.restartButton);
        // counters
        for (int i = 0; i < buttons.length; i++) {
            String buttonID = "button" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        countRound = 0;
        CountPlayerXScore = 0;
        CountPlayerOScore = 0;
        player = true;

    }


    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int boardStatePointer = Integer.parseInt(buttonID.substring(buttonID.length() - 1, buttonID.length()));

        if (player) {
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#FFC34A"));
            boardState[boardStatePointer] = 0;
        } else {
            ((Button) view).setText("O");
            ((Button) view).setTextColor(Color.parseColor("#FFC34A"));
            boardState[boardStatePointer] = 0;
        }
        countRound++;

        if(checkWinner()){
            if(player){
                CountPlayerXScore++;
                updatePlayerScore();
                Toast.makeText();
                playAgain();
            }else{
                CountPlayerOScore++;
                updatePlayerScore();
                Toast.makeText(this)
                playAgain();
            }
        }else if(countRound==9){
            playAgain();
            Toast.makeText(context:this,text:"Draw!",Toast.LENGTH_SHORT).show();
        }else{
            player=!player;
        }

        if(CountPlayerXScore>CountPlayerOScore){
            playerStatus.setText("Player X is winning!");
        }else if(CountPlayerOScore>CountPlayerXScore){
            playerStatus.setText("Player O is winning!");
        }else{
            playerStatus.setText("");
        }

    }

    public boolean checkWinner() {
        boolean winnerResult = false;

        for (int[] wins : wins) {
            if (boardState[wins[0]] == boardState[wins[1]] && boardState[wins[1]] == boardState[wins[2]] && boardState[wins[0]] != 2) {

                winnerResult = true;
            }
        }
        return winnerResult;
    }
    public void updatePlayerScore(){
        playerXscore.setText(Integer.toString(CountPlayerXScore));
        playerXscore.setText(Integer.toString(CountPlayerOScore));
    }
    public void playAgain() {
        countRound = 0;
        player = true;

        for(int i = 0 ; i < buttons.length; i++) {
            boardState[i] = 2;
            buttons[i].setText("");
        }
    }
}
