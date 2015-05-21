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

import java.util.ArrayList;
import java.util.List;

import pl.javamylove.agricolacalculator.R;
import pl.javamylove.agricolacalculator.model.Player;

public class NewGameActivity extends Activity implements View.OnClickListener {

    private String id;
    private List<Player> playersList;
    private Player player1, player2, player3, player4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        if(getIntent().getSerializableExtra("player") == null) setupPlayers();
        setupPlayersGUI();

        BootstrapButton player1Button = (BootstrapButton) findViewById(R.id.player1_button);
        player1Button.setOnClickListener(this);
        BootstrapButton player2Button = (BootstrapButton) findViewById(R.id.player2_button);
        player2Button.setOnClickListener(this);
        BootstrapButton player3Button = (BootstrapButton) findViewById(R.id.player3_button);
        player3Button.setOnClickListener(this);
        BootstrapButton player4Button = (BootstrapButton) findViewById(R.id.player4_button);
        player4Button.setOnClickListener(this);

        BootstrapButton cancelButton = (BootstrapButton) findViewById(R.id.cancel_button);
        final Intent mainActivityIntent = new Intent(this, MainActivity.class);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivityIntent);
            }
        });

        // Zapis gry
        BootstrapButton saveGameButton = (BootstrapButton) findViewById(R.id.save_game_button);
        saveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.games_saved), Toast.LENGTH_LONG).show();
                startActivity(mainActivityIntent);
            }
        });
    }

    private void setupPlayers() {
        player1 = new Player("Gracz 11");
        player2 = new Player("Gracz 22");
        player3 = new Player("Gracz 33");
        player4 = new Player("Gracz 44");
    }

    private void setupPlayersGUI() {
        BootstrapButton b = null;
        b = (BootstrapButton) findViewById(R.id.player1_button);
        b.setText(player1.getName());
        b = (BootstrapButton) findViewById(R.id.player2_button);
        b.setText(player2.getName());
        b = (BootstrapButton) findViewById(R.id.player3_button);
        b.setText(player3.getName());
        b = (BootstrapButton) findViewById(R.id.player4_button);
        b.setText(player4.getName());
        playersList = new ArrayList<Player>();
        playersList.add(player1);
        playersList.add(player2);
        playersList.add(player3);
        playersList.add(player4);
    }

    @Override
    public void onClick(View v) {
        Intent playerActvityIntent = new Intent(this, PlayerActivity.class);
        switch (v.getId()) {
            case R.id.player1_button:
                playerActvityIntent.putExtra("player", player1);
                break;
            case R.id.player2_button:
                playerActvityIntent.putExtra("player", player1);
                break;
            case R.id.player3_button:
                playerActvityIntent.putExtra("player", player1);
                break;
            case R.id.player4_button:
                playerActvityIntent.putExtra("player", player1);
                break;
        }
        startActivity(playerActvityIntent);
    }
}
