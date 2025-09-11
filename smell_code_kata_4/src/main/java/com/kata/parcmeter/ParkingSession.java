package com.kata.parcmeter;

import java.time.LocalDateTime;

public class ParkingSession {
    private static final Price DAILY_CAP = Price.of(18.00);

    private final VehicleType vehicleType;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final PromoCode promoCode;
    private final boolean isWeekend;

    public ParkingSession(VehicleType vehicleType, LocalDateTime startTime,
                          LocalDateTime endTime, PromoCode promoCode, boolean isWeekend) {
        this.vehicleType = vehicleType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.promoCode = promoCode;
        this.isWeekend = isWeekend;
    }

    public Price calculateCost(boolean applyPromo) {
        ParkingDuration duration = ParkingDuration.between(startTime, endTime);

        if (duration.isFree()) {
            return Price.zero();
        }

        Price basePrice = calculateBasePrice(duration);
        Price afterWeekendDiscount = applyWeekendDiscount(basePrice);
        Price afterPromoDiscount = applyPromoDiscount(afterWeekendDiscount, applyPromo);

        return afterPromoDiscount.min(DAILY_CAP);
    }

    private Price calculateBasePrice(ParkingDuration duration) {
        Price hourlyRate = vehicleType.getHourlyRate();
        int billableHours = duration.getBillableHours();
        return hourlyRate.multiply(billableHours);
    }

    private Price applyWeekendDiscount(Price price) {
        DiscountCalculator discount = isWeekend ? new WeekendDiscount() : new NoDiscount();
        return discount.apply(price);
    }

    private Price applyPromoDiscount(Price price, boolean applyPromo) {
        if (!applyPromo) return price;
        return promoCode.applyDiscount(price);
    }

    public VehicleType getVehicleType() { return vehicleType; }
    public PromoCode getPromoCode() { return promoCode; }
    public boolean isWeekend() { return isWeekend; }
}