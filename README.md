# JavaApi

## Api Rest (Springboot 2.7 -  Java 11)
	
  - Must have well configured java 11 

### Steps to run

### 1. Run app

  Run app as an springboot app
	
		mvn clean install
		mvn spring-boot:run 
 
### 2. Get postman Collection 

  If you want to test it, There is a postman collection with the calls to the API here:

		src/main/resources/PostmanCollection

### 3. To Run Test
 
  In folder where is the pom.xml file, execute this:
	
		`mvn -Dtest=RestApplicationTests test`
		
	