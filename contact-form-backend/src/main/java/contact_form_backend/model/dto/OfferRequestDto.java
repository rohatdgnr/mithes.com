package contact_form_backend.model.dto;

import java.util.List;

public class OfferRequestDto {
    private String name;
    private String companyName;
    private String phone;
    private String email;
    private String message;
    private List<ProductRequest> products;

    // Getter & Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<ProductRequest> getProducts() {
        return products;
    }
    public void setProducts(List<ProductRequest> products) {
        this.products = products;
    }
}
