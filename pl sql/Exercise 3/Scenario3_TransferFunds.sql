CREATE TABLE Accounts (
    AccountID    NUMBER PRIMARY KEY,
    CustomerName VARCHAR2(100),
    Balance      NUMBER(15, 2)
);

INSERT INTO Accounts VALUES (101, 'Aryan',   80000.00);
INSERT INTO Accounts VALUES (102, 'Harsh',   25000.00);
INSERT INTO Accounts VALUES (103, 'Abhinav', 60000.00);
INSERT INTO Accounts VALUES (104, 'Aditya',  15000.00);
INSERT INTO Accounts VALUES (105, 'Ankit',   45000.00);

COMMIT;

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) IS
    v_from_balance  Accounts.Balance%TYPE;
    v_to_balance    Accounts.Balance%TYPE;
    v_from_name     Accounts.CustomerName%TYPE;
    v_to_name       Accounts.CustomerName%TYPE;

BEGIN
    SELECT Balance, CustomerName
    INTO   v_from_balance, v_from_name
    FROM   Accounts
    WHERE  AccountID = p_from_account;

    SELECT Balance, CustomerName
    INTO   v_to_balance, v_to_name
    FROM   Accounts
    WHERE  AccountID = p_to_account;

    DBMS_OUTPUT.PUT_LINE('Fund Transfer Initiated');
    DBMS_OUTPUT.PUT_LINE('From : ' || v_from_name || ' (Account ' || p_from_account || ')');
    DBMS_OUTPUT.PUT_LINE('To   : ' || v_to_name   || ' (Account ' || p_to_account   || ')');
    DBMS_OUTPUT.PUT_LINE('Amount: $' || TO_CHAR(p_amount, 'FM999,999,990.00'));
    DBMS_OUTPUT.PUT_LINE(' ');

    IF p_amount <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Transfer amount must be greater than zero.');
        RETURN;
    END IF;

    IF v_from_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE(
            'Error: Insufficient balance in account ' || p_from_account ||
            '. Available: $' || TO_CHAR(v_from_balance, 'FM999,999,990.00')
        );
        RETURN;
    END IF;

    UPDATE Accounts
    SET    Balance = Balance - p_amount
    WHERE  AccountID = p_from_account;

    UPDATE Accounts
    SET    Balance = Balance + p_amount
    WHERE  AccountID = p_to_account;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(
        v_from_name || ' New Balance: $' ||
        TO_CHAR(v_from_balance - p_amount, 'FM999,999,990.00')
    );
    DBMS_OUTPUT.PUT_LINE(
        v_to_name || ' New Balance: $' ||
        TO_CHAR(v_to_balance + p_amount, 'FM999,999,990.00')
    );
    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('Transfer Successful');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One or both account IDs not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END TransferFunds;
/

BEGIN
    TransferFunds(101, 102, 10000);
END;
/
