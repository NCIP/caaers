#!/usr/bin/env ruby

require 'csv'

$terms = []
$rows = []

def term_str(category, term, select)
  s = "#{category}\0#{term}"
  s << "\0#{select}" unless (select.nil? or select.size == 0)
  s
end

# Since the categories/terms are presented in the same order in
# the grades CSV as in the original terms CSV, this method can
# recreate the ids that were assigned in migration 1-12. 
def term_id(category, term, select)
  s = term_str(category, term, select)
  i = $terms.index s
  unless i
    $terms << s
    i = $terms.size - 1
  end
  i += 3001
end

def read()
  reader = CSV::Reader.create(File.open("CTCAEv3Grades.txt"))
  # skip first row
  reader.shift
  
  id = 1
  reader.each do |row|
    cat, term, select, grade_code, grade_text = row
    term_id = term_id(cat, term, select)
    $rows << {
      :id => term_id * 10 + grade_code.to_i,
      :term_id => term_id(cat, term, select), 
      :grade_code => grade_code.to_i, 
      :grade_text => grade_text
    }
    id += 1
  end
end

def create()
  inserts_per_method = 250
  puts <<END
class AddCtcaeV3Grades extends edu.northwestern.bioinformatics.bering.Migration {
    void up() {
END

  puts "        // Have to break up the inserts so as not to exceed the java max method length"
  (0 .. $rows.size / inserts_per_method).each do |index|
    puts "        m#{index}()"
  end

  puts "    }"
  puts ""
  
  (0 .. $rows.size / inserts_per_method).each do |index|
    puts "    void m#{index}() {"
    start = index * inserts_per_method
    print_inserts(start, [inserts_per_method, $rows.size - start].min)
    puts "    }"
    puts ""
  end

  puts <<END
    void down() {
        execute("DELETE FROM ctc_grades")
    }
}
END
end

def print_inserts(start, count)
  puts "        // #{start} .. #{start + count - 1}"
  (start .. start + count - 1).each do |i|
    cols = ""
    [:id, :term_id, :grade_code, :grade_text].each do |colname|
      value = $rows[i][colname]
      cols << "#{colname}: #{value.inspect.gsub("\\r\\n", "\\n")}, " if value && value.size > 0
    end
    cols = cols.slice(0, cols.size-2)
    puts "        insert('ctc_grades', [#{cols}], primaryKey: false)"
  end
end

read
create