package contact_form_backend.model.entity;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Örn: "Süt Ürünleri"
    private String description; // Opsiyonel

    // --- Getter/Setter ---
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
}
