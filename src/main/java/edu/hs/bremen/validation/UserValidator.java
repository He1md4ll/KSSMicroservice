package edu.hs.bremen.validation;

import com.google.common.base.Strings;

import javax.validation.ValidationException;

public class UserValidator {

    public static Boolean validateUserId(String userId) {
        if (!Strings.isNullOrEmpty(userId)) {
            return Boolean.TRUE;
        } else {
            throw new ValidationException("User-ID invalid !");
        }
    }
}