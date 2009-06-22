class PopulatePreExistingConditions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        // Have to break up the inserts so as not to exceed the java max method length
        m0()
        m1()
    }

    void m0() {
        // all0 (25 terms)
        insert('pre_existing_conditions', [ id: 1, condition_text: "Anemia", meddra_llt: "Anaemia", meddra_hlgt: "Anaemias non-haemolytic and marrow depression", meddra_llt_code: "10002034"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 2, condition_text: "Autoimmune disorder", meddra_llt: "Autoimmune disorder ", meddra_hlgt: "Autoimmune disorders", meddra_llt_code: "10003815"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 3, condition_text: "Bacterial infection", meddra_llt: "Bacterial infection", meddra_hlgt: "Bacterial infectious disorders", meddra_llt_code: "10060945"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 4, condition_text: "Chronic obstructive airways disease", meddra_llt: "Chronic obstructive airways disease", meddra_hlgt: "Bronchial disorders (excl neoplasms)", meddra_llt_code: "10009026"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 5, condition_text: "Asthma", meddra_llt: "Asthma", meddra_hlgt: "Bronchial disorders (excl neoplasms)", meddra_llt_code: "10003553"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 6, condition_text: "Bronchospasm", meddra_llt: "Bronchospasm", meddra_hlgt: "Bronchial disorders (excl neoplasms)", meddra_llt_code: "10006482"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 7, condition_text: "Arrhythmia", meddra_llt: "Arrhythmia", meddra_hlgt: "Cardiac arrhythmias", meddra_llt_code: "10003119"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 8, condition_text: "Coagulation disorder", meddra_llt: "Coagulation disorder", meddra_hlgt: "Coagulopathies and bleeding diatheses (excl thrombocytopenic)", meddra_llt_code: "10009731"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 9, condition_text: "Coronary artery disease", meddra_llt: "Coronary artery disease", meddra_hlgt: "Coronary artery disorders", meddra_llt_code: "10011078"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 10, condition_text: "Electrolyte depletion", meddra_llt: "Electrolyte depletion", meddra_hlgt: "Electrolyte and fluid balance conditions", meddra_llt_code: "10014415"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 11, condition_text: "Thrombotic disorder", meddra_llt: "Thrombosis", meddra_hlgt: "Embolism and thrombosis", meddra_llt_code: "10043607"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 12, condition_text: "Cerebrovascular accident", meddra_llt: "Cerebrovascular accident", meddra_hlgt: "Central nervous system vascular disorders", meddra_llt_code: "10008190"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 13, condition_text: "Endocrine disorders", meddra_llt: "Endocrine disorder", meddra_hlgt: "Endocrine and glandular disorders NEC", meddra_llt_code: "10014695"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 14, condition_text: "Skin disorder", meddra_llt: "Skin disorder", meddra_hlgt: "Epidermal and dermal conditions", meddra_llt_code: "10040831"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 15, condition_text: "Eczema", meddra_llt: "Eczema", meddra_hlgt: "Epidermal and dermal conditions", meddra_llt_code: "10014184"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 16, condition_text: "Pancreatitis", meddra_llt: "Pancreatitis", meddra_hlgt: "Exocrine pancreas conditions", meddra_llt_code: "10033645"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 17, condition_text: "Fungal infection", meddra_llt: "Fungal infection", meddra_hlgt: "Fungal infectious disorders", meddra_llt_code: "10017533"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 18, condition_text: "Inflammatory bowel disease", meddra_llt: "Inflammatory bowel disease", meddra_hlgt: "Gastrointestinal inflammatory conditions", meddra_llt_code: "10021972"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 19, condition_text: "Peptic ulcer", meddra_llt: "Peptic ulcer", meddra_hlgt: "Gastrointestinal ulceration and perforation", meddra_llt_code: "10034341"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 20, condition_text: "Diabetes mellitus", meddra_llt: "Diabetes mellitus", meddra_hlgt: "Glucose metabolism disorders (incl diabetes mellitus)", meddra_llt_code: "10012601"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 21, condition_text: "Central line management", meddra_llt: "Central line management", meddra_hlgt: "Vascular therapeutic procedures", meddra_llt_code: "10007934"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 22, condition_text: "Cardiac failure congestive", meddra_llt: "Cardiac failure congestive", meddra_hlgt: "Heart failures", meddra_llt_code: "10007559"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 23, condition_text: "Hepatic disorder", meddra_llt: "Liver disorder", meddra_hlgt: "Hepatic and hepatobiliary disorders", meddra_llt_code: "10024670"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 24, condition_text: "Hepatocellular damage", meddra_llt: "Hepatocellular damage", meddra_hlgt: "Hepatic and hepatobiliary disorders", meddra_llt_code: "10019831"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 25, condition_text: "Hypertension", meddra_llt: "Hypertension", meddra_hlgt: "Vascular hypertensive disorders", meddra_llt_code: "10020772"], primaryKey: false)
    }

    void m1() {
        // all1 (13 terms)
        insert('pre_existing_conditions', [ id: 26, condition_text: "Hepatitis", meddra_llt: "Hepatitis", meddra_hlgt: "Hepatic and hepatobiliary disorders", meddra_llt_code: "10019717"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 27, condition_text: "Osteoarthritis", meddra_llt: "Osteoarthritis", meddra_hlgt: "Joint disorders", meddra_llt_code: "10031161"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 28, condition_text: "Rheumatoid arthritis", meddra_llt: "Rheumatoid arthritis", meddra_hlgt: "Joint disorders", meddra_llt_code: "10039073"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 29, condition_text: "Cigarette smoker", meddra_llt: "Cigarette smoker", meddra_hlgt: "Lifestyle issues", meddra_llt_code: "10009180"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 30, condition_text: "Prior anthracycline", meddra_llt: "Cytotoxic cardiomyopathy", meddra_hlgt: "Myocardial disorders", meddra_llt_code: "10011837"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 31, condition_text: "Pre-existing disease", meddra_llt: "Pre-existing disease", meddra_hlgt: "General signs and symptoms NEC", meddra_llt_code: "10056486"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 32, condition_text: "Peripheral neuropathy", meddra_llt: "Neuropathy peripheral", meddra_hlgt: "Peripheral neuropathies", meddra_llt_code: "10029331"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 33, condition_text: "Thrombocytopenia", meddra_llt: "Thrombocytopenia", meddra_hlgt: "Platelet disorders", meddra_llt_code: "10043554"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 34, condition_text: "Renal impairment", meddra_llt: "Renal impairment", meddra_hlgt: "Renal disorders (excl nephropathies)", meddra_llt_code: "10062237"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 35, condition_text: "Convulsions", meddra_llt: "Convulsions", meddra_hlgt: "Seizures (incl subtypes)", meddra_llt_code: "10010914"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 36, condition_text: "Thyroid disorder", meddra_llt: "Thyroid disorder", meddra_hlgt: "Thyroid gland disorders", meddra_llt_code: "10043709"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 37, condition_text: "HIV infection", meddra_llt: "HIV infection", meddra_hlgt: "Viral infectious disorders", meddra_llt_code: "10020161"], primaryKey: false)
        insert('pre_existing_conditions', [ id: 38, condition_text: "Viral infection", meddra_llt: "Viral infection", meddra_hlgt: "Viral infectious disorders", meddra_llt_code: "10047461"], primaryKey: false)
    }

    void down() {
        execute("DELETE * FROM pre_existing_conditions")
    }
}
