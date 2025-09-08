package org.example.correction.kata1final;

class PrimeGenerator {
    private final int[] primes;
    private final int[] multiples;
    private int currentNumber;
    private int primeCount;
    private int order;
    private int square;

    public PrimeGenerator(int maxPrimes, int maxPrimeFactors) {
        primes = new int[maxPrimes + 1];
        multiples = new int[maxPrimeFactors + 1];
        currentNumber = 1;
        primeCount = 1;
        primes[1] = 2;
        order = 2;
        square = 9;
    }

    public int[] generatePrimes(int maxPrimes) {
        while (primeCount < maxPrimes) {
            currentNumber = generateNextPrime();
            primeCount++;
            primes[primeCount] = currentNumber;
        }

        return primes;
    }

    private int generateNextPrime() {
        boolean isPrime;
        do {
            currentNumber += 2;
            updateMultiples();
            isPrime = isPrime(currentNumber);
        } while (!isPrime);

        return currentNumber;
    }

    private boolean isPrime(int number) {
        for (int n = 2; n < order; n++) {
            while (multiples[n] < number) {
                multiples[n] += primes[n] + primes[n];
            }
            if (multiples[n] == number) {
                return false;
            }
        }
        return true;
    }

    private void updateMultiples() {
        if (currentNumber == square) {
            order++;
            square = primes[order] * primes[order];
            multiples[order - 1] = currentNumber;
        }
    }
}