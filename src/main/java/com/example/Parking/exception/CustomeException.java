package com.example.Parking.exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@Data
public class CustomeException extends RuntimeException{

    private String message;
    private String elementConcerne;
    private String value;

    private HashMap<String,String> error;

    public CustomeException(HashMap<String,String> error, String elementConcerne) {
        this.error = error;
        this.elementConcerne = elementConcerne;
    }

    public CustomeException(String message) {
        this.message = message;
    }

    public CustomeException(String elementConcerne, String value) {
        this.elementConcerne = elementConcerne;
        this.value = value;
    }
}
