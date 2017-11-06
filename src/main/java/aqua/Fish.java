package aqua;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class Fish extends DBParser {
    public Fish() throws FileNotFoundException, UnsupportedEncodingException {
    }

    private String fishID;
    private String fishName;
    private String fishNameLat;
    private String fishFamily;
    private String fishFamilyLat;
    private String fishArea;
    private String temperature;
    private String pH;
    private String dH;
    private String size;
    private String layer;

    private String getFishID() {
        return fishID;
    }

    private void setFishID(String fish) {
        this.fishID = String.valueOf(Integer.parseInt(fish.split("@", 0)[0].trim()));
    }

    public String getFishName() {
        return fishName;
    }

    private void setFishName(String fish) {
        this.fishName = fish.split("@", 0)[3].trim();
    }

    public String getFishNameLat() {
        return fishNameLat;
    }

    private void setFishNameLat(String fish) {
        this.fishNameLat = fish.split("@", 0)[4].trim();
    }

    public String getFishFamily() {
        return fishFamily;
    }

    private void setFishFamily(String fish) {
        this.fishFamily = fish.split("@", 0)[1].trim();
    }

    public String getFishFamilyLat() {
        return fishFamilyLat;
    }

    private void setFishFamilyLat(String fish) {
        this.fishFamilyLat = fish.split("@", 0)[2].trim();
    }

    public String getFishArea() {
        return fishArea;
    }

    private void setFishArea(String fish) {
        this.fishArea = fish.split("@", 0)[5].trim();
    }

    public String getTemperature() {
        return temperature;
    }
    public String getTemperatureC() {
        return temperatureC(temperature);
    }
    public String getTemperatureF() {
        return temperatureF(temperature);
    }

    private void setTemperature(String fish) {
        this.temperature = fish.split("@", 0)[6].replace("t", "").trim();
    }

    public String getpH() {
        return pH;
    }

    private void setpH(String fish) {
        this.pH = fish.split("@", 0)[7].trim().replace("pH", "").trim();
    }

    public String getdH() {
        return dH;
    }

    private void setdH(String fish) {
        this.dH = fish.split("@", 0)[8].trim().replace("dH", "").trim();
    }

    public String getSize() {
        return size;
    }

    private void setSize(String fish) {
        this.size = fish.split("@", 0)[9].replace(",", ".").trim();
    }

    public String getLayer() {
        return layer;
    }

    private void setLayer(String fish) {
        this.layer = fish.split("@", 0)[10].trim();
    }

    private HashMap fishes() throws UnsupportedEncodingException {
        HashMap<String, String> map = new HashMap<String, String>();
        ArrayList<String> list = fish();
        ArrayList<String> images = listFilesInFolder(folder);
        int photo = 0;

        for (String fish : list) {
            setFishID(fish);
            for (String imageName : images) {
                try {
                    photo = (Integer.parseInt(imageName.split("-", 0)[0]));
                } catch (NumberFormatException ignored) {
                }
                if (Integer.parseInt(getFishID()) == photo) {
                    map.put(imageName, getFishID());
                }
            }
        }
        return map;
    }

    void printPhotoList() throws UnsupportedEncodingException {
        ArrayList<String> images = listFilesInFolder(folder);
        int photo = 0;

        System.out.println("id: " + getFishID() + " name: " + getFishName());
        for (String imageName : images) {
            try {
                photo = (Integer.parseInt(imageName.split("-", 0)[0]));
            } catch (NumberFormatException ignored) {
            }
            if (Integer.parseInt(getFishID()) == photo) {
                System.out.println(imageName);
            }
        }
    }

    void setFish(int number) throws UnsupportedEncodingException {
        ArrayList<String> list = fish();
        Object fish = list.get(number-1);

        setFishID(String.valueOf(fish));
        setFishName(String.valueOf(fish));
        setFishNameLat(String.valueOf(fish));
        setFishFamily(String.valueOf(fish));
        setFishFamilyLat(String.valueOf(fish));
        setFishArea(String.valueOf(fish));
        setTemperature(String.valueOf(fish));
        setpH(String.valueOf(fish));
        setdH(String.valueOf(fish));
        setSize(String.valueOf(fish));
        setLayer(String.valueOf(fish));
    }
}

