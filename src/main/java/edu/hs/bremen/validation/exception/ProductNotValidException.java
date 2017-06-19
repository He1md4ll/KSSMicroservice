package edu.hs.bremen.validation.exception;

public class ProductNotValidException extends RuntimeException {

    private static final String MESSAGE = "Product does not exist. Product creation disabled by configuration...";

    public ProductNotValidException() {
        super(MESSAGE);
    }
}