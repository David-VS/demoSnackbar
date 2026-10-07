package be.ehb.demosnackbar.repositories;

import be.ehb.demosnackbar.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
