package contact_form_backend.controller;

import contact_form_backend.model.entity.ContactMessage;
import contact_form_backend.repository.ContactMessageRepository;
import contact_form_backend.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
@CrossOrigin // frontend localhost ise sorun olmaması için
public class ContactController {

    @Autowired
    private  ContactMessageRepository repository;

    @Autowired
    private  MailService mailService;

    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody ContactMessage message) {
        repository.save(message); // veritabanına kaydet
        mailService.sendUserConfirmationEmail(message.getEmail(), message.getName());
        mailService.sendAdminNotificationEmail(message.getEmail(), message.getName(), message.getSubject(), message.getMessage());
        return ResponseEntity.ok("Mesaj başarıyla gönderildi.");
    }


}