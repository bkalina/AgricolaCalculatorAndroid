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

public class HistoryGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_game);

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

}
