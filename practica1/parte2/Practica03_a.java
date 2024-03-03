package parte2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Practica03_a extends JFrame implements ActionListener{

	ListaInsumos listainsumo;
	ListaCategorias listacategorias;
	private JComboBox ComboCategoria;
	private JTextField Tid,Tinsumo;
	private JButton Bagregar, Beliminar, Bsalir;
	private JTextArea areaProductos;
	private JPanel panelFormulario;
	
	public void inicializarcategorias() {
		this.listacategorias = new ListaCategorias();
		Categoria nodo1 = new Categoria("01","Materiales");
		Categoria nodo2 = new Categoria("02","Mano de Obra");
		Categoria nodo3 = new Categoria("03","Maquinaria y Equipo");
		Categoria nodo4 = new Categoria("04","Servicios");
		this.listacategorias.agregarCategoria(nodo1);
		this.listacategorias.agregarCategoria(nodo2);
		this.listacategorias.agregarCategoria(nodo3);
		this.listacategorias.agregarCategoria(nodo4);
		
	}
	public Practica03_a() {
		super("Administración de Productos");
		this.inicializarcategorias();
		this.listainsumo = new ListaInsumos();
		setBounds(0,0,390,370);
		panelFormulario = new JPanel();
		panelFormulario.setLayout(null);
		getContentPane().add(panelFormulario,BorderLayout.CENTER);
		JLabel labelCategoria = new JLabel("Categoria:");
		labelCategoria.setBounds(10,66,71,20);
		ComboCategoria = new JComboBox(this.listacategorias.CategoriaArreglo());
		ComboCategoria.setEditable(false);
		ComboCategoria.setBounds(91,66,160,20);
		ComboCategoria.addActionListener(this);
		panelFormulario.add(labelCategoria);
		panelFormulario.add(ComboCategoria);
		
		JLabel labelId = new JLabel("ID:");
		labelId.setBounds(10,9,71,20);
		this.Tid = new JTextField(10);
		this.Tid.setEditable(false);
		this.Tid.setBounds(91,9,71,20);
		panelFormulario.add(labelId);
		panelFormulario.add(Tid);
		
		JLabel labelInsumo = new JLabel("Insumo:");
		labelInsumo.setBounds(10,34,71,20);
		this.Tinsumo = new JTextField(10);
		this.Tinsumo.setEditable(false);
		this.Tinsumo.setBounds(91,35,147,20);
		panelFormulario.add(labelInsumo);
		panelFormulario.add(Tid);
		
		this.Bagregar = new JButton("Agregar");
		this.Bagregar.setBounds(20,104,111,20);
		this.Bagregar.addActionListener(this);
		panelFormulario.setLayout(null);
		panelFormulario.add(Tinsumo);
		
		this.Beliminar = new JButton("Eliminar");
		this.Beliminar.setBounds(153,104,111,20);
		this.Beliminar.addActionListener(this);
		panelFormulario.setLayout(null);
		panelFormulario.add(Beliminar);
		
		this.Bsalir = new JButton("Salir");
		this.Bsalir.setBounds(274,104,79,20);
		this.Bsalir.addActionListener(this);
		panelFormulario.add(Bsalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10,132,357,179);
		panelFormulario.add(scrollPane);
		this.areaProductos = new JTextArea(10,40);
		scrollPane.setViewportView(areaProductos);
		this.areaProductos.setEditable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}
	
	public void Volveralinicio() {
		this.Bagregar.setText("Agregar");
		this.Bsalir.setText("Salir");
		this.Beliminar.setEnabled(true);
		this.Tid.setEditable(false);
		this.Tinsumo.setEditable(false);
		this.Tid.setText("");
		this.Tinsumo.setText("");
	}
	
	public void Altas() {
		if(this.Bagregar.getText().compareTo("Agregar")==0) {
			this.Bagregar.setText("Salvar");
			this.Bsalir.setText("Cancelar");
			this.Beliminar.setEnabled(false);
			this.Tid.setEditable(true);
			this.Tinsumo.setEditable(true);
			this.ComboCategoria.setEditable(true);
			this.ComboCategoria.setFocusable(true);
		}
		else {
			System.out.println("Aqui");
			if(esdatoscompletos()) {
				String id,insumo,idcategoria;
				id = this.Tid.getText().trim();
				insumo = this.Tinsumo.getText().trim();
				idcategoria = (((Categoria) this.ComboCategoria.getSelectedItem()).getIdcategoria().trim());
				Insumo nodo = new Insumo(id,insumo,idcategoria);
				if(!this.listainsumo.agregarInsumo(nodo))
					JOptionPane.showMessageDialog(this, "Lo siento pero el id "+id+" ya existe, lo yiene asignado "+this.listainsumo.buscarInsumo(id));
				else
					this.areaProductos.setText(this.listainsumo.toString());
			}
			this.Volveralinicio();
		}
	}
	
	public void Eliminar() {
		Object[] opciones = this.listainsumo.idInsumos();
		String id = (String) JOptionPane.showInputDialog(null,"Seleccione una opción: ","Eliminación de Insumos",JOptionPane.PLAIN_MESSAGE,null,opciones,opciones[0]);
		if((id!=null)||(!id.isEmpty())) {
			if(!this.listainsumo.eliminarInsumoPorId(id))
				JOptionPane.showMessageDialog(this, "No existe este id");
			else
				this.areaProductos.setText(this.listainsumo.toString());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.Bagregar) {
			this.Altas();
		}
		else if(e.getSource()==this.Beliminar) {
			this.Eliminar();
		}
		else if(e.getSource() == Bsalir) {
			if(this.Bsalir.getText().compareTo("Cancelar")==0) {
				this.Volveralinicio();
			}
			else
				this.dispose();
		}
		
	}
	
	public static void main(String[] args) {
		new Practica03_a();
	}
	
	
	
	private boolean esdatoscompletos() {
		// TODO Auto-generated method stub
		return false;
	}

}
