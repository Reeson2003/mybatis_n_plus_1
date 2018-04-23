drop table if exists public.author;

CREATE TABLE if not exists public.author(
    author_name character varying(16) NOT NULL,

    author_id bigint NOT NULL
);

drop table if exists public.post;

create  table if not exists public.post(
    post_text character varying(256) NOT NULL,

    post_id bigint not null
);

drop table if exists author_post;

create table if not exists author_post(
    author_id bigint NOT NULL,

    post_id bigint not null
);

insert into "public".author (author_name, author_id) values ('ivan', 1);
insert into "public".author (author_name, author_id) values ('ivan', 2);
insert into "public".author (author_name, author_id) values ('ivan', 3);

insert into "public".post (post_text, post_id) values ('hello0', 1);
insert into "public".post (post_text, post_id) values ('hello1', 2);
insert into "public".post (post_text, post_id) values ('hello2', 3);
insert into "public".post (post_text, post_id) values ('hello3', 4);

insert into "public".author_post (author_id, post_id) values (1,1);
insert into "public".author_post (author_id, post_id) values (1,2);
insert into "public".author_post (author_id, post_id) values (1,3);
insert into "public".author_post (author_id, post_id) values (2,1);
insert into "public".author_post (author_id, post_id) values (2,4);
