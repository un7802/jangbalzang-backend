package org.jangbalzang.smartshoecase.humidity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Humidity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float humidity;

    private Humidity(Float humidity) {
        this.humidity = humidity;
    }

    public Float humidity() {
        return humidity;
    }

    public static Humidity from(Float humidity) {
        return new Humidity(humidity);
    }
}
