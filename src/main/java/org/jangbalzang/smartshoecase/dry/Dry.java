package org.jangbalzang.smartshoecase.dry;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DryMachineState dryMachineState;

    private Integer dryMachineBehaviorTime;

    private Dry(DryMachineState dryMachineState, Integer dryMachineBehaviorTime) {
        this.dryMachineState = dryMachineState;
        this.dryMachineBehaviorTime = dryMachineBehaviorTime;
    }

    public DryState to() {
        return new DryState(dryMachineState, dryMachineBehaviorTime);
    }
}
