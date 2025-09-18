package contact_form_backend.controller;

import contact_form_backend.model.dto.ProductDTO;
import contact_form_backend.model.dto.ProductSeriesDTO;
import contact_form_backend.model.entity.Category;
import contact_form_backend.model.entity.Product;
import contact_form_backend.repository.CategoryRepository;
import contact_form_backend.repository.ProductRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /* ===================== DTO Mapping ===================== */
    private ProductDTO toDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setImageUrl(p.getImageUrl());

        if (p.getCategory() != null) {
            dto.setCategoryId(p.getCategory().getId());
            dto.setCategoryName(p.getCategory().getName());
        }

        // serileri map et
        List<ProductSeriesDTO> seriesDtos = p.getSeriesList().stream().map(s -> {
            ProductSeriesDTO sdto = new ProductSeriesDTO();
            sdto.setId(s.getId());
            sdto.setSeriesCode(s.getSeriesCode());
            sdto.setPrice(s.getPrice());
            sdto.setTechnicalSpecs(s.getTechnicalSpecs());
            return sdto;
        }).toList();

        dto.setSeriesList(seriesDtos);
        return dto;
    }

    /* ===================== READ ===================== */
    @GetMapping
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(p -> ResponseEntity.ok(toDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /* ===================== CREATE ===================== */
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody CreateProductRequest req) {
        if (req.getName() == null || req.getName().isBlank()) {
            return ResponseEntity.badRequest().body("name gerekli");
        }

        Product p = new Product();
        p.setName(req.getName());
        p.setDescription(req.getDescription());
        p.setImageUrl(req.getImageUrl());

        if (req.getCategoryId() != null) {
            Category cat = categoryRepository.findById(req.getCategoryId()).orElse(null);
            if (cat == null) {
                return ResponseEntity.badRequest().body("Geçersiz categoryId: " + req.getCategoryId());
            }
            p.setCategory(cat);
        }

        Product saved = productRepository.save(p);
        return ResponseEntity.created(URI.create("/api/products/" + saved.getId()))
                .body(toDTO(saved));
    }

    /* ===================== DELETE ===================== */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(p -> {
                    productRepository.delete(p);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /* ===================== REQUEST DTO ===================== */
    @Data
    public static class CreateProductRequest {
        private String name;
        private String description;
        private String imageUrl;
        private Long categoryId;
    }
}
