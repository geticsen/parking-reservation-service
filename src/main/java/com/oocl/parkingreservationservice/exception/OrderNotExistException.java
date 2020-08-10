package com.oocl.parkingreservationservice.exception;

import com.oocl.parkingreservationservice.constants.MessageConstants;

public class OrderNotExistException extends Exception{
    @Override
    public String getMessage() {
        return MessageConstants.ODER_NOT_EXIST;
    }
}
