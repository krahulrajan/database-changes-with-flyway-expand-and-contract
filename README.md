# Flyway with expand and contract 

The Expand and Contract (Parallel Run) pattern is the enterprise standard for zero-downtime database changes. It allows old versions (running in production) and new versions (deploying via rolling/canary updates) to run concurrently against the database without errors.

<br>

<b>Scenario</b>: We have a customers table with a single full_name column. Business requirements change, and we need to split it into first_name and last_name without taking the service down or corrupting live reads and writes.

<br>

Flyway automatically creates and manages a tracking table.
<br>
<code>docker exec -it local-postgres psql -U postgres -d enterprisedb -c "SELECT installed_rank, version, description, type, success FROM flyway_schema_history;"</code>
<br>

Flyway looks inside src/main/resources/db/migration/ for versioned scripts (V1__..., V2__...).<br>
It executes those scripts in exact alphabetical/numerical order before Spring finishes booting up and before Hibernate's validation check runs.<br>
It logs a cryptographic SHA-256 hash of each script into flyway_schema_history. If anyone modifies an applied script, Flyway halts boot-up to prevent data corruption.<br>

In entity backward compatibility bridge is built using two specific software patterns: Dual-Write and Fallback-Read.




### Things to be taken care
1. Create two seperate database roles for mirgration and db management
2. Disable flyway
3. Configurations for avoiding data lose. 
4. Trigger automated DB snapshot / WAL backup.