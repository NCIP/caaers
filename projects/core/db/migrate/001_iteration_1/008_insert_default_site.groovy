class InsertDefaultSite extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert("sites", [ name: 'default' , version: '0' ])
    }

    void down() {
        execute("DELETE FROM sites WHERE name='default'");
    }
}
