# Binance Testnet

This is a testnet application for the Binance API. It provides a simple web interface for calling the different
endpoints of the Binance API and displaying the response.

# Requirements<br>

* Java 14
* Maven
* Docker (optional)

# Build and Run

To build the application, run the following command:

```
$ mvn clean package
```

This will compile the application and create an executable JAR file in the target directory.

To run the application, use the following command:

```
$ java -jar target/binance-testnet-0.0.1-SNAPSHOT.jar
```

This will start the application on port 8080 by default. You can access the application at http://localhost:8080 in your
web browser.

# Docker

To build a Docker image for the application, run the following command:

```
$ docker build -t binance-testnet .
```

This will create a Docker image for the application and tag it with the binance-testnet name.

To run the Docker image as a container, use the following command:

```
$ docker run -p 8080:8080 binance-testnet
```

This will start a Docker container for the application and map port 8080 on the host to port 8080 in the container. You
can access the application at http://localhost:8080 in your web browser.

# Endpoints

The following endpoints are available in the application:

* GET /ping: Calls the /v1/ping endpoint of the Binance API.
* GET /time: Calls the /v1/time endpoint of the Binance API.
* GET /exchangeInfo: Calls the /v1/exchangeInfo endpoint of the Binance API.
* GET /order: Calls the /v3/order endpoint of the Binance API to create a new order.

# License

This application is licensed under the MIT License. See the LICENSE file for details.
