create table if not exists customer
(
    id bigint not null
        constraint customer_pkey
            primary key,
    m_created_at timestamp not null,
    m_updated_at timestamp,
    m_version bigint not null,
    address varchar(255),
    first_name varchar(255),
    last_name varchar(255)
);

create table if not exists customer_aud
(
    id bigint not null
        constraint customer_pkey
            primary key,
    m_created_at timestamp not null,
    m_updated_at timestamp,
    m_version bigint not null,
    address varchar(255),
    first_name varchar(255),
    last_name varchar(255)
);

create table if not exists customer_order
(
    id bigint not null
        constraint customer_order_pkey
            primary key,
    m_created_at timestamp not null,
    m_updated_at timestamp,
    m_version bigint not null,
    notes text,
    customer_id bigint
        constraint fkf9abd30bhiqvugayxlpq8ryq9
            references customer
);

create table if not exists customer_customer_order
(
    customer_id bigint not null
        constraint fkhh5k9kfp9qvub7mj6k0yk1vux
            references customer,
    customer_orders_id bigint not null
        constraint uk_25tvggipl50p455ase8a7esqx
            unique
        constraint fkatb16jwp7lj4hd3gyov2bge9i
            references customer_order
);

create table if not exists product
(
    id bigint not null
        constraint product_pkey
            primary key,
    m_created_at timestamp not null,
    m_updated_at timestamp,
    m_version bigint not null,
    name varchar(255) not null,
    price numeric(19,2) not null
);

create table if not exists customer_order_product
(
    customer_order_id bigint not null
        constraint fk8s9o38bpvt7gxgbrkmqjm6vn5
            references customer_order,
    products_id bigint not null
        constraint fkm66ghgqb38d8htvbv3pu8oaim
            references product
);

create table if not exists revinfo
(
    rev integer not null
        constraint revinfo_pkey
            primary key,
    revtstmp bigint
);

create table if not exists customer_aud
(
    id bigint not null,
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
