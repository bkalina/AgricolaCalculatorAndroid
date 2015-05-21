package pl.javamylove.agricolacalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import pl.javamylove.agricolacalculator.R;

public class NewGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // Gracz
        BootstrapButton playerButton = (BootstrapButton) findViewById(R.id.player1_button);
        final Intent playerActvityIntent = new Intent(this, PlayerActivity.class);
        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(playerActvityIntent);
            }
        });

        // Zapis gry
        BootstrapButton saveGameButton = (BootstrapButton) findViewById(R.id.save_game_button);
        final Intent mainActivityIntent = new Intent(this, MainActivity.class);
        saveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.games_saved), Toast.LENGTH_LONG).show();
                startActivity(mainActivityIntent);
            }
        });
    }
}
