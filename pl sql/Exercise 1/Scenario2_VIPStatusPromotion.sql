DECLARE
    CURSOR c_customers IS
        SELECT CustomerID,
               Name,
               Balance,
               IsVIP
        FROM   Customers;

    v_vip_count     NUMBER := 0;
    v_non_vip_count NUMBER := 0;

BEGIN
    DBMS_OUTPUT.PUT_LINE('VIP Status Promotion Processing');
    DBMS_OUTPUT.PUT_LINE(' ');

    FOR rec IN c_customers LOOP

        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET    IsVIP = 'TRUE'
            WHERE  CustomerID = rec.CustomerID;

            v_vip_count := v_vip_count + 1;

            DBMS_OUTPUT.PUT_LINE(
                'Customer: '  || rec.Name ||
                ' | Balance: $' || TO_CHAR(rec.Balance, 'FM999,999,990.00') ||
                ' --> Promoted to VIP Status! IsVIP = TRUE'
            );
        ELSE
            UPDATE Customers
            SET    IsVIP = 'FALSE'
            WHERE  CustomerID = rec.CustomerID;

            v_non_vip_count := v_non_vip_count + 1;

            DBMS_OUTPUT.PUT_LINE(
                'Customer: '  || rec.Name ||
                ' | Balance: $' || TO_CHAR(rec.Balance, 'FM999,999,990.00') ||
                ' --> Does not qualify for VIP. IsVIP = FALSE'
            );
        END IF;

    END LOOP;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(' ');
    DBMS_OUTPUT.PUT_LINE('Summary');
    DBMS_OUTPUT.PUT_LINE('Total VIP Customers     : ' || v_vip_count);
    DBMS_OUTPUT.PUT_LINE('Total Non-VIP Customers : ' || v_non_vip_count);
    DBMS_OUTPUT.PUT_LINE(' Processing Complete ');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
END;
/
