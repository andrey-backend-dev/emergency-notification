package org.example.service.impls;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.dto.response.EmergencyCallDTO;
import org.example.entity.Caller;
import org.example.entity.EmergencyCall;
import org.example.entity.Receiver;
import org.example.entity.TelegramUser;
import org.example.repository.CallerRepository;
import org.example.repository.EmergencyCallRepository;
import org.example.service.EmergencyCallService;
import org.example.tg.TelegramBot;
import org.example.util.enums.ServiceEnum;
import org.example.util.mappers.EmergencyCallMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class EmergencyCallServiceImpl implements EmergencyCallService {
    private final EmergencyCallRepository repository;
    private final CallerRepository callerRepository;
    private final JavaMailSender emailSender;
    private final TelegramBot bot;
    private final EmergencyCallMapper mapper;
    @Value("${business.mail-subject}")
    private String mailSubject;
    @Value("${spring.mail.username}")
    private String from;
    @Value("${business.message}")
    private String templateMessage;

    @Override
    public List<EmergencyCallDTO> makeEmergencyCalls(String username) throws MessagingException, TelegramApiException {
        Caller caller = callerRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("The user with username " + username + " does not exist.")
        );

        Set<Receiver> receivers = caller.getReceivers();
        List<EmergencyCallDTO> emergencyCalls = new ArrayList<>();

        for (Receiver receiver : receivers) {
            if (receiver.getEmail() != null) {
                EmergencyCall emergencyCall = makeEmailEmergencyCall(receiver, caller);
                emergencyCalls.add(mapper.callToCallDto(emergencyCall));
            }

            TelegramUser user = receiver.getTelegramUser();

            if (user != null && user.isStatus()) {
                EmergencyCall emergencyCall = makeTelegramEmergencyCall(receiver, caller, user);
                emergencyCalls.add(mapper.callToCallDto(emergencyCall));
            }
        }

        return emergencyCalls;
    }

    private EmergencyCall makeEmailEmergencyCall(Receiver receiver, Caller caller) throws MessagingException {
        EmergencyCall emergencyCall = new EmergencyCall(receiver, ServiceEnum.EMAIL, LocalDateTime.now());
        emergencyCall.setDateReceived(
                sendEmail(
                        receiver.getEmail(),
                        caller.getMessage(),
                        receiver.getName(),
                        caller.getUsername()
                )
        );
        return repository.save(emergencyCall);
    }

    private EmergencyCall makeTelegramEmergencyCall(Receiver receiver, Caller caller, TelegramUser user) throws TelegramApiException {
        EmergencyCall emergencyCall = new EmergencyCall(receiver, ServiceEnum.TELEGRAM, LocalDateTime.now());
        bot.execute(new SendMessage(
                String.valueOf(user.getChatId()),
                generateMessage(
                        user.getUsername(),
                        caller.getUsername(),
                        caller.getMessage(),
                        ServiceEnum.TELEGRAM
                )
        ));
        emergencyCall.setDateReceived(LocalDateTime.now());
        return repository.save(emergencyCall);
    }

    private LocalDateTime sendEmail(String address, String message, String receiver, String callerName) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(emailSender.createMimeMessage(), true, "UTF-8");
        helper.setFrom(from);
        helper.setTo(address);
        helper.setSubject(mailSubject + " " + callerName);
        helper.setText(generateMessage(receiver, callerName, message, ServiceEnum.EMAIL), true);
        Date sentDate = Date.from(Instant.now());
        helper.setSentDate(sentDate);
        emailSender.send(helper.getMimeMessage());
        return sentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private String generateMessage(String receiver, String callerName, String message, ServiceEnum service) {

        String resultMsg = String.format(templateMessage, receiver, callerName);

        if (message != null)
            resultMsg = resultMsg + "<br><br>His message: " + message;

        if (service == ServiceEnum.TELEGRAM)
            resultMsg = resultMsg.replaceAll("<br>", "\n");

        return resultMsg;
    }
}
