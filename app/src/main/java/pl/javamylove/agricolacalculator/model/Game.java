package pl.javamylove.agricolacalculator.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bartosz Kalina on 2015-05-18.
 */
public class Game implements Serializable {

    private String name;
    private String date;
    private List<Player> playersList;

    public Game() {
    }

    public Game(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Game(String name, String date, List<Player> playersList) {
        this.name = name;
        this.date = date;
        this.playersList = playersList;
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

}
