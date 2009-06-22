#!/usr/bin/env ruby

#
# Processes the sites_of_intervention CSV into a bering migration
#

require 'csv'

$cat_order = []
$terms_by_cat = { }
$sites = { }


def read()
  reader = CSV::Reader.create(File.open("sites_of_intervention"))
  # skip first  row
  reader.shift
  
  id = 1000
  count = 1
  catIndex =1;
  catName = 'all0'
  $sites[catName] ||= [ ]
  $cat_order << catName
  reader.each do |row|
   
    name = row
      
    $sites[catName] << {
      :name => name,
      :id => id
    }
    
 	if ( count.modulo(25) == 0 )
 		catName = 'all' << catIndex.to_s
 		catIndex +=1
 		$sites[catName] ||= [ ]
 		$cat_order << catName
 	end
   
    id += 1
    count +=1
  end
end

def create()
  puts <<END
class InterventionSites extends edu.northwestern.bioinformatics.bering.Migration {
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
        execute("DELETE * FROM intervention_sites")
    }
}
END
end

def print_inserts(cat, index)
  category_id = 200 + index + 1
  puts "        // #{cat} (#{$sites[cat].size} terms)"
  $sites[cat].each do |t|
    cols = ""	
    [:id, :name].each do |colname|
      value = t[colname]
      cols << "#{colname}: #{value.inspect}, " if value && value.size > 0
    end
    cols = cols.slice!(0, cols.size-2)
    puts "        insert('intervention_sites', [ #{cols}], primaryKey: false)"
  end
end




read()
create()
