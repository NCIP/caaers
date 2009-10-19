class CTCAE_V4 extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
      execute("UPDATE ctc_terms SET other_required = 'true' WHERE term LIKE '%- Other%' AND category_id > 400 AND category_id < 500");
    }

    void down() {
        execute("UPDATE ctc_terms SET other_required = 'false' WHERE category_id > 400 AND category_id < 500");
    }
}
