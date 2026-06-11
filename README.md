# TrabajoFinalParadigmas

Proyecto final de Paradigmas de Programación II: Sistema de Noticias.

## Contenido

- `src/Publicable.java` — interfaz para objetos publicables.
- `src/Persona.java` — clase abstracta base para personas.
- `src/Usuario.java` — representa lectores registrados.
- `src/Autor.java` — representa autores/periodistas.
- `src/Comentario.java` — contiene comentarios de personas sobre noticias.
- `src/Noticia.java` — almacena noticias y sus comentarios.
- `src/Registro.java` — controlador en memoria para usuarios, autores, noticias y comentarios.
- `src/GestorArchivo.java` — guarda el estado en archivos de texto.

## Compilación

Desde la raíz del proyecto:

```powershell
javac src\*.java
```

## Ejecución

No hay una clase `Main` incluida todavía. Para probar las clases, se puede crear una clase con un método `main` que utilice los objetos de `src/`.

## Archivos generados

- `usuarios.txt`
- `autores.txt`
- `noticias.txt`
- `comentarios.txt`

Estos archivos se generan cuando se implementa y llama el método `guardarEstado` de `GestorArchivo`.

## Notas

La rama actual con estos cambios es `develop`.