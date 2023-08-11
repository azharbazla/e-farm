# e-Farm API

Aplikasi B2B (Business to Business) antara company dan farmer.
Aplikasi ini dibuat menggunakan Java Spring Boot dan database postgreSQL, dan menerapkan :
- MVC
- Spring Ioc
- Java Stream
- Native SQL Query
- Restful API

### Aplication Properties

Konfigurasi Aplikasi berisi config database, config jpa, serta config error-stacktrace :

```properties
spring.datasource.username={username-datasource}
spring.datasource.password={password-datasource}
spring.datasource.url=jdbc:postgresql://localhost:5432/{database-name}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.error.include-stacktrace=never
```

## Beberapa Endpoint yang tersedia

#### Mengelola data farmer

- **POST** `/farmer` : Menambahkan data farmer baru

  Request Body :

  ```json
  {
    "name": "string",
    "address": "string",
    "telephone": "string",
    "email": "string"
  }
  ```

- **GET** `/farmer` : Menampilkan semua data farmer

  Response Body :

  ```json
  {
    "statusCode": 200,
    "message": "Successfully Get All Farmers",
    "data": [
        {
            "id": "string",
            "name": "string",
            "address": "string",
            "telephone": "string",
            "email": "string"
        }
    ],
    "paging": {
        "currentPage": 1,
        "totalPage": 1,
        "size": 10
    }
  }
  ```
- **GET** `/farmer/{id}` : Menampilkan data farmer berdasarkan id

  Response Body :

  ```json
  {
    "statusCode": 200,
    "message": "Successfully Get Farmer",
    "data": {
        "id": "string",
        "name": "string",
        "address": "string",
        "telephone": "string",
        "email": "string"
    },
    "paging": null
  }
  ```
  
- **PUT** `/farmer` : update data farmer berdasarkan id (dalam json)

  Request Body :

  ```json
  {
    "id": "string",
    "name": "string",
    "address": "string",
    "telephone": "string",
    "email": "string"
  }
  ```

- **DELETE** `/farmer/{id}` : Menghapus data farmer berdasarkan id
  
  Response Body :

  ```json
  {
    "statusCode": 200,
    "message": "Successfully Delete Farmer",
    "data": null,
    "paging": null
  }
  ```
#### Mengelola data company

- **POST** `/company` : Menambahkan data company baru

  Request Body :

  ```json
  {
    "name": "string",
    "address": "string",
    "telephone": "string",
    "email": "string"
  }
  ```

- **GET** `/company` : Menampilkan semua data company

  Response Body :

  ```json
  {
    "statusCode": 200,
    "message": "Successfully Get All Companies",
    "data": [
        {
            "id": "string",
            "name": "string",
            "address": "string",
            "telephone": "string",
            "email": "string"
        }
    ],
    "paging": {
        "currentPage": 1,
        "totalPage": 1,
        "size": 10
    }
  }
  ```
- **GET** `/company/{id}` : Menampilkan data company berdasarkan id

  Response Body :

  ```json
  {
    "statusCode": 200,
    "message": "Successfully Get Company",
    "data": {
        "id": "string",
        "name": "string",
        "address": "string",
        "telephone": "string",
        "email": "string"
    },
    "paging": null
  }
  ```

- **PUT** `/company` : update data company berdasarkan id (dalam json)

  Request Body :

  ```json
  {
    "id": "string",
    "name": "string",
    "address": "string",
    "telephone": "string",
    "email": "string"
  }
  ```

- **DELETE** `/company/{id}` : Menghapus data company berdasarkan id

  Response Body :

  ```json
  {
    "statusCode": 200,
    "message": "Successfully Delete Company",
    "data": null,
    "paging": null
  }
  ```

#### Mengelola data product

- **POST** `/product` : Menambahkan data product baru

  Request Body :

  ```json
  {
    "name": "string",
    "description": "string",
    "category": "string",
    "farmerId": "string",
    "price": "long",
    "stock": "integer"
  }
  ```

- **GET** `/product` : Menampilkan semua data product (filter : hanya yang aktif)

  Response Body :

  ```json
  {
    "statusCode": 200,
    "message": "Successfully Get All Products",
    "data": [
        {
            "id": "string",
            "name": "string",
            "description": "string",
            "category": {
                "id": "string",
                "name": "string"
            },
            "farmer": {
                "id": "string",
                "name": "string",
                "address": "string",
                "telephone": "string",
                "email": "string"
            },
            "price": "long",
            "stock": "integer",
            "isActive": "boolean"
        }
    ],
    "paging": {
        "currentPage": 1,
        "totalPage": 1,
        "size": 10
    }
  }
  ```
- **GET** `/product/{id}` : Menampilkan data product berdasarkan id

  Response Body :

  ```json
  {
    "statusCode": 200,
    "message": "string",
    "data": {
        "id": "string",
        "name": "string",
        "description": "string",
        "category": {
            "id": "string",
            "name": "string"
        },
        "farmer": {
            "id": "string",
            "name": "string",
            "address": "string",
            "telephone": "string",
            "email": "string"
        },
        "price": "long",
        "stock": "integer",
        "isActive": "boolean"
    },
    "paging": null
  }
  ```

- **PUT** `/product` : update data product berdasarkan id (dalam json)

  Request Body :

  ```json
  {
    "id": "string",
    "name": "string",
    "description": "string",
    "category": "string",
    "farmerId": "string",
    "price": "long",
    "stock": "integer"
  }
  ```

- **DELETE** `/product/{id}` : Menghapus data product berdasarkan id

  Response Body :

  ```json
  {
    "statusCode": 200,
    "message": "Successfully Delete Product",
    "data": null,
    "paging": null
  }
  ```