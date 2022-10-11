import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean playerXTurn = true;
    private int roundCount;
    private int playerXScore;
    private int playerOScore;

    private TextView textViewPlayerX;
    private TextView textViewPlayerO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayerX = findViewById(R.id.text_view_pX);
        textViewPlayerO = findViewById(R.id.text_view_pO);

        for(int i = 0;i < 3 ; i++) {
            for(int j = 0;j < 3 ; j++) {
                String buttonID= "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }

        Button buttonRestart = findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();


            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }

        if (playerXTurn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }

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

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

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

    private void playerXWins() {
        playerXScore++;
        Toast.makeText(this, "Player X has Won!", Toast.LENGTH_SHORT).show();
        updateScoreText();
        restartGame();
    }

    private void playerOWins() {
        playerOScore++;
        Toast.makeText(this, "Player O has Won!", Toast.LENGTH_SHORT).show();
        updateScoreText();
        restartGame();
    }

    private void draw() {
        Toast.makeText(this, "Tie/Draw!", Toast.LENGTH_SHORT).show();
        restartGame();
    }
    private void updateScoreText() {
        textViewPlayerX.setText("PlayerX:" + playerXScore);
        textViewPlayerO.setText("PlayerO:" + playerOScore);

    }
    private void restartBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        playerXTurn = true;
    }
    private void restartGame() {
        playerXScore = 0;
        playerOScore = 0;
        updateScoreText();
        restartBoard();
    }
