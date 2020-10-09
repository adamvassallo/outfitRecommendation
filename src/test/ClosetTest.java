import closet.ClothingItem;
import closet.Jacket;
import closet.Pant;
import closet.Shirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosetTest {
    private ClothingItem jacket;
    private ClothingItem pant;
    private ClothingItem shirt;

    @BeforeEach
    public void setup() {
        jacket = new Jacket("Jacket", "Black", "Nike");
        pant = new Pant("Pant", "Black", "Guess");
        shirt = new Shirt("Shirt", "Black", "Raptors");
    }

    @Test
    public void getTypeTest() {
        assertEquals("Jacket", jacket.getType());
        assertEquals("Pant", pant.getType());
        assertEquals("Shirt", shirt.getType());
    }

    @Test
    public void getColourTest() {
        assertEquals("Black", jacket.getColour());
        assertEquals("Black", pant.getColour());
        assertEquals("Black", shirt.getColour());
    }

    @Test
    public void getBrandTest() {
        assertEquals("Nike", jacket.getBrand());
        assertEquals("Guess", pant.getBrand());
        assertEquals("Raptors", shirt.getBrand());
    }
}
