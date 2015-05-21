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
import com.beardedhen.androidbootstrap.BootstrapEditText;

import pl.javamylove.agricolacalculator.R;
import pl.javamylove.agricolacalculator.model.Player;

public class PlayerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        final Player player = (Player) getIntent().getSerializableExtra("player");

        final BootstrapEditText playerName = (BootstrapEditText) findViewById(R.id.player_name_text_edit);
        playerName.setText(player.getName());

        BootstrapButton cancelButton = (BootstrapButton) findViewById(R.id.cancel_button);
        final Intent newGameActivityIntent = new Intent(this, NewGameActivity.class);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newGameActivityIntent);
            }
        });

        // Zapis gracza
        BootstrapButton savePlayerButton = (BootstrapButton) findViewById(R.id.save_player_button);
        savePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setName(playerName.getText().toString());
                newGameActivityIntent.putExtra("player", player);
                Toast.makeText(getApplicationContext(), getString(R.string.saved), Toast.LENGTH_LONG).show();
                startActivity(newGameActivityIntent);
            }
        });
    }

}
