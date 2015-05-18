package pl.javamylove.agricolacalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import pl.javamylove.agricolacalculator.R;

public class HistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Hitoria gry
        Button historyGameButton = (Button) findViewById(R.id.history_game_button);
        final Intent historyGameActivityIntent = new Intent(this, HistoryGameActivity.class);
        historyGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(historyGameActivityIntent);
            }
        });

        // Powrót
        Button backButton = (Button) findViewById(R.id.back_button);
        final Intent mainActivityIntent = new Intent(this, MainActivity.class);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivityIntent);
            }
        });
    }

}
