package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Patryk
 */
public class BoardGUIOld {
    private JFrame boardFrame;
    private JPanel mainPanel;
    private HashMap<Coordinate, JPanel> panels;
    
    public BoardGUIOld(int length, int height){
        boardFrame = new JFrame();
        
        ImageIcon backgroundPicture = new ImageIcon("images/background1920x1281.jpg"); 
        
        mainPanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                g.drawImage(backgroundPicture.getImage(), 0,0, null);
                super.paintComponent(g);
            }
        };
        
        //mainPanel.setPreferredSize(new Dimension(1920,1280));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        mainPanel.setLayout(new GridLayout(height, length));
        mainPanel.setOpaque(false);
        
        for (int x = 0; x <= length-1; x++){
            for (int y = 0; y <= height-1; y++){
                panels.put(new Coordinate(x,y), new JPanel());
            }
        }
        
        int counter = 1;
        for(JPanel panel : panels.values()){
            JLabel label = new JLabel(Integer.toString(counter));
            panel.add(label);
            counter++;
        }
        
        boardFrame.add(mainPanel, BorderLayout.NORTH);
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setTitle("StarAI");
        boardFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        boardFrame.pack();
        boardFrame.setVisible(true);
    }
}


