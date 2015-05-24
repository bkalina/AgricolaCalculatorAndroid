package pl.javamylove.agricolacalculator.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.javamylove.agricolacalculator.model.Game;
import pl.javamylove.agricolacalculator.model.Player;
import pl.javamylove.agricolacalculator.utils.MySQLiteManager;

/**
 * DataAccessObject dla klas
 * Created by Bartosz Kalina on 23-05-2015.
 */
public class GameDAO {

    private SQLiteDatabase database;
    private MySQLiteManager dbManager;
    private String[] allColumns = MySQLiteManager.COLUMNS;

    public GameDAO(Context context) {
        dbManager = new MySQLiteManager(context);
    }

    public void open() throws SQLException {
        database = dbManager.getWritableDatabase();
    }

    public void close() {
        dbManager.close();
    }

    public Game createGame(Game game) {
        ContentValues values = new ContentValues();
        values.put("name", game.getName());
        values.put("date", game.getDate());
        for (int i = 0; i < game.getPlayersList().size(); i++) {
            values.put("p" + (i + 1) + "name", game.getPlayersList().get(i).getName());
            values.put("p" + (i + 1) + "score", game.getPlayersList().get(i).getScore());
            values.put("p" + (i + 1) + "fields", game.getPlayersList().get(i).getFields());
            values.put("p" + (i + 1) + "pastures", game.getPlayersList().get(i).getPasures());
            values.put("p" + (i + 1) + "grain", game.getPlayersList().get(i).getGrain());
            values.put("p" + (i + 1) + "vegetables", game.getPlayersList().get(i).getVegetables());
            values.put("p" + (i + 1) + "active", game.getPlayersList().get(i).getActive());
        }
        values.put("test", "OK");

        long insertId = database.insert(MySQLiteManager.TABLE_GAMES, null,
                values);
        Cursor cursor = database.query(MySQLiteManager.TABLE_GAMES,
                allColumns, MySQLiteManager.GAME_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Game newGame = cursorToGame(cursor);
        cursor.close();
        return newGame;
    }

    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteManager.TABLE_GAMES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Game game = cursorToGame(cursor);
            games.add(game);
            cursor.moveToNext();
        }
        cursor.close();
        return games;
    }

    private Game cursorToGame(Cursor cursor) {
        Game game = new Game();
        game.setName(cursor.getString(0));
        game.setDate(cursor.getString(1));
        game.setPlayersList(new ArrayList<Player>());
        for (int i = 2; i < 30; i += 7) {
            if (cursor.getString(i) == null) break; // gdy nie ma wiecej graczy
            game.getPlayersList().add(new Player(cursor.getString(i), Integer.parseInt(cursor.getString(i + 1)), Integer.parseInt(cursor.getString(i + 2)), Integer.parseInt(cursor.getString(i + 3)), Integer.parseInt(cursor.getString(i + 4)), Integer.parseInt(cursor.getString(i + 5)), Integer.parseInt(cursor.getString(i + 6))));
        }
        return game;
    }
}
