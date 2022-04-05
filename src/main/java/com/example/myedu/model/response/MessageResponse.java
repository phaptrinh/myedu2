package com.example.myedu.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageResponse {
        private String message;

        public MessageResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
