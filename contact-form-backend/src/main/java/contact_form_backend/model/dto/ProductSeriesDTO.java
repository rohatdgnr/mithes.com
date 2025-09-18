package contact_form_backend.model.dto;

import java.util.Map;

public class ProductSeriesDTO {
    private Long id;
    private String seriesCode;
    private Double price;
    private Map<String, String> technicalSpecs;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSeriesCode() { return seriesCode; }
    public void setSeriesCode(String seriesCode) { this.seriesCode = seriesCode; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Map<String, String> getTechnicalSpecs() { return technicalSpecs; }
    public void setTechnicalSpecs(Map<String, String> technicalSpecs) { this.technicalSpecs = technicalSpecs; }
}
