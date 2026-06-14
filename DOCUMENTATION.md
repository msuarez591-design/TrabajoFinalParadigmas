# Documentación del Sistema de Noticias (NEWS)

Resumen simple para entrega de práctica — descripción de clases y cómo funcionan las consultas/filtros.

## Estructura de archivos (src/)

- `Persona.java`  
  - Campos: `int dni`, `String nombre`.
  - Constructor por defecto y constructor con parámetros (valida dni ≠ 0 y nombre no vacío).
  - Getters: `getDni()`, `getNombre()`.
  - Setters: `setDni(int)`, `setNombre(String)` (validan entradas).

- `Autor.java`  
  - Hereda `Persona`.
  - Campo extra: `String medio`.
  - Constructor por defecto y completo con validación.
  - Get/Set: `getMedio()`, `setMedio(String)`.

- `Usuario.java`  
  - Hereda `Persona`.
  - Campo extra: `int edad`.
  - Constructor por defecto y completo (valida edad > 0).
  - Get/Set: `getEdad()`, `setEdad(int)`.

- `Comentario.java`  
  - Campos: `int numero`, `String texto`, `Persona autorComentario` (puede ser `Usuario` o `Autor`).
  - Constructor por defecto y completo (valida texto y autor no nulos).
  - Getters/Setters simples.

- `Noticia.java`  
  - Implementa `Publicable`.
  - Campos: `String titulo`, `String detalle`, `int dia`, `int mes`, `int anio`, `Autor autor`, `ArrayList<Comentario> comentarios`.
  - Constructor por defecto y constructor completo con validación de datos.
  - Métodos:
    - `agregarComentario(Comentario c)` — agrega comentario validando que no sea nulo.
    - Setters simples: `setTitulo`, `setDetalle`, `setDia`, `setMes`, `setAnio`, `setAutor`.
    - Getters: `getTitulo`, `getDetalle`, `getDia`, `getMes`, `getAnio`, `getAutor`, `getComentarios` (devuelve `ArrayList<Comentario>` como pidió el alumno).
    - `obtenerFormato()` — devuelve una línea resumen para impresión.

- `Publicable.java`  
  - Interfaz con `String obtenerFormato()` que obliga a las clases publicables a implementar formato legible.

- `GestorArchivo.java`  
  - Responsable de persistir y recuperar el estado en archivos de texto:
    - `usuarios.txt`  → líneas `dni;nombre;edad`
    - `autores.txt`   → líneas `dni;nombre;medio`
    - `noticias.txt`  → líneas `titulo;detalle;dia;mes;anio;dniAutor`
    - `comentarios.txt` → líneas `numero;texto;dniAutorComentario;tituloNoticia`
  - Métodos: `guardarEstado(ArrayList<Noticia>, ArrayList<Usuario>, ArrayList<Autor>)` y `cargarEstado(...)`.
  - El formato es sencillo `;` separado; se reemplazan `;` en texto por `,` al guardar para evitar romper el parseo.

- `Registro.java`  
  - Orquestador / controlador principal.
  - Campos privados: `ArrayList<Noticia> listaNoticias`, `ArrayList<Usuario> listaUsuarios`, `ArrayList<Autor> listaAutores`, `GestorArchivo gestorArchivo`.
  - Constructores: por defecto y uno que acepta listas (usa listas vacías si se pasa `null`).
  - Métodos de registro:
    - `registrarAutor(Autor)` — agrega si no existe (usa `buscarAutorPorDni`).
    - `registrarUsuario(Usuario)` — agrega si no existe.
    - `cargarNoticia(Noticia)` — valida que la noticia no sea nula y que tenga `autor` y que dicho autor esté registrado.
    - `registrarComentarioEnNoticia(String tituloNoticia, int numero, String texto, int dniLector)` — busca la noticia, verifica que el lector esté registrado como `Usuario` o `Autor` y agrega el comentario.
  - Métodos de búsqueda internos: `buscarAutorPorDni`, `buscarUsuarioPorDni`, `buscarNoticiaPorTitulo`.
  - Consultas/filtros (implementadas de forma simple iterativa):
    - `listarNoticiasPorAnio(int anio)` — itera `listaNoticias` y compara `n.getAnio() == anio`.
    - `listarNoticiasUltimoMes(int mesActual, int anioActual)` — itera y compara mes y año.
    - `mostrarNoticiaYComentarios(String titulo)` — busca noticia por título y luego itera `n.getComentarios()` para imprimirlos.
    - `listarNoticiasPorAutor(int dniAutor)` — busca por `n.getAutor().getDni() == dniAutor`.
  - Getters y setters sencillos para las colecciones y el `GestorArchivo` fueron añadidos (metodología estudiante): `getListaNoticias()`, `setListaNoticias(...)`, `getListaUsuarios()`, `setListaUsuarios(...)`, `getListaAutores()`, `setListaAutores(...)`, `getGestorArchivo()`, `setGestorArchivo(...)`.

- `Main.java`  
  - Clase de prueba/simulación que crea autores, usuarios, noticias, registra comentarios, guarda en disco y luego carga y ejecuta las consultas.

## Cómo funcionan los "filtros" (consultas) — explicación sencilla

Todas las consultas se hacen recorriendo la colección `listaNoticias` y aplicando una condición booleana simple:

- Por año: `if (n.getAnio() == anio)` → mostrar.
- Último mes: `if (n.getMes() == mesActual && n.getAnio() == anioActual)` → mostrar.
- Por autor: `if (n.getAutor().getDni() == dniAutor)` → mostrar.
- Mostrar noticia y comentarios: `buscarNoticiaPorTitulo(...)` y luego iterar la `ArrayList<Comentario>` para listar cada comentario.

Este enfoque es simple, explícito y apropiado para una práctica de estudiante: no usamos streams ni APIs avanzadas, sólo bucles `for` y comparaciones.

## Archivos de persistencia en disco

- `usuarios.txt`, `autores.txt`, `noticias.txt`, `comentarios.txt` en el directorio de ejecución.
- Formato semicolon-separated (`;`).

## Notas finales y recomendaciones

- Código estilo "estudiante": claro, con validaciones sencillas y sin librerías externas.
- Si querés que proteja las colecciones evitando que código externo las modifique directamente, puedo cambiar los getters para devolver copias (`new ArrayList<>(this.listaNoticias)`) o vistas inmutables, pero dijiste que sólo aprendiste `ArrayList`, así que lo dejé simple.
- ¿Queres que genere un breve README con instrucciones de compilación y ejecución? (Puedo agregarlo automáticamente.)
