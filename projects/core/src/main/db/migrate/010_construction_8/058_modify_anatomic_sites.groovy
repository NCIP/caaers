class ModifyAnatomicSites extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
     execute("UPDATE anatomic_sites SET name='Other, specify' WHERE name='Other'")
    }

    void down() {
     execute("UPDATE anatomic_sites SET name='Other' WHERE name='Other, specify'")
    }
}
