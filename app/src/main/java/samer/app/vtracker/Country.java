package samer.app.vtracker;

import java.io.Serializable;

public class Country implements Serializable {
    //Database:
    private long ID;
    private int listIndex;

    private String country_name;
    private String number_infected;
    private String number_recovered;
    private String number_deceased;
    //Sorting:
    private boolean isFavorite;

    //Default constructor:
    public Country() {
        this.country_name = "country name";
        this.number_infected = "number infected";
        this.number_recovered = "number recovered";
        this.number_deceased = "number deceased";
        this.isFavorite = false;
    }

    public Country(String country_name, String number_infected, String number_recovered, String number_deceased) {
        this.country_name = country_name;
        this.number_infected = number_infected;
        this.number_recovered = number_recovered;
        this.number_deceased = number_deceased;
        this.isFavorite = false;
    }

    public Country(int listIndex, String country_name, String number_infected, String number_recovered, String number_deceased) {
        this.listIndex = listIndex;
        this.country_name = country_name;
        this.number_infected = number_infected;
        this.number_recovered = number_recovered;
        this.number_deceased = number_deceased;
        this.isFavorite = false;
    }

    public Country(long ID, int listIndex, String country_name, String number_infected, String number_recovered, String number_deceased, boolean isFavorite) {
        this.ID = ID;
        this.listIndex = listIndex;
        this.country_name = country_name;
        this.number_infected = number_infected;
        this.number_recovered = number_recovered;
        this.number_deceased = number_deceased;
        this.isFavorite = isFavorite;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getListIndex() {
        return listIndex;
    }

    public void setListIndex(int listIndex) {
        this.listIndex = listIndex;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getNumber_infected() {
        return number_infected;
    }

    public void setNumber_infected(String number_infected) {
        this.number_infected = number_infected;
    }

    public String getNumber_recovered() {
        return number_recovered;
    }

    public void setNumber_recovered(String number_recovered) {
        this.number_recovered = number_recovered;
    }

    public String getNumber_deceased() {
        return number_deceased;
    }

    public void setNumber_deceased(String number_deceased) {
        this.number_deceased = number_deceased;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "Country{" +
                "ID=" + ID +
                ", listIndex=" + listIndex +
                ", country_name='" + country_name + '\'' +
                ", number_infected='" + number_infected + '\'' +
                ", number_recovered='" + number_recovered + '\'' +
                ", number_deceased='" + number_deceased + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
