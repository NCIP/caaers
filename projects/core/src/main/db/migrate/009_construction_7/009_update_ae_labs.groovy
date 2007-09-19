class UpdateAELabs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         addColumn("ae_labs","other", 'string');
    }
      
    void down() {
        dropColumn("ae_labs","other");
    }
}
