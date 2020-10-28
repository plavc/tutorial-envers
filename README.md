# Hibernate Envers Tutorial
![Badge](https://github.com/plavc/tutorial-envers/workflows/.github/workflows/maven.yml/badge.svg)


## Features
* easy to use JPA auditing framework
* history data is stored in separate tables (possible to store in separate schema)
* fetching data from primary tables is not impacted
* uses Hibernate listeners to populate history tables

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

## Resources

Hibernate Envers
* https://hibernate.org/orm/envers

Spring Envers
* https://github.com/spring-projects/spring-data-envers

## Alternatives

**Manually handling of historic data**

Preserve historic changes in the primary table.

Downside: 
* single table for old versions - impact SQL operations and table size
* with each query a revision id must be specified

Benefits:
* easy to implement
* updating and inserting is not impacted - one sql operation is sufficient

**Database level history tables**

Create separate history tables. A primary table contains only latest version.
History table is populated with triggers.

Downside:
* implementation of triggers is bound to database vendor
* fetching history data must be implemented separately
Benefits:
* populating history table is done on database level therefore is more eficient as alternatives
* history data is in a separate table