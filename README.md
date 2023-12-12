```
   _____          _ _               ______         _                     _  _   
  / ____|        | (_)             |  ____|       | |                   | || |  
 | |     ___   __| |_ _ __   __ _  | |__ __ _  ___| |_ ___  _ __ _   _  | || |_ 
 | |    / _ \ / _` | | '_ \ / _` | |  __/ _` |/ __| __/ _ \| '__| | | | |__   _|
 | |___| (_) | (_| | | | | | (_| | | | | (_| | (__| || (_) | |  | |_| |    | |  
  \_____\___/ \__,_|_|_| |_|\__, | |_|  \__,_|\___|\__\___/|_|   \__, |    |_|  
                             __/ |                                __/ |         
                            |___/                                |___/         
```

#### Final Project for Coding Factory 4
- Athanasios Grigorakis
- grigorata@aueb.gr

#### Backend: http://localhost:8080
- Used Java Spring Boot 3.2
- Available javadoc and Swagger documentation.

#### Frontend: http://localhost:3000
- Used Custom HTML/CSS/JavaScript/jQuery
- Mobile-first, responsive design.

#### Database: http://127.0.0.1:3306/cf4
- Username: root
- Password: root
- Used MySQL

*The Database structure and data (included for demonstration purposes) can be created with the script: /assets/sql/create_database.sql*

#### Testing:

###### Backend:
- REST APIs tests with Postman and Swagger
- Custom internal logging system for monitoring
###### Frontend:
- Manual tests to ensure functionality
- Custom internal logging system for monitoring

#### Assets:
- Domain Model: assets/ddd/domainModel.png
- Javadoc: assets/javadoc/com/athgri/finalproject/utilities/package-summary.html
- Swagger URL: http://localhost:8080/swagger-ui/index.html#/
- Postman Collections: assets/postman/
- SQL Scripts: assets/sql

  ---

#### Description
This projects implements a car inventory management system which involves a user authentication system, a user session management system, a user management system and a favorites list management system 

- Users need to create an account and must login with their credentials so as to be authenticated and create a unique session.

- Users are able to manage their account and can change password and/or e-mail and they can also permantly delete their account if they want to.

- Users can perform CRUD operations on the inventory. <br>
  Different allowance to perform this operations exists based on user role. <br>
	- Role: user can view & search inventory items
	- Role: admin can additionally add, edit, delete inventory items

- Users are able to create a favorites items list by adding / removing the desired items that are available in the inventory.

- The search functionality supports to either search by an item's ID or by an item's name (name for the current car inventory means matches by either brand or model)

  ---

#### Database Snapshot <br>
  *This is just for reference and do not reflect the actual column names or include all columns of the tables in the database*
###### Users
| ID  | Username | Password   | Role  |
|-----|----------|------------|-------|
| 350 | Senna    | admin      | admin |
| 354 | Stig     | user       | user  |
| 402 | Hamilton | Hamilton1! | user  |

###### Favorites
| ID  | Items List |
|-----|------------|
| 402 | [302]      |

###### Cars
| ID  | Brand         | Model                  | Year |
|-----|---------------|------------------------|------|
| 1   | Lancia        | Delta HF Integrale 16v | 1989 |
| 2   | Ford          | Model A                | 1929 |
| 3   | Lotus         | Europa S2              | 1970 |
| 4   | Mercedes Benz | 220 SB                 | 1963 |
| 5   | Chrysler      | Imperial Airflow Coupe | 1935 |
| 6   | Chevrolet     | Corvette Stingray      | 1966 |
| 7   | Ferrari       | Mondial Cabriolet      | 1985 |
| 8   | Cadillac      | EL Dorado Brougham     | 1957 |
| 9   | Rolls Royce   | Silver Cloud III Coupe | 1965 |
| 10  | Ford          | Mustang Mach 1         | 1970 |
| 302 | Fiat          | Punto GT               | 1993 |
