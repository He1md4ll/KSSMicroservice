package edu.hs.bremen.validation;

import com.google.common.base.Strings;

public class UserValidator {
    public static Boolean isUserValid(String userId) {
        return !Strings.isNullOrEmpty(userId);
    }
}