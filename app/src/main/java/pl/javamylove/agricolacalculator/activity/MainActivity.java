package pl.javamylove.agricolacalculator.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.FontAwesomeText;

import java.util.ArrayList;
import java.util.List;

import pl.javamylove.agricolacalculator.R;
import pl.javamylove.agricolacalculator.model.Game;


public class MainActivity extends Activity {

    private RelativeLayout layout;
    private static int backgroundColor = Color.BLACK;
    private SharedPreferences prefs;

    private static List<Game> db = new ArrayList<Game>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Prefs
        prefs = this.getSharedPreferences(
                "pl.javamylove.agricolacalculator", Context.MODE_PRIVATE);
        backgroundColor = prefs.getInt("pl.javamylove.agricolacalculator.backgroundColor", Color.BLACK);

        // Kolor tla
        layout = (RelativeLayout) findViewById(R.id.main_activity_layout);
        layout.setBackgroundColor(backgroundColor);

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
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            backgroundColor = Color.BLACK;
        } else if (item.getItemId() == 1) {
            backgroundColor = Color.WHITE;
        } else {
            return false;
        }
        layout.setBackgroundColor(backgroundColor);
        prefs.edit().putInt("pl.javamylove.agricolacalculator.backgroundColor", backgroundColor).apply();
        Toast.makeText(this, getString(R.string.background_color_change), Toast.LENGTH_SHORT).show();
        return true;
    }

    public static int getBackgroundColor() {
        return backgroundColor;
    }

    public static void setBackgroundColor(int backgroundColor) {
        MainActivity.backgroundColor = backgroundColor;
    }

    public static List<Game> getDb() {
        return db;
    }

    public static void setDb(List<Game> db) {
        MainActivity.db = db;
    }
}
