package network;

//sourced from: http://zetcode.com/articles/javareadwebpage/ & 210 Edx

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadWebPage {

    //EFFECTS: gathers Vancouver weather data from api
    public String gatherWeather() throws MalformedURLException, IOException {

        BufferedReader br = null;
        try {
            String theURL = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,can&mode=html&APPID=0df5466fd5fcbba5f97ad2fe0933a211";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            return parseWeather(sb.toString());
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    //EFFECTS: extract temperature from weather data
    //sourced from https://www.oracle.com/corporate/features/jsoup-html-parsing-library.html
    public String parseWeather(String htmlText) {
        Document document = Jsoup.parse(htmlText);
        Elements allElements = document.getAllElements();
        for (Element element: allElements) {
            if (element.ownText().contains("C") && !element.ownText().contains("%")) {
                return element.ownText();
            }
        }
        return "blank";
    }
}

