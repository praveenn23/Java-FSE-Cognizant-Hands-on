CREATE TABLE SavingsAccounts (
    AccountID   NUMBER PRIMARY KEY,
    CustomerName VARCHAR2(100),
    Balance     NUMBER(15, 2),
    AccountType VARCHAR2(20) DEFAULT 'SAVINGS'
);

INSERT INTO SavingsAccounts VALUES (1, 'Aryan',   50000.00, 'SAVINGS');
INSERT INTO SavingsAccounts VALUES (2, 'Harsh',   30000.00, 'SAVINGS');
INSERT INTO SavingsAccounts VALUES (3, 'Abhinav', 75000.00, 'SAVINGS');
INSERT INTO SavingsAccounts VALUES (4, 'Aditya',  20000.00, 'SAVINGS');
INSERT INTO SavingsAccounts VALUES (5, 'Ankit',   60000.00, 'SAVINGS');

COMMIT;

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
    CURSOR c_accounts IS
        SELECT AccountID, CustomerName, Balance
        FROM   SavingsAccounts
        WHERE  AccountType = 'SAVINGS';

    v_interest      NUMBER(15, 2);
    v_new_balance   NUMBER(15, 2);
    v_total_updated NUMBER := 0;

BEGIN
    DBMS_OUTPUT.PUT_LINE('Monthly Interest Processing Started');
    DBMS_OUTPUT.PUT_LINE(' ');

    FOR rec IN c_accounts LOOP
        v_interest    := rec.Balance * 0.01;
        v_new_balance := rec.Balance + v_interest;

        UPDATE SavingsAccounts
        SET    Balance = v_new_balance
        WHERE  AccountID = rec.AccountID;

        v_total_updated := v_total_updated + 1;

        DBMS_OUTPUT.PUT_LINE(
            'Account ID: ' || rec.AccountID ||
            ' | Customer: ' || rec.CustomerName ||
            ' | Old Balance: $' || TO_CHAR(rec.Balance, 'FM999,999,990.00') ||
            ' | Interest (1%): $' || TO_CHAR(v_interest, 'FM999,999,990.00') ||
            ' | New Balance: $' || TO_CHAR(v_new_balance, 'FM999,999,990.00')
        );
    END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('Total Accounts Updated: ' || v_total_updated);
    DBMS_OUTPUT.PUT_LINE('Monthly Interest Processing Complete');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END ProcessMonthlyInterest;
/

BEGIN
    ProcessMonthlyInterest;
END;
/
