#!/usr/bin/env ruby

#
# Processes the Prior_therapies CSV into a bering migration
#

require 'csv'

$cat_order = []
$terms_by_cat = { }
$priorTherapies = { }


def read()
  reader = CSV::Reader.create(File.open("prior_therapies.txt"))
  # skip first  row
  reader.shift
  
  id = 1
  count = 1
  catIndex =1;
  catName = 'all0'
  $priorTherapies[catName] ||= [ ]
  $cat_order << catName
  reader.each do |row|
   
    therapy_text, meddra_code, meddra_term = row
      
    $priorTherapies[catName] << {
      :therapy_text => therapy_text,
      :meddra_code => meddra_code,
      :meddra_term => meddra_term,
      :id => id
    }
    
 	if ( count.modulo(25) == 0 )
 		catName = 'all' << catIndex.to_s
 		catIndex +=1
 		$priorTherapies[catName] ||= [ ]
 		$cat_order << catName
 	end
   
    id += 1
    count +=1
  end
end

def create()
  puts <<END
class PopulatePriorTherapy extends edu.northwestern.bioinformatics.bering.Migration {
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
        execute("DELETE * FROM prior_therapies")
    }
}
END
end

def print_inserts(cat, index)
  category_id = 200 + index + 1
  puts "        // #{cat} (#{$priorTherapies[cat].size} terms)"
  $priorTherapies[cat].each do |t|
    cols = ""	
    [:id, :therapy_text, :meddra_code, :meddra_term].each do |colname|
      value = t[colname]
      cols << "#{colname}: #{value.inspect}, " if value && value.size > 0
    end
    cols = cols.slice!(0, cols.size-2)
    puts "        insert('prior_therapies', [ #{cols}], primaryKey: false)"
  end
end




read()
create()
