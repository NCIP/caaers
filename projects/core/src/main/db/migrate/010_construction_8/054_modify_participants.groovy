class ModifyParticipants extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      addColumn("participants","birth_zone", "integer", nullable: false, defaultValue: 0)
      execute("update participants set birth_zone = 0");
    }

    void down() {
        dropColumn("participants","birth_zone")
    }
}
