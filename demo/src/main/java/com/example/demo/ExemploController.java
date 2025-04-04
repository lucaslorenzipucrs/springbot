package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/biblioteca")
public class ExemploController {

    private List<Livro> livros; // Atributo coleção de livros

    // Construtor que inicializa a lista de livros
    public ExemploController() {
        this.livros = new ArrayList<>();
        livros.add(new Livro(1, "1984", "George Orwell", 1949));
        livros.add(new Livro(2, "Dom Casmurro", "Machado de Assis", 1899));
        livros.add(new Livro(3, "O Senhor dos Anéis", "J.R.R. Tolkien", 1954));
        livros.add(new Livro(4, "A Revolução dos Bichos", "George Orwell", 1945));
    }

    @GetMapping("/")
    public String getMensagemInicial() {
        return  "Endpoint de livros...";
    }

    @GetMapping("/livros")
    public List<Livro> getLivros() {
        return livros; 
    }

 
    @GetMapping("/livros/titulos")
    public List<String> getTitulos() {
        return livros.stream()
                     .map(Livro::getTitulo)
                     .collect(Collectors.toList());
    }

  
    @GetMapping("/livros/autores")
    public List<String> getAutores() {
        return livros.stream()
                     .map(Livro::getAutor)
                     .distinct()
                     .collect(Collectors.toList());
    }
}