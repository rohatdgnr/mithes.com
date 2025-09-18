package contact_form_backend.repository;

import contact_form_backend.model.entity.OfferProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferProductRepository extends JpaRepository<OfferProduct, Long> {
}
