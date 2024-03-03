package parte2;

import Librería.Archivotxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Practica03_c extends JFrame {
    private JTextField Tcategoria;
    private JTextField Tid;
    private JTextArea Tareacategoria;
    private Archivotxt archivo;

    public Practica03_c() {
        initComponents();
        archivo = new Archivotxt("datos.txt");
    }

    private void initComponents() {
        setTitle("Administrador de Categorías");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));

        JLabel Lcategoria = new JLabel("Categoría:");
        Tcategoria = new JTextField();
        panel.add(Lcategoria);
        panel.add(Tcategoria);

        JLabel Lid = new JLabel("ID:");
        Tid = new JTextField();
        panel.add(Lid);
        panel.add(Tid);

        JLabel Lareacategoria = new JLabel("Área Categoría:");
        panel.add(Lareacategoria);

        Tareacategoria = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(Tareacategoria);
        panel.add(scrollPane);

        JButton bagregar = new JButton("Agregar");
        JButton beliminar = new JButton("Eliminar");
        JButton bsalir = new JButton("Salir");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(bagregar);
        panelBotones.add(beliminar);
        panelBotones.add(bsalir);

        Container cp = getContentPane();
        cp.add(panel, BorderLayout.CENTER);
        cp.add(panelBotones, BorderLayout.SOUTH);

        bagregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = Tcategoria.getText();
                String id = Tid.getText();
                String areaCategoria = Tareacategoria.getText();
                String linea = categoria + "," + id + "," + areaCategoria;
                guardarDatos(linea);
            }
        });

        beliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar eliminación de datos si es necesario
            }
        });

        bsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        cargarDatos();
    }

    private void guardarDatos(String linea) {
        List<String> datos = archivo.cargarDatos();
        datos.add(linea);
        archivo.guardarDatos(datos);
    }

    private void cargarDatos() {
        List<String> datos = archivo.cargarDatos();
        for (String linea : datos) {
            Tareacategoria.append(linea + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Practica03_c ventana = new Practica03_c();
            ventana.setVisible(true);
        });
    }
}