UPDATE ctc_grades
SET grade_text = 'Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated.'
WHERE grade_code = 1 AND term_id IN (
    SELECT t.id
    FROM ctc_categories c
    JOIN ctc_terms t ON t.category_id = c.id
    JOIN ctc_grades g ON t.id = g.term_id
    WHERE c.version_id = 4 AND t.other_required = true AND grade_code = 1
);

UPDATE ctc_grades
SET grade_text = 'Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL.'
WHERE grade_code = 2 AND term_id IN (
    SELECT t.id
    FROM ctc_categories c
    JOIN ctc_terms t ON t.category_id = c.id
    JOIN ctc_grades g ON t.id = g.term_id
    WHERE c.version_id = 4 AND t.other_required = true AND grade_code = 2
);

UPDATE ctc_grades
SET grade_text = 'Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL.'
WHERE grade_code = 3 AND term_id IN (
    SELECT t.id
    FROM ctc_categories c
    JOIN ctc_terms t ON t.category_id = c.id
    JOIN ctc_grades g ON t.id = g.term_id
    WHERE c.version_id = 4 AND t.other_required = true AND grade_code = 3
);

UPDATE ctc_grades
SET grade_text = 'Life-threatening consequences; urgent intervention indicated.'
WHERE grade_code = 4 AND term_id IN (
    SELECT t.id
    FROM ctc_categories c
    JOIN ctc_terms t ON t.category_id = c.id
    JOIN ctc_grades g ON t.id = g.term_id
    WHERE c.version_id = 4 AND t.other_required = true AND grade_code = 4
);

UPDATE ctc_grades
SET grade_text = 'Death related to AE.'
WHERE grade_code = 5 AND term_id IN (
    SELECT t.id
    FROM ctc_categories c
    JOIN ctc_terms t ON t.category_id = c.id
    JOIN ctc_grades g ON t.id = g.term_id
    WHERE c.version_id = 4 AND t.other_required = true AND grade_code = 5
);
