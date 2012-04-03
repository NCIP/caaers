class UpdateOrganizationStatus extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("update organizations set status = 'AC'");
    }

    void down() {
    }
}