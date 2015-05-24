package pl.javamylove.agricolacalculator.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Klasa pomocnicza do zarzadzania wewnetrzna baza danych
 * Created by Bartosz Kalina on 23-05-2015.
 */
public class MySQLiteManager extends SQLiteOpenHelper {

    public static final String TABLE_GAMES = "games";
    public static final String GAME_ID = "_id";
    public static final String COLUMNS[] = {
            "name", "date",
            "p1name", " p1score", " p1fields", " p1pastures", " p1grain", " p1vegetables", "p1active",
            "p2name", " p2score", " p2fields", " p2pastures", " p2grain", " p2vegetables", "p2active",
            "p3name", " p3score", " p3fields", " p3pastures", " p3grain", " p3vegetables", "p3active",
            "p4name", " p4score", " p4fields", " p4pastures", " p4grain", " p4vegetables", "p4active"};
    private static final String DATABASE_NAME = "games.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_CREATE = makeDBstring();

    public MySQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @return metoda zwraca string z zapytaniem sql tworzacym tabele
     */
    private static String makeDBstring() {
        String dbString = GAME_ID + " integer primary key autoincrement,";
        for (String c : COLUMNS) {
            dbString += (c + " text, ");
        }
        dbString += "test text";
        return dbString;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table " + TABLE_GAMES + "(" + DATABASE_CREATE + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteManager.class.getName(),
                "Upgrade bazy danych z wersji " + oldVersion + " do "
                        + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
        onCreate(db);
    }

}
