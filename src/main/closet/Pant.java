package closet;

public class Pant implements ClothingItem {
    private String type;
    private String colour;
    private String brand;

    public Pant(String type, String colour, String brand) {
        this.type = type;
        this.colour = colour;
        this.brand = brand;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getColour() {
        return colour;
    }

    public String getBrand() {
        return brand;
    }
}
