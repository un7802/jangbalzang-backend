package org.jangbalzang.smartshoecase.humidity.dto.response;

import org.jangbalzang.smartshoecase.humidity.HumidityType;

public record HumidityStateResponse(
    HumidityType status,
    Float humidity
) { }
