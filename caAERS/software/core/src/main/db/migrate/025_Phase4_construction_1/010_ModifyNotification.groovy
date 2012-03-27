class ModifyNotification extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        dropColumn("notifications", "email");
        addColumn("notifications", "roles", "string");
        addColumn("notifications", "emails", "string");
        addColumn("notifications", "study_id", "integer");
        execute('alter table notifications add constraint fk_notifications_stu_id foreign key (study_id) references studies (id)')

    }

    void down() {
        dropColumn("notifications", "roles");
        dropColumn("notifications", "emails");
        dropColumn("notifications", "study_id");
    }
}