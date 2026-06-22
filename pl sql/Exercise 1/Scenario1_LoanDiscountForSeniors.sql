CREATE TABLE Customers (
    CustomerID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100),
    Age          NUMBER,
    Balance      NUMBER(15, 2),
    IsVIP        VARCHAR2(5) DEFAULT 'FALSE'
);

CREATE TABLE Loans (
    LoanID       NUMBER PRIMARY KEY,
    CustomerID   NUMBER REFERENCES Customers(CustomerID),
    LoanAmount   NUMBER(15, 2),
    InterestRate NUMBER(5, 2),
    DueDate      DATE
);

INSERT INTO Customers VALUES (1, 'Aryan',   65, 15000.00, 'FALSE');
INSERT INTO Customers VALUES (2, 'Harsh',   45, 8000.00,  'FALSE');
INSERT INTO Customers VALUES (3, 'Abhinav', 70, 22000.00, 'FALSE');
INSERT INTO Customers VALUES (4, 'Aditya',  30, 5000.00,  'FALSE');
INSERT INTO Customers VALUES (5, 'Ankit',   62, 11000.00, 'FALSE');

INSERT INTO Loans VALUES (101, 1, 50000.00, 8.50, SYSDATE + 20);
INSERT INTO Loans VALUES (102, 2, 30000.00, 9.00, SYSDATE + 45);
INSERT INTO Loans VALUES (103, 3, 70000.00, 7.75, SYSDATE + 10);
INSERT INTO Loans VALUES (104, 4, 20000.00, 10.00, SYSDATE + 60);
INSERT INTO Loans VALUES (105, 5, 40000.00, 8.00, SYSDATE + 25);

COMMIT;

DECLARE
    CURSOR c_customers IS
        SELECT c.CustomerID,
               c.Name,
               c.Age,
               l.LoanID,
               l.InterestRate
        FROM   Customers c
        JOIN   Loans l ON c.CustomerID = l.CustomerID;

    v_new_rate  Loans.InterestRate%TYPE;

BEGIN
    DBMS_OUTPUT.PUT_LINE(' Loan Interest Rate Discount Processing');
    DBMS_OUTPUT.PUT_LINE(' ');

    FOR rec IN c_customers LOOP

        IF rec.Age > 60 THEN
            v_new_rate := rec.InterestRate - 1;

            UPDATE Loans
            SET    InterestRate = v_new_rate
            WHERE  LoanID = rec.LoanID;

            DBMS_OUTPUT.PUT_LINE(
                'Customer: ' || rec.Name ||
                ' (Age: '    || rec.Age  || ')' ||
                ' | Loan ID: '       || rec.LoanID ||
                ' | Old Rate: '      || rec.InterestRate || '%' ||
                ' | New Rate: '      || v_new_rate || '%' ||
                ' --> Discount Applied!'
            );
        ELSE
            DBMS_OUTPUT.PUT_LINE(
                'Customer: ' || rec.Name ||
                ' (Age: '    || rec.Age  || ')' ||
                ' | Loan ID: '  || rec.LoanID ||
                ' | Rate: '     || rec.InterestRate || '%' ||
                ' --> No Discount (Age <= 60)'
            );
        END IF;

    END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE(' Processing Complete ');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
END;
/
