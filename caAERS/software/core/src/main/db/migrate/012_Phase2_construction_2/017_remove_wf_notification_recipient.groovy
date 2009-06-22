class RemoveNotificationRecipient extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropTable("wf_notification_recipient")
    }

    void down() {
        
    }
}