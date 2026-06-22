DECLARE
    CURSOR c_due_loans IS
        SELECT c.CustomerID,
               c.Name,
               l.LoanID,
               l.LoanAmount,
               l.InterestRate,
               l.DueDate,
               (l.DueDate - SYSDATE) AS DaysRemaining
        FROM   Customers c
        JOIN   Loans l ON c.CustomerID = l.CustomerID
        WHERE  l.DueDate BETWEEN SYSDATE AND SYSDATE + 30
        ORDER BY l.DueDate ASC;

    v_reminder_count NUMBER := 0;

BEGIN
    DBMS_OUTPUT.PUT_LINE('Loan Due Date Reminder System');
    DBMS_OUTPUT.PUT_LINE('Date Today : ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE('Checking loans due by: ' || TO_CHAR(SYSDATE + 30, 'DD-MON-YYYY'));
    DBMS_OUTPUT.PUT_LINE(' ');

    FOR rec IN c_due_loans LOOP

        v_reminder_count := v_reminder_count + 1;

        DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
        DBMS_OUTPUT.PUT_LINE('REMINDER #' || v_reminder_count);
        DBMS_OUTPUT.PUT_LINE(
            'Dear ' || rec.Name || ','
        );
        DBMS_OUTPUT.PUT_LINE(
            'This is a reminder that your loan (Loan ID: ' || rec.LoanID || ') ' ||
            'of amount $' || TO_CHAR(rec.LoanAmount, 'FM999,999,990.00') ||
            ' at ' || rec.InterestRate || '% interest rate ' ||
            'is due on ' || TO_CHAR(rec.DueDate, 'DD-MON-YYYY') || '.'
        );
        DBMS_OUTPUT.PUT_LINE(
            'Days Remaining: ' || TRUNC(rec.DaysRemaining) || ' day(s).'
        );
        DBMS_OUTPUT.PUT_LINE(
            'Please ensure timely payment to avoid penalties.'
        );
        DBMS_OUTPUT.PUT_LINE(' ');

    END LOOP;

    DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');

    IF v_reminder_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No loans are due within the next 30 days.');
    ELSE
        DBMS_OUTPUT.PUT_LINE(
            'Total Reminders Sent: ' || v_reminder_count
        );
    END IF;

    DBMS_OUTPUT.PUT_LINE('Reminder Processing Complete');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
END;
/
