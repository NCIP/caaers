#!/usr/bin/env ruby

#
# Processes the CTCv2_M9 CSV into a bering migration
#

require 'csv'

$cat_order = []
$sub_cat_order =[]
$terms_by_cat = { }
$cat_id = { }
$sub_cat_id = { }

def read()
  reader = CSV::Reader.create(File.open("ctep_disease_list.txt"))
  # skip first two rows
  reader.shift
  reader.shift
  
  id = 1
  cat_id = 0
  parent_id = 0
  category_id =0 # to be filled later
  reader.each do |row|
    # Puts each column of the row into a variable
    cat, sub_cat, ctep_term, term, medra_code = row
    #puts cat
    #puts term
    if !( $cat_order.include? cat )
       
        cat_id +=1
        $cat_order << cat
        $cat_id[cat] ||= [ ]
        $cat_id[cat] << { :id => cat_id , :parent_id => nil }
    end
    
    # If the sub_cat_order does does not contain this sub_cat
    if !( $cat_order.include? sub_cat )
        
        cat_id +=1
        $cat_order << sub_cat
        $cat_id[sub_cat] ||= [ ]
        $cat_id[sub_cat] << { :id => cat_id , :parent_id => $cat_id[cat][0].values[0]  }
    end
    
    $terms_by_cat[sub_cat] ||= [ ]
    $terms_by_cat[sub_cat] << { 
      :term => term, 
      :ctep_term => (ctep_term =~ /Not Available/ ? nil : ctep_term), 
      :medra_code => medra_code.to_i,
      :category_id => cat_id,
      :id => id
    }
   
    id += 1
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
        execute("DELETE FROM disease_terms ")
        execute("DELETE FROM disease_categories ")
    }
}
END
end

def print_inserts(cat, index)
  #category_id = 200 + index + 1
  #puts "        // #{cat} (#{$terms_by_cat[cat].size} terms)"
  #puts "        insert('ctc_categories', [version_id: 2, id: #{category_id}, name: '#{cat}'], primaryKey: false)"
  $cat_id[cat].each do |t|
    cols = ""
    [:id, :parent_id].each do |colname|
      value = t[colname]
      cols << "#{colname}: #{value.inspect}, " if value && value.size > 0
    end
    cols = cols.slice!(0, cols.size-2)
    puts "        insert('disease_categories', [ #{cols}, name: \"#{cat}\" ], primaryKey: false)"
  end
  
  if ( $terms_by_cat[cat] != nil )
  $terms_by_cat[cat].each do |t|
    cols = ""
    [:id, :term, :ctep_term, :category_id, :medra_code].each do |colname|
      value = t[colname]
      cols << "#{colname}: #{value.inspect}, " if value && value.size > 0
    end
    cols = cols.slice!(0, cols.size-2)
    puts "        insert('disease_terms', [ #{cols}], primaryKey: false)"
  end
  end
  
  
 
end

read()
create()
