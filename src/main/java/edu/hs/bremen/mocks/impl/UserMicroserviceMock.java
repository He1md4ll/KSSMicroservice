package edu.hs.bremen.mocks.impl;

import edu.hs.bremen.mocks.IUserMicroserviceMock;

public class UserMicroserviceMock implements IUserMicroserviceMock {

    @Override
    public boolean validateUser(String userID) {
        // Add communication to user micro service here for user validation
        return Boolean.TRUE;
    }
}