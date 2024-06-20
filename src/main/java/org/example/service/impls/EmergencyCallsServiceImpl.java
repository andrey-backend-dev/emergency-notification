package org.example.service.impls;

import jakarta.mail.MessagingException;
import org.example.dto.response.EmergencyCallsDTO;
import org.example.entity.Caller;
import org.example.entity.Caller2ReceiverBinding;
import org.example.entity.EmergencyCalls;
import org.example.repository.CallerRepository;
import org.example.repository.EmergencyCallsRepository;
import org.example.service.EmergencyCallsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class EmergencyCallsServiceImpl implements EmergencyCallsService {
    private final EmergencyCallsRepository repository;
    private final CallerRepository callerRepository;
    private final JavaMailSender emailSender;
    @Value("${business.mail-subject}")
    private String mailSubject;
    @Value("${spring.mail.username}")
    private String from;
    @Value("${business.message}")
    private String templateMessage;

    public EmergencyCallsServiceImpl(EmergencyCallsRepository repository, CallerRepository callerRepository, JavaMailSender emailSender) {
        this.repository = repository;
        this.callerRepository = callerRepository;
        this.emailSender = emailSender;
    }

    @Override
    public List<EmergencyCallsDTO> makeEmergencyCalls(long caller_id) throws MessagingException {
        Caller caller = callerRepository.findById(caller_id).orElseThrow(
                () -> new IllegalArgumentException("The user with id " + caller_id + " does not exist.")
        );

        Set<Caller2ReceiverBinding> bindings = caller.getBindings();
        List<EmergencyCallsDTO> emergencyCalls = new ArrayList<>();

        for (Caller2ReceiverBinding binding : bindings) {
            EmergencyCalls emergencyCall = new EmergencyCalls(binding, LocalDateTime.now());

            switch (binding.getService()) {
                case EMAIL -> sendEmail(
                        binding.getServiceLink(),
                        caller.getMessage(), binding.getReceiver(),
                        caller.getUsername(),
                        caller.getEmail()
                );
                case TELEGRAM -> sendEmail(null, null, null, null, null);
            }

            emergencyCall.setDateReceived(LocalDateTime.now());

            emergencyCall = repository.save(emergencyCall);

            emergencyCalls.add(new EmergencyCallsDTO(emergencyCall));
        }

        return emergencyCalls;
    }

    @Override
    public void sendEmail(String address, String message, String receiver, String callerName, String callerAddress) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(emailSender.createMimeMessage(), true, "UTF-8");
        helper.setFrom(from);
        helper.setTo(address);
        helper.setSubject(mailSubject + " " + callerName);
        helper.setText(generateMessage(String.format(templateMessage, receiver, callerName, callerAddress), message), true);
        helper.setSentDate(Date.from(Instant.now()));
        emailSender.send(helper.getMimeMessage());
    }

    private String generateMessage(String templateMessage, String message) {

        if (message != null)
            templateMessage = templateMessage + "<br><br>His message: " + message;

        return templateMessage;
    }
}
