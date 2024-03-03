package Prcaticas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CrearObraFrame extends JInternalFrame {
    private JTextField nombreObraTextField;
    private JTextField descripcionTextField;

    public CrearObraFrame() {
        setTitle("Crear Obra");
        setClosable(true);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);

        // Layout principal
        JPanel mainPanel = new JPanel();

        // Generar ID automáticamente (no visible)
        String idObra = generarID();

        // Panel para el nombre de la obra
        JPanel nombrePanel = new JPanel();
        nombrePanel.setBounds(0, 0, 602, 50);
        JLabel nombreLabel = new JLabel("Nombre de la Obra:");
        nombreLabel.setBounds(5, 8, 160, 14);
        nombreObraTextField = new JTextField(20);
        nombreObraTextField.setBounds(200, 5, 166, 20);
        nombrePanel.setLayout(null);
        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombreObraTextField);

        // Panel para la descripción de la obra
        JPanel descripcionPanel = new JPanel();
        descripcionPanel.setBounds(0, 50, 602, 186);
        JLabel descripcionLabel = new JLabel("Descripción de la Obra:");
        descripcionLabel.setBounds(211, 11, 186, 14);
        descripcionTextField = new JTextField(20);
        descripcionTextField.setBounds(10, 34, 582, 152);
        descripcionPanel.setLayout(null);
        descripcionPanel.add(descripcionLabel);
        descripcionPanel.add(descripcionTextField);

        // Botón para guardar la obra
        JButton guardarButton = new JButton("Guardar Obra");
        guardarButton.setBounds(240, 258, 99, 23);
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarObra(idObra);
            }
        });
        mainPanel.setLayout(null);

        // Agregar componentes al panel principal
        mainPanel.add(nombrePanel);
        mainPanel.add(descripcionPanel);
        mainPanel.add(guardarButton);

        // Agregar panel principal al JInternalFrame
        getContentPane().add(mainPanel);

        pack();
        setVisible(true);
    }

    private String generarID() {
        // Aquí puedes implementar la lógica para generar un ID único automáticamente
        // Por ejemplo, podrías utilizar la fecha y hora actual, u otro método que prefieras
        return "ID-001";
    }

    private void guardarObra(String idObra) {
        String nombreObra = nombreObraTextField.getText();
        String descripcion = descripcionTextField.getText();

        // Guardar en un archivo de texto
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("obras.txt", true))) {
            writer.write(idObra + "," + nombreObra + "," + descripcion);
            writer.newLine();
            writer.close();
            JOptionPane.showMessageDialog(this, "Obra guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la obra.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Limpiar campos después de guardar
        nombreObraTextField.setText("");
        descripcionTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Crear Obra");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setVisible(true);

            JDesktopPane desktopPane = new JDesktopPane();
            frame.add(desktopPane);

            CrearObraFrame internalFrame = new CrearObraFrame();
            desktopPane.add(internalFrame);
        });
    }
}

