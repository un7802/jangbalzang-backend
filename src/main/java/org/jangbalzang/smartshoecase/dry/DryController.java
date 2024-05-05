package org.jangbalzang.smartshoecase.dry;

import org.jangbalzang.smartshoecase.dry.dto.request.DryMachineStateChangeRequest;
import org.jangbalzang.smartshoecase.dry.dto.response.DryMachineStateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dry")
public class DryController {

    private final DryService dryService;

    @Autowired
    public DryController(DryService dryService) {
        this.dryService = dryService;
    }

    @PostMapping("/turn")
    public DryMachineStateResponse changeMachineState(@RequestBody DryMachineStateChangeRequest request) {
        return dryService.turnStateDryMachine(request.state());
    }

    @GetMapping("/poll")
    public DryMachineStateResponse getMachineState() {
        return dryService.getDryMachineState();
    }
}
