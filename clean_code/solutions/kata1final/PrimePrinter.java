package org.example.correction.kata1final;

class PrimePrinter {
    private final int rowsPerPage;
    private final int columnsPerPage;

    public PrimePrinter(int rowsPerPage, int columnsPerPage) {
        this.rowsPerPage = rowsPerPage;
        this.columnsPerPage = columnsPerPage;
    }

    public void printPrimes(int[] primes, int maxPrimes) {
        int pageNumber = 1;
        int pageOffset = 1;

        while (pageOffset <= maxPrimes) {
            printPageHeader(pageNumber, maxPrimes);

            for (int rowOffset = pageOffset; rowOffset < pageOffset + rowsPerPage; rowOffset++) {
                for (int column = 0; column < columnsPerPage; column++) {
                    int index = rowOffset + column * rowsPerPage;
                    if (index <= maxPrimes) {
                        System.out.printf("%10d", primes[index]);
                    }
                }
                System.out.println();
            }

            pageNumber++;
            pageOffset += rowsPerPage * columnsPerPage;
        }
    }

    private void printPageHeader(int pageNumber, int maxPrimes) {
        System.out.println("The First " + maxPrimes + " Prime Numbers === Page " + pageNumber);
        System.out.println();
    }
}