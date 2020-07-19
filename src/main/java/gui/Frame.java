package gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(Panel panel){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(100, 100);
        setSize(new Dimension(700, 600));
        setLayout(null);
        setTitle("Voronoi Diagram");
        add(panel);
        setVisible(true);
    }
}
