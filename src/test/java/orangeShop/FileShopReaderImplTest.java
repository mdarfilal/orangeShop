package orangeShop;


import com.orange.shop.service.impl.FileShopReaderImpl;
import com.orange.shop.model.Line;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileShopReaderImplTest {

    @Test
    public void shouldReturnAllLinesWhenFileExists() {
        FileShopReaderImpl reader = new FileShopReaderImpl();
        reader.setNameFile("orange_shop.csv");
        final int allFileShopLines = 226;

        List<Line> allLines = reader.setAllLine();

        assertEquals(allFileShopLines, allLines.size());
    }

    @Test
    public void shouldSkipHeaderLinesWhenFileExists() {
        FileShopReaderImpl reader = new FileShopReaderImpl();
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
        FileShopReaderImpl reader = new FileShopReaderImpl();
        reader.setNameFile("toto.csv");
        assertThrows(IndexOutOfBoundsException.class, () -> reader.setAllLine());
    }
}
