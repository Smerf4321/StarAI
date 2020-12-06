package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Patryk
 */
public class BoardGUI {
    private JFrame boardFrame;
    private JPanel mainPanel;
    private JPanel[][] panels;
    
    public BoardGUI(int length, int height){
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
        
        int counter = 1;
        for (int x = 0; x <= length-1; x++){
            for (int y = 0; y <= height-1; y++){
                JLabel label = new JLabel(Integer.toString(counter));
                JPanel panel = new JPanel();
                panel.add(label);
                panels[x][y] = panel;
                mainPanel.add(panel);
                counter++;
            }
        }
        
        boardFrame.add(mainPanel, BorderLayout.NORTH);
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setTitle("StarAI");
        boardFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        boardFrame.pack();
        boardFrame.setVisible(true);
    }
}




