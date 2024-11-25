package com.notePad.securedNotePad.controller.kafka;


import com.notePad.securedNotePad.entity.User;
import com.notePad.securedNotePad.kafka.JsonKafkaProducer;
import com.notePad.securedNotePad.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka/")
@RequiredArgsConstructor
public class KafkaProducerController {



    //private final KafkaProducerService kafkaProducerService;
    private final KafkaProducer kafkaProducerService;
    private final JsonKafkaProducer jsonKafkaProducerService;




    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam("message") String message) {
        try {
            kafkaProducerService.sendMessage(message);
            return "Message sent successfully!";
        } catch (Exception e) {
            return "Failed to send message. Check logs for details.";
        }
    }

    @PostMapping("/sendJsonMessage")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {

            jsonKafkaProducerService.sendMessage(user);
            return ResponseEntity.ok("Message sent successfully!");

    }
}

