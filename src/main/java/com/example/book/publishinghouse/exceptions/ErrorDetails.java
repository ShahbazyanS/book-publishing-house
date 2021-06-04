package com.example.book.publishinghouse.exceptions;

public class ErrorDetails {
    private String messages;
    private String returnCode;

    public ErrorDetails(String messages, String returnCode) {
        this.messages = messages;
        this.returnCode = returnCode;
    }
}
