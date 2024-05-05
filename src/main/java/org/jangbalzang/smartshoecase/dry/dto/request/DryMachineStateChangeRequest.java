package org.jangbalzang.smartshoecase.dry.dto.request;

import org.jangbalzang.smartshoecase.dry.DryMachineState;

public record DryMachineStateChangeRequest(
    DryMachineState state
) {
}
