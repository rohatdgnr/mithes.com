package contact_form_backend.controller;

import contact_form_backend.model.entity.Subscriber;
import contact_form_backend.model.dto.SubscriberRequest;
import contact_form_backend.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscribers")
@RequiredArgsConstructor
public class SubscriberController {

    @Autowired
    private  SubscriberService subscriberService;

    @PostMapping
    public ResponseEntity<Subscriber> subscribe(@RequestBody SubscriberRequest request) {
        System.out.println("Gelen e-posta: " + request.getEmail()); // log ekle
        Subscriber saved = subscriberService.subscribe(request.getEmail());
        return ResponseEntity.ok(saved);
    }
}