package edu.hs.bremen.mocks.impl;

import edu.hs.bremen.mocks.IUserMicroserviceMock;

/**
 * Object to mock behaviour of user micro service
 * to authenticate user
 */
public class UserMicroserviceMock implements IUserMicroserviceMock {

    /**
     * Checks if user is valid and authenticates user.
     * No real communication to service => Mock.
     * @return valid or not
     */
    @Override
    public boolean validateUser(String userID) {
        // Add communication to user micro service here for user validation
        return Boolean.TRUE;
    }
}