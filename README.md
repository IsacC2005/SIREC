![Logo](src/recursos/logoSinFondo110x110.png)
# SIREC 


## Contenido del Repositorio

El repositorio está organizado de la siguiente manera:

* `src/`: Contiene el código fuente principal del proyecto.
* `database/`: Incluye archivos relacionados con la base de datos.
    * `database.sql`: Script SQL para crear la base de datos y poblarla con datos de prueba.
* `README.md`: Este archivo con la información del proyecto.
* [Otras carpetas o archivos relevantes]

## Instrucciones de Instalación y Configuración

Para utilizar este proyecto, sigue los siguientes pasos:

1.  **Descargar el Código:**
    Descarga el repositorio completo a tu computadora. Puedes hacerlo clonando el repositorio con Git (si estás familiarizado con él) o descargando el archivo ZIP desde la interfaz de GitHub/GitLab/Bitbucket.

2.  **Importar el Proyecto al IDE:**
    Importa el código fuente del proyecto a tu entorno de desarrollo integrado (IDE) preferido. Puedes utilizar NetBeans o Eclipse, siguiendo los procedimientos estándar de importación de proyectos Java (o el lenguaje que hayas utilizado).

    * **Para NetBeans:** Generalmente, ve a `Archivo` -> `Abrir Proyecto...` y selecciona la carpeta donde descargaste el proyecto.
    * **Para Eclipse:** Generalmente, ve a `Archivo` -> `Importar...` -> `General` -> `Proyectos existentes en el espacio de trabajo` y selecciona la carpeta raíz del proyecto.

3.  **Configurar la Base de Datos:**
    Este proyecto requiere una base de datos para funcionar correctamente. Se incluye un script SQL (`database/database.sql`) para facilitar su creación y configuración con datos de prueba. Sigue estos pasos:

    a.  **Acceder al Cliente de Base de Datos:** Abre tu cliente de base de datos (por ejemplo, MySQL Workbench, pgAdmin, DBeaver, etc.) conectado al servidor de base de datos que vayas a utilizar.

    b.  **Crear una Nueva Base de Datos (Opcional):** Si lo deseas, puedes crear una nueva base de datos para este proyecto. El nombre de la base de datos debe coincidir con la configuración especificada en el código del proyecto (`src\conect\OpenBd.java`, créala en tu servidor.

    c.  **Ejecutar el Script SQL:** Abre el archivo `database/database.sql` con tu cliente de base de datos y ejecuta el script. Esto creará las tablas necesarias y las llenará con datos de prueba que pueden ser útiles para entender el funcionamiento del proyecto.

4.  **Configurar la Conexión a la Base de Datos en el Proyecto:**
    Asegúrate de que la configuración de la conexión a la base de datos en tu proyecto (dentro de NetBeans o Eclipse) coincida con la base de datos que has creado y configurado para esto tendrias que modificar el archivo `src\conect\OpenBd.java` aqui esta la configuracion de conexion a la base de datos.

5.  **Ejecutar el Proyecto:**
    Una vez que hayas importado el proyecto y configurado la base de datos, puedes ejecutar el proyecto desde tu IDE (NetBeans o Eclipse) de la manera habitual.


## Usuarios por Defecto

El script SQL (`database/database.sql`) incluido en este repositorio crea automáticamente dos usuarios con roles y permisos diferenciados para facilitar las pruebas y la comprensión del sistema:

* **Usuario Administrador:**
    * **Nombre de Usuario:** `isacc`
    * **Contraseña:** `isacc`
    * **Permisos:** Este usuario tiene privilegios completos para realizar todas las operaciones dentro del sistema, incluyendo consultas, inserciones, modificaciones y eliminaciones de datos, así como cualquier otra funcionalidad administrativa.

* **Usuario Básico:**
    * **Nombre de Usuario:** `Root`
    * **Contraseña:** `Toor`
    * **Permisos:** Este usuario tiene permisos limitados y está diseñado principalmente para realizar consultas y visualizar la información almacenada en la base de datos. No podrá realizar operaciones que modifiquen los datos.

**Importante:** Estas credenciales son para propósitos de prueba y desarrollo. **En un entorno de producción real, es crucial modificar estas contraseñas por defecto y establecer mecanismos de autenticación y autorización más seguros.**

Al ejecutar el script SQL, estos usuarios serán creados en la base de datos, y podrás utilizarlos para probar las diferentes funcionalidades del proyecto según el rol asignado. Recuerda configurar correctamente la conexión a la base de datos en tu proyecto para que la aplicación pueda autenticar a estos usuarios.

