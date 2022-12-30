package com.unibg.ticketgeneratorVisualizer.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class Interfaccia extends JPanel {

    static ButtonGroup topGroup = new ButtonGroup();
    static JLabel winTitle = new JLabel("Prossimo numero");
    static JLabel nextNumber = new JLabel("50");
    static JLabel op1 = new JLabel("");
    static JLabel op2 = new JLabel("");
    static JLabel op3 = new JLabel("");
    static JLabel op4 = new JLabel("");
    static JLabel op5 = new JLabel("");

    static JLabel fakeOp1 = new JLabel(" ");
    static JLabel fakeOp2 = new JLabel(" ");
    static JLabel fakeOp3 = new JLabel(" ");
    static JLabel fakeOp4 = new JLabel(" ");
    static JLabel fakeOp5 = new JLabel(" ");
    static JPanel infopanel = new JPanel();

    static JFrame jF = new JFrame("Dashboard");

    static GridBagConstraints gbc;


    public Interfaccia(){

        winTitle.setFont(new Font("SansSerif", Font.BOLD, 40));
        nextNumber.setFont(new Font("SansSerif", Font.BOLD, 100));
        nextNumber.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 5), new EmptyBorder(30, 30, 30, 30)));

        op1.setFont(new Font("SansSerif", Font.PLAIN, 80));

        op2.setFont(new Font("SansSerif", Font.PLAIN, 80));
        op3.setFont(new Font("SansSerif", Font.PLAIN, 80));
        op4.setFont(new Font("SansSerif", Font.PLAIN, 80));
        op5.setFont(new Font("SansSerif", Font.PLAIN, 80));

        fakeOp1.setFont(new Font("SansSerif", Font.PLAIN, 40));
        fakeOp2.setFont(new Font("SansSerif", Font.PLAIN, 40));
        fakeOp3.setFont(new Font("SansSerif", Font.PLAIN, 40));
        fakeOp4.setFont(new Font("SansSerif", Font.PLAIN, 40));
        fakeOp5.setFont(new Font("SansSerif", Font.PLAIN, 40));








        infopanel.setBackground(Color.WHITE);

        infopanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;


        infopanel.setOpaque(false);
        infopanel.setPreferredSize(new Dimension());



        jF.getContentPane().add(infopanel);
        jF.pack();
        jF.setLocationRelativeTo(null);
        jF.setResizable(false);
        jF.setVisible(true);
        jF.setExtendedState(JFrame.MAXIMIZED_BOTH);

        jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void setData(int i, String data) {
        if(i == 0)
            op1.setText(data);
        if(i == 1)
            op2.setText(data);
        if(i == 2)
            op3.setText(data);
        if(i == 3)
            op4.setText(data);
        if(i == 4)
            op5.setText(data);


        infopanel.setPreferredSize(jF.getSize());



        // Row 0 - Filename
        // Col 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.PAGE_START;
        infopanel.add(winTitle, gbc);


        // Col 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        infopanel.add(nextNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        infopanel.add(fakeOp1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        infopanel.add(fakeOp2, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        infopanel.add(op1, gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        infopanel.add(op2, gbc);


        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        infopanel.add(op3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        infopanel.add(op4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        infopanel.add(op5, gbc);


        fakeOp1.setVisible(true);
        fakeOp2.setVisible(true);
        fakeOp3.setVisible(true);
        fakeOp4.setVisible(true);
        fakeOp5.setVisible(true);

        op1.setVisible(true);
        op2.setVisible(true);
        op3.setVisible(true);
        op4.setVisible(true);
        op5.setVisible(true);
        infopanel.revalidate();
        infopanel.repaint();


        jF.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

}


