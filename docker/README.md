# springboot-h2-rest-api-health-prj
## Run application in docker containers. 
Start:<br />
```
mvn clean package
cd docker
docker-compose up --build
```
Open http://localhost:81/api/v1/patients to GET patients<br />
H2 database UI will be available at http://localhost:8082<br />
Login settings: JDBC URL=jdbc:h2:./h2database, username=<in properties file>, password=<in properties file><br />
Debug: Listening for transport dt_socket at address: 8090<br />
Stop:<br />
```
Ctrl+c
docker-compose down
```