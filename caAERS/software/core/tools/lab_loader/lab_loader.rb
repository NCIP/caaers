#!/usr/bin/env ruby

#
# Processes the Labs CSV into a bering migration
#

require 'csv'

$cat_order = []
$terms_by_cat = { }

def read()
  reader = CSV::Reader.create(File.open("labs.txt"))
  # skip first two rows
  reader.shift
  #reader.shift
  
  id = 1001
  reader.each do |row|
    cat, term = row
    $cat_order << cat unless $cat_order.include? cat
    $terms_by_cat[cat] ||= [ ]
    $terms_by_cat[cat] << { 
      :term => term,
      :id => id
    }
    id += 1
  end
end

def create()
  puts <<END
class AddLabTerms extends edu.northwestern.bioinformatics.bering.Migration {
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
        execute("DELETE FROM lab_terms WHERE category_id > 100 AND category_id < 200")
        execute("DELETE FROM lab_categories WHERE version_id=1")
    }
}
END
end

def print_inserts(cat, index)
  category_id = 100 + index + 1
  puts "        // #{cat} (#{$terms_by_cat[cat].size} terms)"
  puts "        insert('lab_categories', [version_id: 1, id: #{category_id}, name: '#{cat}'], primaryKey: false)"
  $terms_by_cat[cat].each do |t|
    cols = ""
    [:id, :term].each do |colname|
      value = t[colname]
      cols << "#{colname}: #{value.inspect}, " if value && value.size > 0
    end
    cols = cols.slice!(0, cols.size-2)
    puts "        insert('lab_terms', [category_id: #{category_id}, #{cols}], primaryKey: false)"
  end
end

read()
create()
