package com.oocl.parkingreservationservice.exception;

import com.oocl.parkingreservationservice.constants.MessageConstants;

public class ParkingOrderException extends Exception {
    public ParkingOrderException(String message) {
        super(message);
    }
}
