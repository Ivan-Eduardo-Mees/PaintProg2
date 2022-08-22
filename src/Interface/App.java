package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import javax.swing.filechooser.FileNameExtensionFilter;


public class App extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8780737787493128881L;


	PainelDesenhavel pd = new PainelDesenhavel(); 
    JButton button;
    JLabel rodape;

    ActionListener bts = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			pd.setColor((((JComponent) e.getSource()).getBackground()));				
		}    
    };
    

    MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); 
            pd.paint(e.getX(), e.getY());
            rodape.setText("Imagem Modificada");
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            pd.paint(e.getX(),e.getY());
            rodape.setText("Imagem Modificada");
        }
        
    };

    ActionListener saveImage = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG File", "jpg");
            fileChooser.addChoosableFileFilter(filter);            
    
            int returnVal = fileChooser.showDialog(pd, "Salvar");
            
			String filee = "";
			
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	
                File file = fileChooser.getSelectedFile();
                filee = file.getPath();
                
                if(file.getPath().split("\\.").length != 2) {
               		filee = file.getPath() + ".jpg";
                }

            	pd.saveImage(filee);
            	rodape.setText("Imagem Original");

            } 
           
		}    
    };

    public JPanel buildButton() {
        JPanel pnEsqFlow = new JPanel(new FlowLayout());
        JPanel pnEsqBts = new JPanel(new GridLayout(0, 1));
        
        Color[] Colors = {
        		Color.black,
        		Color.blue,
        		Color.gray,
        		Color.green,
        		Color.pink,
        		Color.orange,
        		Color.red,
        		Color.yellow,
        		Color.white
        };
   
        for (Color cor : Colors) {
        	button = new JButton(". . .");	
        	button.setBackground(cor);
        	button.addActionListener(bts);
			pnEsqBts.add(button);
        }
        
        pnEsqFlow.add(pnEsqBts);
        return pnEsqFlow;
    }
    
    public PainelDesenhavel buildDrawPanel() {
        pd.setBackground(Color.white);
        pd.addMouseListener(ma);
        pd.addMouseMotionListener(ma);
        
        return pd;
    }
    
    public JMenuBar buildMenu() {
        JMenu fileMenu = new JMenu("Arquivo");
        
        JMenuItem savePaint = new JMenuItem("Salvar");
        savePaint.addActionListener(saveImage);
        
        JMenuItem close = new JMenuItem("Sair");
        
        close.addActionListener((e) -> {
  	       dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  	       setState(Frame.ICONIFIED);
        });
        
        fileMenu.add(savePaint);
        fileMenu.add(close);
          
        JMenu helpMenu = new JMenu("Ajuda");
        JMenuItem about = new JMenuItem("Sobre");
        
        about.addActionListener((e) -> {
            JOptionPane.showMessageDialog(null, "Programação II");
            
        });
        
        helpMenu.add(about);
        
  	    JMenuBar menuBar = new JMenuBar();
  	    menuBar.add(fileMenu);
  	    menuBar.add(helpMenu);  
  	    return menuBar;
    }
    
    
    public void montarTela(){
	    setTitle("Paint Prog II");
	    Container container = getContentPane();
	
	    container.add(buildButton(), BorderLayout.WEST);
	    
	    container.add(buildDrawPanel(), BorderLayout.CENTER);
	    
	    setJMenuBar(buildMenu());
	    
	    rodape = new JLabel("Imagem Original");
	
	    container.add(rodape, BorderLayout.SOUTH);
	    
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);             
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.montarTela();
    }
}
