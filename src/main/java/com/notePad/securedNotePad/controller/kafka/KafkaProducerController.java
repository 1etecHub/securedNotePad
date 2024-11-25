package com.notePad.securedNotePad.controller.kafka;


import com.notePad.securedNotePad.kafka.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka/")
public class KafkaProducerController {



    //private final KafkaProducerService kafkaProducerService;
    private final KafkaProducer kafkaProducerService;



    public KafkaProducerController(KafkaProducer kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String message) {
        try {
            kafkaProducerService.sendMessage(message);
            return "Message sent successfully!";
        } catch (Exception e) {
            return "Failed to send message. Check logs for details.";
        }
    }
}

