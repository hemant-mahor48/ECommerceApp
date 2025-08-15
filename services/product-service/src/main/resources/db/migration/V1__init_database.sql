create table if not exists category(
id bigint not null primary key,
description varchar(255),
name varchar(255)
);

create table if not exists product(
id bigint not null primary key,
name varchar(255),
description varchar(255),
available_qty double precision not null,
price numeric(38,2),
category_id bigint
            constraint categoryConstraint references category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;