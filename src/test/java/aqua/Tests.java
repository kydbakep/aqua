package aqua;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

class Tests extends DBParser{
    Tests() throws FileNotFoundException, UnsupportedEncodingException {
    }

    @Test
    void testRemovingZeros() {
        System.out.println(removeZeros(25.0));
    }

    @Test
    void testParseFish() throws IOException {
        printAllFirstRows();
    }

    @Test
    void testClass() throws FileNotFoundException, UnsupportedEncodingException {
        Fish fish = new Fish();
        fish.setFish(14);
        fish.printPhotoList();
        System.out.println(fish.temperatureC(fish.getTemperature()));
        System.out.println(fish.temperatureF(fish.getTemperature()));

        fish.setFish(2);
        fish.printPhotoList();

        fish.setFish(5);
        fish.printPhotoList();
    }
}
