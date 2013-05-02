class InsertDefaultSite extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert('sites', [ id: 1, name: "default"], primaryKey: false)
    }

    void down() {
        execute("DELETE FROM sites WHERE name='default'");
    }
}
