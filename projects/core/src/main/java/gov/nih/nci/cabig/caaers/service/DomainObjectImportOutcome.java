package gov.nih.nci.cabig.caaers.service;

import java.util.ArrayList;
import java.util.List;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;
/*
 * A wrapper around a MutableDomain object
 * 
 */
public class DomainObjectImportOutcome<T extends MutableDomainObject> {
    private List<Message> messages = new ArrayList<Message>();
    private T importedDomainObject;
    private boolean isSavable = true;
    public enum Severity { ERROR, WARNING }

    public void addErrorMessage(String msg, Severity severity){
    	if (severity == Severity.ERROR) {
			isSavable = false;
		}
        messages.add(new Message("Message ", severity));
    }

    public boolean hasErrors(){
        return messages.size() > 0;
    }

    @Override
    public String toString() {
        return messages.toString();
    }
    
	public List<Message> getMessages() {
		return messages;
	}

	public T getImportedDomainObject() {
		return importedDomainObject;
	}

	public void setImportedDomainObject(T importedDomainObject) {
		this.importedDomainObject = importedDomainObject;
	}

	public boolean isSavable() {
		return isSavable;
	}

	public void setSavable(boolean isSavable) {
		this.isSavable = isSavable;
	}
	
    public static class Message {
        private String message;
        private Severity severity;
        private String property;

        public Message(String message, Severity severity) {
            this.message = message;
            this.severity = severity;
        }
        
        public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Severity getSeverity() {
			return severity;
		}

		public void setSeverity(Severity severity) {
			this.severity = severity;
		}

		@Override
        public String toString() {
            return message + ", " + property;
        }
    }
}
