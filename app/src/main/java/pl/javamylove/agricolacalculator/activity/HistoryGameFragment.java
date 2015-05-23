package pl.javamylove.agricolacalculator.activity;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.javamylove.agricolacalculator.model.Game;
import pl.javamylove.agricolacalculator.model.Player;


public class HistoryGameFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Lista graczy
        List<String> values = new ArrayList<String>();
        for(Player player : HistoryGameActivity.getGame().getPlayersList()) {
            values.add("Gracz: " + player.getName() + " wynik: " + player.getScore());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

}
