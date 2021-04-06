package htw.ai.akma.airmonitor.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author : Enrico Gamil Toros
 * Project name : air-monitor
 * @version : 1.0
 * @since : 04.04.21
 **/

@Entity
@Table(name = "co")
public class Co {

    @Id
    @Column(name = "co_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coId;

    @NotNull
    @Column(name = "ppm")
    private double ppm;

    @NotNull
    @Column(name = "timestamp")
    @jdk.jfr.Timestamp()
    private Timestamp timestamp;

    @NotNull
    @Column(name = "sensorId")
    private int sensorId;

    public Long getCoId() {
        return coId;
    }

    public void setCoId(Long concertId) {
        this.coId = concertId;
    }

    public double getPpm() {
        return ppm;
    }

    public void setPpm(double ppm) {
        this.ppm = ppm;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String toString() {
        return "Co{" +
                "coId=" + coId +
                ", ppm=" + ppm +
                ", timestamp=" + timestamp +
                ", sensorId=" + sensorId +
                '}';
    }
}
