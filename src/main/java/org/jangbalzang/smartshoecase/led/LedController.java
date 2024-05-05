package org.jangbalzang.smartshoecase.led;

import org.jangbalzang.smartshoecase.led.dto.request.LedStateChangeRequest;
import org.jangbalzang.smartshoecase.led.dto.response.LedStateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/led")
public class LedController {

    private final LedService ledService;

    @Autowired
    public LedController(LedService ledService) {
        this.ledService = ledService;
    }

    @PostMapping
    public LedStateResponse changeState(@RequestBody LedStateChangeRequest request) {
        return ledService.changeState(request.state());
    }

    @GetMapping
    public LedStateResponse getCurrentState() {
        return ledService.getCurrentLedState();
    }
}
