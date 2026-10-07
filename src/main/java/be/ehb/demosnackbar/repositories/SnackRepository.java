package be.ehb.demosnackbar.repositories;

import be.ehb.demosnackbar.model.Snack;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SnackRepository extends CrudRepository<Snack, String> {

    Snack findByName(String name);

    List<Snack> findByNameContainsIgnoreCase(String name);

    List<Snack> findByNameContainsIgnoreCaseAndPriceBetweenOrderByName(String name, float min, float max);

    @Query("SELECT s FROM Snack s WHERE s.price BETWEEN :min AND :max")
    List<Snack> searchByPrice(float min, float max);
}
