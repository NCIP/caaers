create table nas (
 na raw(512) primary key
);

create table handles
(
  prefix       raw(255) not null,
  handle       raw(255) not null,
  idx          number not null,
  type         raw(255),
  data         raw(345),
  ttl_type     number,
  ttl          number,
  timestamp    number,
  refs         varchar2(16),
  admin_read   varchar2(5),
  admin_write  varchar2(5),
  pub_read     varchar2(5),
  pub_write    varchar2(5)
);