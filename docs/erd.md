# ERD

```mermaid
erDiagram
    WCR_JOB_HISTORY {
        id int PK
        start_time timestamp
        end_time timestamp
        job_type WCR_JOB_TYPE
    }

    WCR_JOB_TYPE {
        id int PK
        
    }
```