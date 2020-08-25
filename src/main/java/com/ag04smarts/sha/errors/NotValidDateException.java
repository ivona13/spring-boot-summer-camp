package com.ag04smarts.sha.errors;

import java.text.ParseException;

public class NotValidDateException extends ParseException {

    public NotValidDateException(int errorOffset) {
        super("Date is not in appropriate form!", errorOffset);
    }
}
