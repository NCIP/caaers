class CreateAEInterventionCauseType extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        insert("ae_cause_types", [code: "OI", name: "other intervention"], primaryKey: false)
        insert("ae_cause_types", [code: "BI", name: "biological intervention"], primaryKey: false)
        insert("ae_cause_types", [code: "HI", name: "behavioral intervention"], primaryKey: false)
        insert("ae_cause_types", [code: "GI", name: "genetic intervention"], primaryKey: false)
        insert("ae_cause_types", [code: "DI", name: "dietary suppliment intervention"], primaryKey: false)
    }

    void down() {
    }
}