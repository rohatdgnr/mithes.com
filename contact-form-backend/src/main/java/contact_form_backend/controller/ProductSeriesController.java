package contact_form_backend.controller;

import contact_form_backend.model.entity.Product;
import contact_form_backend.model.entity.ProductSeries;
import contact_form_backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products/{productId}/series")
@RequiredArgsConstructor
@CrossOrigin
public class ProductSeriesController {

    private final ProductRepository productRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> addSeries(@PathVariable Long productId, @RequestBody CreateSeriesRequest req) {
        return productRepository.findById(productId).map(product -> {
            ProductSeries s = new ProductSeries();
            s.setSeriesCode(req.getSeriesCode());
            s.setPrice(req.getPrice());
            s.setTechnicalSpecs(new HashMap<>(req.getTechnicalSpecs()));
            s.setProduct(product);

            product.getSeriesList().add(s);
            productRepository.save(product);

            return ResponseEntity.created(
                    URI.create("/api/products/" + productId + "/series/" + s.getId())
            ).body(s);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Data
    public static class CreateSeriesRequest {
        private String seriesCode;
        private Double price;
        private Map<String, String> technicalSpecs = new HashMap<>();
    }
}
