package org.jangbalzang.smartshoecase.led;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Led {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LedState state;

    public LedState state() {
        return state;
    }

    public void turnOn() {
        this.state = LedState.ON;
    }

    public void turnOff() {
        this.state = LedState.OFF;
    }

    public void turnBy(LedState state) {
        this.state = state;
    }
}
