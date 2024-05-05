package org.jangbalzang.smartshoecase.dry;

public record DryState(
   DryMachineState dryMachineState,
   Integer dryMachineBehaviorTime
) {}
