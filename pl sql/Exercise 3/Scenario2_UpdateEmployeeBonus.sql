CREATE TABLE Employees (
    EmployeeID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100),
    Department   VARCHAR2(50),
    Salary       NUMBER(15, 2)
);

INSERT INTO Employees VALUES (1, 'Aryan',   'Engineering', 60000.00);
INSERT INTO Employees VALUES (2, 'Harsh',   'Engineering', 55000.00);
INSERT INTO Employees VALUES (3, 'Abhinav', 'HR',          45000.00);
INSERT INTO Employees VALUES (4, 'Aditya',  'Engineering', 70000.00);
INSERT INTO Employees VALUES (5, 'Ankit',   'HR',          48000.00);

COMMIT;

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department    IN VARCHAR2,
    p_bonus_percent IN NUMBER
) IS
    CURSOR c_employees IS
        SELECT EmployeeID, Name, Salary
        FROM   Employees
        WHERE  Department = p_department;

    v_bonus_amount  NUMBER(15, 2);
    v_new_salary    NUMBER(15, 2);
    v_updated_count NUMBER := 0;

BEGIN
    DBMS_OUTPUT.PUT_LINE('Employee Bonus Update - Department: ' || p_department);
    DBMS_OUTPUT.PUT_LINE('Bonus Percentage: ' || p_bonus_percent || '%');
    DBMS_OUTPUT.PUT_LINE(' ');

    FOR rec IN c_employees LOOP
        v_bonus_amount := rec.Salary * (p_bonus_percent / 100);
        v_new_salary   := rec.Salary + v_bonus_amount;

        UPDATE Employees
        SET    Salary = v_new_salary
        WHERE  EmployeeID = rec.EmployeeID;

        v_updated_count := v_updated_count + 1;

        DBMS_OUTPUT.PUT_LINE(
            'Employee: ' || rec.Name ||
            ' | Old Salary: $' || TO_CHAR(rec.Salary, 'FM999,999,990.00') ||
            ' | Bonus: $' || TO_CHAR(v_bonus_amount, 'FM999,999,990.00') ||
            ' | New Salary: $' || TO_CHAR(v_new_salary, 'FM999,999,990.00')
        );
    END LOOP;

    IF v_updated_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE(' ');
        DBMS_OUTPUT.PUT_LINE('Total Employees Updated: ' || v_updated_count);
        DBMS_OUTPUT.PUT_LINE('Bonus Processing Complete');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END UpdateEmployeeBonus;
/

BEGIN
    UpdateEmployeeBonus('Engineering', 10);
END;
/
