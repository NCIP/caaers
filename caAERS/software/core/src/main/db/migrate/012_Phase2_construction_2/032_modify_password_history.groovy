class ModifyPasswordHistory extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        execute("ALTER TABLE password_history DROP CONSTRAINT fk_user_id")
    }

    void down() {
       //not possible
    }
}