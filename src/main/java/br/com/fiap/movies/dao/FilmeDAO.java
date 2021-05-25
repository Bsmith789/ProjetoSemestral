package br.com.fiap.movies.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.fiap.movies.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("fiap-movies");
    EntityManager manager = factory.createEntityManager();

    public void create(Filme filme) {

        manager.getTransaction().begin();
        manager.persist(filme);
        manager.getTransaction().commit();

        manager.close();
    }

    public List<Filme> buscarTodos() {
        TypedQuery<Filme> query = manager.createQuery("SELECT a FROM Filme a", Filme.class);
        return query.getResultList();
    }

    public void apagar(Filme filme) {
        manager.getTransaction().begin();
        manager.remove(filme);
        manager.getTransaction().commit();
    }

    public void salvar(Filme filme){
        Filme filmeAtualiza = buscarPorId(filme.getId());
        manager.getTransaction().begin();
        filmeAtualiza.setTitulo(filme.getTitulo());
        filmeAtualiza.setSinopse(filme.getSinopse());
        filmeAtualiza.setGenero(filme.getGenero());
        filmeAtualiza.setOndeAssistir(filme.getOndeAssistir());
        filmeAtualiza.setAssistido(filme.isAssistido());
        filmeAtualiza.setAvaliacao(filme.getAvaliacao());
        manager.getTransaction().commit();
    }

    public Filme salvarPorId(Long id){
        return manager.find(Filme.class,id);
    }

    public void salvar(Long id){
        salvar(salvarPorId(id));
    }

    public void atualizar(Filme filme){
        manager.getTransaction().begin();
        manager.merge(filme);
        manager.getTransaction().commit();
    }

    public void atualizar(Long id){
        atualizar(atualizaPorId(id));
    }

    public Filme atualizaPorId(Long id){
        return manager.find(Filme.class,id);
    }

    public void apagar(Long id) {
        apagar(buscarPorId(id));
    }

    public Filme buscarPorId(Long id) {
        return manager.find(Filme.class, id);
    }

    private List<Filme> retornarDadosDeTeste(){
        ArrayList<Filme> listaFilmes = new ArrayList<Filme>();
        listaFilmes.add(new Filme("Viuva Negra", "Uma mulher da pesada", "Ação", "Netflix", true, 4));
    return listaFilmes;
    }

}
