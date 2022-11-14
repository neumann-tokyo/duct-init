begin;
create table articles(
  id serial primary key,
  title varchar(255),
  body text,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp
);
commit;
