spring-data-mongo
-----------------

## requirement
- docker 
- java 11
- gradle

## mongoDB example application build
    gradlew bootJar
    
## docker build command
    docker build . -t mongo-example
    
## run command
    docker-compose up
    
## API Test
account api test used account.http

reactive account api test used reactive-account.http
