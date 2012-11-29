class ModifyTaskNotificationText extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         execute("update task_configuration set message = 'Study  : \${STUDY}\n" +
                 "Subject: \${SUBJECT}\n" +
                 "Reporting Period : \${COURSE}\n" +
                 "' || message where length(message) > 0")
    }

    void down() {
       //ignore
    }
}