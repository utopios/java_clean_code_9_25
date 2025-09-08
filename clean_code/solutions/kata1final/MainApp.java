package org.example.correction.kata1final;

public class MainApp {
    public static void main(String[] args){

        final int MAX_PRIMES = 1000;
        final int ROWS_PER_PAGE = 50;
        final int COLUMNS_PER_PAGE = 4;
        final int MAX_PRIME_FACTORS = 30;

        PrimeGenerator primeGenerator = new PrimeGenerator(MAX_PRIMES, MAX_PRIME_FACTORS);
        int[] primes = primeGenerator.generatePrimes(MAX_PRIMES);

        PrimePrinter primePrinter = new PrimePrinter(ROWS_PER_PAGE, COLUMNS_PER_PAGE);
        primePrinter.printPrimes(primes, MAX_PRIMES);
    }
}
