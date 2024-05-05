package org.jangbalzang.smartshoecase.dry.dto.response;

import org.jangbalzang.smartshoecase.dry.DryMachineState;

import java.time.LocalDateTime;

public record DryMachineStateResponse(
    LocalDateTime dryEndTime,
    DryMachineState machineState
) {

    public static DryMachineStateResponse empty() {
        return of(null, DryMachineState.OFF);
    }

    public static DryMachineStateResponse of(LocalDateTime dryEndTime, DryMachineState dryMachineState) {
        return new DryMachineStateResponse(dryEndTime, dryMachineState);
    }
}
