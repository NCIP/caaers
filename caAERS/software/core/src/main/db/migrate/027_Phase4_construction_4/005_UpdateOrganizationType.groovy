class UpdateOrganizationType extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("update organizations  set type = 'ITN';");
    }

    void down() {
        execute("update organizations  set type = null;");
    }
}

