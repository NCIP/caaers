#!/usr/bin/env ruby

#
# Processes the CTCv2_M9 CSV into a bering migration
#

require 'csv'

$cat_order = []
$terms_by_cat = { }
$agents = { }

# "Adverse Events Category","AE/Supra-ordinate Term","MedDRA/ CTEP Term","MedDRA Code(v90)/ CTEP Code"

# "ALLERGY/IMMUNOLOGY","Allergic reaction/hypersensitivity (including drug fever)","Hypersensitivity","10020751"

# "NSC","Agent Name"
# "723227","(161-180)ESO-1 Peptide"
# "726584","(H115D)VHL35 Peptide"

def read()
  #reader = CSV::Reader.create(File.open("ctep_agent_list.txt"))
  reader = CSV::Reader.create(File.open("ctep_agent_list_dec_09.txt"))
  # skip first two rows
  reader.shift
  reader.shift
  
  #id = 1
  id=1306
  count = 1
  catIndex =1;
  catName = 'all0'
  $agents[catName] ||= [ ]
  $cat_order << catName
  reader.each do |row|
    # puts id
    # cat, term, ctep_term, ctep_code = row
      nsc, name = row
 	
    $agents[catName] << {
      :nsc => nsc.to_i,
      :name => name,
      #:other_required => (agent_name =~ /-Other/ ? '1' : nil), 
      :id => id
    }
    
 	if ( count.modulo(25) == 0 )
 		catName = 'all' << catIndex.to_s
 		catIndex +=1
 		$agents[catName] ||= [ ]
 		$cat_order << catName
 		#puts catName
 	end
   
    id += 1
    count +=1
  end
end

def create()
  puts <<END
class AddCtcV2Terms extends edu.northwestern.bioinformatics.bering.Migration {
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
        execute("DELETE * FROM agents")
    }
}
END
end

def print_inserts(cat, index)
  category_id = 200 + index + 1
  #puts "        // #{cat} (#{$terms_by_cat[cat].size} terms)"
  puts "        // #{cat} (#{$agents[cat].size} terms)"
  #puts "        insert('ctc_categories', [version_id: 2, id: #{category_id}, name: '#{cat}'], primaryKey: false)"
  #$terms_by_cat[cat].each do |t|
  $agents[cat].each do |t|
    cols = ""
    #[:id, :term, :ctep_term, :ctep_code, :other_required].each do |colname|	
    [:id, :nsc, :name,].each do |colname|
      value = t[colname]
      cols << "#{colname}: #{value.inspect}, " if value && value.size > 0
    end
    cols = cols.slice!(0, cols.size-2)
    ##puts "        insert('ctc_terms', [category_id: #{category_id}, #{cols}], primaryKey: false)"
    #puts "        insert('agents', [agent_id: #{category_id}, #{cols}], primaryKey: false)"
    puts "        insert('agents', [ #{cols}], primaryKey: false)"
  end
end




read()
create()
