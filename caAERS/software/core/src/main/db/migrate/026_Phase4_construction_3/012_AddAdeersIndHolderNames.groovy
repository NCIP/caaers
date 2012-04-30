class PopulateAdeersIndHolderNames extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
        insert('organizations', [ id: -101, nci_institute_code: "ADEERS-101", name: "Company"], primaryKey: false)
        insert('organizations', [ id: -102, nci_institute_code: "ADEERS-102", name: "CIP"], primaryKey: false)
        insert('organizations', [ id: -103, nci_institute_code: "ADEERS-103", name: "CTEP"], primaryKey: false)
        insert('organizations', [ id: -104, nci_institute_code: "ADEERS-104", name: "Investigator"], primaryKey: false)
        insert('organizations', [ id: -105, nci_institute_code: "ADEERS-104", name: "Site"], primaryKey: false)
    }

    void down() {
        execute("DELETE FROM organizations WHERE id IN (-101, -102, -103, -105)")
    }

}
