
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weather.TodayWeather;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodayWeatherTest {
    private TodayWeather todayWeather;

    @BeforeEach
    public void setup() {
        todayWeather = new TodayWeather("10");
    }

    @Test
    public void getTempTest() {
        assertEquals(10, todayWeather.getTemp());
    }
}
