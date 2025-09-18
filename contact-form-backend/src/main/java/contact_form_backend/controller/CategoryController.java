package contact_form_backend.controller;

import contact_form_backend.model.entity.Category;
import contact_form_backend.model.entity.Product;
import contact_form_backend.repository.CategoryRepository;
import contact_form_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    // Tüm kategoriler
    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    // Tek kategori
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Oluştur
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category saved = categoryRepository.save(category);
        return ResponseEntity.created(URI.create("/api/categories/" + saved.getId())).body(saved);
    }

    // İsteğe bağlı: Kategoriye bağlı ürünleri getir
}
