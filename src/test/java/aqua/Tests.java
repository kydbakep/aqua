package aqua;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Tests {
    public Tests() throws FileNotFoundException {
    }

    private DBParser dbParser = new DBParser();

    @Test
    public void read() throws IOException {
        dbParser.printFirstRow();
    }


    public String removeZeros(Double a) {
        int x;
        if (a % 1 == 0) {
            x = a.intValue();
            return String.valueOf(x);
        } else {
            return String.valueOf(a);
        }
    }

    @Test
    public void testRemovingZeros() {
        System.out.println(removeZeros(25.0));
    }

}
