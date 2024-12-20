import org.example.dto.CarDTO;
import org.example.dto.CarModelDTO;
import org.example.dto.CarState;
import org.example.dto.DealerCenter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DealerCenterTest {

    private DealerCenter dealerCenter;
    private List<CarDTO> carList;
    private CarModelDTO model;

    @BeforeEach
    void setUp() {
        model = new CarModelDTO(1, "Toyota", "Camry", "US", "Japan");
        carList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            carList.add(new CarDTO(i, model, null, CarState.В_НАЛИЧИИ, "Standard", "Red", new BigDecimal(20000)));
        }
        List<CarDTO> showroomCars = new ArrayList<>();
        dealerCenter = new DealerCenter(1, "Best Cars", carList, showroomCars);
    }

    @Test
    void testSingleThreadProcessing() {
        long startTime = System.nanoTime();
        processCarsSingleThread(dealerCenter);
        long endTime = System.nanoTime();

        assertEquals(10000, dealerCenter.getShowroomCars().size());
        System.out.println("Single thread processing time: " + (endTime - startTime) + " nanoseconds");
    }

    @Test
    void testMultiThreadProcessing() throws InterruptedException {
        long startTime = System.nanoTime();
        processCarsMultiThread(dealerCenter);
        long endTime = System.nanoTime();

        assertEquals(dealerCenter.getShowroomCars().size(), dealerCenter.getShowroomCars().size());
        System.out.println("Multi-thread processing time: " + (endTime - startTime) + " nanoseconds");
    }

    private static void processCarsSingleThread(DealerCenter dealerCenter) {
        for (CarDTO car : dealerCenter.getCars()) {
            if ("Red".equals(car.getColor())) {
                dealerCenter.getShowroomCars().add(car);
            }
        }
    }

    private static void processCarsMultiThread(DealerCenter dealerCenter) throws InterruptedException {
        int numThreads = 4;
        int carsPerThread = dealerCenter.getCars().size() / numThreads;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            final int startIndex = i * carsPerThread;
            final int endIndex = (i == numThreads - 1) ? dealerCenter.getCars().size() : (i + 1) * carsPerThread;
            tasks.add(() -> {
                for (int j = startIndex; j < endIndex; j++) {
                    CarDTO car = dealerCenter.getCars().get(j);
                    if ("Red".equals(car.getColor())) {
                        dealerCenter.getShowroomCars().add(car);
                    }
                }
                return null;
            });
        }
        executorService.invokeAll(tasks);
        executorService.shutdown();
    }
}
