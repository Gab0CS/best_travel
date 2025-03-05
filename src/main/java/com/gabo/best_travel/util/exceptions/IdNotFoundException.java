package com.gabo.best_travel.util.exceptions;

public class IdNotFoundException extends RuntimeException {
    
    private static final String ERROR_MESSAGE = "Record doesn't exists";

    public IdNotFoundException(String tableName){
        super(String.format(ERROR_MESSAGE, tableName));
    }
}
