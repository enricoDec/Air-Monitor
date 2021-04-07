package htw.ai.akma.airmonitor.repo;

import htw.ai.akma.airmonitor.model.Co;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Enrico Gamil Toros
 * Project name : air-monitor
 * @version : 1.0
 * @since : 04.04.21
 **/
@Repository
public interface CoRepository extends CrudRepository<Co, Long> {
    @Query(value = "SELECT * FROM esp8266.public.co", nativeQuery = true)
    List<Co> getAllCo();

    @Query(value = "SELECT last_value FROM esp8266.public.co_co_id_seq", nativeQuery = true)
    int getLastSerial();

    @Query(value = "SELECT * FROM esp8266.public.co WHERE co_id BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Co> getAllByCoIdBetween(int low, int high);
}