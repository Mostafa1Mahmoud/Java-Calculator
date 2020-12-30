package com.GUI_Project;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame Frame =new JFrame("Calculator");
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Layer layer =  new Layer();
        layer.setBackground(Color.gray);
        layer.setPreferredSize(new Dimension(1000,420));
        Frame.getContentPane().add(layer);
        Frame.pack();
        Frame.setVisible(true);
     }
}