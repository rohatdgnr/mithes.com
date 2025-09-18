package contact_form_backend.service;

import contact_form_backend.model.entity.OfferProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * Kullanıcıya teklifinin alındığına dair bilgi e-postası gönderir.
     */
    public void sendOfferConfirmationEmail(String toEmail, String name) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(toEmail);  // kullanıcı maili
        mailMessage.setSubject("Teklifiniz Alındı");
        mailMessage.setText("Merhaba " + name + ",\n\n"
                + "Teklif talebinizi aldık. En kısa sürede sizinle iletişime geçilecektir.\n\n"
                + "İyi günler dileriz.");
        mailSender.send(mailMessage);
    }

    /**
     * Admin'e yeni gelen teklif bilgisini yollar.
     */
    public void sendOfferNotificationToAdmin(String userEmail, String name, String companyName, String phone, String message, List<OfferProduct> products) {
        StringBuilder productListText = new StringBuilder();
        for (OfferProduct p : products) {
            productListText.append("- ")
                    .append(p.getName())
                    .append(", Adet: ").append(p.getQuantity())
                    .append(", Fiyat: ").append(p.getPrice())
                    .append("\n");
        }

        String mailBody = "Yeni bir teklif alındı:\n\n"
                + "Ad Soyad: " + name + "\n"
                + "Şirket: " + companyName + "\n"
                + "E-posta: " + userEmail + "\n"
                + "Telefon: " + phone + "\n\n"
                + "Mesaj:\n" + message + "\n\n"
                + "Ürünler:\n" + productListText.toString();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(fromEmail);
        mailMessage.setSubject("Yeni Teklif Geldi");
        mailMessage.setText(mailBody);

        mailSender.send(mailMessage);
    }


    // Mevcut contact form fonksiyonları istersen aynı kalabilir:
    public void sendUserConfirmationEmail(String toEmail, String name) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Mesajınızı aldık");
        mailMessage.setText("Merhaba " + name + ",\n\nMesajınızı aldık. İlgili arkadaşlar sizinle en kısa sürede iletişime geçecektir.\n\nTeşekkürler.");
        mailSender.send(mailMessage);
    }

    public void sendAdminNotificationEmail(String userEmail, String name, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(fromEmail);
        mailMessage.setSubject("Yeni İletişim Formu Mesajı: " + subject);
        mailMessage.setText(
                "Gönderen: " + name + " <" + userEmail + ">\n\nMesaj:\n" + message
        );
        mailSender.send(mailMessage);
    }
}
