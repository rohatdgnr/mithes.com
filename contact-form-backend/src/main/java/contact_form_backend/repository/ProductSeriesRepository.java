package contact_form_backend.repository;

import contact_form_backend.model.entity.ProductSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSeriesRepository extends JpaRepository<ProductSeries, Long> {}
