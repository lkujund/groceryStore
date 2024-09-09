package hr.grocery.store.grocerystore.service;

import hr.grocery.store.grocerystore.model.Log;
import hr.grocery.store.grocerystore.repository.SpringDataJpaLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService{

    private SpringDataJpaLogRepository logRepository;

    @Override
    public List<Log> getLogs() {
        return logRepository.findAll();
    }
}
