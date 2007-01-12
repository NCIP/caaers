INSERT 
(id, name, version)
VALUES
(1, 'default', 0)
INT0 studies;

INSERT 
(id, version, short_title, long_title, description,
 multi_institution_indicator, principal_investigator_code,
 principal_investigator_name, primary_sponsor_code,
 primary_sponsor_name, phase_code)
VALUES
(1, 0, 'short title', 'long title', 'description',
 'false', 'pi_code', 'pi_name', 'ps_code', 'ps_name',
 'p_code')
INTO sites
 
COMMIT;