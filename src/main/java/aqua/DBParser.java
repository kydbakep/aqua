package aqua;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class DBParser {
    DBParser() throws FileNotFoundException, UnsupportedEncodingException {
    }

    final File folder = new File("./storage/Photos");

    private Scanner getScanner()  {
        try {
            FileInputStream dataBaseInputStream = new FileInputStream("./src/main/resources/db-utf.txt");
            DataInputStream dataInputStream = new DataInputStream(dataBaseInputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(dataInputStream, "UTF-8"));
            return new Scanner(reader);
        } catch (UnsupportedEncodingException ex){
            System.out.println(ex.getMessage());
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> fish() throws UnsupportedEncodingException {
        Scanner scanner = getScanner();
        ArrayList<String> list = new ArrayList<String>();
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
        return list;
    }

    public ArrayList<String> listFilesInFolder(final File folder) {
        ArrayList<String> list = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesInFolder(fileEntry);
            } else {
                list.add(fileEntry.getName());
            }
        }
        return list;
    }

    void printAllFirstRows() throws IOException {
        ArrayList<String> list = fish();
        ArrayList<String> images = listFilesInFolder(folder);

        int fishNumber;
        int photo = 0;

        for (String unit : list) {
            String fishID = (unit
                    .split("@", 0)[0])
                    .trim();
            String fishName = (unit
                    .split("@", 0)[3])
                    .trim();
            String fishFamily = (unit
                    .split("@", 0)[1])
                    .trim();
            String fishFamilyLat = (unit
                    .split("@", 0)[2])
                    .trim();
            String fishNameLat = (unit
                    .split("@", 0)[4])
                    .trim();
            String fishArea = (unit
                    .split("@", 0)[5])
                    .trim();
            String temperature = (unit
                    .split("@", 0)[6])
                    .replace("t", "")
                    .trim();
            String pH = (unit
                    .split("@", 0)[7])
                    .trim()
                    .replace("pH", "")
                    .trim();
            String dH = (unit
                    .split("@", 0)[8])
                    .trim().replace("dH", "")
                    .trim();
            String size = (unit
                    .split("@", 0)[9])
                    .replace(",", ".")
                    .trim();
            String layer = (unit
                    .split("@", 0)[10])
                    .trim();

            fishNumber = (Integer.parseInt(fishID));
//            System.out.println(unit);
            System.out.println("id: " + fishNumber
                    + " * familyCyr: " + fishFamily
                    + " * familyLat: " + fishFamilyLat
                    + " * nameCyr: " + fishName
                    + " * nameLat: " + fishNameLat
                    + " * fishArea: " + fishArea
                    + " * T, C: " + temperatureC(temperature)
                    + " * T, F: " + temperatureF(temperature)
                    + " * pH: " + pH(pH)
                    + " * dH: " + dH(dH)
                    + " * size: " + size(size)
                    + " * layer(s): " + layer(layer)
            );

            for (String imageName : images) {
                try {
                    photo = (Integer.parseInt(imageName.split("-", 0)[0]));
                } catch (NumberFormatException ignored) {
                }
                if (fishNumber == photo) {
                    System.out.println(imageName);
                }
            }
        }
    }

    public ArrayList<Object> getPhotos(int number) throws UnsupportedEncodingException {
        int photo = number;
        ArrayList<String> list = fish();
        ArrayList<String> images = listFilesInFolder(folder);
        String unit = String.valueOf(list.get(number-1));

        String fishID = (unit
                .split("@", 0)[0])
                .trim();
        String fishName = (unit
                .split("@", 0)[3])
                .trim();
        String fishFamily = (unit
                .split("@", 0)[1])
                .trim();
        String fishFamilyLat = (unit
                .split("@", 0)[2])
                .trim();
        String fishNameLat = (unit
                .split("@", 0)[4])
                .trim();
        String fishArea = (unit
                .split("@", 0)[5])
                .trim();
        String temperature = (unit
                .split("@", 0)[6])
                .replace("t", "")
                .trim();
        String pH = (unit
                .split("@", 0)[7])
                .trim()
                .replace("pH", "")
                .trim();
        String dH = (unit
                .split("@", 0)[8])
                .trim().replace("dH", "")
                .trim();
        String size = (unit
                .split("@", 0)[9])
                .replace(",", ".")
                .trim();
        String layer = (unit
                .split("@", 0)[10])
                .trim();

        System.out.println("ID: " + fishID + "; NAME: " + fishName);

        ArrayList<Object> arrayList = new ArrayList<Object>();

        for (Object imageName : images) {
            try {
                photo = (Integer.parseInt(imageName.toString().split("-", 0)[0]));
            } catch (NumberFormatException ignored) {
            }
            if (number == photo) {
                arrayList.add(imageName);
            }
        }
        return arrayList;
    }

//    ------------------------------------------------------------------------------------------------------------------

    String removeZeros(Double a) {
        int x;
        if (a % 1 == 0) {
            x = a.intValue();
            return String.valueOf(x);
        } else {
            return String.valueOf(a);
        }
    }

    String temperatureC(String temperature) {
        // Temperature (min, max)
        Double minC = 0.0;
        Double maxC;
        try {
            minC = (Double.parseDouble(temperature
                    .split("-")[0]));
            maxC = (Double.parseDouble(temperature
                    .split("-")[1]));
        } catch (ArrayIndexOutOfBoundsException outOfBound) {
            maxC = (Double.parseDouble(temperature
                    .split("-")[0]));
        }

        if (minC.equals(maxC)) {
            return removeZeros(minC);
        } else return removeZeros(minC) + "-" + removeZeros(maxC);
    }

    String temperatureF(String temperature) {
        // Temperature (min, max)
        Double minF = 0.0;
        Double maxF;
        try {
            minF = (Double.parseDouble(temperature
                    .split("-")[0]));
            maxF = (Double.parseDouble(temperature
                    .split("-")[1]));
        } catch (ArrayIndexOutOfBoundsException outOfBound) {
            maxF = (Double.parseDouble(temperature
                    .split("-")[0]));
        }
        if (minF.equals(maxF)) {
            minF = minF * 9 / 5 + 32;
            return removeZeros(minF);
        } else {
            minF = minF * 9 / 5 + 32;
            maxF = maxF * 9 / 5 + 32;
            return removeZeros(minF) + "-" + removeZeros(maxF);
        }
    }

    private String pH(String pH) {
        // pH (min, max)
        double pHmin = 0;
        double pHmax;
        try {
            pHmin = (Double.parseDouble(pH
                    .replace(",", ".")
                    .replace("Б", ".")
                    .replace("?", ".")
                    .split("-")[0]));
            pHmax = (Double.parseDouble(pH
                    .replace(",", ".")
                    .replace("Б", ".")
                    .replace("?", ".")
                    .split("-")[1]));
        } catch (ArrayIndexOutOfBoundsException outOfBound) {
            pHmax = (Double.parseDouble(pH
                    .replace(",", ".")
                    .replace("Б", ".")
                    .replace("?", ".")
                    .split("-")[0]));
        }
        if (pHmin == pHmax) {
            return removeZeros(pHmin);
        } else return removeZeros(pHmin) + "-" + removeZeros(pHmax);
    }

    private String dH(String dH) {
        // dH (min, max)
        Double dHmin = 0.0;
        Double dHmax;
        try {
            dHmin = (Double.parseDouble(dH
                    .replace(",", ".")
                    .replace("Б", ".")
                    .replace("?", ".")
                    .split("-")[0]));
            dHmax = (Double.parseDouble(dH
                    .replace(",", ".")
                    .replace("Б", ".")
                    .replace("?", ".")
                    .split("-")[1]));
        } catch (ArrayIndexOutOfBoundsException outOfBound) {
            dHmax = (Double.parseDouble(dH
                    .replace(",", ".")
                    .replace("Б", ".")
                    .replace("?", ".")
                    .split("-")[0]));
        }
        if (dHmin.equals(dHmax)) {
            return removeZeros(dHmin);
        } else return removeZeros(dHmin) + "-" + removeZeros(dHmax);
    }

    private String size(String size) {
        //Size
        double minSize = 0;
        double maxSize;
        try {
            minSize = Double.parseDouble(size.split("-")[0]);
            maxSize = Double.parseDouble(size.split("-")[1]);
        } catch (Throwable throwable) {
            maxSize = Double.parseDouble(size.split("-")[0]);
        }
        if (minSize == maxSize) {
            return removeZeros(minSize);
        } else return removeZeros(minSize) + "-" + removeZeros(maxSize);
    }

    private String layer(String layer) {
        String layers = "";

        boolean layerUp = layer.split("-")[0].equals(String.valueOf(1));
        boolean layerMiddle = layer.split("-")[1].equals(String.valueOf(1));
        boolean layerDown = layer.split("-")[2].equals(String.valueOf(1));

        if (layerUp) layers += "up ";
        if (layerMiddle) layers += "middle ";
        if (layerDown) layers += "down ";
        return layers;
    }
}


// ПЕРЕВІРКА НА МАКСИМАЛЬНУ ДОВЖИНУ РЯДКА (ДЛЯ БД)======================================================================
//        int maxSize = 0;
//        for (String size : sizes) {
//            int sz = size.length();
//            if (sz >= maxSize) {
//                maxSize = sz;
//            }
//        }
//        System.out.println("\nmaxFamilySize: " + maxSize);

//        System.out.println(tCs);
//        for (int i = 0; i < tCs.size(); i++) {
//            int min = 0;
//            int max;
//            try {
//                min = Integer.parseInt(tCs.get(i).split("-")[0]);
//                max = Integer.parseInt(tCs.get(i).split("-")[1]);
//            } catch (ArrayIndexOutOfBoundsException outOfBound) {
//                max = Integer.parseInt(tCs.get(i).split("-")[0]);
//            }
//            System.out.println("MIN: " + min);
//            System.out.println("MAX: " + max);
//        }
// END OF 'ПЕРЕВІРКА НА МАКСИМАЛЬНУ ДОВЖИНУ РЯДКА (ДЛЯ БД)'=============================================================
