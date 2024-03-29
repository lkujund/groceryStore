INSERT INTO GROCERY_CATEGORY(NAME)
VALUES('FRUITS_AND_VEGETABLES'),('MEAT'),('FISH'),('DAIRY'),
      ('OILS_AND_VINEGARS'),('SPICES'),('BAKERY'),('SWEETS_AND_PASTRIES'),
      ('ALCOHOL_DRINKS'),('NON_ALCOHOL_DRINKS'),('TOBACCO'),('CANNED_FOODS'),
      ('COFFEE_AND_TEA'),('BASIC_FOODSTUFFS'),('FROZEN_GOODS'),('HOUSEHOLD_ITEMS');

INSERT INTO MEASURING_UNIT(NAME)
VALUES('KILOGRAM'),('LITRE'),('PIECE'),('PACKAGE');

INSERT INTO GROCERY(NAME, CATEGORY_ID, MEASURING_UNIT_ID, MEASURE, PRICE, DESCRIPTION)
VALUES ('APPLE', 1, 1, 1.0, 1.19, 'Granny smith'),
       ('BEEF', 2, 1, 1.0, 11.04, 'Fresh beef'),
       ('TUNA STEAK', 3, 3, 1.0, 10.09, 'Yummy tuna'),
       ('12-PACK MILK', 4, 4, 1.0, 9.60, '12 litres of milk'),
       ('OLIVE OIL', 5, 2, 1.0, 12.33, 'Croatian quality olive oil'),
       ('CURRY', 6, 4, 1.0, 2.15, '50g package of curry'),
       ('CHIA BREAD', 7, 3, 1.0, 1.39, 'Freshly baked bread with chia seeds'),
       ('7DAYS CROISSANT', 8, 3, 1.0, 0.89, 'Double cream croissant');

INSERT INTO ROLE(ROLE)
VALUES('USER'),('ADMIN');

INSERT INTO USERS(USERNAME, EMAIL, PWD_HASH, FIRST_NAME, LAST_NAME, ROLE_ID)
VALUES ('user1','user1@a.hr','1234','Uso','Userich',1),
       ('admin','admin@a.hr','1234','Ado','Adminich',2);

INSERT INTO PAYMENT_OPTION(OPTION)
VALUES('CASH ON DELIVERY'),('CARD'),('PAYPAL');