class UpdateDiseaseTerms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("update disease_terms set medra_code = '10066354' where medra_code = '90002022'");
        execute("update disease_terms set medra_code = '10066353' where medra_code = '90600316'");
        execute("update disease_terms set medra_code = '10066351' where medra_code = '90600176'");
        execute("update disease_terms set medra_code = '10066352' where medra_code = '90600180'");
    }

    void down() {

    }
}