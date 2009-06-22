class UpdateReportDeliveryDefs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
         addColumn("report_delivery_defs","user_name", 'string');
       	 addColumn("report_delivery_defs","password", 'string');
    }
      
    void down() {
        dropColumn("report_delivery_defs","user_name");
       	dropColumn("report_delivery_defs","password");
    }
}
