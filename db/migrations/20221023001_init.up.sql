begin;
create table users(
  id serial primary key,
  email varchar(255),
  password_digest varchar(500),
  created_at timestamp with time zone,
  updated_at timestamp with time zone
);
commit;
