class ModifySiteResearchStaff extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {
		addColumn("site_research_staffs","retired_indicator", "boolean", defaultValue: 0)
    }

    void down() {
      dropColumn("site_research_staffs", "retired_indicator")
    }
}