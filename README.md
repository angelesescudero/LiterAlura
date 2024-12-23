#**CHALLENGE LITERALURA**

En este emocionante desafío de programación, construi un catálogo de libros propio: el LiterAlura. 
Gestión de Libros con Spring Boot que te permite gestionar un catálogo de libros Aprovechando , el proyecto ofrece a los usuarios la capacidad de buscar, filtrar y gestionar libros de dominio público de manera eficiente.

En este proceso, se efectúan peticiones a una API de libros, la API Gutendex,  se procesan datos en formato JSON, se almacenan en una base de datos PostgreSQL y, finalmente, se filtran y se muestran los libros y autores relevantes.

El usuario puede registrar libros en una base de datos y recibir información acerca de los libros que hay registrados
El desafió está enfocado en el consumo de una API y la persistencia de datos.
![image](https://github.com/user-attachments/assets/cea3d3fe-bd22-4a1b-808b-c3b2126cf959)



## Objetivo
Desarrollar un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de interacción. 
Los libros se buscarán a través de una API específica.

Los pasos  que se siguieron  para  completar este desafío, fueron :

- Configuración del Ambiente Java;
- Creación del Proyecto;
- Consumo de la API [Gutendex](https://gutendex.com/) ;
- Análisis de la Respuesta JSON;
- Inserción y consulta en la base de datos;
- Exhibición de resultados a los usuarios;

## Funcionalidades  

1. **Buscar un libro por titulo:**  
   - Permite buscar libros en la API de Gutendex.  
   - Si el libro ya existe en la base de datos, no se vuelve a agregar.  

2. **Mostrar todos los libros registrados:**  
   - Muestra todos los libros que han sido buscados y registrados en la base de datos.  

3. **Listar todos los autores registrados:**  
   - Muestra una lista de todos los autores que han sido registrados en la base de datos.  

4. **Listar autores vivos en un determinado año:**  
   - Lista los autores vivos en un año determinado, solo devuelve los datos de los libros registrados.  

5. **Listar libros por idioma:**  
   - Muestra los idiomas disponibles, y devuelve una lista con los libros registrados en el idioma seleccionado.  

6. **Buscar libro por autor:**  
   - Lista los libros registrados, que sean de un mismo autor.  

0. **Salir:**  
   - Cierra la aplicación.  

## Tecnologías Utilizadas  

- **Java Spring Boot:** Desarrollo del backend.  
- **Spring Data JPA:** Gestión de la persistencia y consultas a la base de datos.  
- **PostgreSQL:** Base de datos relacional utilizada para almacenar los datos de los libros y autores. 
- **Jackson:** Procesamiento de JSON para consumir la API de Gutendex.  
- **Driver de PostgreSQL:** Conexión entre la aplicación y la base de datos.  

## Requisitos  

- JDK 17 o superior.  
- PostgreSQL instalado y configurado.  
- Maven para la gestión de dependencias.  


## Configuración 

Sigue estos pasos para instalar y ejecutar el proyecto en tu máquina local:

Clona este repositorio:

git clone https://github.com/tu-usuario/literalura.git
Instala las dependencias: Si estás utilizando IntelliJ IDEA, abre el proyecto y Spring Boot se encargará de descargar las dependencias automáticamente.

Configura la base de datos: Asegúrate de tener PostgreSQL instalado y crea una base de datos llamada literalura. 
Luego, configura los detalles de la base de datos en el archivo application.properties ubicado en src/main/resources.

En el archivo application.properties  agrega estas líneas para conectar con PostgreSQL:

spring.datasource.url=jdbc:postgresql://localhost:xxxx/literalura 
spring.datasource.username=tu-usuario 
spring.datasource.password=tu-contraseña 
spring.jpa.hibernate.ddl-auto=update 
spring.jpa.properties.hibernate.format_sql=true

Reemplaza tu-usuario y tu-contraseña con los datos correspondientes de tu base de datos.

Ejecuta el proyecto: Si usas IntelliJ IDEA, simplemente haz clic en el botón de Run o ejecuta el siguiente comando desde la terminal en el directorio raíz del proyecto: 
Para ejecutar la aplicación, usa el siguiente comando: Esto iniciará el servidor y podrás acceder a la aplicación en tu navegador.




