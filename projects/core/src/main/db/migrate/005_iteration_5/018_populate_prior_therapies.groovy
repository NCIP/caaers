class PopulatePriorTherapy extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        // Have to break up the inserts so as not to exceed the java max method length
        m0()
    }

    void m0() {
        // all0 (19 terms)
        insert('prior_therapies', [ id: 1, therapy_text: "Anti-retroviral Therapy", meddra_term: "90003000"], primaryKey: false)
        insert('prior_therapies', [ id: 2, therapy_text: "Antisense", meddra_term: "90003002"], primaryKey: false)
        insert('prior_therapies', [ id: 3, therapy_text: "Bone Marrow Transplant", meddra_code: "Bone marrow transplant", meddra_term: "10061730"], primaryKey: false)
        insert('prior_therapies', [ id: 4, therapy_text: "Chemotherapy (NOS)", meddra_code: "Chemotherapy NOS", meddra_term: "10050693"], primaryKey: false)
        insert('prior_therapies', [ id: 5, therapy_text: "Chemotherapy multiple agents systemic", meddra_code: "Chemotherapy multiple agents systemic", meddra_term: "10008452"], primaryKey: false)
        insert('prior_therapies', [ id: 6, therapy_text: "Chemotherapy non-cytotoxic", meddra_term: "90003014"], primaryKey: false)
        insert('prior_therapies', [ id: 7, therapy_text: "Chemotherapy single agent systemic", meddra_code: "Chemotherapy single agent systemic", meddra_term: "10008456"], primaryKey: false)
        insert('prior_therapies', [ id: 8, therapy_text: "Drug and/or Immunotherapy", meddra_term: "90003006"], primaryKey: false)
        insert('prior_therapies', [ id: 9, therapy_text: "Gene Transfer", meddra_term: "90003004"], primaryKey: false)
        insert('prior_therapies', [ id: 10, therapy_text: "Hematopoietic Stem Cell Transplantation", meddra_code: "Stem cell transplant", meddra_term: "10063581"], primaryKey: false)
        insert('prior_therapies', [ id: 11, therapy_text: "Hormonal Therapy", meddra_code: "Hormone therapy", meddra_term: "10065646"], primaryKey: false)
        insert('prior_therapies', [ id: 12, therapy_text: "Image Directed Local Therapy", meddra_term: "90003016"], primaryKey: false)
        insert('prior_therapies', [ id: 13, therapy_text: "No prior therapy", meddra_code: "No previous exposure to drug NOS", meddra_term: "10052052"], primaryKey: false)
        insert('prior_therapies', [ id: 14, therapy_text: "Oncolytic Virotherapy", meddra_term: "90003008"], primaryKey: false)
        insert('prior_therapies', [ id: 15, therapy_text: "Prior Therapy NOS", meddra_term: "90003010"], primaryKey: false)
        insert('prior_therapies', [ id: 16, therapy_text: "Radiation Therapy", meddra_code: "Radiation Therapy", meddra_term: "10037770"], primaryKey: false)
        insert('prior_therapies', [ id: 17, therapy_text: "Surgery", meddra_code: "Surgery", meddra_term: "10042609"], primaryKey: false)
        insert('prior_therapies', [ id: 18, therapy_text: "Therapy (NOS)", meddra_term: "90003012"], primaryKey: false)
        insert('prior_therapies', [ id: 19, therapy_text: "Vaccine", meddra_code: "Immunisation", meddra_term: "10021430"], primaryKey: false)
    }

    void down() {
        execute("DELETE FROM prior_therapies")
    }
}
