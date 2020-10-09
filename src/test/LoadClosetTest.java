import closet.ClothingItem;
import model.LoadCloset;
import model.Recommendation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weather.TodayWeather;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoadClosetTest {
    private ArrayList<String> newClothes;
    private Recommendation recommendation;
    private LoadCloset modifyCloset;
    private TodayWeather weatherLow;
    private TodayWeather weatherLowBoundary;
    private TodayWeather weatherLowerMid;
    private TodayWeather weatherMidBoundary;
    private TodayWeather weatherUpperMid;
    private TodayWeather weatherUpperBoundary;
    private TodayWeather weatherUpper;
    private TodayWeather weatherRainy;
    private TodayWeather weatherSunny;

    @BeforeEach
    public void setup() {
        recommendation = new Recommendation();
        modifyCloset = new LoadCloset();
        modifyCloset.load();
        newClothes = new ArrayList<>();
        weatherLow = new TodayWeather("1");
        weatherLowBoundary = new TodayWeather("5");
        weatherLowerMid = new TodayWeather("10");
        weatherMidBoundary = new TodayWeather("14");
        weatherUpperMid = new TodayWeather("16");
        weatherUpperBoundary = new TodayWeather("18");
        weatherUpper = new TodayWeather("20");
        weatherRainy = new TodayWeather("11");
        weatherSunny = new TodayWeather("15");
    }

    @Test
    public void topArticleTest() {
            String rec1 = recommendation.topArticle(weatherLow, modifyCloset);
            assertTrue(rec1.contains("Jacket"));
            String rec2 = recommendation.topArticle(weatherLowBoundary, modifyCloset);
            assertTrue(rec2.contains("Jacket"));
            String rec3 = recommendation.topArticle(weatherLowerMid, modifyCloset);
            assertTrue(rec3.contains("Jacket"));
            String rec4 = recommendation.topArticle(weatherMidBoundary, modifyCloset);
            assertTrue(rec4.contains("Shirt"));
            String rec5 = recommendation.topArticle(weatherUpperMid, modifyCloset);
            assertTrue(rec5.contains("Shirt"));
            String rec6 = recommendation.topArticle(weatherUpperBoundary, modifyCloset);
            assertTrue(rec6.contains("Shirt"));
            String rec7 = recommendation.topArticle(weatherUpper, modifyCloset);
            assertTrue(rec7.contains("Shirt"));
    }

    @Test
    public void bottomArticleTest() {
            String rec1 = recommendation.bottomArticle(weatherUpperMid, modifyCloset);
            assertTrue(rec1.contains("Pant"));
            String rec2 = recommendation.bottomArticle(weatherUpperBoundary, modifyCloset);
            assertTrue(rec2.contains("Pant"));
            String rec3 = recommendation.bottomArticle(weatherUpper, modifyCloset);
            assertTrue(rec3.contains("Pant"));
    }

    @Test
    public void loadTest() {
        modifyCloset.load();
        ArrayList<ClothingItem> closetList = modifyCloset.getClosetList();
        assertEquals("Jacket", closetList.get(0).getType());
        assertEquals("Brown", closetList.get(0).getColour());
    }

    @Test
    public void saveTest() {
        modifyCloset.load();
        modifyCloset.save();
        try {
            List<String> lines = Files.readAllLines(Paths.get("./data/outputfile.txt"));
            assertEquals("Jacket,Brown,Barbour", lines.get(0));
            assertEquals("Jacket,Black,J Lindeberg", lines.get(2));
        } catch (Exception e) {
            System.out.print("File not found: outputfile of test ");
        }
    }

    @Test
    public void addToClosetListTestJacket() {
        modifyCloset.load();
        newClothes.add("Jacket");
        newClothes.add("Black");
        newClothes.add("Nike");
        modifyCloset.addToClosetList(newClothes);
        ArrayList<ClothingItem> closetList = modifyCloset.getClosetList();
        assertEquals("Shirt", closetList.get(4).getType());
        assertEquals("Black", closetList.get(4).getColour());
        assertEquals("Raptors", closetList.get(4).getBrand());
    }

    @Test
    public void addToClosetListTestPant() {
        modifyCloset.load();
        newClothes.add("Pant");
        newClothes.add("Black");
        newClothes.add("Nike");
        modifyCloset.addToClosetList(newClothes);
        ArrayList<ClothingItem> closetList = modifyCloset.getClosetList();
        assertEquals("Shirt", closetList.get(4).getType());
        assertEquals("Black", closetList.get(4).getColour());
        assertEquals("Raptors", closetList.get(4).getBrand());
    }

    @Test
    public void addToClosetListTestShirt() {
        modifyCloset.load();
        newClothes.add("Shirt");
        newClothes.add("Black");
        newClothes.add("Nike");
        modifyCloset.addToClosetList(newClothes);
        ArrayList<ClothingItem> closetList = modifyCloset.getClosetList();
        assertEquals("Shirt", closetList.get(4).getType());
        assertEquals("Black", closetList.get(4).getColour());
        assertEquals("Raptors", closetList.get(4).getBrand());
    }

}

