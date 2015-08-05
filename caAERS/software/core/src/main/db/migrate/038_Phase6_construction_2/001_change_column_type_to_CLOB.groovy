/**
 * User: Janakiram_G
 * Date: 08/05/15
 */

class ChangeColumnTypeToCLOB extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

        if (databaseMatches('oracle')){

            //Change integration_log_message -> message column type to CLOB
            addColumn("integration_log_message","temp_message","string", limit:4000);
            execute("update integration_log_message set temp_message = message");
            execute("alter table integration_log_message modify message  null;"); //message is not null column
            execute("update integration_log_message set message = null");
            execute("alter table integration_log_message modify message long");
            execute("alter table integration_log_message modify message clob");
            execute("alter table integration_log_message modify message  not null");
            execute("update integration_log_message set message = temp_message");
            execute("alter table integration_log_message drop column temp_message");

            //Change integration_log_details -> outcome column type to CLOB
            addColumn("integration_log_details","temp_outcome","string", limit:4000);
            execute("update integration_log_details set temp_notes = outcome");
            execute("update integration_log_details set outcome = null");
            execute("alter table integration_log_details modify outcome long");
            execute("alter table integration_log_details modify outcome clob");
            execute("update integration_log_details set outcome = temp_outcome");
            execute("alter table integration_log_details drop column temp_outcome");

            //Change integration_logs -> notes column type to CLOB
            addColumn("integration_logs","temp_notes","string", limit:4000);
            execute("update integration_logs set temp_notes = notes");
            execute("update integration_logs set notes = null");
            execute("alter table integration_logs modify notes long");
            execute("alter table integration_logs modify notes clob");
            execute("update integration_logs set notes = temp_notes");
            execute("alter table integration_logs drop column temp_notes");
        }

    }

    void down() {
        if (databaseMatches('oracle')){
            dropColumn("integration_logs","temp_notes");
            dropColumn("integration_log_message","temp_message");
            dropColumn("integration_log_details","temp_outcome");
        }
    }
}
