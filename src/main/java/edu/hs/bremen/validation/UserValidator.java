package edu.hs.bremen.validation;

import com.google.common.base.Strings;
import edu.hs.bremen.mocks.IUserMicroserviceMock;
import edu.hs.bremen.mocks.impl.UserMicroserviceMock;

import javax.validation.ValidationException;

public class UserValidator {

    private static IUserMicroserviceMock userMicroserviceMock = new UserMicroserviceMock();

    private UserValidator(){}

    // TODO: User validation could be extracted to request interceptor
    public static Boolean validateUserId(String userId) {
        if (!Strings.isNullOrEmpty(userId) && userMicroserviceMock.validateUser(userId)) {
            return Boolean.TRUE;
        } else {
            throw new ValidationException("User-ID invalid !");
        }
    }
}