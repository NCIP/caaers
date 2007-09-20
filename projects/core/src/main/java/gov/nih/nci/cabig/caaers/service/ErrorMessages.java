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

    @Override
    public String toString() {
        return messages.toString();
    }

    public static class Message {
        private String message;
        private String property;

        public Message(String message, String property) {
            this.message = message;
            this.property = property;
        }

        @Override
        public String toString() {
            return message + ", " + property;
        }
    }
}
