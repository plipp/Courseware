# Introduction in the Playframework

Bases on the Typesafe Reactor project seeds from:
- [Java Seed](https://www.typesafe.com/activator/template/play-java-reactive-platform-15v01)
    - [Sources](https://github.com/playframework/playframework/tree/master/templates/play-java)
- [Scala Seed](https://www.typesafe.com/activator/template/play-scala-reactive-platform-15v01)
    - [Sources](https://github.com/playframework/playframework/tree/master/templates/play-scala)

## Topics

Inspired by [Play Framework: async I/O without the thread pool and callback hell](http://engineering.linkedin.com/play/play-framework-async-io-without-thread-pool-and-callback-hell)

### Thread Pool vs Events
TODO: 

### Basic Skeleton (Scala Seed)

- application.conf
- routes
   -> Controller 1
      - with simple action (no async)
      -> Result for 
	  -  index.scala.html
	  -> main.scala.html
    
- Initial Tests
    - ApplicationSpec with FakeApplication/Request...
    - IntegrationSpec with running Application (Selenium)
      - Starts play

- Running Play in IntelliJ: Play2 Run Configuration (or: sbt run)

### WebService Call

- Controller 2
    - with async call to other WS (-> Future)

### From List to Future

- map, flatMap ...

- recover

...

### Playing around

- sbt/console | Worksheets with play-interaction
