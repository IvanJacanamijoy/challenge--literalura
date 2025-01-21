# Challenge literalura
Este es un proyecto realizado como challenge, usando la API de Gutendex

# Objetivo
Desarrollar un catálogo de Libros que permita la interacción textual (a través de la consola) con los usuarios, proporcionando un mínimo de 5 opciones de interacción. Los libros se obtendrán mediante una API específica. Los detalles sobre la API y las opciones de interacción con el usuario se especificarán en la columna "Backlog"/"Listo para iniciar".

- Configuración del Ambiente Java;
- Creación del proyecto literalura;
- Consumo de la API de Gutendex;
- Análisis de la respuesta en formato JSON;
- inclusión y consulta en la base de datos;
- Presentacion de resultados a los usuarios;

# Funcionalidades

## Menú

El sistema cuenta con un menú interactivo que ofrece 5 opciones principales:

![menuDeLaAplicacion](https://github.com/user-attachments/assets/501ec986-9ccd-4f52-897a-a8da153b48fa)


## Búsqueda de libros por el título

Permite buscar un libro ingresando su título, realiza una consulta a la API Gutendex para obtener información del libro.

![busquedaDelLibro](https://github.com/user-attachments/assets/dfeca150-84fa-49c0-aab8-6359f154598a)

Si el libro es encontrado, se muestra un mensaje confirmando su disponibilidad y verifica la existencia del mismo en la base de datos.En caso de que el autor no esté registrado en la base de datos, se registran el autor y el libro.

![libroEncontrado](https://github.com/user-attachments/assets/ceb289ae-65b9-4626-a8fe-784492325d23)

Por ultimo, se presenta la información del libro.

![informacionDelLibro](https://github.com/user-attachments/assets/079b4e24-e4a5-41fa-9684-536efa8fb5d3)

## Listado de libros

Muestra todos los libros que se encuentren en la base de datos:

![listadoDeLibros](https://github.com/user-attachments/assets/79021c73-a695-4343-8dfb-6384cbbbc9a9)

## Listado de autores y libros

Presenta un listado de autores registrados junto con los libros asociados a cada uno.

![listadoDeAutores](https://github.com/user-attachments/assets/a57a2340-2a94-477a-a4cf-c48c2da495fb)

## Consulta por año

Solicita al usuario ingresar un año. Posteriormente muestra los resultados correspondientes:

![autoresPorAño](https://github.com/user-attachments/assets/717c7cbc-5827-4662-9207-af8d876bc0f1)


## Consulta por idioma

Se muestra un menú para seleccionar un idioma y posteriormente muestra los libros disponibles en el idioma seleccionado:

![librosPorIdioma](https://github.com/user-attachments/assets/c9096994-fe0c-4519-b381-bc221aa7ca36)

Si no hay libros en el idioma seleccionado, se muestra un mensaje de error:

![errorIdioma](https://github.com/user-attachments/assets/0f4d2172-3f1b-4d14-9790-fdf8b78d13d1)


## Salida

Al finalizar la aplicacion se muestra un mensaje de despedida.

![finalizandoAplicacion](https://github.com/user-attachments/assets/ae93ed28-eab4-44fb-85f4-facc4a5ea270)
