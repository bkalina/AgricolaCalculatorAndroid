package pl.javamylove.agricolacalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import pl.javamylove.agricolacalculator.R;
import pl.javamylove.agricolacalculator.model.Game;
import pl.javamylove.agricolacalculator.model.Player;

/**
 * Activity pod dodawanie nowej rozgrywki
 */
public class NewGameActivity extends Activity implements View.OnClickListener {

    private String id;
    private BootstrapEditText gameNameEdit;
    private static Game game;
    private static Player player1, player2, player3, player4;
    private List<Player> playersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // Kolor tla
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.new_game_activity_layout);
        layout.setBackgroundColor(MainActivity.getBackgroundColor());

        // Utworzenie gry i nowych graczy jesli to nowa gra
        if (PlayerActivity.getPlayer() == null) setupGame();

        // Update GUI
        setupPlayersGUI();
        gameNameEdit = (BootstrapEditText) findViewById(R.id.game_name_text_edit);

        // Przyciski graczy
        BootstrapButton player1Button = (BootstrapButton) findViewById(R.id.player1_button);
        player1Button.setOnClickListener(this);
        BootstrapButton player2Button = (BootstrapButton) findViewById(R.id.player2_button);
        player2Button.setOnClickListener(this);
        BootstrapButton player3Button = (BootstrapButton) findViewById(R.id.player3_button);
        player3Button.setOnClickListener(this);
        BootstrapButton player4Button = (BootstrapButton) findViewById(R.id.player4_button);
        player4Button.setOnClickListener(this);

        // Anuluj
        BootstrapButton cancelButton = (BootstrapButton) findViewById(R.id.cancel_button);
        final Intent mainActivityIntent = new Intent(this, MainActivity.class);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerActivity.setPlayer(null);
                startActivity(mainActivityIntent);
            }
        });

        // Zapis gry
        BootstrapButton saveGameButton = (BootstrapButton) findViewById(R.id.save_game_button);
        saveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameNameEdit.getText().toString().equals(null) || gameNameEdit.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.game_name_valid), Toast.LENGTH_SHORT).show();
                } else {
                    saveGame();
                    Toast.makeText(getApplicationContext(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
                    startActivity(mainActivityIntent);
                }
            }
        });
    }

    /**
     * Zapisanie rozgrywki
     */
    private void saveGame() {
        // Utworzenie gry
        List<Player> temp = new ArrayList<>();
        for (Player player : playersList) {
            if (player.getActive() == 1)
                temp.add(player);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyy");
        game = new Game(UUID.randomUUID().toString(), gameNameEdit.getText().toString(), formatter.format(new Date()), temp);
        PlayerActivity.setPlayer(null);
        // Zapis gry
        MainActivity.getGameDAO().createGame(game);
    }

    /**
     * Utworzenie nowej rozgrywki
     */
    private void setupGame() {
        game = new Game();
        player1 = new Player("Gracz 1");
        player2 = new Player("Gracz 2");
        player3 = new Player("Gracz 3");
        player4 = new Player("Gracz 4");
    }

    /**
     * Update GUI zgodnie z modelem
     */
    private void setupPlayersGUI() {
        BootstrapButton b;
        b = (BootstrapButton) findViewById(R.id.player1_button);
        b.setText(player1.getName() + "  :  " + player1.getScore() + "pkt");
        b = (BootstrapButton) findViewById(R.id.player2_button);
        b.setText(player2.getName() + "  :  " + player2.getScore() + "pkt");
        b = (BootstrapButton) findViewById(R.id.player3_button);
        b.setText(player3.getName() + "  :  " + player3.getScore() + "pkt");
        b = (BootstrapButton) findViewById(R.id.player4_button);
        b.setText(player4.getName() + "  :  " + player4.getScore() + "pkt");
        playersList = new ArrayList<>();
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
                PlayerActivity.setPlayer(player1);
                break;
            case R.id.player2_button:
                PlayerActivity.setPlayer(player2);
                break;
            case R.id.player3_button:
                PlayerActivity.setPlayer(player3);
                break;
            case R.id.player4_button:
                PlayerActivity.setPlayer(player4);
                break;
        }
        startActivity(playerActvityIntent);
    }

}
