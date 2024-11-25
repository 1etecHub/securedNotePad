package com.notePad.securedNotePad.kafka;

import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer {

    private static final Logger LOGGER = Logger.getLogger(KafkaConsumer.class);

    @KafkaListener(topics="notepad", groupId = "myGroup")
    public void consume (String message){

        LOGGER.info(String.format("message received -> %s", message));

    }
}
