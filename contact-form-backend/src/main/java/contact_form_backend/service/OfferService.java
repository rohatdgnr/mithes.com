package contact_form_backend.service;

import contact_form_backend.model.dto.OfferRequestDto;
import contact_form_backend.model.dto.ProductRequest;
import contact_form_backend.model.entity.Offer;
import contact_form_backend.model.entity.OfferProduct;
import contact_form_backend.model.entity.Product;
import contact_form_backend.repository.OfferRepository;
import contact_form_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    @Autowired
    private  OfferRepository offerRepository;
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  MailService mailService;

    public Offer createOffer(OfferRequestDto dto) {
        Offer offer = new Offer();
        offer.setName(dto.getName());
        offer.setCompanyName(dto.getCompanyName());
        offer.setPhone(dto.getPhone());
        offer.setEmail(dto.getEmail());
        offer.setMessage(dto.getMessage());

        List<OfferProduct> offerProducts = new ArrayList<>();

        for (ProductRequest p : dto.getProducts()) {
            Product product = productRepository.findById(p.getProductId())
                    .orElseThrow(() -> new RuntimeException("Ürün bulunamadı: " + p.getProductId()));

            OfferProduct offerProduct = new OfferProduct();
            offerProduct.setProductId(product.getId());
            offerProduct.setName(product.getName());
            offerProduct.setQuantity(p.getQuantity());
            offerProduct.setOffer(offer); // bağla

            offerProducts.add(offerProduct);
        }

        offer.setProducts(offerProducts);
        Offer savedOffer = offerRepository.save(offer);

        // Mail gönder
        mailService.sendOfferConfirmationEmail(savedOffer.getEmail(), savedOffer.getName());
        mailService.sendOfferNotificationToAdmin(
                savedOffer.getEmail(),
                savedOffer.getName(),
                savedOffer.getCompanyName(),
                savedOffer.getPhone(),
                savedOffer.getMessage(),
                savedOffer.getProducts()
        );

        return savedOffer;
    }
}
