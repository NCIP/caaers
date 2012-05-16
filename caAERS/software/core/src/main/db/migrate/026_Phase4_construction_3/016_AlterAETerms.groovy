class AlterAgentAETerms extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        if (databaseMatches('postgresql')){
            execute("alter table agent_terms alter column term_id set not null");
        }

        if (databaseMatches('oracle')) {
            execute("alter table agent_terms modify term_id not null");
        }
    }

    void down() {
        //no need
    }
}