insert into caaers_users (id,login_name,salt,token,token_time,password_last_set,last_login,num_failed_logins) 
select nextval('caaers_users_id_seq'),login_id,salt,token,token_time,password_last_set,last_login,num_failed_logins
from research_staffs where login_id is not null;

insert into caaers_users (id,login_name,salt,token,token_time,password_last_set,last_login,num_failed_logins) 
select nextval('caaers_users_id_seq'),login_id,salt,token,token_time,password_last_set,last_login,num_failed_logins
from investigators where login_id is not null and allowed_to_login = true;

DELETE FROM caaers_users WHERE login_name in
(SELECT login_name FROM caaers_users cu
GROUP BY login_name
HAVING COUNT(*) > 1)