class InsertOrganizations extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        // Have to break up the inserts so as not to exceed the java max method length
        m0()
    }

    void m0() {
        insert('organizations', [ id: 5, name: "Division of Cancer Prevention"], primaryKey: false)
        insert('organizations', [ id: 6, name: "Cancer Therapy Evaluation Program"], primaryKey: false)
    }

    void down() {
        execute("DELETE FROM organizations where id in (5,6)")
    }
}
