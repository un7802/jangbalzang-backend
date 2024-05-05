package org.jangbalzang.smartshoecase.dry;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DryTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dryEndTime;

    public void addDryEndTime(int behaviorTime) {
        LocalDateTime now = LocalDateTime.now();

        this.dryEndTime = now.plusMinutes(behaviorTime);
    }

    public void release() {
        this.dryEndTime = null;
    }

    public boolean isNotSet() {
        return dryEndTime == null;
    }

    public boolean isExpired() {
        return !isNotSet() && LocalDateTime.now().isAfter(dryEndTime);
    }

    public LocalDateTime dryEndTime() {
        return dryEndTime;
    }
}
