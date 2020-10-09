package closet;

public class Jacket implements ClothingItem {
    private String type;
    private String colour;
    private String brand;

    public Jacket(String type, String colour, String brand) {
        this.type = type;
        this.colour = colour;
        this.brand = brand;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getColour() {
        return colour;
    }
}
