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

        System.out.println(fish.getFishName());
        System.out.println(fish.getTemperatureC());
        System.out.println(fish.getTemperatureF());
    }
}
