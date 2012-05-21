class UpdateOrganizationType extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("update organizations  set org_type = 'ITN';");
    }

    void down() {
        execute("update organizations  set org_type = null;");
    }
}

