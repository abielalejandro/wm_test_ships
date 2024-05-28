package com.rgarcia.w2m.exceptions;

public class SpaceShipNotFoundException extends RuntimeException {
    public SpaceShipNotFoundException() {
        super("SpaceShip not found");
    }

    public SpaceShipNotFoundException(String message) {
        super(message);
    }

    public SpaceShipNotFoundException(Throwable cause) {
        super(cause);
    }

    public SpaceShipNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }			
}
