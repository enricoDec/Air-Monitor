package htw.ai.akma.airmonitor.utils;

import htw.ai.akma.airmonitor.model.Co;
import htw.ai.akma.airmonitor.repo.CoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author : Enrico Gamil Toros
 * Project name : air-monitor
 * @version : 1.0
 * @since : 05.04.21
 **/

@Component
public class CoRoutine {
    private static final int delay = 600000; //every 10min = 144 per day
//    private static final int delay = 1000;
    private final CoRepository coRepository;

    public CoRoutine(CoRepository coRepository) {
        this.coRepository = coRepository;
    }

    @Scheduled(fixedRate = delay)
    public void getCo() {
        //Sensor 0
        final String uri = "http://192.168.2.222/gas";
        final int sensorId = 0;

        RestTemplate restTemplate = new RestTemplate();
        float result = Float.parseFloat(Objects.requireNonNull(restTemplate.getForObject(uri, String.class)));
        //Round to 2 decimals
        result = (float) (Math.round(result * 100.0) / 100.0);

        Co co = new Co();
        co.setPpm(result);
        co.setSensorId(sensorId);
        co.setTimestamp(new Timestamp(System.currentTimeMillis()));
        coRepository.save(co);
    }
}
