package com.unibg.ticketgenerator.gui;

import com.unibg.ticketgenerator.TicketGeneratorApplication;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerInterface extends JPanel {
    static JLabel winTitle = new JLabel("TicketGenerator - Server");
    static JButton startServerButton = new JButton("START");
    static JButton stopServerButton = new JButton("STOP");

    static JLabel serverStatus = new JLabel("Server status: OFFLINE");

    static ButtonGroup buttonGroup = new ButtonGroup();

    static JPanel infopanel = new JPanel();

    static JFrame jF = new JFrame("Server");


    public ServerInterface() {

        winTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        winTitle.setVerticalAlignment(JLabel.CENTER);
        winTitle.setHorizontalAlignment(JLabel.CENTER);

        startServerButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        startServerButton.setBackground(Color.BLUE);
        startServerButton.setForeground(Color.WHITE);
        startServerButton.setFocusPainted(false);
        startServerButton.setPreferredSize(new Dimension(100, 40));
        startServerButton.setMaximumSize(new Dimension(100, 40));
        startServerButton.setBorderPainted(false);
        startServerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Border line = new LineBorder(Color.BLACK, 0);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        startServerButton.setBorder(compound);
        startServerButton.setMargin(new Insets(0, 10, 0, 10));
        //startServerButton.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 5), new EmptyBorder(30, 30, 30, 30)));

        stopServerButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        stopServerButton.setBackground(Color.RED);
        stopServerButton.setForeground(Color.WHITE);
        stopServerButton.setFocusPainted(false);
        stopServerButton.setPreferredSize(new Dimension(100, 40));
        stopServerButton.setMaximumSize(new Dimension(100, 40));
        startServerButton.setBorderPainted(false);
        stopServerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        stopServerButton.setBorder(compound);
        stopServerButton.setEnabled(false);
        stopServerButton.setMargin(new Insets(0, 10, 0, 10));
        //stopServerButton.setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 5), new EmptyBorder(30, 30, 30, 30)));

        serverStatus.setFont(new Font("SansSerif", Font.BOLD, 15));
        serverStatus.setForeground(Color.RED);
        serverStatus.setVerticalAlignment(JLabel.CENTER);
        serverStatus.setHorizontalAlignment(JLabel.CENTER);

        infopanel.setPreferredSize(new Dimension(500, 200));
        infopanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 0, 10);
        infopanel.add(startServerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 0, 10);
        infopanel.add(stopServerButton, gbc);

        jF.getContentPane().add(infopanel);
        jF.getContentPane().add(winTitle, BorderLayout.NORTH);
        jF.getContentPane().add(serverStatus, BorderLayout.SOUTH);
        jF.pack();
        jF.setLocationRelativeTo(null);
        jF.setResizable(false);
        jF.setVisible(true);

        jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //start server button click listener
        startServerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TicketGeneratorApplication.startServer();

                serverStatus.setText("Server status: ONLINE");
                serverStatus.setForeground(Color.BLUE);
                startServerButton.setEnabled(false);
                stopServerButton.setEnabled(true);
            }
        });

        //stop server button click listener
        stopServerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                TicketGeneratorApplication.stopServer();

                serverStatus.setText("Server status: OFFLINE");
                serverStatus.setForeground(Color.RED);
                startServerButton.setEnabled(true);
                stopServerButton.setEnabled(false);
            }
        });

    }

}


