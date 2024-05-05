package org.jangbalzang.smartshoecase.dry;

import jakarta.transaction.Transactional;
import org.jangbalzang.smartshoecase.dry.dto.response.DryMachineStateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DryService {

    private final DryRepository dryRepository;
    private final DryTimeRepository dryTimeRepository;

    @Autowired
    public DryService(DryRepository dryRepository, DryTimeRepository dryTimeRepository) {
        this.dryRepository = dryRepository;
        this.dryTimeRepository = dryTimeRepository;
    }

    @Transactional
    public DryMachineStateResponse turnStateDryMachine(DryMachineState dryMachineState) {
        // 건조기 관련 설정이 되어있지 않은 경우: http://localhost:8080/h2-console 에서 dry 관련 설정 필요.
        Dry dry = dryRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Dry machine not set."));
        DryState dryState = dry.to();
        // 건조기 시간 데이터가 설정되어있지 않은 경우.
        DryTime dryTime = dryTimeRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Dry Time not set."));
        DryMachineState currentDryState = dryTime.isNotSet() || dryTime.isExpired() ? DryMachineState.OFF : DryMachineState.ON;
        // 현재 건조기 상태랑 똑같은 상태가 입력되었을 때 예외를 던진다.
        if (currentDryState == dryMachineState) {
            throw new IllegalArgumentException("It can't be set to the same state");
        }
        if (dryMachineState == DryMachineState.ON) {
            dryTime.addDryEndTime(dryState.dryMachineBehaviorTime());
        }
        else {
            dryTime.release();
        }

        return new DryMachineStateResponse(dryTime.dryEndTime(), dryMachineState);
    }

    @Transactional
    DryMachineStateResponse getOrReleaseWhenExpired() {
        DryTime dryTime = dryTimeRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Dry Time not set."));
        if (dryTime.isNotSet()) {
            return DryMachineStateResponse.empty();
        }
        if (dryTime.isExpired()) {
            dryTime.release();
            return DryMachineStateResponse.empty();
        }

        return DryMachineStateResponse.of(dryTime.dryEndTime(), DryMachineState.ON);
    }

    public DryMachineStateResponse getDryMachineState() {
        return getOrReleaseWhenExpired();
    }
}
