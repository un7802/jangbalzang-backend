package org.jangbalzang.smartshoecase.led;

import jakarta.transaction.Transactional;
import org.jangbalzang.smartshoecase.led.dto.response.LedStateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LedService {

    private final LedRepository ledRepository;

    @Autowired
    public LedService(LedRepository ledRepository) {
        this.ledRepository = ledRepository;
    }

    @Transactional
    public LedStateResponse changeState(LedState ledState) {
        Led led = ledRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Led State not set."));
        led.turnBy(ledState);

        return new LedStateResponse(led.state());
    }

    public LedStateResponse getCurrentLedState() {
        return ledRepository.findById(1L)
            .map(v -> new LedStateResponse(v.state()))
            .orElseThrow(() -> new RuntimeException("Led State not set."));
    }
}
