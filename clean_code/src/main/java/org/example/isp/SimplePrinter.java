interface Machine {
    void print(Document doc);
    void fax(Document doc);
    void scan(Document doc);
}

class Document {
    // Document implementation
}

class SimplePrinter implements Machine {
    @Override
    public void print(Document doc) {
        // Implementation for printing
    }

    @Override
    public void fax(Document doc) {
        throw new UnsupportedOperationException("Fax not supported");
    }

    @Override
    public void scan(Document doc) {
        throw new UnsupportedOperationException("Scan not supported");
    }
}