package gov.nih.nci.cabig.caaers.service;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessages {
    private List<Message> messages = new ArrayList<Message>();

    public void addErrorMessage(String msg, String property){
        messages.add(new Message("Invalid " + msg, property));
    }

    public boolean hasErrors(){
        return messages.size() > 0;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return messages.toString();
    }

    public static class Message {
        private String text;
        private String property;

        public Message(String message, String property) {
            this.text = message;
            this.property = property;
        }

        public String getText() {
            return text;
        }

        public String getProperty() {
            return property;
        }

        @Override
        public String toString() {
            return text + ", " + property;
        }
    }
}
