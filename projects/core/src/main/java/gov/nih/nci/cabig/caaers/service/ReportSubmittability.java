package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReportSubmittability {
    private Map<ExpeditedReportSection, List<Message>> messages;

    public ReportSubmittability() {
        messages = new TreeMap<ExpeditedReportSection, List<Message>>();
    }

    public boolean isSubmittable() {
        return messages.isEmpty();
    }

    public void addMissingField(ExpeditedReportSection section, String name, String property) {
        insert(section, new Message(name + " is mandatory", property));
    }

    public void addValidityMessage(ExpeditedReportSection section, String message) {
        insert(section, new Message(message));
    }

    private void insert(ExpeditedReportSection section, Message message) {
        if (!messages.containsKey(section)) {
            messages.put(section, new LinkedList<Message>());
        }
        messages.get(section).add(message);
    }

    public Map<ExpeditedReportSection, List<Message>> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return messages.toString();
    }

    public static class Message {
        private String text;

        private String property;

        public Message(String text) {
            this(text, null);
        }

        public Message(String text, String property) {
            this.text = text;
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
            return text + ":" + property;
        }
    }
}
