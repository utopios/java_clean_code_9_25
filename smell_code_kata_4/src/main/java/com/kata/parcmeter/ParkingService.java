package com.kata.parcmeter;

public class ParkingService {

    public double calculate(Session legacySession, boolean applyPromo) {
        ParkingSession session = convertFromLegacy(legacySession);
        return session.calculateCost(applyPromo).asDouble();
    }

    private ParkingSession convertFromLegacy(Session legacy) {
        if (legacy == null) {
            return new ParkingSession(
                    VehicleType.CAR,
                    null, null,
                    PromoCode.none(),
                    false
            );
        }

        VehicleType type = VehicleType.fromString(legacy.vehicleType);
        PromoCode promo = legacy.promo != null ? PromoCode.of(legacy.promo) : PromoCode.none();

        return new ParkingSession(type, legacy.in, legacy.out, promo, legacy.wknd);
    }
}
