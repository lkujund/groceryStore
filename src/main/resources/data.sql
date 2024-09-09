INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('FRUITS_AND_VEGETABLES');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('MEAT');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('FISH');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('DAIRY');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('OILS_AND_VINEGARS');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('SPICES');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('BAKERY');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('SWEETS_AND_PASTRIES');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('ALCOHOL_DRINKS');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('NON_ALCOHOL_DRINKS');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('TOBACCO');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('CANNED_FOODS');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('COFFEE_AND_TEA');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('BASIC_FOODSTUFFS');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('FROZEN_GOODS');
INSERT INTO GROCERY_CATEGORY(NAME) VALUES ('HOUSEHOLD_ITEMS');

INSERT INTO MEASURING_UNIT(NAME) VALUES ('KILOGRAM');
INSERT INTO MEASURING_UNIT(NAME) VALUES ('LITRE');
INSERT INTO MEASURING_UNIT(NAME) VALUES ('PIECE');
INSERT INTO MEASURING_UNIT(NAME) VALUES ('PACKAGE');

INSERT INTO GROCERY(NAME, CATEGORY_ID, MEASURING_UNIT_ID, MEASURE, PRICE, DESCRIPTION)
VALUES ('APPLE', 1, 1, 1.0, 1.19, 'Granny smith'),
       ('BEEF', 2, 1, 1.0, 11.04, 'Fresh beef'),
       ('TUNA STEAK', 3, 1, 1.0, 10.09, 'Yummy tuna'),
       ('12-PACK MILK', 4, 4, 1.0, 9.60, '12 litres of milk'),
       ('OLIVE OIL', 5, 2, 1.0, 12.33, 'Croatian quality olive oil'),
       ('CURRY', 6, 3, 1.0, 2.15, '50g package of curry'),
       ('CHIA BREAD', 7, 3, 1.0, 1.39, 'Freshly baked bread with chia seeds'),
       ('7DAYS CROISSANT', 8, 3, 1.0, 0.89, 'Double cream croissant');

-- INSERT INTO ROLE(ROLE)
-- VALUES('USER'),('ADMIN');
--
-- INSERT INTO USERS(USERNAME, EMAIL, PWD_HASH, FIRST_NAME, LAST_NAME, ROLE_ID)
-- VALUES ('user1','user1@a.hr','1234','Uso','Userich',1),
--        ('admin','admin@a.hr','1234','Ado','Adminich',2);
--
-- INSERT INTO PAYMENT_OPTION(OPTION)
-- VALUES('CASH ON DELIVERY'),('CARD'),('PAYPAL');