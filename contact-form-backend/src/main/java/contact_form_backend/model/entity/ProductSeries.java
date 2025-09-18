package contact_form_backend.model.entity;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ProductSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seriesCode;  // ör: DH.SIL.1100
    private Double price;

    @ElementCollection
    @CollectionTable(name = "product_series_specs", joinColumns = @JoinColumn(name = "series_id"))
    @MapKeyColumn(name = "spec_key")
    @Column(name = "spec_value")
    private Map<String, String> technicalSpecs = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // --- Getter / Setter ---
    public Long getId() { return id; }
    public String getSeriesCode() { return seriesCode; }
    public Double getPrice() { return price; }
    public Map<String, String> getTechnicalSpecs() { return technicalSpecs; }
    public Product getProduct() { return product; }

    public void setId(Long id) { this.id = id; }
    public void setSeriesCode(String seriesCode) { this.seriesCode = seriesCode; }
    public void setPrice(Double price) { this.price = price; }
    public void setTechnicalSpecs(Map<String, String> technicalSpecs) { this.technicalSpecs = technicalSpecs; }
    public void setProduct(Product product) { this.product = product; }
}
