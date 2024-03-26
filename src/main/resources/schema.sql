CREATE TABLE GROCERY_CATEGORY(
    ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(20)
);

CREATE TABLE MEASURING_UNIT(
    ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(20)
);

CREATE TABLE GROCERY(
    ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    CATEGORY_ID INT NOT NULL,
    MEASURING_UNIT_ID INT NOT NULL,
    MEASURE REAL NOT NULL,
    PRICE REAL NOT NULL,
    DESCRIPTION VARCHAR(50) NOT NULL,
    IMAGE BLOB
    FOREIGN KEY (CATEGORY_ID) REFERENCES GROCERY_CATEGORY(ID)
    FOREIGN KEY (MEASURING_UNIT_ID) REFERENCES MEASURING_UNIT(ID)
);

