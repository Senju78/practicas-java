package parte2;

import javax.swing.*;
import java.awt.*;


public class Practica03_b extends JFrame {
    private JTextField Tcategoria;
    private JTextField Tid;
    private JTextArea Tareacategoria;

    public Practica03_b() {
        initComponents();
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
        Tcategoria.setEditable(false);
        panel.add(Lcategoria);
        panel.add(Tcategoria);

        JLabel Lid = new JLabel("ID:");
        Tid = new JTextField();
        Tid.setEditable(false);
        panel.add(Lid);
        panel.add(Tid);

        JLabel Lareacategoria = new JLabel("Área Categoría:");
        panel.add(Lareacategoria);

        Tareacategoria = new JTextArea();
        Tareacategoria.setEditable(false);
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Practica03_b ventana = new Practica03_b();
            ventana.setVisible(true);
        });
    }
}
