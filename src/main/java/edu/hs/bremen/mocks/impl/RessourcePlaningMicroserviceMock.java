package edu.hs.bremen.mocks.impl;

import edu.hs.bremen.mocks.IRessourcePlaningMicroserviceMock;
import edu.hs.bremen.model.dto.BasketEntryDto;
import org.springframework.stereotype.Component;

/**
 * Object to mock behaviour of resource planning micro service
 * to verify availability of product counts.
 */
@Component
public class RessourcePlaningMicroserviceMock implements IRessourcePlaningMicroserviceMock {

    /**
     * Checks if product is available.
     * No real communication to service => Mock.
     * @return available or not
     */
    @Override
    public boolean checkAvailability(BasketEntryDto basketEntryDto) {
        // Add communication to product micro service here for product validation
        return Boolean.TRUE;
    }
}