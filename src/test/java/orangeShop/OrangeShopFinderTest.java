package orangeShop;

import com.orange.shop.FileShopReader;
import com.orange.shop.Line;
import com.orange.shop.OrangeShopFinderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import utils.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class OrangeShopFinderTest {

    @Mock
    FileShopReader fileShopReader;

    @InjectMocks
    OrangeShopFinderImpl shop;

    double userLon;
    double userLat;

    @BeforeEach
    public void setup() {
        userLon = 1.1;
        userLat = 1.1;
    }

    @Test
    public void shouldReturnShopWithSunusngAvailable() {

        String userMobile = "sunusng";

        Line shopWithSunusng = new Line(1.1, 1.1, "First Shop", 1, 0, 0);
        Line shopWithoutSunusng = new Line(1.1, 1.1, "Second Shop", 0, 1, 1);
        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(shopWithSunusng, shopWithoutSunusng));

        String shopWithMobileAvailable = shop.findOrangeShopWithMobileAvailable(userLon, userLat, userMobile);

        assertEquals(shopWithSunusng.getShopDescription(), shopWithMobileAvailable);
    }

    @Test
    public void shouldReturnNewLineWhenMobileNotAvailable() {
        String userMobile = "sunusng";

        Line shopWithoutSunusng = new Line(1.1, 1.1, "Second Shop", 0, 1, 1);
        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(shopWithoutSunusng));

        String shopWithMobileAvailable = shop.findOrangeShopWithMobileAvailable(userLon, userLat, userMobile);

        Assertions.assertNull(shopWithMobileAvailable);
    }

    @Test
    public void shouldReturnShopWithIpomAvailable() {
        String userMobile = "ipom";

        Line shopWithIpom = new Line(1.1, 1.1, "First Shop", 0, 1, 1);
        Line shopWithoutIpom = new Line(1.1, 1.1, "Second Shop", 1, 0, 1);

        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(shopWithoutIpom, shopWithIpom));

        String shopWithMobileAvailable = shop.findOrangeShopWithMobileAvailable(userLon, userLat, userMobile);

        assertEquals(shopWithIpom.getShopDescription(), shopWithMobileAvailable);
    }

    @Test
    public void shouldReturnShopWithWeiweiAvailable() {
        String userMobile = "weiwei";

        Line shopWithWeiwei = new Line(1.1, 1.1, "First Shop", 0, 0, 1);
        Line shopWithoutWeiwei = new Line(1.1, 1.1, "Second Shop", 0, 0, 0);

        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(shopWithWeiwei, shopWithoutWeiwei));

        String shopWithMobileAvailable = shop.findOrangeShopWithMobileAvailable(userLon, userLat, userMobile);

        assertEquals(shopWithWeiwei.getShopDescription(), shopWithMobileAvailable);
    }

    @Test
    public void shouldReturnOrangeShopWhenOnlyOneAvailable() {
        String userMobile = "weiwei";

        Line shopWithWeiwei = new Line(1.1, 1.1, "First Shop", 0, 0, 1);

        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(shopWithWeiwei));

        String nearestShop = shop.findOrangeShopWithMobileAvailable(userLon, userLat, userMobile);

        assertEquals(shopWithWeiwei.getShopDescription(), nearestShop);
    }

    @Test
    public void shouldReturnShopDistanceGivenUserPosition() {
        userLon = -1.42988;
        userLat = 46.66976;
        // 2.1 km : https://fr.distance.to/46.66976,%201.42988/46.69141,%20-1.43024
        // 2.4 km : http://www.heclectics-pictures.com/Distance.php
        // User Position : -1.42988;46.66976;[Orange] 85 La Roche-sur-Yon (15 Rue Georges Clémenceau);3;3;2
        //-1.43024;46.69141;[Orange] 85 La Roche-sur-Yon (CC Les Flaneries);5;9;5

        double distanceBetweenUserAndShop = 2250;

        Line lesFlaneriesShop = new Line(-1.43024, 46.69141, "[Orange] 85 La Roche-sur-Yon (CC Les Flaneries)", 0, 0, 1);

        double distanceBetweenPoints = shop.findDistanceBetweenPoints(userLon, userLat, lesFlaneriesShop.getLongitude(), lesFlaneriesShop.getLatitude());

        assertEquals(distanceBetweenUserAndShop, distanceBetweenPoints, 200);
    }

    @Test
    public void shouldReturnNearestAvailableShopGivenUserPosition() {
        userLon = -1.42988;
        userLat = 46.66976;
        String userMobile = "weiwei";

        Line velizyShop = new Line(2.22137, 48.78192, "78 Vélizy-Villacoublay", 0, 0, 1);
        Line lesFlaneriesShop = new Line(-1.43024, 46.69141, "[Orange] 85 La Roche-sur-Yon (CC Les Flaneries)", 0, 0, 1);

        when(fileShopReader.setAllLine()).thenReturn(Arrays.asList(velizyShop, lesFlaneriesShop));

        String nearestShopWithMobileAvailable = shop.findOrangeShopWithMobileAvailable(userLon, userLat, userMobile);

        assertEquals(lesFlaneriesShop.getShopDescription(), nearestShopWithMobileAvailable);
    }
}
