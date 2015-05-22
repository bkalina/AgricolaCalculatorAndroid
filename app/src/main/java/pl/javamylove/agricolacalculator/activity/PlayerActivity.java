package pl.javamylove.agricolacalculator.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import pl.javamylove.agricolacalculator.R;
import pl.javamylove.agricolacalculator.model.Player;

public class PlayerActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

    private static Player player;
    private BootstrapEditText playerNameEdit;
    private SegmentedGroup fieldsSegment, pasturesSegment, grainSegment, vegetableSegment;
    private String nameTemp = player.getName();
    private int fieldsTemp, pasturesTemp, grainTemp, vegetablesTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Nazwa gracza
        playerNameEdit = (BootstrapEditText) findViewById(R.id.player_name_text_edit);
        playerNameEdit.setText(nameTemp);

        // Segmenty punktacji
        fieldsSegment = (SegmentedGroup) findViewById(R.id.fields);
        pasturesSegment = (SegmentedGroup) findViewById(R.id.pastures);
        grainSegment = (SegmentedGroup) findViewById(R.id.grain);
        vegetableSegment = (SegmentedGroup) findViewById(R.id.vegetables);

        // Listenery
        fieldsSegment.setOnCheckedChangeListener(this);
        pasturesSegment.setOnCheckedChangeListener(this);
        grainSegment.setOnCheckedChangeListener(this);
        vegetableSegment.setOnCheckedChangeListener(this);

        // RadioButtony
        List<RadioButton> fieldsButtons = Arrays.asList((RadioButton) findViewById(R.id.field1), (RadioButton) findViewById(R.id.field2), (RadioButton) findViewById(R.id.field3), (RadioButton) findViewById(R.id.field4), (RadioButton) findViewById(R.id.field5));
        List<RadioButton> pasturesButtons = Arrays.asList((RadioButton) findViewById(R.id.pasture1), (RadioButton) findViewById(R.id.pasture2), (RadioButton) findViewById(R.id.pasture3), (RadioButton) findViewById(R.id.pasture4), (RadioButton) findViewById(R.id.pasture5));
        List<RadioButton> grainButtons = Arrays.asList((RadioButton) findViewById(R.id.grain1), (RadioButton) findViewById(R.id.grain2), (RadioButton) findViewById(R.id.grain3), (RadioButton) findViewById(R.id.grain4), (RadioButton) findViewById(R.id.grain5));
        List<RadioButton> vegetablessButtons = Arrays.asList((RadioButton) findViewById(R.id.vegetable1), (RadioButton) findViewById(R.id.vegetable2), (RadioButton) findViewById(R.id.vegetable3), (RadioButton) findViewById(R.id.vegetable4), (RadioButton) findViewById(R.id.vegetable5));
        List<List<RadioButton>> lists = Arrays.asList(fieldsButtons, pasturesButtons, grainButtons, vegetablessButtons);

        // Odtworzenie wynikow
        fieldsTemp = player.getPointsList().get(0);
        pasturesTemp = player.getPointsList().get(1);
        grainTemp = player.getPointsList().get(2);
        vegetablesTemp = player.getPointsList().get(3);
        int pointsIterator = 0;
        for(List<RadioButton> list : lists) {
            for(RadioButton btn : list) {
                if(btn.getTag().toString().equals(player.getPointsList().get(pointsIterator).toString())) btn.setChecked(true);
                else btn.setChecked(false);
            }
            pointsIterator++;
        }

        // Anulowanie
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
                if (playerNameEdit.getText().toString().equals(null) || playerNameEdit.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), getString(R.string.player_name_valid), Toast.LENGTH_SHORT).show();
                } else {
                    savePlayerData(playerNameEdit.getText().toString(), fieldsTemp, pasturesTemp, grainTemp, vegetablesTemp);
                    System.out.println(player);
                    Toast.makeText(getApplicationContext(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
                    startActivity(newGameActivityIntent);
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //Toast.makeText(getApplicationContext(), findViewById(checkedId).getTag().toString(), Toast.LENGTH_SHORT).show();
        if (group.getId() == fieldsSegment.getId())
            fieldsTemp = Integer.parseInt(findViewById(checkedId).getTag().toString());
        else if (group.getId() == pasturesSegment.getId())
            pasturesTemp = Integer.parseInt(findViewById(checkedId).getTag().toString());
        else if (group.getId() == grainSegment.getId())
            grainTemp = Integer.parseInt(findViewById(checkedId).getTag().toString());
        else if (group.getId() == vegetableSegment.getId())
            vegetablesTemp = Integer.parseInt(findViewById(checkedId).getTag().toString());
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        PlayerActivity.player = player;
    }

    private void savePlayerData(String name, int fields, int pastures, int grain, int vegetables) {
        player.setActive(1);
        player.setName(name);
        player.setFields(fields);
        player.setPasures(pastures);
        player.setGrain(grain);
        player.setVegetables(vegetables);

        List<Integer> points = new ArrayList<Integer>();
        points.add(fields);
        points.add(pastures);
        points.add(grain);
        points.add(vegetables);
        player.setPointsList(points);

        player.setScore(fields + pastures + grain + vegetables);

    }

}
