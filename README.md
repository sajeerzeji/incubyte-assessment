# Spring Boot String Calculation Service

This project is a simple Spring Boot application intended to showcase unit testing. It provides a single service for string calculations, specifically adding numbers provided as strings with delimiters.

## Requirements

- Java 17
- Spring Boot 3.2.2

## Usage

You can just run the maven test and see how the calculations are working

### Adding Numbers

The service class `StringCalculatorService` provides a method `add` which adds numbers inputted as strings with delimiters. For example:

```java
StringCalculatorService stringCalculatorService = new StringCalculatorService();
int result = stringCalculatorService.add("1,2,3");
System.out.println(result); // Output: 6
