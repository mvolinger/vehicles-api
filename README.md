<h3 style="text-align:center;"> 
	The Vehicles API
</h3>

<p style="text-align:center;">
  <a href="#scroll-project">Project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#woman_technologist-technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#information_source-how-to-use-it">How to use it</a>&nbsp;&nbsp;&nbsp;
</p>

## :scroll: Project

REST API to manage Ficticius Clean company vehicles fuel consumption.

## :woman_technologist: Technologies

- Java 11
- Gradle
- Spring Boot
- Lombok
- Swagger
- Junit
- Mockito
- Spring Data JPA  
- H2 Database

## :information_source: How to use it

### First, you will need to install:
- [Git][git]
- [Java 11][java]
- [IntelliJ][intellij]
- [Gradle Plugin][gradle]  
- [Lombok Plugin][lombok]

### Then clone the project to your machine
```bash
# using this command
$ git clone https://github.com/mvolinger/vehicles-api.git
```

### Running it
```
> Open vehicles-api with IntelliJ IDE
> Wait until Gradle build task finish
> Run VehiclesApiApplication with hotkey Shift+F10
```

### Using it
```bash
# Swagger
http://localhost:8080/swagger-ui.html

# Perform the creation of a vehicle
# Attention! manufactureDate must be dd-MM-yyyy
[POST] http://localhost:8080/api/vehicle

#Get information about vehicles fuel consumption by params
[GET] http://localhost:8080/api/vehicle?cityKm=10&fuelPrice=5.50&highwayKm=10

```
### Finally, you can always
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1490ee486efd2e3ba7da)
```
> Postman for Web is supported on latest Chrome, Firefox & Edge browsers.
> Although I got the warning "Something went wrong. We couldn’t fetch your collection due to some unforeseen circumstances. Try again.",
> Postman saved the collection 'Vehicles API' into my workspace menu.
```
---
Made by mvolinger :wave: [Get in touch!](https://www.linkedin.com/in/monisevolinger/)

[git]: https://git-scm.com/
[java]: https://adoptopenjdk.net/
[intellij]: https://jetbrains.com/idea/download/
[lombok]: https://plugins.jetbrains.com/plugin/6317-lombok
[gradle]: https://plugins.jetbrains.com/plugin/13112-gradle
