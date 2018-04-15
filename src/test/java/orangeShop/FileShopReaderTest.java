package orangeShop;


import com.orange.shop.FileShopReader;
import com.orange.shop.Line;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileShopReaderTest {

    @Test
    public void shouldReturnAllLinesWhenFileExists() {
        FileShopReader reader = new FileShopReader();
        reader.setNameFile("orange_shop.csv");
        final int allFileShopLines = 226;

        List<Line> allLines = reader.setAllLine();

        assertEquals(allFileShopLines, allLines.size());
    }

    @Test
    public void shouldSkipHeaderLinesWhenFileExists() {
        FileShopReader reader = new FileShopReader();
        reader.setNameFile("orange_shop.csv");
        List<Line> allLines = reader.setAllLine();

        assertAll("Headers",
                () -> assertNotEquals("longitude", allLines.get(0).getLongitude()),
                () -> assertNotEquals("latitude", allLines.get(0).getLatitude()),
                () -> assertNotEquals("shopDescription", allLines.get(0).getShopDescription()),
                () -> assertNotEquals("sunusng", allLines.get(0).getSunusng()),
                () -> assertNotEquals("ipom", allLines.get(0).getIpom()),
                () -> assertNotEquals("weiwei", allLines.get(0).getWeiwei()));

    }

    @Test
    public void shouldThrownAnExceptionWhenFileDoesntExist() {
        FileShopReader reader = new FileShopReader();
        reader.setNameFile("toto.csv");
        assertThrows(RuntimeException.class, () -> reader.setAllLine());
    }
}
