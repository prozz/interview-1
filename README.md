# Used Libraries and Tools

* Maven (Build Automation Tool)
* Guice (Dependency Injection)
* Spark (Web Microframework)
* Unirest (HTTP client)
* Mockito and JUnit (Testing)
* Jackson (for handling JSON)
* Guava (various helper methods)

# Solution description

### Endpoints unit testing

My first goal is to establish proper unit tests environment.

Endpoints unit tests are set up in a way, that use real HTTP client inside service container. This way I may use mocked 
services, but whole communication is done on HTTP level. It's superior method to plain TDD on Route classes, because 
it makes testing different headers, parameters and URLs very easy while maintaining tests speed. Also note how easy 
and clean route unit tests are.

To make sure Guice, Spark and test env talk together, I added simple random route and service and wrote first test to 
make sure application starts. Then I made sure I can run application via maven command and it produces valid output WAR.

Now you may run:

* mvn clean 
* mvn test
* mvn exec:java

Example random endpoint is available here: http://localhost:4567/random

### Url fetcher to interact with any external source

Second goal is to implement low level UrlFetcher interface. Thanks to it we can easily mock any HTTP response in service
 layer. This class is extensible in case we need POST or other HTTP methods, as well as headers and other stuff in response. 
This is glue code and should be kept short. I tested it with existing repository then retired it's unit test with 
@Ignore annotation. We don't need frequently running tests to hit external services. It's also possible to mark it with 
JUnit category and just run in CI env from time to time. At this step I also was able to save Github response containing 
valid JSON for use in service tests.

### Github Service implementation

Main service implementation uses Jackson to handle JSON. Fortunately we may use it's autobinding features in ObjectMapper 
to make code concise. If this turns out to be problematic, there is possibilty to switch to manual JSON reading/writing.
At this step it's a good practice to extract ObjectMapper creation to some factory on side, so that it may be reused elsewhere. 
I'm not doing it here, to save some development time.

### Repositories endpoint

Final step is to implement repositories endpoint with use of just implemented github service and endpoint testing environment. 
To make sure responses are serialized properly and routes code is easily readable, I added ResponseBuilder class which may be 
extended in future for other types of responses. For clarity also Fixtures class was created to handle common domain objects 
used in tests. 

### Final touches

After above was done, I added:

* some package names refactoring for consistency
* fixing maven encoding warnings 
* integration end to end test

Before deploying to production I would do:

* remove (or allow to be available only from development environment) /random endpoint
* add cache on routes level to not hit github api rate limit too fast (probably via interceptor/filter mechanism)
* add exception resolver, so that all random exceptions ends as '500 - Something went wrong' instead of stacktrace shown to user
* add possibility to easily configure port the app listens on
* make sure generated WAR works well
* ask friend for review or some testing

To test and fire the app run:

`mvn clean test exec:java`

then go to:
`http://localhost:4567/repositories/prozz/ogs-notifier` (or any other owner/repository combo)