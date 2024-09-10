package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.model.Log;
import hr.grocery.store.grocerystore.model.UserEvent;
import hr.grocery.store.grocerystore.repository.SpringDataJpaLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService{

    private SpringDataJpaLogRepository logRepository;

    @Override
    public List<Log> getLogs() {
        return logRepository.findAll();
    }

    public void logLogin(String name, String address, UserEvent event) {
        Log log = new Log();
        log.setName(name);
        log.setAddress(address);
        log.setLoginTs(Instant.now());
        log.setUserEvent(event);
        logRepository.save(log);
    }
}
