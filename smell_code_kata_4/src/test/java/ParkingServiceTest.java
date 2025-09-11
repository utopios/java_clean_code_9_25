import com.kata.parcmeter.ParkingService;
import com.kata.parcmeter.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParkingServiceTest {

    private final ParkingService svc = new ParkingService();

    private Session s(String type, String promo, boolean wknd,
                      int y, int m, int d, int h1, int min1, int h2, int min2) {
        Session s = new Session();
        s.vehicleType = type;
        s.promo = promo;
        s.wknd = wknd;
        s.in = LocalDateTime.of(y, m, d, h1, min1);
        s.out = LocalDateTime.of(y, m, d, h2, min2);
        return s;
    }

    @Test
    void free_0_10_car_weekday() {
        var s = s("CAR", null, false, 2025, 1, 7, 10, 0, 10, 10);
        assertThat(svc.calculate(s, true)).isEqualTo(0.00);
    }

    @Test
    void motorbike_40min_one_hour_charged() {
        var s = s("MOTORBIKE", null, false, 2025, 1, 7, 10, 0, 10, 40);
        assertThat(svc.calculate(s, true)).isEqualTo(2.00);
    }

    @Test
    void truck_80min_two_hours() {
        var s = s("TRUCK", null, false, 2025, 1, 7, 10, 0, 11, 20);
        assertThat(svc.calculate(s, true)).isEqualTo(12.00);
    }

    @Test
    void car_190min_three_hours() {
        var s = s("CAR", null, false, 2025, 1, 7, 10, 0, 13, 10);
        assertThat(svc.calculate(s, true)).isEqualTo(9.00);
    }

    @Test
    void weekend_plus_promo() {
        var s = s("CAR", "PROMO10", true, 2025, 1, 4, 10, 0, 12, 1); // samedi
        assertThat(svc.calculate(s, true)).isEqualTo(4.86);
    }

    @Test
    void daily_cap_truck() {
        var s = s("TRUCK", null, false, 2025, 1, 7, 8, 0, 17, 45); // ~9h45
        assertThat(svc.calculate(s, true)).isEqualTo(18.00);
    }
}
