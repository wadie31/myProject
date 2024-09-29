CREATE OR REPLACE PROCEDURE PROCESS_BATCH_PAYMENTS(
    p_customer_id IN UUID,
    p_transaction_count OUT INTEGER,
    p_total_amount OUT DECIMAL,
    p_error_code OUT VARCHAR2,
    p_error_message OUT VARCHAR2
) AS
    total_balance DECIMAL;
    total_amount DECIMAL := 0;
BEGIN
   
    p_transaction_count := 0;
    p_total_amount := 0;
    p_error_code := NULL;
    p_error_message := NULL;

    SELECT balance INTO total_balance FROM customer WHERE id = p_customer_id;

  
    FOR rec IN (SELECT * FROM payments WHERE customer_id = p_customer_id AND status = 'PENDING') LOOP
        total_amount := total_amount + rec.amount; -
    END LOOP;

    IF total_balance >= total_amount THEN
        SAVEPOINT before_processing;

        UPDATE customer
        SET balance = balance - total_amount 
        WHERE id = p_customer_id;

       
        UPDATE payments 
        SET status = 'COMPLETED', processed_at = SYSDATE 
        WHERE customer_id = p_customer_id AND status = 'PENDING';

       
        p_transaction_count := SQL%ROWCOUNT; 
        p_total_amount := total_amount; 

        COMMIT; 
    ELSE
        UPDATE payments 
        SET status = 'FAILED' 
        WHERE customer_id = p_customer_id AND status = 'PENDING';

        p_error_code := 'INSUFFICIENT_FUNDS';
        p_error_message := 'Not enough balance to process transactions.';
        ROLLBACK TO before_processing; 
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        p_error_code := SQLCODE;
        p_error_message := SQLERRM;
        ROLLBACK; 
END PROCESS_BATCH_PAYMENTS;