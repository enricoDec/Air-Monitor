package htw.ai.akma.airmonitor.controller;

import htw.ai.akma.airmonitor.model.Co;
import htw.ai.akma.airmonitor.repo.CoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : Enrico Gamil Toros
 * Project name : air-monitor
 * @version : 1.0
 * @since : 04.04.21
 **/

@RestController
@RequestMapping("/co")
public class CoRestController {

    private final CoRepository coRepository;

    public CoRestController(CoRepository coRepository) {
        this.coRepository = coRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Co>> getCos(
            @RequestParam(value = "last") int amount
    ) {
        int lastId = coRepository.getLastSerial();
        int startId = lastId - amount + 1;
        if (startId < 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<Co> resultList = coRepository.getAllByCoIdBetween(startId, lastId);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping(value = "/live")
    public ResponseEntity<Float> getPpm() {
        final String uri = "http://192.168.2.223/temperature";
        RestTemplate restTemplate = new RestTemplate();
        float result = Float.parseFloat(Objects.requireNonNull(restTemplate.getForObject(uri, String.class)));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
