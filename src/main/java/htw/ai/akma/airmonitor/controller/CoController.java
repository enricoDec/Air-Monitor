package htw.ai.akma.airmonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Enrico Gamil Toros
 * Project name : air-monitor
 * @version : 1.0
 * @since : 06.04.21
 **/
@Controller
public class CoController {

    @GetMapping("/live")
    public String liveChart() {
        return "live";
    }

    @GetMapping("/history")
    public String historyChart() {
        return "history";
    }
}
