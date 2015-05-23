package pl.javamylove.agricolacalculator.activity;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import pl.javamylove.agricolacalculator.activity.MainActivity;
import pl.javamylove.agricolacalculator.model.Game;


public class GameListFragment extends ListFragment implements AbsListView.OnItemClickListener {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Lista rozgrywek
        List<String> values = new ArrayList<String>();
        for (Game game : MainActivity.getDb()) {
            values.add("Rozgrywka: " + game.getName() + " z dnia " + game.getDate());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HistoryGameActivity.setGame(MainActivity.getDb().get(position));
        Intent historyGameActivityIntent = new Intent(getActivity(), HistoryGameActivity.class);
        startActivity(historyGameActivityIntent);
    }
}
