# Flyway with expand and contract 

The Expand and Contract (Parallel Run) pattern is the enterprise standard for zero-downtime database changes. It allows old versions (running in production) and new versions (deploying via rolling/canary updates) to run concurrently against the database without errors.

<br>

<b>Scenario</b>: We have a customers table with a single full_name column. Business requirements change, and we need to split it into first_name and last_name without taking the service down or corrupting live reads and writes.

<br>

Flyway automatically creates and manages a tracking table.
<code>docker exec -it local-postgres psql -U postgres -d enterprisedb -c "SELECT installed_rank, version, description, type, success FROM flyway_schema_history;"</code>

