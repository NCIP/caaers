class CreateReportIndex extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
        createTable("report_index") { t ->
            t.addVersionColumn()
            t.addColumn("login_id", "string")
            t.addColumn("report_id", "integer", nullable: false)
            t.addColumn("role_code", "integer", defaultValue: 0)
            t.addColumn("grid_id", "string")
            t.addColumn("R_101" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_102" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_103" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_104" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_105" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_106" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_107" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_108" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_109" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_110" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_111" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_112" , "boolean" , nullable: false , defaultValue: 0)
 			t.addColumn("R_113" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_114" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_115" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_116" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_117" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_118" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_119" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_120" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_121" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_122" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_123" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_7942" , "boolean" , nullable: false , defaultValue: 0)
			t.addColumn("R_7943" , "boolean" , nullable: false , defaultValue: 0)
        }
         execute('alter table report_index add constraint fk_report_index_rep_id foreign key (report_id) references REPORT_SCHEDULES (id)')
    }

    void down() {
        dropTable("report_index")
    }
}
