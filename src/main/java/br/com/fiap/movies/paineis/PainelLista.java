package br.com.fiap.movies.paineis;

import br.com.fiap.movies.dao.FilmeDAO;
import br.com.fiap.movies.model.Filme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;


public class PainelLista extends JPanel {

    private static final long serialVersionUID = 1L;

    private PainelCadastro painelCadastro = new PainelCadastro();
    private JLabel lista = new JLabel("Lista de Filmes");
    private JButton atualizar = new JButton("Atualizar");
    private JButton salvar = new JButton("Salvar");
    private JButton carregar = new JButton("Carregar");
    private JButton apagar = new JButton("Apagar");
    private DefaultTableModel modelo = new DefaultTableModel();
    private JTable tabela = new JTable(modelo);

    public PainelLista() {
        init();
    }

    private void init() {
        modelo.addColumn("Id");
        modelo.addColumn("Título");
        modelo.addColumn("Sinopse");
        modelo.addColumn("Gênero");
        modelo.addColumn("Onde assistir");
        modelo.addColumn("Assistido");
        modelo.addColumn("Avaliação");

        carregarDados();

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        botoes.add(salvar);
        botoes.add(atualizar);
        botoes.add(carregar);
        botoes.add(apagar);

        add(botoes, BorderLayout.AFTER_LINE_ENDS);

        salvar.addActionListener(this::actionPerformed);
        atualizar.addActionListener(this::actionPerformed);
        carregar.addActionListener(this::actionPerformed);
        apagar.addActionListener(this::actionPerformed);


        add(lista);
    }

    private void carregarDados() {
        modelo.setNumRows(0);

        FilmeDAO dao = new FilmeDAO();

        List<Filme> listaFilmes = dao.buscarTodos();

        for (Filme filme : listaFilmes) {
            String[] linha = {
                    filme.getId().toString(),
                    filme.getTitulo(),
                    filme.getSinopse(),
                    filme.getGenero(),
                    filme.getOndeAssistir(),
                    String.valueOf(filme.isAssistido()),
                    String.valueOf(filme.getAvaliacao())
            };
            modelo.addRow(linha);
            dao.salvar(filme);
        }
    }

    private void salvar(){
        FilmeDAO dao = new FilmeDAO();
        int linha = tabela.getSelectedRow();
        String id = tabela.getValueAt(linha,0).toString();
        Filme filme = dao.salvarPorId(Long.valueOf(id));
        int resposta = JOptionPane.showConfirmDialog(this,"Tem certeza que quer salvar esse filme?");
        if(resposta == JOptionPane.YES_OPTION){
            dao.salvar(filme);
            carregarDados();
        }
    }

    private void apagar() {
        FilmeDAO dao = new FilmeDAO();
        int linha = tabela.getSelectedRow();
        String id = tabela.getValueAt(linha, 0).toString();
        Filme filme = dao.buscarPorId(Long.valueOf(id));
        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que quer apagar o filme selecionado?");
        if (resposta == JOptionPane.YES_OPTION) {
            dao.apagar(filme);
            carregarDados();
        }
    }

    private void atualizar() {
        FilmeDAO dao = new FilmeDAO();
        int linha = tabela.getSelectedRow();
        String id = tabela.getValueAt(linha,0).toString();
        Filme filme = dao.atualizaPorId(Long.valueOf(id));
        int resposta = JOptionPane.showConfirmDialog(this,"Tem certeza que quer alterar o filme selecionado ?");
        if(resposta == JOptionPane.YES_OPTION){
            painelCadastro.getCampos().getTitulo().setText(filme.getTitulo());
            painelCadastro.getCampos().getSinopse().setText(filme.getSinopse());
            painelCadastro.getCampos().getGenero().setToolTipText(filme.getGenero());
            painelCadastro.getComponentes().getOndeAssistir().setToolTipText(filme.getOndeAssistir());
            painelCadastro.getComponentes().getAssistido().setText(String.valueOf(filme.isAssistido()));
            painelCadastro.getComponentes().getAvaliacao().setRating(filme.getAvaliacao());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == carregar) carregarDados();
        if (e.getSource() == apagar) apagar();
        if (e.getSource() == atualizar) atualizar();
        if(e.getSource() == salvar) salvar();
    }


}