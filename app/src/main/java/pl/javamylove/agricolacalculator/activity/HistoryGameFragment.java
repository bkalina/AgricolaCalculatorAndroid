package pl.javamylove.agricolacalculator.activity;

import android.app.ListFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TwoLineListItem;

import java.util.ArrayList;
import java.util.List;

import pl.javamylove.agricolacalculator.R;
import pl.javamylove.agricolacalculator.model.Player;


/**
 * Fragment z lita graczy bioracych udzial w danej rozgrywce wraz z pelna punktacja
 */
public class HistoryGameFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Lista graczy - tytul
        final List<String> playerTitle = new ArrayList<>();
        // Lista graczy - wyniki
        final List<String> playerScores = new ArrayList<>();
        for (Player player : HistoryGameActivity.getGame().getPlayersList()) {
            playerTitle.add("Gracz: " + player.getName() + "\nwynik: " + player.getScore() + "pkt");
            playerScores.add(
                    getString(R.string.fieldNo) + player.getFields() + "szt\n" +
                            getString(R.string.pastureNo) + player.getFields() + "szt\n" +
                            getString(R.string.grainNo) + player.getGrain() + "szt\n" +
                            getString(R.string.vegetablesNo) + player.getVegetables() + "szt");
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, playerTitle) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        TwoLineListItem row;
                        if (convertView == null) {
                            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            row = (TwoLineListItem) inflater.inflate(android.R.layout.simple_list_item_2, null);
                        } else {
                            row = (TwoLineListItem) convertView;
                        }
                        row.getText1().setText(playerTitle.get(position));
                        row.getText2().setText(playerScores.get(position));
                        int color = Color.WHITE;
                        if (MainActivity.getBackgroundColor() == Color.WHITE) color = Color.BLACK;
                        row.getText1().setTextColor(color);
                        row.getText2().setTextColor(color);

                        return row;
                    }
                };
        setListAdapter(adapter);
    }

}
