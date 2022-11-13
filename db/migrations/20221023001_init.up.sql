begin;
create table articles(
  id serial primary key,
  title varchar(255),
  body text,
  created_at timestamp with time zone,
  updated_at timestamp with time zone
);
commit;
