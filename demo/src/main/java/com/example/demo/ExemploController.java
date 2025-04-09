package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



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
        livros.add(new Livro(5, "Aprendendo Java", "Maria da Silva", 2022));
        livros.add(new Livro(5, "Aprendendo Java II", "Maria da Silva", 2024));
        livros.add(new Livro(6, "Java Avançado", "Pedro da Silva", 2024));
        livros.add(new Livro(6, "Java Avançado II", "Pedro da Silva", 2025));
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

    

    @GetMapping("/livrosautor")
    public List<Livro> getLivrosPorAutor(@RequestParam String autor) {
        return livros.stream()
                 .filter(livro -> livro.getAutor().equalsIgnoreCase(autor))
                 .collect(Collectors.toList());
    }

    @GetMapping("/livrosautorano/{autor}/ano/{ano}")
    public List<Livro> getLivrosPorAutorEAno(@PathVariable String autor, @PathVariable int ano) {
        return livros.stream()
                 .filter(livro -> livro.getAutor().equalsIgnoreCase(autor) && livro.getAno() == ano)
                 .collect(Collectors.toList());
    }

    @PostMapping("/novolivro")
    public String adicionarLivro(@RequestBody Livro novoLivro) {
        livros.add(novoLivro);
        return "Livro adicionado: " + novoLivro.getTitulo();
    }

    @GetMapping("/livrotitulo/{titulo}")
    public ResponseEntity<Object> getLivroPorTitulo(@PathVariable String titulo) {
        return livros.stream()
                 .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo))
                 .findFirst()
                 .<ResponseEntity<Object>>map(ResponseEntity::ok)
                 .orElse(ResponseEntity.status(404).body("Livro com título \"" + titulo + "\" não encontrado"));
    }
}