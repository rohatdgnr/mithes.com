package contact_form_backend.controller;

import contact_form_backend.model.dto.OfferRequestDto;
import contact_form_backend.model.entity.Offer;
import contact_form_backend.repository.OfferRepository;
import contact_form_backend.service.MailService;
import contact_form_backend.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
@CrossOrigin // CORS problemi olmaması için
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private MailService mailService;

    @PostMapping
    public ResponseEntity<String> createOffer(@RequestBody OfferRequestDto dto) {
        if (dto.getProducts() == null || dto.getProducts().isEmpty()) {
            return ResponseEntity.badRequest().body("Teklif en az 1 ürün içermelidir.");
        }
        offerService.createOffer(dto);
        return ResponseEntity.ok("Teklif başarıyla kaydedildi.");
    }


}
