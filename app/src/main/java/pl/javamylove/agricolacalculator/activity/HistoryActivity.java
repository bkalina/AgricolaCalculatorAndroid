package pl.javamylove.agricolacalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import pl.javamylove.agricolacalculator.R;

/**
 * Activity pod prezentacje listy rozgrywek
 */
public class HistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Kolor tla
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.history_activity_layout);
        layout.setBackgroundColor(MainActivity.getBackgroundColor());

        // Opis listy
        TextView gameTitle = (TextView) findViewById(R.id.listTitle);
        gameTitle.setText("Lista rozgrywek");

        // Powrot
        BootstrapButton backButton = (BootstrapButton) findViewById(R.id.back_button);
        final Intent mainActivityIntent = new Intent(this, MainActivity.class);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivityIntent);
            }
        });
    }

}
