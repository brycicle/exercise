# Exercise - Bryce Francis Deyto

### Tech
* [Spring Boot](https://spring.io/projects/spring-boot/)
* [Hibernate](https://hibernate.org/)
* [Feign](https://spring.io/projects/spring-cloud-openfeign)
* [Lombok](https://projectlombok.org/)
* [Liquibase](http://www.liquibase.org/)
* [Checkstyle](http://checkstyle.sourceforge.net/)
* [Spotbugs](https://spotbugs.github.io/)
* [PMD](https://pmd.github.io/)
* [ArchUnit](https://www.archunit.org/)
* [Hamcrest](http://hamcrest.org/JavaHamcrest/)
* [Springfox](https://springfox.github.io/springfox/)
* [Sleuth](https://spring.io/projects/spring-cloud-sleuth)
* [Docker](https://www.docker.com/)
* [Kubernetes](https://kubernetes.io/)
* [Google Cloud](https://cloud.google.com/)
* [AWS RDS](https://aws.amazon.com/rds/?trkCampaign=acq_paid_search_brand&sc_channel=ps&sc_campaign=acquisition_PH&sc_publisher=Google&sc_category=Database&sc_country=PH&sc_geo=APAC&sc_outcome=acq&sc_detail=amazon%20sql%20database&sc_content={adgroup}&sc_matchtype=e&sc_segment=476950688383&sc_medium=ACQ-P|PS-GO|Brand|Desktop|SU|Database|Solution|PH|EN|Sitelink&s_kwcid=AL!4422!3!476950688383!e!!g!!amazon%20sql%20database&ef_id=Cj0KCQjwytOEBhD5ARIsANnRjVh1AAtbJBHxczv8whKasju8HggYAfHIpSA-1LN7HfITHqP6QjHC-ycaAhVpEALw_wcB:G:s&s_kwcid=AL!4422!3!476950688383!e!!g!!amazon%20sql%20database)
* [Angular 10](https://angular.io/)
* [Bootstrap](https://getbootstrap.com/)
* [ngBootstrap](https://ng-bootstrap.github.io/#/home)

# Services

### Auth/Gateway Service
* [Swagger Docs](http://35.240.158.142:8000/swagger-ui/index.html)
* [Docker Repository](https://hub.docker.com/repository/docker/bryce27923/auth)

### Identification Service
* [Swagger Docs](http://35.186.153.252:8001/swagger-ui/index.html)
* [Docker Repository](https://hub.docker.com/repository/docker/bryce27923/identifiication)

### Address Service
* [Swagger Docs](http://34.87.189.125:8002/swagger-ui/index.html)
* [Docker Repository](https://hub.docker.com/repository/docker/bryce27923/address)

### Communication Service
* [Swagger Docs](http://34.87.173.62:8003/swagger-ui/index.html)
* [Docker Repository](https://hub.docker.com/repository/docker/bryce27923/communication)

### Communication Service
* [Swagger Docs](http://34.87.173.62:8003/swagger-ui/index.html)
* [Docker Repository](https://hub.docker.com/repository/docker/bryce27923/communication)

### Web Application
* [Docker Repository](https://hub.docker.com/repository/docker/bryce27923/web-exercise)
* [Live Server](http://34.126.77.114:4200/)

# Running the app
### Using Docker/Docker Compose
* Pull all images
* Create docker-compose.yaml file
```
version: '3.7'

services:
  auth-service:
    image: bryce27923/auth:latest
    mem_limit: 700m
    ports:
      - "8000:8000"
    networks:
      - exercise-network
    depends_on:
      - identification-service
      - address-service
      - communication-service

  identification-service:
    image: bryce27923/identification:latest
    mem_limit: 700m
    ports:
      - "8001:8001"
    networks:
      - exercise-network

  communication-service:
    image: bryce27923/communication:latest
    mem_limit: 700m
    ports:
      - "8003:8003"
    networks:
      - exercise-network

  address-service:
    image: bryce27923/address:latest
    mem_limit: 700m
    ports:
      - "8002:8002"
    networks:
      - exercise-network

  web-app-service:
    image: bryce27923/web-exercise:latest
    mem_limit: 2000m
    ports:
      - "4200:4200"
    networks:
      - exercise-network

networks:
  exercise-network:
```
* Open browser and run the app on http://localhost:4200

### Manual Setup
* Clone all repositories
* Use ```mvn spring-boot:run``` on the spring boot applications
* Use ```npm install``` on the angular application and run with ```npm start```
* Open browser and run the app on http://localhost:4200