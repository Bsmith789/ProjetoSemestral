package br.com.fiap.movies.paineis;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.fiap.movies.componentes.StarRater;

public class PainelComponentes extends JPanel {

    private static final long serialVersionUID = 1L;

    private PainelOndeAssistir ondeAssistir = new PainelOndeAssistir();
    private JCheckBox assistido = new JCheckBox("Assistido");
    private StarRater avaliacao = new StarRater();

    public PainelComponentes() {
        setLayout(new GridLayout(4,1));
        init();
    }

    private void init() {
        add(ondeAssistir);
        add(assistido);
        add(new JLabel("Avaliação"));
        add(avaliacao);
    }

    public PainelOndeAssistir getOndeAssistir() {
        return ondeAssistir;
    }

    public JCheckBox getAssistido() {
        return assistido;
    }

    public StarRater getAvaliacao() {
        return avaliacao;
    }



}