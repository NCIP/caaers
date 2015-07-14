/**
 * User: Janakiram_G
 * Date: 5/11/15
 */

class AddCreatedDateToAE extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("adverse_events","created_date","timestamp");
    }

    void down() {
        dropColumn("adverse_events","created_date");
    }
}
