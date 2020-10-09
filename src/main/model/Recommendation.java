package model;

import weather.TodayWeather;

import java.util.ArrayList;

public class Recommendation {
    private ArrayList<String> clothesRecommendation = new ArrayList<>();
    private LoadCloset closet;
    private int randomInt;

    public Recommendation() {
    }

    //EFFECTS: returns article of clothing for body
    public String topArticle(TodayWeather weather, LoadCloset closet) {
        int riJ = getRandomInt(closet.getJacketList().size()); //randomInt details sourced from https://javarevisited.blogspot.com/2013/05/how-to-generate-random-numbers-in-java-between-range.html
        int riS = getRandomInt(closet.getShirtList().size());
        if (weather.getTemp() < 5) {
            return closet.getJacketList().get(2).getColour() + " " + closet.getJacketList().get(2).getBrand() + " "
                    + closet.getJacketList().get(2).getType() + " and "
                    + closet.getShirtList().get(riS).getColour() + " " + closet.getShirtList().get(riS).getBrand() + " "
                    + closet.getShirtList().get(riS).getType();
        } else if (weather.getTemp() < 14) {
            return closet.getJacketList().get(riJ).getColour() + " " + closet.getJacketList().get(riJ).getBrand() + " "
                    + closet.getJacketList().get(riJ).getType() + " and "
                    + closet.getShirtList().get(riS).getColour() + " " + closet.getShirtList().get(riS).getBrand() + " "
                    + closet.getShirtList().get(riS).getType();
        }
        return closet.getShirtList().get(riS).getColour() + " " + closet.getShirtList().get(riS).getBrand() + " "
                + closet.getShirtList().get(riS).getType();
    }

    //EFFECTS: returns article of clothing for legs
    public String bottomArticle(TodayWeather weather, LoadCloset closet) {
        int randPantInt = getRandomInt(closet.getPantList().size());
        if (weather.getTemp() < 18) {
            return closet.getPantList().get(randPantInt).getColour() + " "
                    + closet.getPantList().get(randPantInt).getBrand() + " "
                    + closet.getPantList().get(randPantInt).getType();
        }
        return closet.getPantList().get(randPantInt).getColour() + " "
                + closet.getPantList().get(randPantInt).getBrand() + " "
                + closet.getPantList().get(randPantInt).getType();
    }

    //MODIFIES: this
    //EFFECTS: sources clothing items to wear based off closet items
    public ArrayList<String> clothesRecommendation(TodayWeather weather) {
        closet = new LoadCloset();
        closet.load();
        clothesRecommendation.add(topArticle(weather, closet));
        clothesRecommendation.add(bottomArticle(weather, closet));
        return clothesRecommendation;
    }

    //EFFECTS: produces a random integer
    public int getRandomInt(int size) {
        return randomInt = (int) (size * Math.random());
    }



}
