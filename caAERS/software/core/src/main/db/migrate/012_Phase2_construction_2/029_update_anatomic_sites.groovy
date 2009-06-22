class UpdateAnatomicSites extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         execute('delete from anatomic_sites where id in (52,53,57,63)')
    }

    void down() {
    }
}