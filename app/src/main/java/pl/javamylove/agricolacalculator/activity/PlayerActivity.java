package pl.javamylove.agricolacalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;

import pl.javamylove.agricolacalculator.R;

public class PlayerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Zapis gracza
        BootstrapButton savePlayerButton = (BootstrapButton) findViewById(R.id.save_player_button);
        final Intent newGameActivityIntent = new Intent(this, NewGameActivity.class);
        savePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newGameActivityIntent);
            }
        });
    }

}
