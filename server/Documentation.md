# -- Documentation for Backend -- <br>


# _inicial settings_:
### Folders to create before starting:
  - C:/faculty/images-students
  - C:/faculty/files-students
 
### Create docker container rabbitmq of the course:
  ```
  docker run -it --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management 
  ```
  - create queue ```courses-for-student```
  - create queue ```user-for-authentication```

### Download PostgreSQL and create these databases:
  - authentication
  - course
  - student 
 
# _microservices startup order_
  - 1°: Eureka server
  - 2°: Authentication
  - 3°: Course and Student
  - 4°: Gateway

# _ports_
- Eureka interface: Http://localhost:8761:
    - user: user
    - password: user
- RabbitMQ interface: Http://localhost:15672:
    - user: guest
    - password: guest
 
# _Student microservice_:
# endpoints:

### 📌 Find all students - (GET) host/student
    ⚙️Response status: 200
    🔐Access permission: 
        🔑 ADMIN 
        🔑 COORDINATOR
        🔑 DIRECTOR

### 📌 Find by registration - (GET) host/student/search/registration/{registration}
    ⚙️Response status: 200
    🔐Access permission: 
        🔑 ADMIN 
        🔑 COORDINATOR
        🔑 DIRECTOR

### 📌 Find by Id - (GET) host/student/search/id/{id}
    ⚙️Response status: 200
    🔐Access permission: 
        🔑 STUDENT
        🔑 ADMIN 
        🔑 COORDINATOR
        🔑 DIRECTOR

### 📌 Find all files - (GET) host/files
    ⚙️Response status: 200
    🔐Access permission: 
        🔑 ADMIN 
        🔑 COORDINATOR
        🔑 DIRECTOR

### 📌 Find file by reference - (GET) host/files/{reference-file}
    ⚙️Response status: 200
    🔐Access permission: 
        🔑 STUDENT
        🔑 ADMIN 
        🔑 COORDINATOR
        🔑 DIRECTOR

### 📌 Delete file by reference - (DELETE) host/files/{reference-file}
    ⚙️Response status: 204
    🔐Access permission: 
        🔑 STUDENT
        🔑 ADMIN 
        🔑 COORDINATOR
        🔑 DIRECTOR

### 📌 Find all images - (GET) host/student/images
    ⚙️Response status: 200
    🔐Access permission:
        🔑 ADMIN 
        🔑 COORDINATOR
        🔑 DIRECTOR

### 📌 Find image by reference - (GET) host/student/images/{reference-file}
    ⚙️Response status: 200
    🔐Access permission: 
        🔑 STUDENT
        🔑 ADMIN 
        🔑 COORDINATOR
        🔑 DIRECTOR

### * Delete image by reference - (GET) host/student/images/{reference-file} _ 200

### * Create student - (POST) host/student _ 201

### * Enable access and create curriculum - (PUT) host/student/enable/access/{id} _ 200

### * Find all courses - (GET) host/student/course/list _ 200
```
{
    "completeName": "Pedro Alves Rodrigues",
    "email": "pedro@gmail.com",
    "password": "123456",
    "cpf": "86859683058",
    "birthday": "2002-01-22",
    "city": "jequié",
    "nationatily": "brasileira",
    "ethnicity": "PRETA",
    "phone": "73900000000",
    "address": "rua a",
    "numberHouse": 64
}
```
### * Update student - (PUT) host/student/update _ 200
```
{
    "completeName": "samuel Cruz Rodrigues",
    "registration": "b959efd0-2",
    "email": "llll@gmail.com",
    "cpf": "86859683058",
    "birthday": "2002-01-22",
    "city": "jequié",
    "nationatily": "brasileira",
    "ethnicity": "PRETO",
    "phone": "73900000000",
    "address": "rua a",
    "numberHouse": 64
}
```
### * Upload files - (POST) host/student/upload/file _ 201
```
KEY                    TYPE                 VALUE
file                   file                 directory to file
username               text                 {username student}
fileType               text                 {CPF || RG || COMPLETATION}
```
### * Upload image profile - (POST) host/student/upload/image _ 201
```
KEY                    TYPE                 VALUE
file                   file                 directory to file
username               text                 {username student}
```
### * Disable student - (DELETE) host/student/delete/{id} _ 204

### * Enable student - (PUT) host/student/enable/{id} _ 204

### * Register documents for avaliation - (Post) host/student/files/documents/register _ 204
```
KEY                    TYPE                 VALUE
username               text                 {username student}
cpf                    file                 directory to file
rg                     file                 directory to file
completation           file                 directory to file
```

### * Send documents to the coordinator - (PUT) host/student/enable/access/verify/{id} _ 204
