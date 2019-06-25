# FyleApi

How to start the FyleApi application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/api-dev-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

## Test using script
`bash ./check_script.sh`

## Usage

### Login
Log in using basic auth and obtain a JWT token.

#### Example request:

`curl -u user:011235 -X GET 'http://localhost:8080/fyle/getToken'`

#### Example response:

```
{
  "token": " eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuby1zdWJqZWN0IiwiZXhwIjoxNTYxNTM4MTAyfQ.uOh32SPOwNlWAiP6I0wzvct6sijdlB2iuCGBaDHPb34"
}
```

### BanksResource:
Will accept the JWT obtained from the login above and echo some values from the JWT back.

#### Example request:
`curl -X GET -H 'Authorization: Bearer  eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuby1zdWJqZWN0IiwiZXhwIjoxNTYxNTM4MTAyfQ.uOh32SPOwNlWAiP6I0wzvct6sijdlB2iuCGBaDHPb34' 'http://localhost:8080/banks?ifsc=ABNA0100332'`

#### Example response:

```
{
    "name": "THE ROYAL BANK OF SCOTLAND N V",
    "id": 110
}

```

### BranchesResource:
For getting details about branches with bank_name and city as parameter.

#### Example request:
`curl -X GET -H 'Authorization: Bearer  eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuby1zdWJqZWN0IiwiZXhwIjoxNTYxNTM4MTAyfQ.uOh32SPOwNlWAiP6I0wzvct6sijdlB2iuCGBaDHPb34' 'http://localhost:8080/branches?bank_name=ALLAHABAD%20BANK&city=KOLKATA'`

#### Example Response
```
[{
    "ifsc": "ALLA0210022",
    "bank_id": 11,
    "branch": "ALIPORE BRANCH KOLKATA",
    "address": "┬á1A, RONALDSHAY ROAD, KOLKATA - 700027",
    "city": "KOLKATA",
    "district": "KOLKATA",
    "state": "WEST BENGAL"
}]
```