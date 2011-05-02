class CreateAEInterventionCauseType extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        addColumn("site_investigators", 'code', 'integer', defaultValue: 0)
        addColumn("site_investigators", 'phone_number', 'string')
        addColumn("site_investigators", 'fax_number', 'string')
        addColumn("site_investigators", 'street', 'string')
        addColumn("site_investigators", 'city', 'string')
        addColumn("site_investigators", 'state', 'string')
        addColumn("site_investigators", 'zip', 'string')
        addColumn("site_investigators", 'country', 'string')
    }

    void down() {
        dropColumn("site_investigators", 'code');
        dropColumn("site_investigators", 'phone_number');
        dropColumn("site_investigators", 'fax_number');
        dropColumn("site_investigators", 'street');
        dropColumn("site_investigators", 'city');
        dropColumn("site_investigators", 'state');
        dropColumn("site_investigators", 'zip');
        dropColumn("site_investigators", 'country');
    }
}
