>  **Example run/build scripts can be found in the `scripts` folder**

<br>

# [Plain JavaFX Project, not recomended](https://www.jetbrains.com/help/idea/javafx.html)
1. Install the sdk somewhere
2. Create a new project (javafx)
3. Add javafx to the library (java library)
4. Add VM arguments to the run config:
`--module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml`



# [Maven JavaFX Project](https://openjfx.io/openjfx-docs/)
1. Create a new project (maven)
2. From archetype (add new archetype, if not exists in the list)
  - GroupId: `org.openjfx`
  - ArtifactId: `javafx-maven-archetypes`
  - Version: `0.0.5`
3. Enter the project name and artifact coordinates (like with spring)
4. Change the value of `archetypeArtifactId` to `javafx-archetype-fxml`
5. Add the `javafx-version` property and set it to `11`
6. Delete the `module-info.java`, it only creates problems and nobody needs it
7. Add a run configuration (maven)
  - Command line: `clean compile javafx:run`

## Add Jackson
1. Add jackson to the `dependencies` in `pom.xml`:
```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.12.3</version>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.datatype</groupId>
  <artifactId>jackson-datatype-jsr310</artifactId>
  <version>2.12.3</version>
</dependency>
```
2. To use Jackson without problems, you have to [configure it like spring](https://www.baeldung.com/spring-boot-customize-jackson-objectmapper#default-configuration) does:
```java
public static ObjectMapper getJsonMapper() {
  final ObjectMapper json = new ObjectMapper();
  json.findAndRegisterModules();
  json.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
  json.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  json.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  return json;
}
```



# Spring Project
1. Create a new project (spring initializr)
2. Enter the settings, make sure to set:
  - Type: `Maven`
  - Language: `Java`
  - Packaging: `Jar`
  - java version: `11`
3. Add the dependencies
  - Developer Tools
    - Spring Boot DevTools
    - Lombok
  - Web
    - Spring Web
  - SQL
    - Spring Data JPA
    - H2 Database
    - MySQL Driver
  - IO
    - Validation
4. Enter the project name
5. Add the `application.properties` in `main/resources` (mysql)
```ini
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.username=usr
spring.datasource.password=pwd
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto=update

server.error.include-message=always
server.port=8080
```
6. Create a resources folder under `test` and add the `application.properties` (in-memory database)
```ini
spring.datasource.url=jdbc:h2:mem:db
spring.datasource.username=usr
spring.datasource.password=pwd
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

server.error.include-message=always
server.port=8080
```
7. Make sure to start your database before running the spring app (not necessary for running the tests)

## Add Docker
1. Copy the `docker-compose.yml` and the `wait-for-it.sh` files to the project
  - Make sure the volume for the jar is correct, `./target/<artifactId>-<version>.jar` (see `pom.xml`)
2. Make sure to update the `volume` of the `app` service
3. Add a run configuration (docker-compose)
  - Set the compose file and services
  - Add a maven goal before launch, command line: `-dskipTests clean package`



# Android Project
1. Create a new project (empty activity)
2. Configure the project
  - Language: `Kotlin`
  - Minimum SDK: `API 26, Android 8.0 Oreo`

## Add Retrofit
1. Add retrofit dependencies to the `build.gradle` file (the module one under `Gradle Scripts`)
```gradle
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-jackson:2.9.0'
implementation 'com.fasterxml.jackson.core:jackson-databind:2.12.3'
implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.3'
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9'
```
2. Add the required permissions to the `AndroidManifest.xml` file (under `manifests`)
- As children of `manifest`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```
- As an attribute in `application`:
```xml
android:usesCleartextTraffic="true"
```
3. To use Retrofit without problems, you have to [configure Jackson like spring](https://www.baeldung.com/spring-boot-customize-jackson-objectmapper#default-configuration) does:
```kt
val json = ObjectMapper()
json.findAndRegisterModules()
json.disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
json.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
json.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

val retrofit = Retrofit.Builder()
  .baseUrl("http://WRONG_URL:8080")
  .addConverterFactory(JacksonConverterFactory.create(json))
  .build()
```

4. **Don't forget to add your activities to the `AndroidManifest.xml` file (under `manifests`)**
