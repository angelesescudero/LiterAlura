#**CHALLENGE LITERALURA**

En este emocionante desafío de programación, construi un catálogo de libros propio: el LiterAlura. 
Gestión de Libros con Spring Boot que te permite gestionar un catálogo de libros Aprovechando , el proyecto ofrece a los usuarios la capacidad de buscar, filtrar y gestionar libros de dominio público de manera eficiente.

En este proceso, se efectúan peticiones a una API de libros, la API Gutendex,  se procesan datos en formato JSON, se almacenan en una base de datos PostgreSQL y, finalmente, se filtran y se muestran los libros y autores relevantes.

El usuario puede registrar libros en una base de datos y recibir información acerca de los libros que hay registrados
El desafió está enfocado en el consumo de una API y la persistencia de datos.

##OBJETIVO: Desarrollar un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de interacción. Los libros se buscarán a través de una API específica.

Los pasos  que se siguieron  para  completar este desafío, fueron :

- Configuración del Ambiente Java;
- Creación del Proyecto;
- Consumo de la API;
- Análisis de la Respuesta JSON;
- Inserción y consulta en la base de datos;
- Exhibición de resultados a los usuarios;

Funcionalidades
Búsqueda de Libros: Encuentra libros por título, autor o idioma.
Filtrado por Idioma: Filtra los libros registrados por idioma.
Top 15 Libros: Visualiza los 15 libros más registrados en el sistema.
Estadísticas de Libros: Obtén estadísticas sobre el número total de libros y autores en el catálogo.
Interacción con la API Gutendex: Accede a miles de libros de dominio público de la API Gutendex.
Gestión de Datos: La persistencia de datos es gestionada mediante Spring Data JPA y una base de datos PostgreSQL.

###TECNOLOGIAS UTILIZADAS:
Spring Boot: Framework para el desarrollo de aplicaciones Java.
Spring Data JPA: Para la interacción con la base de datos.
PostgreSQL: Base de datos relacional utilizada para almacenar los datos de los libros.
Gutendex API: API que permite acceder a libros de dominio público.
Java 17: Lenguaje de programación utilizado para el desarrollo.

Instalación
Sigue estos pasos para instalar y ejecutar el proyecto en tu máquina local:

Clona este repositorio:

git clone https://github.com/tu-usuario/literalura.git
Instala las dependencias: Si estás utilizando IntelliJ IDEA, abre el proyecto y Spring Boot se encargará de descargar las dependencias automáticamente.

Configura la base de datos: Asegúrate de tener PostgreSQL instalado y crea una base de datos llamada literalura. Luego, configura los detalles de la base de datos en el archivo application.properties ubicado en src/main/resources.

En el archivo application.properties  agrega estas líneas para conectar con PostgreSQL:

spring.datasource.url=jdbc:postgresql://localhost:xxxx/literalura 
spring.datasource.username=tu-usuario 
spring.datasource.password=tu-contraseña 
spring.jpa.hibernate.ddl-auto=update 
spring.jpa.properties.hibernate.format_sql=true

Reemplaza tu-usuario y tu-contraseña con los datos correspondientes de tu base de datos.

Ejecuta el proyecto: Si usas IntelliJ IDEA, simplemente haz clic en el botón de Run o ejecuta el siguiente comando desde la terminal en el directorio raíz del proyecto: Para ejecutar la aplicación, usa el siguiente comando: Esto iniciará el servidor y podrás acceder a la aplicación en tu navegador.
