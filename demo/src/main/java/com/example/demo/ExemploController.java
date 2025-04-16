package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/biblioteca")
public class ExemploController {

    private final Acervo acervo;

    @Autowired
    public ExemploController(Acervo acervo) {
        this.acervo = acervo;
    }

    @GetMapping("/")
    public String getMensagemInicial() {
        return "Endpoint de livros...";
    }

    @GetMapping("/livros")
    public List<Livro> getLivros() {
        return acervo.getAll();
    }

    @GetMapping("/livros/titulos")
    public List<String> getTitulos() {
        return acervo.getTitulos();
    }

    @GetMapping("/livros/autores")
    public List<String> getAutores() {
        return acervo.getAutores();
    }

    @GetMapping("/livrosautor")
    public List<Livro> getLivrosPorAutor(@RequestParam String autor) {
        return acervo.buscarPorAutor(autor);
    }

    @GetMapping("/livrosautorano/{autor}/ano/{ano}")
    public List<Livro> getLivrosPorAutorEAno(@PathVariable String autor, @PathVariable int ano) {
        return acervo.buscarPorAutorEAno(autor, ano);
    }

    @PostMapping("/novolivro")
    public String adicionarLivro(@RequestBody Livro novoLivro) {
        acervo.adicionarLivro(novoLivro);
        return "Livro adicionado: " + novoLivro.getTitulo();
    }

    @GetMapping("/livrotitulo/{titulo}")
    public ResponseEntity<Object> getLivroPorTitulo(@PathVariable String titulo) {
        Livro livro = acervo.buscarPorTitulo(titulo);
        if (livro != null) {
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.status(404).body("Livro com título \"" + titulo + "\" não encontrado");
        }
    }
    
    @GetMapping("/livrosano/{ano}")
    public List<Livro> getLivrosPorAno(@PathVariable int ano) {
        return acervo.getAll().stream()
                .filter(livro -> livro.getAno() == ano)
                .collect(Collectors.toList());
    }

    
    @PostMapping("/alteralivro")
    public boolean alterarLivro(@RequestBody Livro livroAlterado) {
        List<Livro> livros = acervo.getAll();

        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            if (livro.getId() == livroAlterado.getId()) {
                livros.set(i, livroAlterado); // substitui pelo novo livro
                return true;
            }
        }
        return false;
    }
}