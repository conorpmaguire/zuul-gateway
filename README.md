# Zuul Gateway v1.0

Requirements
------------

Write a server application that accepts requests over the network and sends those request to a second server. Have the second server respond to the requests of the client via the first server.

* The request should be a simple operation e.g. multiply two numbers.
* The application should be able to handle multiple concurrent requests.
* The exercise can be over engineered to show specific design choices.

Pros:
-----
* It works
* Good documentation. 
* Good logging
* Using build tool.
* Good use of interfaces
* Created specific exceptions

Cons:
-----
* Variable naming not following JLS standards
* Unit test coverage > 25%
* Error handling of configuration properties could be better
* Addition of new action e.g. Division would require additional Message Types and new Message Processors.
* Doesn’t ClientFacingServer doesn’t recover from FunctionServer outage.
* In case of FunctionServer outage error message in misleading


Solution
--------
This solution consists of two Spring Boot applications. The first application is the "Resource Server" which contains a RESTful interface which accepts a mathematical expression (e.g. 2*4) as a query parameter, evaluates the expression, and finally returns an integer result.


To Run Unit Tests
-----------------
The services and RESTful controllers all have unit or integration tests. To run the tests, run "`mvn test`" from the command line.

NOTE: In order to run "ProxyServerTest", please ensure that the Resource Server application is already running (on port 9080).

To Test the Applications
------------------------
The "Proxy Server" is configured to run on port 8080. The "Resource Server" is configured to run on port 9080. Run the server applications by first building an executable JAR file for each server application:

*c:/workspace/bloomberg/resource-server> mvn package*
*c:/workspace/bloomberg/proxy-server> mvn package*

This will produce an executable JAR 'resource-server-1.0-SNAPSHOT.jar'

Run the applications using the following commands:

*cd c:/workspace/bloomberg/resource-server/target*

*java -jar resource-server-1.0-SNAPSHOT.jar*

*cd c:/workspace/bloomberg/proxy-server/target*

*java -jar proxy-server-1.0-SNAPSHOT.jar*

Ensure both application servers have started and do not report any errors, then use a web browser to access the resource server **directly** as follows:

> http://localhost:8090/compute?expr=12*12

Now, the final test, ensure that you can access the resource server THROUGH the proxy server:

> http://localhost:8080/resource/compute?expr=12*10

