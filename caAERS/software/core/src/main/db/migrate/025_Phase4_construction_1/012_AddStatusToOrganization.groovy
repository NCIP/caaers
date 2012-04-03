class AddStatusToOrganization extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("organizations", "status", "string", nullable: false, defaultValue: 'AC');
    }

    void down() {
        dropColumn("organizations", "status");
    }
}