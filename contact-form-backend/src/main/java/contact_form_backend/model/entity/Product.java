package contact_form_backend.model.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Seriler (1 ürün – çok seri)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSeries> seriesList = new ArrayList<>();

    // --- Getter / Setter ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public Category getCategory() { return category; }
    public List<ProductSeries> getSeriesList() { return seriesList; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setCategory(Category category) { this.category = category; }
    public void setSeriesList(List<ProductSeries> seriesList) { this.seriesList = seriesList; }
}
