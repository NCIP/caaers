class ChangeReports extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
          if (databaseMatches('oracle')) {
           execute('alter table report_schedules modify (due_on timestamp)')
           execute('alter table report_schedules modify (created_on timestamp)')
           execute('alter table report_schedules modify (submitted_on timestamp)')
        } else if (databaseMatches('postgresql')){
            execute('ALTER TABLE report_schedules ALTER due_on TYPE timestamp')
            execute('ALTER TABLE report_schedules ALTER created_on TYPE timestamp')
            execute('ALTER TABLE report_schedules ALTER submitted_on TYPE timestamp')
        } else {
            execute('ALTER TABLE report_schedules ALTER COLUMN due_on timestamp')
            execute('ALTER TABLE report_schedules ALTER COLUMN created_on timestamp')
            execute('ALTER TABLE report_schedules ALTER COLUMN submitted_on timestamp')
        }
  
    }
    
    void down() {
        if (databaseMatches('oracle')) {
           execute('alter table report_schedules modify (due_on date)')
           execute('alter table report_schedules modify (created_on date)')
           execute('alter table report_schedules modify (submitted_on date)')
        } else if (databaseMatches('postgresql')){
            execute('ALTER TABLE report_schedules ALTER due_on TYPE date')
            execute('ALTER TABLE report_schedules ALTER created_on TYPE date')
            execute('ALTER TABLE report_schedules ALTER submitted_on TYPE date')
        } else {
            execute('ALTER TABLE report_schedules ALTER COLUMN due_on date')
            execute('ALTER TABLE report_schedules ALTER COLUMN created_on date')
            execute('ALTER TABLE report_schedules ALTER COLUMN submitted_on date')
        }
    }
}

