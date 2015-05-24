package pl.javamylove.agricolacalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import pl.javamylove.agricolacalculator.R;
import pl.javamylove.agricolacalculator.model.Game;

/**
 * Activity pod prezentacje konkretnej rozgrywki po wybraniu z listy rozgrywek
 */
public class HistoryGameActivity extends Activity {

    private static Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_game);

        // Kolor tla
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.history_game_activity_layout);
        layout.setBackgroundColor(MainActivity.getBackgroundColor());

        // Opis gry
        TextView gameTitle = (TextView) findViewById(R.id.gameTitle);
        gameTitle.setText("Rozgrywka: " + game.getName() + "\nz dnia " + game.getDate());

        // Powrot
        BootstrapButton backButton = (BootstrapButton) findViewById(R.id.back_button);
        final Intent historyActivityIntent = new Intent(this, HistoryActivity.class);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(historyActivityIntent);
            }
        });
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        HistoryGameActivity.game = game;
    }
}
