
# --- !Ups

CREATE TABLE ORDERS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    CLIENT_ID BIGINT NOT NULL,
    ITEM_TYPE INT NOT NULL,
    ITEM_TAG VARCHAR(30) NOT NULL,
    BID_PX FLOAT8,
    CREATED TIMESTAMP NOT NULL,
    UPDATED TIMESTAMP NOT NULL,
    HAS_DEAL BOOLEAN NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ITEMS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    SOURCE_ID BIGINT NOT NULL,
    ASK_PX FLOAT8 NOT NULL,
    TAG VARCHAR(30) NOT NULL,
    TYPE INT NOT NULL,
    CREATED TIMESTAMP NOT NULL,
    UPDATED TIMESTAMP NOT NULL,
    IS_ACTIVE BOOLEAN NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE DEALS (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PARENT_ID BIGINT,
    CREATED TIMESTAMP NOT NULL,
    UPDATED TIMESTAMP NOT NULL,
    CLIENT_ID BIGINT NOT NULL,
    ITEM_ID BIGINT NOT NULL,
    ORDER_ID BIGINT NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE DEALS_DONE (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PARENT_ID BIGINT NOT NULL,
    CREATED TIMESTAMP NOT NULL,
    COMMENT VARCHAR,
    PRIMARY KEY (ID)
);

CREATE TABLE DEALS_REJ (
    ID BIGINT NOT NULL AUTO_INCREMENT,
    PARENT_ID BIGINT NOT NULL,
    CREATED TIMESTAMP NOT NULL,
    COMMENT VARCHAR,
    PRIMARY KEY (ID)
);

# --- !Downs

DROP TABLE ORDERS;
DROP TABLE ITEMS;
DROP TABLE DEALS;
DROP TABLE DEALS_DONE;
DROP TABLE DEALS_REJ;