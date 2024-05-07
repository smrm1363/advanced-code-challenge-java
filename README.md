**Prerequisites**

- JDK 21
- IntelliJ (recommended) or your favorite Java IDE
- Recommended: Bash environment with installed 'curl' or Postman for testing

**Preamble**
     
Your mission would you decide to accept it:

As part of the multi-disciplinary development elite team **PIT** in Statista you are creating software to alleviate the 
issues from our Sales team. For this, a new requirement has been raised to implement a RESTful web service that stores 
_booking_ objects in memory and return information about them.

Note: A _booking_ is a request from any of our beloved customers to acquire one of our products. 

**Challenge**

The module **code-challenge** already contains the basic structure of a Spring Boot application for you.

The bookings to be stored have the following fields:
 - booking_id
 - description
 - price
 - currency
 - subscription_start_date
 - email
 - department

Please define as many departments as you will like, and create a unique method `doBusiness()` for each department. This 
is your time to shine, so the method implementations can be as simple, elegant, or complicated as you want. 

Feel free to select the best data type that, in your opinion, would define those fields the best.

You should not use a database or some other sort of persistence but come up with an own data structure to store the 
transactions.

In any moment where the implementation implies the usage of an external server (i.e. sending e-mail's) feel free to mock
creatively these external resources, keeping in mind that all real-world use-cases must be covered.

In general, we are looking for a good implementation, code quality, code resilience, code extensibility, code 
maintainability and how the implementation is tested.
Basically something you wouldn't be too embarrassed to push to production.

**API Specification:**

**POST /bookingservice/bookings**

Creates a new booking and sends an e-mail with the details.

**PUT /bookingservice/bookings/{booking_id}**

Insert, replace if already exists a booking.

**GET /bookingservice/bookings/{booking_id}**

Returns the specified booking as JSON.

**GET /bookingservice/bookings/department/{department}**

Returns a JSON list of all bookings ids with the given department.

**GET /bookingservice/bookings/currencies**

Returns a JSON list with all used currencies in the existing bookings.

**GET /bookingservice/sum/{currency}**

Returns the sum of all bookings prices with the given currency.

**GET /bookingservice/bookings/dobusiness/{booking_id}**

Returns the result of `doBusiness()` for the given booking corresponding department.


# Description how it is done
I tried to show some parts of my skills in writing code in a sample code challenge. Since this is just a testing project, I tried to keep the balance between the features and spending time on it, indeed, there could be more improvements in the real case as following:
- Unit tests and integration tests are not compelete with fully coverage, there are just some of them showing the skill in general.
- Security concerns are not covered
- docker file is so basic but could be more configured
- There is no database which in real case would be needed
- Many other potential improvements
## Tech-stack and architecture
- Java 21
- Spring boot
- REST
- Open API
- Hexagonal architecture
- Junit
- Bean validation
- Maven
- Docker

## How to run
You should have Java && Maven installed on your machine.

To run the tests
```
mvn test
```

To be able to build and run it via maven you should be in the **module directory** ***(/code-challenge)***
```
1. mvn clean install
2. mvn spring-boot:run
```

You could also use docker but this is optional, yould should be in the root of the project(please change the ports based on your free ports)
```
1. docker build -t booking-service .
2. docker run -p 8080:8080 booking-service
```

Open the Open-API page on your browser
[Open-API page]()http://localhost:8080/swagger-ui/index.html#)


- One note: regarding GET /bookingservice/bookings/department/{department} I assumed the {department} is the departments name, however, the department-number or id would be my preference in real case.


Finally, I am very thankful for your time and consideration. I will be happy to have the opportunity to answare your questions about my technichal decisions.

All the best.