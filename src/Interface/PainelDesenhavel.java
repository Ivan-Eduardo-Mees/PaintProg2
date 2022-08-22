package Interface;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PainelDesenhavel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4615093553694940274L;
	
	BufferedImage image; 
	Color color = Color.black;


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        iniBuffered();
        checkSize();
        g.drawImage(image, 0, 0, null);
    }
    
    private void iniBuffered() {
        if(image == null) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            g.setColor(getBackground());
            g.fillRect(0,0,getWidth(),getHeight());
            g.dispose();
        }
    }
    
    private void checkSize()
    {
        if (image.getWidth() != getWidth() || image.getHeight() != getHeight()){
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            g.setColor(getBackground());
            g.fillRect(0,0,getWidth(),getHeight());
            g.drawImage(this.image, 0,0,null);
            g.dispose();
            this.image = image;
        }
    }
    
    public void setColor(Color cor) {
        this.color = cor;
    }
    
    public void paint(int x, int y){
    	Graphics g = getGraphics();
        g.setColor(color);
        g.fillRect(x, y, 10, 10);
        
        Graphics gg = image.getGraphics();
        gg.setColor(color);
        gg.fillRect(x, y, 5, 5);
    }  	
    
    public void saveImage(String file) {
    	try {
           	 ImageIO.write(this.image, "PNG", new File(file));
		}catch (Exception e) {
			System.out.println("ERRO AO SALVAR IMG");
		}
    }
}

