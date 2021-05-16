package com.max.anuncios.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException (String msg) {
        super(msg);
    }
}
