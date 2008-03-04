package gov.nih.nci.cabig.caaers.esb.client;

public interface ESBMessageConsumer {
    public void processMessage(String message);
}
