package fr.elitesystems.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isWinner = Boolean.FALSE;
    int[] players = {R.drawable.cross, R.drawable.round};
    TextView[] scores = {null, null};
    int played = 0;
    int[] gridState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    // Check if there is a winner
    private boolean isWinner(int currentPlayer) {
        for (int[] winningPosition : winningPositions) {
            if (currentPlayer == gridState[winningPosition[0]] && gridState[winningPosition[0]] == gridState[winningPosition[1]] && gridState[winningPosition[1]] == gridState[winningPosition[2]]) {
                Integer newScore = Integer.parseInt(scores[currentPlayer-1].getText().toString()) + 1;
                scores[currentPlayer-1].setText(newScore.toString());
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public void dropIn(View view) {
        if (!isWinner && view.getAlpha() == 0f) {
            int currentPlayer = (++played%2) + 1;
            gridState[Integer.parseInt(view.getTag().toString())] = currentPlayer;
            ((ImageView) view).setImageResource(players[currentPlayer - 1]);
            view.animate().alpha(1f).setDuration(1000);
            isWinner = isWinner(currentPlayer);
            if (isWinner) {
                Toast.makeText(getApplicationContext(), "You WIN !", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void reInit(View view) {
        for (TextView score : scores) {
            score.setText("0");
        }
        replay(null);
    }

    public void replay(View view) {
        isWinner = Boolean.FALSE;
        for (int i = 0; i < gridState.length; i++) {
            gridState[i] = 0;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0;  i < gridLayout.getChildCount(); i++) {
            gridLayout.getChildAt(i).setAlpha(0f);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scores[0] = (TextView) findViewById(R.id.score1);
        scores[1] = (TextView) findViewById(R.id.score2);
    }
}
