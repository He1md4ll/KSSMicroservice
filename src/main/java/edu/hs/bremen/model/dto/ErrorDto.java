package edu.hs.bremen.model.dto;

public class ErrorDto {
    private String errorKey;
    private String errorMessage;

    public ErrorDto(String errorKey, String errorMessage) {
        this.errorKey = errorKey;
        this.errorMessage = errorMessage;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}