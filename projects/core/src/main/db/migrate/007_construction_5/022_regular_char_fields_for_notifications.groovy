class RegularCharFieldsForNotifications extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        ['planned', 'scheduled'].each { t ->
            // this implementation copies existing values on PostgreSQL only.  There are no deployed
            // oracle systems at present.
            addColumn("${t}_notifications", 'body_text', 'string')

            if (databaseMatches('postgres')) {
                // note that this will not fully copy the body if there is more than
                // one page in the blob.
                execute("UPDATE ${t}_notifications SET body_text=encode((SELECT data FROM pg_catalog.pg_largeobject WHERE loid=body AND pageno=0), 'escape')");
            }

            dropColumn("${t}_notifications", 'body')
            renameColumn("${t}_notifications", 'body_text', 'body')
        }
    }

    void down() {
        // down does not restore bodies on any platform
        dropColumn('planned_notifications', 'body')
        dropColumn('scheduled_notifications', 'body')
        if (databaseMatches('postgres')) {
            execute('alter table planned_notifications add column body oid')
            execute('alter table scheduled_notifications add column body oid')
        } else if (databaseMatches('oracle')) {
            execute('alter table planned_notifications add (body blob)')
            execute('alter table scheduled_notifications add (body blob)')
        } else if (databaseMatches('hsql')) {
            execute('alter table planned_notifications add column body longvarbinary');
            execute('alter table scheduled_notifications add column body longvarbinary');
        }
    }
}