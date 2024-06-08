package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

    @Entity
    public class Message {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String senderId;

        private String recipientId;

        private String content;
        private LocalDateTime timestamp;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSender() {
            return senderId;
        }

        public void setSender(String senderId) {
            this.senderId = senderId;
        }

        public String getRecipient() {
            return recipientId;
        }

        public void setRecipient(String recipientId) {
            this.recipientId = recipientId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

    }
