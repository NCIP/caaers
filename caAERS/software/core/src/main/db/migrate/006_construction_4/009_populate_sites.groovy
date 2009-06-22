class PopulateSites extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        // Have to break up the inserts so as not to exceed the java max method length
        m0()
    }

    void m0() {
        insert('sites', [ id: 2, name: "National Cancer Institute"], primaryKey: false)
      	insert('sites', [ id: 3, name: "Wake Forest Comprehensive Cancer Center"], primaryKey: false)
        insert('sites', [ id: 4, name: "Duke University Comprehensive Cancer Center"], primaryKey: false)
    }

    void down() {
        execute("DELETE FROM sites")
    }
}
