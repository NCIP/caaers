class AddMergedOrganization extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("organizations", "merged_org_id", "integer");
        execute("alter table organizations add constraint fk_org_org_id FOREIGN KEY(merged_org_id) references organizations(ID)");
    }

    void down() {
        dropColumn("organizations", "merged_org_id");
    }
}