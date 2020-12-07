create sequence hibernate_sequence;

create table customer
(
    id bigint generated by default as identity
        constraint customer_pkey
            primary key,
    m_created_at timestamp not null,
    m_updated_at timestamp,
    m_version bigint not null,
    address varchar(255),
    first_name varchar(255),
    last_name varchar(255)
);

create table customer_order
(
    id bigint generated by default as identity
        constraint customer_order_pkey
            primary key,
    m_created_at timestamp not null,
    m_updated_at timestamp,
    m_version bigint not null,
    notes text,
    customer_id integer generated by default as identity
        constraint fkf9abd30bhiqvugayxlpq8ryq9
            references customer
);

create table customer_customer_order
(
    customer_id integer generated by default as identity
        constraint fkhh5k9kfp9qvub7mj6k0yk1vux
            references customer,
    customer_orders_id integer generated by default as identity
        constraint uk_25tvggipl50p455ase8a7esqx
            unique
        constraint fkatb16jwp7lj4hd3gyov2bge9i
            references customer_order
);

create table product
(
    id bigint generated by default as identity
        constraint product_pkey
            primary key,
    m_created_at timestamp not null,
    m_updated_at timestamp,
    m_version bigint not null,
    name varchar(255) not null,
    price numeric(19,2) not null
);

create table customer_order_product
(
    customer_order_id integer generated by default as identity
        constraint fk8s9o38bpvt7gxgbrkmqjm6vn5
            references customer_order,
    products_id integer generated by default as identity
        constraint fkm66ghgqb38d8htvbv3pu8oaim
            references product
);

create table revinfo
(
    rev integer not null
        constraint revinfo_pkey
            primary key,
    revtstmp bigint
);

create table customer_aud
(
    id integer generated by default as identity,
    rev integer not null
        constraint fkgfwtmwfqmkddg7g0pg36luq54
            references revinfo,
    revtype smallint,
    revend integer
        constraint fk6rei85s3koe6kc9ybqgx7rp2b
            references revinfo,
    m_version bigint,
    address varchar(255),
    address_mod boolean,
    first_name varchar(255),
    first_name_mod boolean,
    last_name varchar(255),
    last_name_mod boolean,
    constraint customer_aud_pkey
        primary key (id, rev)
);

create table customer_order_aud
(
    id integer generated by default as identity,
    rev integer not null
        constraint fk4xw5sgr8ilkq1g3hvcm5w7965
            references revinfo,
    revtype smallint,
    revend integer
        constraint fk5hxni6ttnfoyb19rh0fy2o6vp
            references revinfo,
    m_version bigint,
    notes text,
    customer_id integer generated by default as identity,
    constraint customer_order_aud_pkey
        primary key (id, rev)
);

create table customer_order_product_aud
(
    rev integer not null
        constraint fkdp5ouc3sf5ru48bwgauqccg5d
            references revinfo,
    customer_order_id integer generated by default as identity,
    products_id integer generated by default as identity,
    revtype smallint,
    revend integer
        constraint fkecacsydqay8ksu7pq6qww25o5
            references revinfo,
    constraint customer_order_product_aud_pkey
        primary key (rev, customer_order_id, products_id)
);

create table product_aud
(
    id integer generated by default as identity,
    rev integer not null
        constraint fk9vwllld6jlw5xys1ay911oh1x
            references revinfo,
    revtype smallint,
    revend integer
        constraint fk5p6tj2b8lsyoofok68n5h7n57
            references revinfo,
    m_version bigint,
    name varchar(255),
    name_mod boolean,
    price numeric(19,2),
    price_mod boolean,
    constraint product_aud_pkey
        primary key (id, rev)
);

