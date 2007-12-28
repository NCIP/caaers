class AddLabTerms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        // Have to break up the inserts so as not to exceed the java max method length
        m0()
        m1()
        m2()
        m3()
        m4()
        m5()
    }

    void m0() {
        // Bone Marrow Biopsy (3 terms)
        insert('lab_categories', [version_id: 1, id: 101, name: 'Bone Marrow Biopsy'], primaryKey: false)
        insert('lab_terms', [category_id: 101, id: 1001, term: "Bone Marrow Cellularity"], primaryKey: false)
        insert('lab_terms', [category_id: 101, id: 1002, term: "Bone Marrow blasts"], primaryKey: false)
        insert('lab_terms', [category_id: 101, id: 1003, term: "Peripheral Blasts count"], primaryKey: false)
    }

    void m1() {
        // Chemistry (25 terms)
        insert('lab_categories', [version_id: 1, id: 102, name: 'Chemistry'], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1004, term: "Albumin - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1005, term: "Amylase - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1006, term: "Amylase - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1007, term: "Bilirubin direct - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1008, term: "Bilirubin total - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1009, term: "Blood Urea Nitrogen (BUN)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1010, term: "Calcium - blood (Ca)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1011, term: "Carbon dioxide - blood (CO2)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1012, term: "Chloride - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1013, term: "Cholesterol - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1014, term: "Creatine phosphokinase - blood (CK)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1015, term: "Creatinine - serum (Cr)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1016, term: "Creatinine clearance (CrCl)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1017, term: "Glucose - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1018, term: "Glutamic-oxaloacetic transferase (AST , SGOT)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1019, term: "Glutamic-pyruvate transferase (ALT , SGPT)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1020, term: "Lactate dehydrogenase - blood (LDH)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1021, term: "Lipase"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1022, term: "Magnesium - blood (Mg)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1023, term: "Phosphorus"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1024, term: "Potassium - blood (K)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1025, term: "Protein NOS (Total)"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1026, term: "Sodium - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1027, term: "Triglycerides"], primaryKey: false)
        insert('lab_terms', [category_id: 102, id: 1028, term: "Urine analysis"], primaryKey: false)
    }

    void m2() {
        // Coagulation (6 terms)
        insert('lab_categories', [version_id: 1, id: 103, name: 'Coagulation'], primaryKey: false)
        insert('lab_terms', [category_id: 103, id: 1029, term: "Activated partial thromboplastin time (APTT)"], primaryKey: false)
        insert('lab_terms', [category_id: 103, id: 1030, term: "Fibrin D-dimer"], primaryKey: false)
        insert('lab_terms', [category_id: 103, id: 1031, term: "Fibrin degradation products (FDP)"], primaryKey: false)
        insert('lab_terms', [category_id: 103, id: 1032, term: "Fibrinogen - blood"], primaryKey: false)
        insert('lab_terms', [category_id: 103, id: 1033, term: "International normalised ratio (INR)"], primaryKey: false)
        insert('lab_terms', [category_id: 103, id: 1034, term: "Prothrombin time (PT)"], primaryKey: false)
    }

    void m3() {
        // Hematologic (7 terms)
        insert('lab_categories', [version_id: 1, id: 104, name: 'Hematologic'], primaryKey: false)
        insert('lab_terms', [category_id: 104, id: 1035, term: "ANC"], primaryKey: false)
        insert('lab_terms', [category_id: 104, id: 1036, term: "Blood neutrophils (ANC)"], primaryKey: false)
        insert('lab_terms', [category_id: 104, id: 1037, term: "Hematocrit (Hct)"], primaryKey: false)
        insert('lab_terms', [category_id: 104, id: 1038, term: "Hemoglobin (Hb)"], primaryKey: false)
        insert('lab_terms', [category_id: 104, id: 1039, term: "Hemoglobin (fetal)"], primaryKey: false)
        insert('lab_terms', [category_id: 104, id: 1040, term: "Platelet count (PLT)"], primaryKey: false)
        insert('lab_terms', [category_id: 104, id: 1041, term: "White blood cell count (WBC)"], primaryKey: false)
    }

    void m4() {
        // Microbiology (3 terms)
        insert('lab_categories', [version_id: 1, id: 105, name: 'Microbiology'], primaryKey: false)
        insert('lab_terms', [category_id: 105, id: 1042, term: "Bacterial infection NOS"], primaryKey: false)
        insert('lab_terms', [category_id: 105, id: 1043, term: "Fungal infection NOS"], primaryKey: false)
        insert('lab_terms', [category_id: 105, id: 1044, term: "Viral infection NOS"], primaryKey: false)
    }

    void m5() {
        // Respiratory (5 terms)
        insert('lab_categories', [version_id: 1, id: 106, name: 'Respiratory'], primaryKey: false)
        insert('lab_terms', [category_id: 106, id: 1045, term: "Blood bicarbonate"], primaryKey: false)
        insert('lab_terms', [category_id: 106, id: 1046, term: "Blood carbon dioxide"], primaryKey: false)
        insert('lab_terms', [category_id: 106, id: 1047, term: "Oxygen saturation"], primaryKey: false)
        insert('lab_terms', [category_id: 106, id: 1048, term: "pH NOS"], primaryKey: false)
        insert('lab_terms', [category_id: 106, id: 1049, term: "pO2"], primaryKey: false)
    }

    void down() {
        execute("DELETE FROM lab_terms WHERE category_id > 100 AND category_id < 200")
        execute("DELETE FROM lab_categories WHERE version_id=1")
    }
}
