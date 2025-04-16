package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Acervo {
    private List<Livro> livros;

    public Acervo() {
        livros = new LinkedList<>();
        livros.add(new Livro(110, "Aprendendo Java", "Maria da Silva", 2015));
        livros.add(new Livro(120, "Spring-Boot", "Jose de Souza", 2020));
        livros.add(new Livro(130, "Principios SOLID", "Pedro da Silva", 2023));
        livros.add(new Livro(140, "Padroes de Projeto", "Joana Moura", 2023));
        livros.add(new Livro(150, "Teste Unitario", "Pedro da Silva", 2024));
        livros.add(new Livro(1, "1984", "George Orwell", 1949));
        livros.add(new Livro(2, "Dom Casmurro", "Machado de Assis", 1899));
        livros.add(new Livro(3, "O Senhor dos Anéis", "J.R.R. Tolkien", 1954));
        livros.add(new Livro(4, "A Revolução dos Bichos", "George Orwell", 1945));
        livros.add(new Livro(5, "Aprendendo Java", "Maria da Silva", 2022));
        livros.add(new Livro(5, "Aprendendo Java II", "Maria da Silva", 2024));
        livros.add(new Livro(6, "Java Avançado", "Pedro da Silva", 2024));
        livros.add(new Livro(6, "Java Avançado II", "Pedro da Silva", 2025));
    }

    public List<Livro> getAll() {
        return livros;
    }

    public List<String> getTitulos() {
        return livros.stream()
                     .map(Livro::getTitulo)
                     .collect(Collectors.toList());
    }

    public List<String> getAutores() {
        return livros.stream()
                     .map(Livro::getAutor)
                     .distinct()
                     .collect(Collectors.toList());
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public List<Livro> buscarPorAutor(String autor) {
        return livros.stream()
                .filter(livro -> livro.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    public List<Livro> buscarPorAutorEAno(String autor, int ano) {
        return livros.stream()
                .filter(livro -> livro.getAutor().equalsIgnoreCase(autor) && livro.getAno() == ano)
                .collect(Collectors.toList());
    }

    public Livro buscarPorTitulo(String titulo) {
        return livros.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }
}