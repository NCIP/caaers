class ChangeScheduledNotifications extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
          if (databaseMatches('oracle')) {
           execute('alter table scheduled_notifications modify (scheduled_on timestamp)')
           execute('alter table scheduled_notifications modify (created_on timestamp)')
        } else if (databaseMatches('postgresql')){
            execute('ALTER TABLE scheduled_notifications ALTER scheduled_on TYPE timestamp')
            execute('ALTER TABLE scheduled_notifications ALTER created_on TYPE timestamp')
        } else {
            execute('ALTER TABLE scheduled_notifications ALTER COLUMN scheduled_on timestamp')
            execute('ALTER TABLE scheduled_notifications ALTER COLUMN created_on timestamp')
        }
  
    }
    
    void down() {
        if (databaseMatches('oracle')) {
           execute('alter table scheduled_notifications modify (scheduled_on date)')
           execute('alter table scheduled_notifications modify (created_on date)')
        } else if (databaseMatches('postgresql')){
            execute('ALTER TABLE scheduled_notifications ALTER scheduled_on TYPE date')
            execute('ALTER TABLE scheduled_notifications ALTER created_on TYPE date')
        } else {
            execute('ALTER TABLE scheduled_notifications ALTER COLUMN scheduled_on date')
            execute('ALTER TABLE scheduled_notifications ALTER COLUMN created_on date')
        }
    }
}

