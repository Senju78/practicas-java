package ParteFinal2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Practica02_actividad5 extends JFrame implements ActionListener {

	private JFrame ventanaPrincipal;
    private JMenuBar barraMenu;
    private JMenu mOperacion, mConfiguracion, mSalir;
    private JMenuItem smSalida, smCategorias, smInsumos, smObras, smDatos;
    private JDesktopPane Escritorio;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Practica02_actividad5 window = new Practica02_actividad5();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Practica02_actividad5() {
		ventanaPrincipal = new JFrame();
        ventanaPrincipal.setBounds(100, 100, 622, 395);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        barraMenu = new JMenuBar();
        ventanaPrincipal.setJMenuBar(barraMenu);

        mOperacion = new JMenu("Operacion");
        barraMenu.add(mOperacion);

        mConfiguracion = new JMenu("Configuracion");
        barraMenu.add(mConfiguracion);

        mSalir = new JMenu("Salir");
        barraMenu.add(mSalir);

        smCategorias = new JMenuItem("Categorias");
        mConfiguracion.add(smCategorias);

        smInsumos = new JMenuItem("Insumos");
        mConfiguracion.add(smInsumos);
        
        smObras = new JMenuItem("Obras");
        mConfiguracion.add(smObras);
        
        smDatos = new JMenuItem("Guardar Datos en XMl");
        mConfiguracion.add(smDatos);

        smSalida = new JMenuItem("Salida");
        mSalir.add(smSalida);
        ventanaPrincipal.getContentPane().setLayout(new CardLayout(0, 0));
        
        Escritorio = new JDesktopPane();
        ventanaPrincipal.getContentPane().add(Escritorio, "name_116799488008200");

        this.smCategorias.addActionListener(this);
        this.smInsumos.addActionListener(this);
        this.smObras.addActionListener(this);
        this.smSalida.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.smSalida)
            this.ventanaPrincipal.dispose();
        else if (e.getSource() == this.smCategorias) {
            JOptionPane.showMessageDialog(this.ventanaPrincipal, "Llamando a Conceptos");
            Practica02_actividad5_2 hijo2 = new Practica02_actividad5_2(ventanaPrincipal);
	        this.Escritorio.add(hijo2);
	        hijo2.setVisible(true);
	        hijo2.guardarCategoriasEnXML();
        }
        else if (e.getSource() == this.smInsumos) {
        	Practica02_actividad5_1 hijo = new Practica02_actividad5_1(ventanaPrincipal);
            this.Escritorio.add(hijo);
            hijo.setVisible(true);
            hijo.guardarInsumosEnXML();
        }
        else if (e.getSource() == this.smObras) {
        	Practica02_actividad5_3 hijo3 = new Practica02_actividad5_3(ventanaPrincipal);
            this.Escritorio.add(hijo3);
            hijo3.setVisible(true);
        }
        else if (e.getSource() == this.smDatos) {
        	Practica02_actividad5_2 hijo2 = new Practica02_actividad5_2(ventanaPrincipal);
        	 this.Escritorio.add(hijo2);
             hijo2.setVisible(false);
        	 hijo2.guardarCategoriasEnXML();
        	 Practica02_actividad5_1 hijo = new Practica02_actividad5_1(ventanaPrincipal);
             this.Escritorio.add(hijo);
             hijo.setVisible(false);
             hijo.guardarInsumosEnXML();
        }
	}
	
}
