package org.jangbalzang.smartshoecase.humidity;

import org.jangbalzang.smartshoecase.humidity.dto.request.HumiditySaveRequest;
import org.jangbalzang.smartshoecase.humidity.dto.response.HumidityStateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/humidity")
public class HumidityController {

    private final HumidityService humidityService;

    @Autowired
    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveHumidity(@RequestBody HumiditySaveRequest saveRequest) {
        humidityService.saveHumidity(saveRequest.humidity());
    }

    @GetMapping("/state")
    public HumidityStateResponse checkHumidity(@RequestParam("humidity") Float humidity) {
        HumidityType currentState = humidityService.checkHumidity(humidity);

        return new HumidityStateResponse(currentState, humidity);
    }
}
