package htw.ai.akma.airmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AirMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirMonitorApplication.class, args);
    }

}
