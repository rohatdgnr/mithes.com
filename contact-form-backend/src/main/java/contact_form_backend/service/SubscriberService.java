package contact_form_backend.service;

import contact_form_backend.model.entity.Subscriber;
import contact_form_backend.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriberService {
@Autowired
    private  SubscriberRepository repository;

    public Subscriber subscribe(String email) {
        return repository.findByEmail(email)
                .orElseGet(() -> repository.save(new Subscriber(null, email)));
    }
}
