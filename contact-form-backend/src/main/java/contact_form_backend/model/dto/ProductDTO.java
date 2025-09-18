package contact_form_backend.model.dto;

import java.util.List;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String categoryName;
    private Long categoryId;
    private List<ProductSeriesDTO> seriesList; // 🔹 ürün serileri

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public List<ProductSeriesDTO> getSeriesList() { return seriesList; }
    public void setSeriesList(List<ProductSeriesDTO> seriesList) { this.seriesList = seriesList; }
}
