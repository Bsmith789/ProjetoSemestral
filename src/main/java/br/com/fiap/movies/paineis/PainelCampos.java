package br.com.fiap.movies.paineis;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PainelCampos extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField titulo = new JTextField();
    private JTextArea sinopse = new JTextArea();
    private String[] generos = {"Ação", "Aventura", "Romance", "Comédia", "Terror"};
    private JComboBox<String> genero = new JComboBox<String>(generos);

    public PainelCampos() {
        setLayout(new GridLayout(6, 1));
        setBorder(new EmptyBorder(20, 15, 20, 10));
        init();
    }

    private void init() {
        add(new JLabel("Título"));
        add(getTitulo());
        add(new JLabel("Sinopse"));
        add(getSinopse());
        add(new JLabel("Gênero"));
        add(getGenero());
    }

    public JTextField getTitulo() {
        return titulo;
    }

    public JTextArea getSinopse() {
        return sinopse;
    }

    public JComboBox<String> getGenero() {
        return genero;
    }


}