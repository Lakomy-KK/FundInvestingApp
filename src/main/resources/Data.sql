/* Set Fund types */
INSERT INTO fund_type (type_name, type_code) VALUES ('Polski', '1');
INSERT INTO fund_type (type_name, type_code) VALUES ('Zagraniczny', '2');
INSERT INTO fund_type (type_name, type_code) VALUES ('Pieniężny', '3');

/* Insert example Funds

Polish Funds */
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Aligo Capital Sp z o.o.', 1, 'AC');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('eFund S.A.', 1, 'EF');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Innovation Nest', 1, 'IN');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Catmood Sp. z o.o.', 1, 'CAT');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('IdeaLab Venture Capital', 1, 'IV');

/* Foreign Funds */
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Bridgewater Associates', 2, 'BA');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Man Group', 2, 'MG');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Baupost Group', 2, 'BG');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Two Sigma Investments', 2, 'TSC');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Bain Capital', 2, 'BC');

/* Cash Funds */
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Alior Pieniężny', 3, 'AP');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('MetLife Lokacyjny', 3, 'MLL');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Optimum Gotówkowy', 3, 'OG');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Prime Money INC', 3, 'PMINC');
INSERT INTO fund (fund_name, fund_type_id, fund_code)
VALUES ('Money Safe SDL', 3, 'MSDL');

/* End Inserting Funds

Investments styles */
INSERT INTO investition_style (style_name, pl_fund_investment, fr_fund_investment, cash_fund_investment)
VALUES (0, 0.2, 0.75, 0.05);
INSERT INTO investition_style (style_name, pl_fund_investment, fr_fund_investment, cash_fund_investment)
VALUES (1, 0.3, 0.6, 0.1);
INSERT INTO investition_style (style_name, pl_fund_investment, fr_fund_investment, cash_fund_investment)
VALUES (2, 0.4, 0.2, 0.4);
