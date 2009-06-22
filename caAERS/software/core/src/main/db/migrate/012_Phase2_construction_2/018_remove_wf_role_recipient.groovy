class RemoveRoleRecipient extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
     	dropTable("wf_role_recipient")
    }

    void down() {
    }
}