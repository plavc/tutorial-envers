# Hibernate Envers Tutorial

## Side Effects

* For each insert hibernate execute two additional inserts. One in `revinfo` table and another `product_aud` table.
```
Hibernate: insert into product (m_created_at, m_updated_at, m_version, name, price, id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into revinfo (rev, revtstmp) values (null, ?)
Hibernate: insert into product_aud (revtype, revend, revend_tstmp, m_version, name, name_mod, price, price_mod, id, rev) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

```

* For each update hibernate execute at least two additional inserts.

## Maven Dependency
https://mvnrepository.com/artifact/org.hibernate/hibernate-envers
```
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-envers</artifactId>
    <version>5.4.22.Final</version>
</dependency>

```

## Spring Integration

```
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-envers</artifactId>
</dependency>
```