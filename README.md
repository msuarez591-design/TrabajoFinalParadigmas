# TrabajoFinalParadigmas

Proyecto final de Paradigmas de Programación II: Sistema de Noticias (NEWS).

Sistema completamente orientado a objetos para administrar artículos, autores, usuarios y comentarios.

## Contenido

### Clases principales (`src/`)

- `Publicable.java` — interfaz para objetos publicables.
- `Persona.java` — clase abstracta base para personas (DNI, nombre).
- `Usuario.java` — hereda de Persona, representa lectores registrados (incluye edad).
- `Autor.java` — hereda de Persona, representa autores/periodistas (incluye medio de comunicación).
- `Comentario.java` — almacena comentarios con número, texto y autor (Usuario o Autor).
- `Noticia.java` — almacena noticias con título, detalle, fecha (día, mes, año), autor y comentarios asociados.
- `Registro.java` — controlador principal que gestiona usuarios, autores, noticias y comentarios en memoria.
- `GestorArchivo.java` — persiste el estado del sistema en archivos de texto.
- `Main.java` — clase de prueba completa que demuestra todas las consignas.

## Compilación

Desde la raíz del proyecto:

```powershell
javac src\*.java
```

## Ejecución

```powershell
java -cp src Main
```

O en una línea:

```powershell
javac src\*.java ; java -cp src Main
```

## Lo que hace Main.java

El programa realiza las siguientes operaciones:

1. **Registro de Autores** — Registra dos autores en el sistema.
2. **Registro de Usuarios** — Registra dos usuarios lectores.
3. **Carga de Noticias** — Publica dos noticias por parte de los autores.
4. **Registro de Comentarios** — Usuarios comentan sobre las noticias.
5. **Guardado en Disco** — Persiste todo el estado en archivos de texto.
6. **Carga desde Disco** — Simula el reinicio de la aplicación cargando datos desde archivos.
7. **Consultas Solicitadas:**
   - **Consulta A:** Listar noticias publicadas en un año específico (2026).
   - **Consulta B:** Listar noticias del último mes (junio 2026).
   - **Consulta C:** Mostrar una noticia con todos sus comentarios.
   - **Consulta D:** Listar artículos de un autor específico.

## Archivos generados

- `usuarios.txt` — Lista de usuarios registrados.
- `autores.txt` — Lista de autores registrados.
- `noticias.txt` — Lista de noticias publicadas.
- `comentarios.txt` — Todos los comentarios del sistema.

Estos archivos se generan automáticamente al ejecutar el programa.

## Estructura de datos

### Noticia
- Título, Detalle
- Día, Mes, Año
- Autor (registrado)
- Lista de comentarios

### Autor
- DNI, Nombre
- Medio de comunicación

### Usuario
- DNI, Nombre
- Edad

### Comentario
- Número, Texto
- Persona (Usuario o Autor)

## Criterios cumplidos

✓ Desarrollo completamente orientado a objetos.
✓ Programa ejecutable que cumple todas las consignas.
✓ Todas las funciones implementadas para cada clase.
✓ Persistencia en archivos de texto.
✓ Todas las consultas solicitadas funcionando.
✓ Validaciones defensivas en las operaciones.