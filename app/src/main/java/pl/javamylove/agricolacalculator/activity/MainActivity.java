package pl.javamylove.agricolacalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.FontAwesomeText;

import pl.javamylove.agricolacalculator.R;


public class MainActivity extends Activity {

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout)findViewById(R.id.main_activity_layout);
        // Nowa gra
        BootstrapButton newGameButton = (BootstrapButton) findViewById(R.id.new_game_button);
        final Intent newGameActivityIntent = new Intent(this, NewGameActivity.class);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newGameActivityIntent);
            }
        });

        // Historia gier
        BootstrapButton historyButton = (BootstrapButton) findViewById(R.id.history_button);
        final Intent historyActivityIntent = new Intent(this, HistoryActivity.class);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(historyActivityIntent);
            }
        });

        // Ustawienia
        final FontAwesomeText settingsButton = (FontAwesomeText) findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(settingsButton);
                openContextMenu(settingsButton);
                unregisterForContextMenu(settingsButton);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(getString(R.string.background_color));
        menu.add(0, 0, 0, getString(R.string.black_color));
        menu.add(0, 1, 1, getString(R.string.white_color));
        menu.add(0, 2, 2, getString(R.string.green_color));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            layout.setBackgroundColor(Color.BLACK);
            Toast.makeText(this, getString(R.string.background_color_change), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == 1) {
            layout.setBackgroundColor(Color.WHITE);
            Toast.makeText(this, getString(R.string.background_color_change), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == 2) {
            layout.setBackgroundColor(Color.GREEN);
            Toast.makeText(this, getString(R.string.background_color_change), Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }
        return true;
    }

}
