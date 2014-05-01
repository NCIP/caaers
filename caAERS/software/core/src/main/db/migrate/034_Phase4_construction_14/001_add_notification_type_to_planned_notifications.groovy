class AddNotificationTypeToPlannedNotifications extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("planned_notifications","rpt_def_notification_type","integer");
    }

    void down() {
        dropColumn("planned_notifications","rpt_def_notification_type");
    }
}