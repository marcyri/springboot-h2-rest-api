# springboot-h2-rest-api
Small rest-api service

### For Docker with H2 database run:
`cd docker`<br />
`docker build -t marcyri/h2-with-init .`<br />
`docker container run --publish 9092:9082 -p 8082:8082 --detach --name health-patient-service-docker-container marcyri/h2-with-init`<br />
*useful tips:* for H2 to work with tcp , you need to start H2 in server mode<br />
this already done in Dockerfile<br />
