class CTCAE4_Grades extends edu.northwestern.bioinformatics.bering.Migration {

    void up() {

      if (databaseMatches('oracle')) {
    	  execute("DELETE FROM ctc_grades WHERE id IN (SELECT g.id FROM ctc_categories c JOIN ctc_terms t ON t.category_id = c.id JOIN ctc_grades g ON t.id = g.term_id WHERE c.version_id = 4 AND t.other_required = 1)")
      } else {
    	  execute("DELETE FROM ctc_grades WHERE id IN (SELECT g.id FROM ctc_categories c JOIN ctc_terms t ON t.category_id = c.id JOIN ctc_grades g ON t.id = g.term_id WHERE c.version_id = 4 AND t.other_required = true)")
      }

      insert('ctc_grades', [id: 43041, term_id: 4111, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43042, term_id: 4111, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43043, term_id: 4111, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43044, term_id: 4111, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43045, term_id: 4111, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 43189, term_id: 4147, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43190, term_id: 4147, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43191, term_id: 4147, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43192, term_id: 4147, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43193, term_id: 4147, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 43194, term_id: 4148, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43195, term_id: 4148, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43196, term_id: 4148, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43197, term_id: 4148, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43198, term_id: 4148, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 43227, term_id: 4157, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43228, term_id: 4157, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43229, term_id: 4157, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43230, term_id: 4157, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43231, term_id: 4157, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 43264, term_id: 4168, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43265, term_id: 4168, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43266, term_id: 4168, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43267, term_id: 4168, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43268, term_id: 4168, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)
      
      insert('ctc_grades', [id: 43350, term_id: 4193, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43351, term_id: 4193, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43352, term_id: 4193, grade_code: "3", grade_text: "Severe or medically significant but not immediately sight-threatening; hospitalization or prolongation of existing hospitalization indicated; disabling;  limiting self care ADL"], primaryKey: false)
      insert('ctc_grades', [id: 43353, term_id: 4193, grade_code: "4", grade_text: "Sight-threatening consequences; urgent intervention indicated; blindness (20/200 or worse) in the affected eye"], primaryKey: false)

      insert('ctc_grades', [id: 43850, term_id: 4310, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43851, term_id: 4310, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43852, term_id: 4310, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43853, term_id: 4310, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43854, term_id: 4310, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 43925, term_id: 4334, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43926, term_id: 4334, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43927, term_id: 4334, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43928, term_id: 4334, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43929, term_id: 4334, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 43984, term_id: 4350, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43985, term_id: 4350, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43986, term_id: 4350, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 43987, term_id: 4350, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 43988, term_id: 4350, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 44012, term_id: 4356, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44013, term_id: 4356, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 44014, term_id: 4356, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 44015, term_id: 4356, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44016, term_id: 4356, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 44294, term_id: 4432, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44295, term_id: 4432, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 44296, term_id: 4432, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of existing hospitalization indicated; disabling; limiting self care ADL"], primaryKey: false)
      insert('ctc_grades', [id: 44297, term_id: 4432, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44298, term_id: 4432, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 44649, term_id: 4510, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44650, term_id: 4510, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 44651, term_id: 4510, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of existing hospitalization indicated; disabling; limiting self care ADL"], primaryKey: false)
      insert('ctc_grades', [id: 44652, term_id: 4510, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44653, term_id: 4510, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 44775, term_id: 4548, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44776, term_id: 4548, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 44777, term_id: 4548, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of existing hospitalization indicated; disabling; limiting self care ADL"], primaryKey: false)
      insert('ctc_grades', [id: 44778, term_id: 4548, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44779, term_id: 4548, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 44884, term_id: 4572, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44885, term_id: 4572, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 44886, term_id: 4572, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 44887, term_id: 4572, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 44888, term_id: 4572, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45038, term_id: 4618, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45039, term_id: 4618, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45040, term_id: 4618, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45041, term_id: 4618, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45042, term_id: 4618, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45261, term_id: 4681, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45262, term_id: 4681, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45263, term_id: 4681, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45264, term_id: 4681, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45265, term_id: 4681, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45275, term_id: 4686, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45276, term_id: 4686, grade_code: "2", grade_text: "Moderate, local or noninvasive intervention indicated; limiting instrumental ADL"], primaryKey: false)
      insert('ctc_grades', [id: 45277, term_id: 4686, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45278, term_id: 4686, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45279, term_id: 4686, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45354, term_id: 4706, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45355, term_id: 4706, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45356, term_id: 4706, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; disabling;  limiting self care ADL"], primaryKey: false)
      insert('ctc_grades', [id: 45357, term_id: 4706, grade_code: "4", grade_text: "Life-threatening consequences; hospitalization or urgent intervention indicated"], primaryKey: false)
      insert('ctc_grades', [id: 45358, term_id: 4706, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45428, term_id: 4726, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45429, term_id: 4726, grade_code: "2", grade_text: "Moderate, local or noninvasive intervention indicated; limiting instrumental ADL"], primaryKey: false)
      insert('ctc_grades', [id: 45430, term_id: 4726, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45431, term_id: 4726, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45432, term_id: 4726, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45609, term_id: 4777, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45610, term_id: 4777, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45611, term_id: 4777, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45612, term_id: 4777, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45613, term_id: 4777, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45862, term_id: 4836, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45863, term_id: 4836, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45864, term_id: 4836, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of existing hospitalization indicated; disabling; limiting self care ADL"], primaryKey: false)
      insert('ctc_grades', [id: 45865, term_id: 4836, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45866, term_id: 4836, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45967, term_id: 4870, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45968, term_id: 4870, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45969, term_id: 4870, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45970, term_id: 4870, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45971, term_id: 4870, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45975, term_id: 4872, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45976, term_id: 4872, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45977, term_id: 4872, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45978, term_id: 4872, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45979, term_id: 4872, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 45980, term_id: 4873, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45981, term_id: 4873, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45982, term_id: 4873, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 45983, term_id: 4873, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 45984, term_id: 4873, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)

      insert('ctc_grades', [id: 46045, term_id: 4890, grade_code: "1", grade_text: "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."], primaryKey: false)
      insert('ctc_grades', [id: 46046, term_id: 4890, grade_code: "2", grade_text: "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."], primaryKey: false)
      insert('ctc_grades', [id: 46047, term_id: 4890, grade_code: "3", grade_text: "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."], primaryKey: false)
      insert('ctc_grades', [id: 46048, term_id: 4890, grade_code: "4", grade_text: "Life-threatening consequences; urgent intervention indicated."], primaryKey: false)
      insert('ctc_grades', [id: 46049, term_id: 4890, grade_code: "5", grade_text: "Death related to AE."], primaryKey: false)
    }

    void down() {
    }
}
