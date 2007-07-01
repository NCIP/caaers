class CreateReportDeliveryDefs extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable('report_deliveries') { t ->
            t.addColumn('end_point','string', nullable:false)
            t.addColumn('delivery_status','string', nullable:false)
            t.addColumn('rdd_id','integer', nullable:true) //report_delivery_defs_id
            t.addColumn('rp_id', 'integer', nullable:true) //report_id
            t.addVersionColumn()
            t.addColumn('grid_id' , 'string' , nullable:true);
        }
        execute('alter table report_deliveries add constraint fk_rd_rdd_id foreign key (rdd_id) references report_delivery_defs (id) on delete cascade')
        execute('alter table report_deliveries add constraint fk_rd_rp_id foreign key (rp_id) references report_schedules (id) on delete cascade')
    }

    void down() {
        dropTable('report_deliveries')
    }
}