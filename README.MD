# Book API
API criada para o desafio "Publicando Sua API REST na Nuvem Usando Spring Boot 3,
Java 17 e Railway" da DIO.

Esta API salva informações de livros em um banco de dados.

Diagrama de classes:
```mermaid
    classDiagram
        class Book {
            - String name
            - LocalDate release
            - String synopsis
            - int pages
            - double rating
        }
        
        class Author {
            - String name
        }
        
        class Publisher {
            - String name
        }
        
        class Genre {
            - String name
        }

        Book "1..N" *-- "1" Author
        Book "1..N" *-- "1" Publisher
        Book "1..N" *-- "1" Genre
```