# Random Number Generator in Spring Boot


To build the current code:
## Software requirements: 
- Docker

## pom.xml
Update the following value to the desire tag for the artifact created by maven:
```
	<version>development</version>
```

## Steps to build:
In the root directory:
```docker build -t random-number-app:development .```

This commmand will build an image running the tests included in the directory 
```
src/test/java/com/theworkshop/randomnumbergenerator
```

## Running the image: 
Run the following command so as to run the image: 

```docker run -p 8080:8080 random-number-app:development ```

This will start a container with the built image and exposing the port to the 8080

## Testing the WebApp:
Open your browser and use:

```localhost:8080/healthcheck```

A message should appear showing the current status: 

```
Status: Healthy
```

To use the application to create a list of random numbers use the following path: 

```localhost:8080//generateRandomNumbers?count=5&min=0&max=10```

#### Parameters:
- count - number of items
- min - minimum number to show
- max - maximum number to show

