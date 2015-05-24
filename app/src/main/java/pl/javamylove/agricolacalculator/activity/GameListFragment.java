package pl.javamylove.agricolacalculator.activity;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TwoLineListItem;

import java.util.ArrayList;
import java.util.List;

import pl.javamylove.agricolacalculator.model.Game;
import pl.javamylove.agricolacalculator.model.Player;


/**
 * Fragment zawierajacy w sobie liste rozgrywek zapisanych w bazie danych
 */
public class GameListFragment extends ListFragment implements AbsListView.OnItemClickListener {

    private List<Game> games;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Pobranie gier z DB
        games = MainActivity.getGameDAO().getAllGames();

        // Lista rozgrywek - tytul
        final List<String> gamesTitle = new ArrayList<>();
        // Lista rozgrywek - zwyciezca
        final List<String> gamesWinner = new ArrayList<>();
        for (Game game : games) {
            gamesTitle.add("Rozgrywka: " + game.getName() + "\nz dnia " + game.getDate());
            Player winner = game.getPlayersList().get(0);
            for (int i = 1; i < game.getPlayersList().size(); i++) {
                if (game.getPlayersList().get(i).getScore() > winner.getScore())
                    winner = game.getPlayersList().get(i);
            }
            gamesWinner.add("Wygral: " + winner.getName() + " z wynikiem " + String.valueOf(winner.getScore()) + "pkt");
        }

        // Adapter do listy
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, gamesTitle) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TwoLineListItem row;
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    row = (TwoLineListItem) inflater.inflate(android.R.layout.simple_list_item_2, null);
                } else {
                    row = (TwoLineListItem) convertView;
                }

                row.getText1().setText(gamesTitle.get(position));
                row.getText2().setText(gamesWinner.get(position));
                int color = Color.WHITE;
                if (MainActivity.getBackgroundColor() == Color.WHITE) color = Color.BLACK;
                row.getText1().setTextColor(color);
                row.getText2().setTextColor(color);

                return row;
            }
        };
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HistoryGameActivity.setGame(games.get(position));
        Intent historyGameActivityIntent = new Intent(getActivity(), HistoryGameActivity.class);
        startActivity(historyGameActivityIntent);
    }
}
