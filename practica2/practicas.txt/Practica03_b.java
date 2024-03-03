package Prcaticas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Practica03_b extends JFrame {
    private JTextField Tid;
    private JTextField Tcategoria;
    private JTable table;

    public Practica03_b() {
        setTitle("Practica03_ben");
        setSize(587, 410);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
// vamos por unos tacos Anna :)
        
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(0, 0, 765, 40);
        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(67, 0, 119, 20);
        JLabel labelCategoria = new JLabel("Categoría:");
        labelCategoria.setBounds(67, 20, 158, 20);
        Tid = new JTextField();
        Tid.setBounds(235, 0, 298, 20);
        Tcategoria = new JTextField();
        Tcategoria.setBounds(235, 20, 298, 20);
        inputPanel.setLayout(null);
        inputPanel.add(labelId);
        inputPanel.add(Tid);
        inputPanel.add(labelCategoria);
        inputPanel.add(Tcategoria);
        getContentPane().add(inputPanel);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(35, 5, 107, 23);
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(230, 5, 98, 23);
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(377, 5, 107, 23);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 51, 765, 33);
        buttonPanel.setLayout(null);
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnSalir);
        getContentPane().add(buttonPanel);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCategoria();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCategoria();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 91, 541, 269);
        scrollPane.setViewportBorder(null);
        getContentPane().add(scrollPane);

        cargarDatosDesdeArchivo();

        setVisible(true);
    }

    private void cargarDatosDesdeArchivo() {
        File archivo = new File("datos.txt");
        if (archivo.exists()) {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Categoría");

            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 2) {
                        model.addRow(datos);
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            table.setModel(model);
        }
    }

    private void agregarCategoria() {
    	    String id = Tid.getText();
    	    String categoria = Tcategoria.getText();

    	    DefaultTableModel model = (DefaultTableModel) table.getModel();

    	    boolean existe = false;
    	    for (int i = 0; i < model.getRowCount(); i++) {
    	        if (model.getValueAt(i, 0).equals(id)) {
    	            existe = true;
    	            break;
    	        }
    	    }

    	    if (!existe) {
    	        model.addRow(new String[]{id, categoria});
    	        try {
    	            BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt", true));
    	            bw.write(id + "," + categoria);
    	            bw.newLine();
    	            bw.close();
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
    	    } else {
    	        JOptionPane.showMessageDialog(this, "El ID de la categoría ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
    	    }
    	}


    private void eliminarCategoria() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(filaSeleccionada);

            try {
        
                BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt"));
                for (int i = 0; i < model.getRowCount(); i++) {
                    bw.write(model.getValueAt(i, 0) + "," + model.getValueAt(i, 1));
                    bw.newLine();
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Practica03_b::new);
    }
}
