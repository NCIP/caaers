#!/usr/bin/env ruby

#
# Processes the Organizations txt into a bering migration
#

require 'csv'

$cat_order = []
$terms_by_cat = { }
$orgs = { }

#$groupId = -15
#$pgpeid = 1015

#$groupId = -1013
#$pgpeid = 2013

#$groupId = -2013
#$pgpeid = 3013

#$groupId = -3013
#$pgpeid = 4013

#$groupId = -4013
#$pgpeid = 5013

#$groupId = -5013
#$pgpeid = 6013

#$groupId = -6013
#$pgpeid = 7013

$groupId = -7013
$pgpeid = 8013

def read()
  #reader = CSV::Reader.create(File.open("org_codes_1"))
  #reader = CSV::Reader.create(File.open("org_codes_2"))
  #reader = CSV::Reader.create(File.open("org_codes_3"))
  #reader = CSV::Reader.create(File.open("org_codes_4"))
  #reader = CSV::Reader.create(File.open("org_codes_5"))
  #reader = CSV::Reader.create(File.open("org_codes_6"))
  #reader = CSV::Reader.create(File.open("org_codes_7"))
  reader = CSV::Reader.create(File.open("org_codes_8"))
  
  # skip first two rows
  reader.shift
  reader.shift
  
  #id = 100000
  #id = 100998
  #id = 101998
  #id = 102998
  #id = 103998
  #id = 104998
  #id = 105998
  id = 106998
  count = 1
  catIndex =1;
  catName = 'all0'
  
  $orgs[catName] ||= [ ]
  $cat_order << catName
  reader.each do |row|
    # puts id
    # cat, term, ctep_term, ctep_code = row
      nci_institute_code, name,city, state, country = row
 	
    $orgs[catName] << {
      :nci_institute_code => nci_institute_code,
      :name => name,
      :city => city,
      :state => state,
      :country => country,
      :id => id
    }
    
 	if ( count.modulo(25) == 0 )
 		catName = 'all' << catIndex.to_s
 		catIndex +=1
 		$orgs[catName] ||= [ ]
 		$cat_order << catName
 		#puts catName
 	end
   
    id += 1
    count +=1
  end
end

def create()
  puts <<END
class OrganizationCodes extends edu.northwestern.bioinformatics.bering.Migration {
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
        execute("delete from csm_pg_pe where pg_pe_id >= 1015 and  pg_pe_id <= 8013 ");
        execute("delete from CSM_PROTECTION_GROUP where protection_group_id  <= -15 ");
        execute("delete from csm_protection_element where protection_element_id <= -15 ");
        execute("delete from csm_group where group_id <= -15 ");
        execute("DELETE from organizations where id >= 100000 and id < 110000")
    }
}
END
end

def print_inserts(cat, index)
  category_id = 200 + index + 1
 
  
  #puts "        // #{cat} (#{$terms_by_cat[cat].size} terms)"
  puts "        // #{cat} (#{$orgs[cat].size} terms)"
  #puts "        insert('ctc_categories', [version_id: 2, id: #{category_id}, name: '#{cat}'], primaryKey: false)"
  #$terms_by_cat[cat].each do |t|
  $orgs[cat].each do |t|
    cols = ""
    groupName = "gov.nih.nci.cabig.caaers.domain.Organization."
    groupDesc = ""
    
    [:id, :nci_institute_code, :name, :city, :state, :country].each do |colname|
      value = t[colname]
      cols << "#{colname}: #{value.inspect}, " if value && value.size > 0
    end
    cols = cols.slice!(0, cols.size-2)
    
    groupName << t[:nci_institute_code]
    groupDesc << t[:nci_institute_code]
    groupDesc << " group"

   
    puts "      insert('organizations', [ #{cols}], primaryKey: false) "
    puts "      insert('csm_group',[GROUP_ID: #{$groupId},GROUP_NAME:\"#{groupName}\",GROUP_DESC:\"#{groupDesc}\",application_id: -1], primaryKey: false); "
    puts "      insert('csm_protection_element',[protection_element_id: #{$groupId},protection_element_name:\"#{groupName}\",object_id: \"#{groupName}\",application_id: -1], primaryKey: false); "
    puts "      insert('CSM_PROTECTION_GROUP',[PROTECTION_GROUP_ID: #{$groupId},PROTECTION_GROUP_NAME:\"#{groupName}\", PARENT_PROTECTION_GROUP_ID:-5, application_id: -1, LARGE_ELEMENT_COUNT_FLAG:0], primaryKey: false);"
    puts "      insert('csm_pg_pe',[pg_pe_id:#{$pgpeid} ,protection_group_id: #{$groupId}, protection_element_id:#{$groupId}], primaryKey: false);"
  	$groupId -=1
  	$pgpeid +=1
  end
end




read()
create()
