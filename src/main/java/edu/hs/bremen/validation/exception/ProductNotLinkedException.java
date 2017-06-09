package edu.hs.bremen.validation.exception;

public class ProductNotLinkedException extends RuntimeException {

    private static final String MESSAGE = "Order not linked to product. Cannot remove.";

    public ProductNotLinkedException() {
        super(MESSAGE);
    }
}