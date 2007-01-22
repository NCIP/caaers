create table nas (
 na bytea primary key
);

create table handles
(
  prefix       bytea not null,
  handle       bytea not null,
  idx          integer not null,
  type         bytea,
  data         bytea,
  ttl_type     integer,
  ttl          integer,
  timestamp    integer,
  refs         varchar(16),
  admin_read   boolean,
  admin_write  boolean,
  pub_read     boolean,
  pub_write    boolean
);