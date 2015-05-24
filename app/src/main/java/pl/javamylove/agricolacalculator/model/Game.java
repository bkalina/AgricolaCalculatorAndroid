package pl.javamylove.agricolacalculator.model;

import java.io.Serializable;
import java.util.List;

/**
 * Klasa rozgrywki
 * Created by Bartosz Kalina on 2015-05-18.
 */
public class Game implements Serializable {

    private String id;
    private String name;
    private String date;
    private List<Player> playersList;

    public Game() {
    }

    public Game(String id, String name, String date, List<Player> playersList) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.playersList = playersList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", playersList=" + playersList +
                '}';
    }
}
