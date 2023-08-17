# JavaApi

## Api Rest (Springboot 2.7 -  Java 11)
	
  - Must have well configured java 11 

### Steps to run

### 1. Start mysql database in a docker container

	* In terminal enter into folder /docker 	`cd docker/`
	* Execute `docker-compose up -d`
	

### 2. Run app

  Run app as an springboot app, go back to root folder (where is the pom.xml file)
  
		cd ..
		
  & execute
   
		mvn clean install
		mvn spring-boot:run
		
  Hibernate will create automatically the tables, if not, you can to execute the file `create.sql`
		
 
### 3. Get postman Collection 

  If you want to test it, There is a postman collection with the calls to the API here:

		src/main/resources/PostmanCollection
		

### 4. To Run Test
 
  In folder where is the pom.xml file, execute this:
	
		`mvn -Dtest=RestApplicationTests test`
		
	