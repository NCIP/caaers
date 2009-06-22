class ModifyAdverseEventAttributions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
     setNullable("ae_attributions", "attribution_code", true)
    }

    void down() {
     setNullable("ae_attributions", "attribution_code", false)
    }
}
