import org.example.dto.CarModelDTO;
import org.example.service.CarService;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private final CarService carService = new CarService();

    @Test
    void testGetUniqueBrands() {
        Set<String> uniqueBrands = carService.getUniqueBrands();
        assertNotNull(uniqueBrands);
        assertEquals(3, uniqueBrands.size());
        assertTrue(uniqueBrands.contains("Toyota"));
        assertTrue(uniqueBrands.contains("Honda"));
        assertTrue(uniqueBrands.contains("Ford"));
    }

    @Test
    void testGetCarsByBrand() {
        List<CarModelDTO> toyotaCars = carService.getCarsByBrand("Toyota");
        assertNotNull(toyotaCars);
        assertEquals(3, toyotaCars.size());
        assertTrue(toyotaCars.stream().allMatch(car -> "Toyota".equalsIgnoreCase(car.getBrand())));

        List<CarModelDTO> fordCars = carService.getCarsByBrand("Ford");
        assertEquals(1, fordCars.size());
    }

    @Test
    void testGroupCarsByBrand() {
        Map<String, Integer> carGroups = carService.groupCarsByBrand();
        assertNotNull(carGroups);
        assertEquals(3, carGroups.size());
        assertEquals(3, carGroups.get("Toyota"));
        assertEquals(2, carGroups.get("Honda"));
        assertEquals(1, carGroups.get("Ford"));
    }
}