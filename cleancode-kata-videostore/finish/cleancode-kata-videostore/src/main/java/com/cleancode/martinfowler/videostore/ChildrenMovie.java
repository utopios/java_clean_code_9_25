package com.cleancode.martinfowler.videostore;

public class ChildrenMovie extends Movie {
    public ChildrenMovie(String title) {
        super(title);
    }

    @Override
    public double determineAmount(int daysRented) {
        double rentalAmount = 1.5; //Normalement avec des const
        if (daysRented > 3)
            rentalAmount += (daysRented - 3) * 1.5;
        return rentalAmount;
    }

    @Override
    public int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
