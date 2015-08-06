/**
 * User: Janakiram_G
 * Date: 08/05/15
 */

class ChangeColumnTypeToCLOB extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

        if (databaseMatches('oracle')){

            //Change integration_log_message -> message column type to CLOB
            execute("alter table integration_log_message modify message  null;"); //message is not null column
            execute("alter table integration_log_message modify message long");
            execute("alter table integration_log_message modify message clob");
            execute("alter table integration_log_message modify message  not null");

            //Change integration_log_details -> outcome column type to CLOB
            execute("alter table integration_log_details modify outcome long");
            execute("alter table integration_log_details modify outcome clob");

            //Change integration_logs -> notes column type to CLOB
            execute("alter table integration_logs modify notes long");
            execute("alter table integration_logs modify notes clob");

        }

    }

    void down() {

    }
}
