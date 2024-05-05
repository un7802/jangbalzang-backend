package org.jangbalzang.smartshoecase.led.dto.request;

import org.jangbalzang.smartshoecase.led.LedState;

public record LedStateChangeRequest(
    LedState state
){ }
