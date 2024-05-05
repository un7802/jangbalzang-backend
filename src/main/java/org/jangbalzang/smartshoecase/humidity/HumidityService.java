package org.jangbalzang.smartshoecase.humidity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumidityService {

    private final HumidityRepository humidityRepository;

    @Autowired
    public HumidityService(HumidityRepository humidityRepository) {
        this.humidityRepository = humidityRepository;
    }

    public void saveHumidity(Float humidity) {
        Humidity newHumidityEntity = Humidity.from(humidity);
        humidityRepository.save(newHumidityEntity);
    }

    /**
     * 설정한 습도보다 높은지 체크하는 함수 입니다.
     *
     * @param currentHumidity 현재 습도 입니다.
     *
     * @return 현재 설정된 습도보다 높다면 HIGH를 반환합니다.
     * */
    public HumidityType checkHumidity(Float currentHumidity) {
        List<Humidity> humidities = humidityRepository.findAll();
        if (humidities.isEmpty()) {
            throw new RuntimeException("Humidity's not set.");
        }

        return humidities.get(0).humidity() < currentHumidity ?
                HumidityType.HIGHER : HumidityType.LOWER;
    }
}
