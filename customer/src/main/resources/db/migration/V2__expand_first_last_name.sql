-- Step 1: Add new columns as NULLABLE (Never add NOT NULL without defaults in expand!)
ALTER TABLE customers 
    ADD COLUMN first_name VARCHAR(120),
    ADD COLUMN last_name VARCHAR(120);

-- Step 2: Immediate data backfill for existing records
UPDATE customers 
SET first_name = split_part(full_name, ' ', 1),
    last_name = CASE 
        WHEN position(' ' in full_name) > 0 THEN substring(full_name from position(' ' in full_name) + 1)
        ELSE ''
    END
WHERE first_name IS NULL;