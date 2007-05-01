#!/usr/bin/env ruby

#
# Processes the Anatomic_sites CSV into a bering migration
#

require 'csv'

$cat_order = []
$terms_by_cat = { }
$coditions = { }


def read()
  reader = CSV::Reader.create(File.open("Pre-existing-Conds_M9.txt"))
  # skip first  row
  reader.shift
  
  id = 1
  count = 1
  catIndex =1;
  catName = 'all0'
  $coditions[catName] ||= [ ]
  $cat_order << catName
  reader.each do |row|
    # puts id
    # cat, term, ctep_term, ctep_code = row
      #puts row
      #theRow = (row.to_s).split('$');
      #puts theRow
 	  
       condition_text, meddra_llt, meddra_llt_code, meddra_hlgt = row
      #puts row.to_s
      
 	  #puts condition_text
 	  #puts category
 	  #puts name
      
    $coditions[catName] << {
      :condition_text => condition_text,
      :meddra_llt => meddra_llt,
      :meddra_llt_code => meddra_llt_code,
      :meddra_hlgt => meddra_hlgt,
      :id => id
    }
    
 	if ( count.modulo(25) == 0 )
 		catName = 'all' << catIndex.to_s
 		catIndex +=1
 		$coditions[catName] ||= [ ]
 		$cat_order << catName
 		#puts catName
 	end
   
    id += 1
    count +=1
  end
end

def create()
  puts <<END
class PopulatePreExistingConditions extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
END

  puts "        // Have to break up the inserts so as not to exceed the java max method length"
  $cat_order.each_with_index do |cat, index|
    puts "        m#{index}()"
  end

  puts "    }"
  puts ""
  
  $cat_order.each_with_index do |cat, index|
    puts "    void m#{index}() {"
    print_inserts(cat, index)
    puts "    }"
    puts ""
  end

  puts <<END
    void down() {
        execute("DELETE * FROM pre_existing_conditions")
    }
}
END
end

def print_inserts(cat, index)
  category_id = 200 + index + 1
  #puts "        // #{cat} (#{$terms_by_cat[cat].size} terms)"
  puts "        // #{cat} (#{$coditions[cat].size} terms)"
  #puts "        insert('ctc_categories', [version_id: 2, id: #{category_id}, name: '#{cat}'], primaryKey: false)"
  #$terms_by_cat[cat].each do |t|
  $coditions[cat].each do |t|
    cols = ""
    #[:id, :term, :ctep_term, :ctep_code, :other_required].each do |colname|	
    [:id, :condition_text, :meddra_llt, :meddra_hlgt, :meddra_llt_code].each do |colname|
      value = t[colname]
      cols << "#{colname}: #{value.inspect}, " if value && value.size > 0
    end
    cols = cols.slice!(0, cols.size-2)
    ##puts "        insert('ctc_terms', [category_id: #{category_id}, #{cols}], primaryKey: false)"
    #puts "        insert('coditions', [agent_id: #{category_id}, #{cols}], primaryKey: false)"
    puts "        insert('pre_existing_conditions', [ #{cols}], primaryKey: false)"
  end
end




read()
create()
