package edu.hs.bremen.mocks;

@FunctionalInterface
public interface IUserMicroserviceMock {
    boolean validateUser(String userID);
}