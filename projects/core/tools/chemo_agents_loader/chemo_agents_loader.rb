#!/usr/bin/env ruby

#
# Processes the chemo_agents_list_adeers CSV into a bering migration
#

require 'csv'

$cat_order = []
$terms_by_cat = { }
$agents = { }


def read()
  reader = CSV::Reader.create(File.open("chemo_agents_list_adeers.txt"))
  # skip first  row
  reader.shift
  
  id = 1000
  count = 1
  catIndex =1;
  catName = 'all0'
  $agents[catName] ||= [ ]
  $cat_order << catName
  reader.each do |row|
   
    name = row
      
    $agents[catName] << {
      :name => name,
      :id => id
    }
    
 	if ( count.modulo(25) == 0 )
 		catName = 'all' << catIndex.to_s
 		catIndex +=1
 		$agents[catName] ||= [ ]
 		$cat_order << catName
 	end
   
    id += 1
    count +=1
  end
end

def create()
  puts <<END
class PopulateChemoAgents extends edu.northwestern.bioinformatics.bering.Migration {
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
        execute("DELETE * FROM chemo_agents")
    }
}
END
end

def print_inserts(cat, index)
  category_id = 200 + index + 1
  puts "        // #{cat} (#{$agents[cat].size} terms)"
  $agents[cat].each do |t|
    cols = ""	
    [:id, :name].each do |colname|
      value = t[colname]
      cols << "#{colname}: #{value.inspect}, " if value && value.size > 0
    end
    cols = cols.slice!(0, cols.size-2)
    puts "        insert('chemo_agents', [ #{cols}], primaryKey: false)"
  end
end




read()
create()
