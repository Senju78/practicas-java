package parte2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Practica03_d extends JFrame {
    private JTextField Tid;
    private JTextField Tproducto;
    private JComboBox<String> ComboCategoria;
    private JTextArea Tarea;

    public Practica03_d() {
        initComponents();
        cargarDatosDesdeArchivo();
    }

    private void initComponents() {
        setTitle("Administrador de Insumos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));

        JLabel Lid = new JLabel("ID:");
        Tid = new JTextField();
        panel.add(Lid);
        panel.add(Tid);

        JLabel Lproducto = new JLabel("Producto:");
        Tproducto = new JTextField();
        panel.add(Lproducto);
        panel.add(Tproducto);

        JLabel Lcategoria = new JLabel("Categoría:");
        panel.add(Lcategoria);

        ComboCategoria = new JComboBox<>();
        ComboCategoria.setEnabled(true); // Habilitar edición del combo box
        cargarCategorias();
        panel.add(ComboCategoria);

        JLabel Lareacategoria = new JLabel("Área Categoría:");
        panel.add(Lareacategoria);

        Tarea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(Tarea);
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

        // Asociar controladores de eventos a los botones
        bagregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarInsumo();
            }
        });

        beliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarInsumo();
            }
        });

        bsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
                dispose();
            }
        });

        // Seleccionar el primer índice del JComboBox al iniciar la ventana
        ComboCategoria.setSelectedIndex(0);
    }

    private void cargarCategorias() {
        ComboCategoria.addItem("Categoría 1");
        ComboCategoria.addItem("Categoría 2");
        ComboCategoria.addItem("Categoría 3");
    }

    private void agregarInsumo() {
        String id = Tid.getText();
        String producto = Tproducto.getText();
        String categoria = (String) ComboCategoria.getSelectedItem();

        if (!id.isEmpty() && !producto.isEmpty() && categoria != null) {
            Tarea.append(id + "," + producto + "," + categoria + "\n");
            Tid.setText("");
            Tproducto.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingrese todos los valores.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarInsumo() {
        String texto = Tarea.getText();
        if (!texto.isEmpty()) {
            // Eliminar la última línea del texto
            String[] lineas = texto.split("\n");
            StringBuilder nuevoTexto = new StringBuilder();
            for (int i = 0; i < lineas.length - 1; i++) {
                nuevoTexto.append(lineas[i]).append("\n");
            }
            Tarea.setText(nuevoTexto.toString());
        }
    }

    private void guardarDatos() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("datos.txt"));
            writer.write(Tarea.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosDesdeArchivo() {
        try {
            FileReader fileReader = new FileReader("datos.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                Tarea.append(linea + "\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos desde el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Practica03_d ventana = new Practica03_d();
            ventana.setVisible(true);
        });
    }
    }
