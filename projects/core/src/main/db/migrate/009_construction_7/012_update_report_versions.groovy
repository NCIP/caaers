class UpdateReportVersions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         addColumn("report_versions","report_version_id", 'string');
         execute("update report_versions set report_version_id='NA'");
    }
      
    void down() {
        dropColumn("report_versions","report_version_id");
    }
}
