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


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Nowa gra
        Button newGameButton = (Button) findViewById(R.id.new_game_button);
        final Intent newGameActivityIntent = new Intent(this, NewGameActivity.class);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newGameActivityIntent);
            }
        });

        // Historia gier
        Button historyButton = (Button) findViewById(R.id.history_button);
        final Intent historyActivityIntent = new Intent(this, HistoryActivity.class);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(historyActivityIntent);
            }
        });
    }
}
