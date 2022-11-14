begin;

truncate table articles;

insert into articles
  (title, body)
values
  ('test', 'bodybodybody'),
  ('test2', 'aaaaaa')
;

commit;
