# IngenieraSoftwareProyecto

**API**
* Se necesitan descargar las dependencias utilizando - mvn Install
* Version JDK-21
* La api esta diseñada con:
    - Un repository que se encarga de implementar las opciones del JPA para manejar las consultas a la DB.
    - Las Interfaces para seguir con los principios SOLID.
    - Modelos para el manejo del JPA.
    - Controladores para generar los endpoints.
    - Servicios para hacer el impl de las interfaces y agregar la logica de negocio.

**Instalar Maven**
* Descargar el zip y extraerlo https://maven.apache.org/download.cgi
* Copiar el path donde quedo instalado
* Abrir variables de entorno
* Agregar una nueva variable 
    - Nombre: MAVEN_HOME
    - Valor: El path donde esta la carpeta del maven ejemplo: C:\Users\Alejandro\apache-maven-3.9.9
* Buscar el Path en variables de entorno y agregar al final %MAVEN_HOME%\bin
* Reiniciar
* Probar mvn --v 

**Client**
* El frontend estara en react.js