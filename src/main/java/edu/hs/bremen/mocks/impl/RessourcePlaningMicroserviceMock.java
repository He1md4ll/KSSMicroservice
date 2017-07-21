package edu.hs.bremen.mocks.impl;

import edu.hs.bremen.mocks.IRessourcePlaningMicroserviceMock;
import edu.hs.bremen.model.dto.BasketEntryDto;
import org.springframework.stereotype.Component;

@Component
public class RessourcePlaningMicroserviceMock implements IRessourcePlaningMicroserviceMock {

    @Override
    public boolean checkAvailability(BasketEntryDto basketEntryDto) {
        // Add communication to product micro service here for product validation
        return Boolean.TRUE;
    }
}