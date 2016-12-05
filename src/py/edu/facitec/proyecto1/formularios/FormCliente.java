package py.edu.facitec.proyecto1.formularios;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import py.edu.facitec.proyecto1.dao.ClienteDao;
import py.edu.facitec.proyecto1.entidad.Cliente;
import py.edu.facitec.proyecto1.util.ModeloTbClientes;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormCliente extends JDialog {
	private JTextField tfCodigo;
	private JTextField tfDocumento;
	private JTextField tfNombre;
	private JTextField tfDireccion;
	private JLabel lblTelefono;
	private JLabel lblSexo;
	private JTextField tfTelefono;
	
	private JComboBox cbSexo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JCheckBox chActivo;
	private JTable tbClientes;
	private ModeloTbClientes modeloTbClientes;
	private List<Cliente> listaClientes;
	private JTextField tfBuscar;
	private JLabel lblBuscar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCliente dialog = new FormCliente();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public FormCliente() {
		setBounds(100, 100, 836, 318);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(12, 28, 70, 15);
		getContentPane().add(lblCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(100, 26, 179, 19);
		getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(12, 56, 90, 15);
		getContentPane().add(lblDocumento);
		
		tfDocumento = new JTextField();
		tfDocumento.setBounds(100, 54, 179, 19);
		getContentPane().add(tfDocumento);
		tfDocumento.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 83, 70, 15);
		getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(100, 85, 319, 19);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(12, 118, 70, 15);
		getContentPane().add(lblDireccion);
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(100, 116, 319, 19);
		getContentPane().add(tfDireccion);
		tfDireccion.setColumns(10);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(12, 151, 70, 15);
		getContentPane().add(lblTelefono);
		
		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(12, 177, 70, 15);
		getContentPane().add(lblSexo);
		
		tfTelefono = new JTextField();
		tfTelefono.setBounds(100, 146, 194, 19);
		getContentPane().add(tfTelefono);
		tfTelefono.setColumns(10);
		
		cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		cbSexo.setBounds(100, 177, 179, 24);
		getContentPane().add(cbSexo);
		
		chActivo = new JCheckBox("Activo");
		chActivo.setBounds(100, 209, 129, 23);
		getContentPane().add(chActivo);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarCliente();
			}
		});
		btnGuardar.setBounds(12, 239, 117, 25);
		getContentPane().add(btnGuardar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarClientePorCodigo();
			}
		});
		btnBuscar.setBounds(291, 23, 117, 25);
		getContentPane().add(btnBuscar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificarCliente();
			}
		});
		btnModificar.setBounds(141, 239, 117, 25);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarCliente();
			}
		});
		btnEliminar.setBounds(291, 239, 117, 25);
		getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(457, 42, 353, 226);
		getContentPane().add(scrollPane);
		
		modeloTbClientes = new ModeloTbClientes();
		
		tbClientes = new JTable(modeloTbClientes);
		tbClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cargarFormulario();
			}
		});
		scrollPane.setViewportView(tbClientes);
		
		tfBuscar = new JTextField();
		tfBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					consultarClientesPorFiltros();
				}
			}
		});
		tfBuscar.setBounds(505, 11, 305, 24);
		getContentPane().add(tfBuscar);
		tfBuscar.setColumns(10);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setBounds(457, 13, 51, 20);
		getContentPane().add(lblBuscar);
		
		recuperarClientes();

	}
	
	private void registrarCliente(){
		Cliente c = new Cliente(); //creamos el objeto cliente
		c.setDocumento(tfDocumento.getText()); //seteamos el valor
		c.setNombre(tfNombre.getText());
		c.setDireccion(tfDireccion.getText());
		c.setTelefono(tfTelefono.getText());
		c.setActivo(chActivo.isSelected());
		c.setSexo(cbSexo.getSelectedIndex());
		
		//el resto es tarea
		
		ClienteDao.guardarCliente(c);
		vaciarCampos();
		recuperarClientes();
	}
	
	private void consultarClientePorCodigo() {
		
		int codigo = Integer.parseInt(tfCodigo.getText());
		Cliente cliente = ClienteDao.recuperarClientePorCodigo(codigo);
		
		if (cliente != null) {
			tfNombre.setText(cliente.getNombre());
			tfDireccion.setText(cliente.getDireccion());
			tfTelefono.setText(cliente.getTelefono());
			tfDocumento.setText(cliente.getDocumento());
			cbSexo.setSelectedIndex(cliente.getSexo());
			chActivo.setSelected(cliente.isActivo());
		}
	}
	
	private void modificarCliente() {
		Cliente c = new Cliente(); //creamos el objeto cliente
		
		c.setId(Integer.parseInt(tfCodigo.getText()));
		
		c.setDocumento(tfDocumento.getText()); //seteamos el valor
		c.setNombre(tfNombre.getText());
		c.setDireccion(tfDireccion.getText());
		c.setTelefono(tfTelefono.getText());
		c.setActivo(chActivo.isSelected());
		c.setSexo(cbSexo.getSelectedIndex());
		
		//el resto es tarea
		
		ClienteDao.modificarCliente(c);
		vaciarCampos();
		recuperarClientes();
	}
	
	private void eliminarCliente() {
		int codigo = Integer.parseInt(tfCodigo.getText());
	
		ClienteDao.eliminarClientePorCodigo(codigo);
		vaciarCampos();
		recuperarClientes();
	}
	
	//Recupera todos los clientes y los pasa al modelo de la tabla
	private void recuperarClientes() {
		listaClientes = ClienteDao.recuperarTodo();
		modeloTbClientes.setLista(listaClientes);
		modeloTbClientes.fireTableDataChanged();
	}
	
	//Recupera todos los clientes que cumplan con la condicion
	//y los pasa al modelo de la tabla
	private void consultarClientesPorFiltros() {
		listaClientes = ClienteDao.recuperarClientesPorFiltros(tfBuscar.getText());
		modeloTbClientes.setLista(listaClientes);
		modeloTbClientes.fireTableDataChanged();
	}
	
	//Cargar los datos al formulario cuando seleccionemos un registro del jTable
	private void cargarFormulario() {
		if (tbClientes.getSelectedRow()>=0) {
			//Recuperamos el objeto del arraylist de acuerdo a la posicion seleccionada
			//en el jtable
			Cliente cliente = listaClientes.get(tbClientes.getSelectedRow());
			
			tfCodigo.setText(cliente.getId()+"");
			tfNombre.setText(cliente.getNombre());
			tfDireccion.setText(cliente.getDireccion());
			tfTelefono.setText(cliente.getTelefono());
			tfDocumento.setText(cliente.getDocumento());
			cbSexo.setSelectedIndex(cliente.getSexo());
			chActivo.setSelected(cliente.isActivo());
		}
	}
	
	private void vaciarCampos() {
		tfCodigo.setText("");
		tfNombre.setText("");
		tfDireccion.setText("");
		tfTelefono.setText("");
		tfDocumento.setText("");
		cbSexo.setSelectedIndex(0);
		chActivo.setSelected(false);
	}
} //FIN DE LA CLASE
