package Prcaticas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class practica3_c extends JInternalFrame {
    private JTextField Tid;
    private JTextField Tcategoria;
    private JTable table;
    private JMenuBar menuBar;

    public practica3_c() {
        setTitle("practica3_ca");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        disableMenu();

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                enableMenu();
                dispose(); 
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(600, 300));
        mainPanel.setLayout(null);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(0, 1, 881, 65);
        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(65, 0, 61, 32);
        JLabel labelCategoria = new JLabel("Categoría:");
        labelCategoria.setBounds(65, 26, 85, 39);
        Tid = new JTextField();
        Tid.setBounds(177, 0, 175, 25);
        Tcategoria = new JTextField();
        Tcategoria.setBounds(177, 36, 185, 18);
        inputPanel.setLayout(null);
        inputPanel.add(labelId);
        inputPanel.add(Tid);
        inputPanel.add(labelCategoria);
        inputPanel.add(Tcategoria);
        mainPanel.add(inputPanel);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 62, 881, 40);
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 5, 112, 23);
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(166, 5, 96, 23);
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(303, 5, 84, 23);
        buttonPanel.setLayout(null);
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnSalir);
        mainPanel.add(buttonPanel);

   
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 881, 363);
        mainPanel.add(scrollPane);

    
        getContentPane().add(mainPanel);

 
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                enableMenu();
                dispose();
            }
        });

        pack();
        setVisible(true);
    }

    private void disableMenu() {
        menuBar.setEnabled(false);
    }

    private void enableMenu() {
        menuBar.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane);

            practica3_c internalFrame = new practica3_c();
            desktopPane.add(internalFrame);
        });
    }
}
