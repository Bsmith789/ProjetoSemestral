package br.com.fiap.movies.componentes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Poster extends JLabel{

    private static final long serialVersionUID = 1L;

    private ImageIcon imagem = new ImageIcon("src/imagens/logo1.jpg");

    public Poster() {
        setIcon(imagem);
    }

}