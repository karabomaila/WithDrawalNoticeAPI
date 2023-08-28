An API to allow investors to create withdrawal notices, get investor information and to create CSV file of notices.

End points:
- http://localhost:8080/withdrawalnotice/api/v1/investor GET, for getting investor information
- http://localhost:8080/withdrawalnotice/api/v1/investor POST, for adding investor information
- http://localhost:8080/withdrawalnotice/api/v1/investor/product POST, for adding investor product
- http://localhost:8080/withdrawalnotice/api/v1/notice  POST, for creating a withdrawal notice
- http://localhost:8080/withdrawalnotice/api/v1/statements GET, for getting a CSV file of notices

h2 database URL=http://localhost:8080/withdrawalnotice/h2-console/login.do