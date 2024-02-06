# -- Documentation for Backend -- <br>


# _inicial settings_:
- Folders to create before starting:
  - C:/faculty/images-students
  - C:/faculty/files-students
 
  - 
# _Student microservice_:
## endpoints:
### * Find all students - (GET) host/student _ 200

### * Find by registration - (GET) host/student/search/registration/{registration} _ 200

### * Find by Id - (GET) host/student/search/id/{id}

### * Find all files - (GET) host/files _ 200

### * Find all images - (GET) host/images _ 200

### * Create student - (POST) host/student _ 201
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
### * Delete by id - (DELETE) host/student/delete/{id} _ 204
