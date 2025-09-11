package com.kata.parcmeter;


public class InvoicePrinter {
    public String print(Session legacySession, ParkingService service) {
        ParkingSession session = convertFromLegacy(legacySession);
        Price price = session.calculateCost(true);

        String vehicleType = session.getVehicleType().name();
        String weekendStatus = session.isWeekend() ? "WEEKEND" : "WEEKDAY";
        String promoStatus = session.getPromoCode().toString();

        return String.format("INVOICE[%s;%s;%s]=%s",
                vehicleType, weekendStatus, promoStatus, price);
    }

    public double getPrice(Session legacySession, ParkingService service) {
        return service.calculate(legacySession, true);
    }

    private ParkingSession convertFromLegacy(Session legacy) {
        VehicleType type = VehicleType.fromString(legacy.vehicleType);
        PromoCode promo = legacy.promo != null ? PromoCode.of(legacy.promo) : PromoCode.none();
        return new ParkingSession(type, legacy.in, legacy.out, promo, legacy.wknd);
    }
}
