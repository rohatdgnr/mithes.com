package contact_form_backend.repository;

import contact_form_backend.model.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByEmail(String email);
}
