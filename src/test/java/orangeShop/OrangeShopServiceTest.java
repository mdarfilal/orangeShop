package orangeShop;

import com.orange.shop.service.impl.OrangeShopFinderImpl;
import com.orange.shop.service.impl.OrangeShopServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import utils.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class OrangeShopServiceTest {

    @Mock
    OrangeShopFinderImpl shop;

    @InjectMocks
    OrangeShopServiceImpl shopService;

    @Test
    public void shouldReturnErrorMessageWhenNoShopAvailable() {
        when(shop.findOrangeShopWithMobileAvailable(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble(), ArgumentMatchers.anyString())).thenReturn(null);
        String result = shopService.findOrangeShopWithMobileAvailable(1.1, 1.1, "toto");
        assertEquals("Désolé, votre téléphone n'est disponible dans aucun magasin", result);
    }

    @Test
    public void shouldReturnShopAvailableWithCustomMessageWhenMobileAvailable() {
        when(shop.findOrangeShopWithMobileAvailable(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble(), ArgumentMatchers.anyString())).thenReturn("Vélizy");
        String result = shopService.findOrangeShopWithMobileAvailable(1.1, 1.1, "weiwei");
        assertEquals("Un téléphone est disponible dans le magasin : Vélizy", result);
    }
}
