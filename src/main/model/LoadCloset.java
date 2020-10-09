package model;

import closet.ClothingItem;
import closet.Jacket;
import closet.Pant;
import closet.Shirt;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadCloset implements Loadable, Saveable {
    ArrayList<ClothingItem> closetList;
    ArrayList<Jacket> jacketList;
    ArrayList<Shirt> shirtList;
    ArrayList<Pant> pantList;

    public LoadCloset() {
    }

    //EFFECTS: reads closet items from input file
    @Override
    public void load() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("./data/inputfile.txt"));
            closetList = new ArrayList<>();
            jacketList = new ArrayList<>();
            shirtList = new ArrayList<>();
            pantList = new ArrayList<>();

            for (String line : lines) {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                addToClosetList(partsOfLine);
            }
        } catch (Exception e) {
            System.out.print("File not found: inputfile ");
        }
    }

    //MODIFIES: this
    //EFFECTS: splits line of input at a comma
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    //MODIFIES: this
    //EFFECTS: saves closet items to output file
    @Override
    public void save() {
        try {
            PrintWriter writer = new PrintWriter("./data/outputfile.txt", "UTF-8");

            for (ClothingItem line : closetList) {
                writer.println(line.getType() + "," + line.getColour() + "," + line.getBrand());
            }
            writer.close();
        } catch (Exception e) {
            System.out.print("File not found: outputfile ");
        }
    }

    //EFFECTS: categorizes clothes by type, then sends clothing item to a method to be added to a list
    public void addToClosetList(ArrayList<String> newClothes) {
        if (newClothes.size() == 0) {
            return;
        } else if (newClothes.get(0).equals("Jacket")) {
            addJacketToClosetList(newClothes);
        } else if (newClothes.get(0).equals("Shirt")) {
            addShirtToClosetList(newClothes);
        } else if (newClothes.get(0).equals("Pant")) {
            addPantToClosetList(newClothes);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds Pant item to list of pants
    private void addPantToClosetList(ArrayList<String> newClothes) {
        Pant pant = new Pant(newClothes.get(0), newClothes.get(1), newClothes.get(2));
        pantList.add(pant);
        closetList.add(pant);
    }

    //MODIFIES: this
    //EFFECTS: adds Shirt item to list of shirts
    private void addShirtToClosetList(ArrayList<String> newClothes) {
        Shirt shirt = new Shirt(newClothes.get(0), newClothes.get(1), newClothes.get(2));
        shirtList.add(shirt);
        closetList.add(shirt);
    }

    //MODIFIES: this
    //EFFECTS: adds Jacket item to list of jackets
    private void addJacketToClosetList(ArrayList<String> newClothes) {
        Jacket jacket = new Jacket(newClothes.get(0), newClothes.get(1), newClothes.get(2));
        jacketList.add(jacket);
        closetList.add(jacket);
    }

    public ArrayList<ClothingItem> getClosetList() {
        return closetList;
    }

    public ArrayList<Jacket> getJacketList() {
        return jacketList;
    }

    public ArrayList<Shirt> getShirtList() {
        return shirtList;
    }

    public ArrayList<Pant> getPantList() {
        return pantList;
    }
}
