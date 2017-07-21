package edu.hs.bremen.mocks;

import edu.hs.bremen.model.dto.BasketEntryDto;

@FunctionalInterface
public interface IRessourcePlaningMicroserviceMock {
    boolean checkAvailability(BasketEntryDto basketEntryDto);
}