package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PainelPrincipal extends JPanel{
	/**
	 */
	private static final long serialVersionUID = 1L;
	private String caminhoImagem;
	
    public PainelPrincipal(String caminhoImagem) {
        this.caminhoImagem =  caminhoImagem;
    }

    @Override  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        Graphics2D gr = ( Graphics2D )g.create();
        
        try {  
           BufferedImage buffer = ImageIO.read(new File(caminhoImagem));
           gr.drawImage(buffer, null, 0, 0 ); // desenha a imagem
        } catch (IOException ex) {  
           Logger.getLogger(PainelPrincipal.class.getName()).log(Level.SEVERE, null, ex);  
        } 
    }
}