package weather;

//indexOf sourced from https://www.javatpoint.com/java-string-indexof

import model.Recommendation;

import java.util.ArrayList;

public class TodayWeather {
    private int temp;

    public TodayWeather(String temp) {
        int newTemp = removeLastCharacter(temp);
        this.temp = newTemp;
    }

    //MODIFIES: this
    //EFFECTS: gets clothes recommendation based on temperature
    public ArrayList<String> getClothesRecommendation() {
        Recommendation styler = new Recommendation();
        return styler.clothesRecommendation(this);
    }

    //EFFECTS: remove "degrees celsius" symbol and decimals from temperature web data
    //sourced from https://www.xenovation.com/blog/development/java/remove-last-character-from-string-java
    public int removeLastCharacter(String str) {
        String result = null;
        if (str.contains(".")) {
            if ((str != null) && (str.length() > 0)) {
                int i = str.indexOf('.');
                result = str.substring(0, str.length() - (str.length() - i));
            }
        } else {
            result = str;
        }
        int i = Integer.parseInt(result); //sourced from https://alvinalexander.com/java/edu/qanda/pjqa00010.shtml
        return i;
    }

    public int getTemp() {
        return this.temp;
    }

}

