package py.edu.facitec.proyecto1.buscadores;

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
import py.edu.facitec.proyecto1.interfaces.InterfazCliente;
import py.edu.facitec.proyecto1.util.ModeloTbClientes;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BuscadorCliente extends JDialog {
	private JTable tbClientes;
	private ModeloTbClientes modeloTbClientes;
	private List<Cliente> listaClientes;
	private JTextField tfBuscar;
	private JLabel lblBuscar;
	private InterfazCliente interfazVenta;
	
	
	public InterfazCliente getInterfazVenta() {
		return interfazVenta;
	}

	public void setInterfazVenta(InterfazCliente interfazVenta) {
		this.interfazVenta = interfazVenta;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscadorCliente dialog = new BuscadorCliente();
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
	public BuscadorCliente() {
		setBounds(100, 100, 388, 318);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 353, 226);
		getContentPane().add(scrollPane);
		
		modeloTbClientes = new ModeloTbClientes();
		
		tbClientes = new JTable(modeloTbClientes);
		tbClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tbClientes.getSelectedRow()>=0) {
					Cliente cliente = listaClientes.get(tbClientes.getSelectedRow());
					interfazVenta.cargarCliente(cliente);
					dispose();
				}
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
		tfBuscar.setBounds(58, 11, 305, 24);
		getContentPane().add(tfBuscar);
		tfBuscar.setColumns(10);
		
		lblBuscar = new JLabel("Buscar");
		lblBuscar.setBounds(10, 13, 51, 20);
		getContentPane().add(lblBuscar);
		
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
	
	
	
} //FIN DE LA CLASE
