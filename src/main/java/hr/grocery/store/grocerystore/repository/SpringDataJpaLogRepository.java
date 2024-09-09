package hr.grocery.store.grocerystore.repository;

import hr.grocery.store.grocerystore.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaLogRepository extends JpaRepository<Log, Integer> {

}