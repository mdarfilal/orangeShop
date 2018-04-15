package orangeShop;

import com.orange.shop.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import utils.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrangeShopFinderTest {

    @Mock
    FileShopReader fileShopReader;

    @Test
    public void shouldReturnShopWithSunusngAvailable() {
        double userLon = 1.1;
        double userLat = 1.1;
        String userMobile = "sunusng";

        Line shopWithSunusng = new Line(1.1, 1.1, "First Shop", 1, 0, 0);
        Line shopWithoutSunusng = new Line(1.1, 1.1, "Second Shop", 0, 1, 1);
        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(shopWithSunusng, shopWithoutSunusng));

        OrangeShopFinder shop = new OrangeShopFinderImpl(fileShopReader);

        String shopWithMobileAvailable = shop.findOrangeShopWithMobileAvailable(userLat, userLon, userMobile);

        Assertions.assertEquals(shopWithSunusng.getShopDescription(), shopWithMobileAvailable);
    }

    @Test
    public void shouldReturnNewLineWhenMobileNotAvailable() {
        double userLon = 1.1;
        double userLat = 1.1;
        String userMobile = "sunusng";

        Line shopWithoutSunusng = new Line(1.1, 1.1, "Second Shop", 0, 1, 1);
        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(shopWithoutSunusng));

        OrangeShopFinder shop = new OrangeShopFinderImpl(fileShopReader);

        String shopWithMobileAvailable = shop.findOrangeShopWithMobileAvailable(userLat, userLon, userMobile);

        Assertions.assertNull(shopWithMobileAvailable);
    }

    @Test
    public void shouldReturnShopWithIpomAvailable() {
        double userLon = 1.1;
        double userLat = 1.1;
        String userMobile = "ipom";

        Line shopWithIpom = new Line(1.1, 1.1, "First Shop", 0, 1, 1);
        Line shopWithoutIpom = new Line(1.1, 1.1, "Second Shop", 1, 0, 1);

        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(shopWithoutIpom, shopWithIpom));

        OrangeShopFinder shop = new OrangeShopFinderImpl(fileShopReader);

        String shopWithMobileAvailable = shop.findOrangeShopWithMobileAvailable(userLat, userLon, userMobile);

        Assertions.assertEquals(shopWithIpom.getShopDescription(), shopWithMobileAvailable);
    }

    @Test
    public void shouldReturnShopWithWeiweiAvailable() {
        double userLon = 1.1;
        double userLat = 1.1;
        String userMobile = "weiwei";

        Line shopWithWeiwei = new Line(1.1, 1.1, "First Shop", 0, 0, 1);
        Line shopWithoutWeiwei = new Line(1.1, 1.1, "Second Shop", 0, 0, 0);

        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(shopWithWeiwei, shopWithoutWeiwei));

        OrangeShopFinder shop = new OrangeShopFinderImpl(fileShopReader);

        String shopWithMobileAvailable = shop.findOrangeShopWithMobileAvailable(userLat, userLon, userMobile);

        Assertions.assertEquals(shopWithWeiwei.getShopDescription(), shopWithMobileAvailable);
    }
}
