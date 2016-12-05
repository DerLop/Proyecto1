package py.edu.facitec.proyecto1.formularios;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import py.edu.facitec.proyecto1.buscadores.BuscadorCliente;
import py.edu.facitec.proyecto1.dao.VentaDao;
import py.edu.facitec.proyecto1.entidad.Cliente;
import py.edu.facitec.proyecto1.entidad.Venta;
import py.edu.facitec.proyecto1.interfaces.InterfazCliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class FormVenta extends JDialog implements InterfazCliente {
	private JTextField tfNumeroVenta;
	private JTextField tfCodigoCliente;
	private JTextField tfNombeCliente;
	private Cliente cliente;
	private Venta venta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormVenta dialog = new FormVenta();
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
	public FormVenta() {
		setBounds(100, 100, 589, 465);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 553, 66);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNroVenta = new JLabel("Nro Venta");
		lblNroVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNroVenta.setBounds(10, 11, 62, 14);
		panel.add(lblNroVenta);
		
		tfNumeroVenta = new JTextField();
		tfNumeroVenta.setBounds(93, 8, 86, 20);
		panel.add(tfNumeroVenta);
		tfNumeroVenta.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(10, 41, 62, 14);
		panel.add(lblCliente);
		
		tfCodigoCliente = new JTextField();
		tfCodigoCliente.setBounds(93, 38, 86, 20);
		panel.add(tfCodigoCliente);
		tfCodigoCliente.setColumns(10);
		
		tfNombeCliente = new JTextField();
		tfNombeCliente.setBounds(206, 38, 325, 20);
		panel.add(tfNombeCliente);
		tfNombeCliente.setColumns(10);
		
		JButton btnBuscarCliente = new JButton("...");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarBuscadorCliente();
			}
		});
		btnBuscarCliente.setBounds(178, 37, 25, 23);
		panel.add(btnBuscarCliente);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recuperVentaPorCodigo();
			}
		});
		btnConsultar.setBounds(442, 7, 89, 23);
		panel.add(btnConsultar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarVenta();
			}
		});
		btnGuardar.setBounds(99, 392, 89, 23);
		getContentPane().add(btnGuardar);
		
		JButton btnAnular = new JButton("Anular");
		btnAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				anularVenta();
			}
		});
		btnAnular.setBounds(364, 392, 89, 23);
		getContentPane().add(btnAnular);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificarVenta();
			}
		});
		btnModificar.setBounds(225, 392, 89, 23);
		getContentPane().add(btnModificar);

	}
	
	private void mostrarBuscadorCliente() {
		BuscadorCliente buscadorCliente = new BuscadorCliente();
		buscadorCliente.setInterfazVenta(this);
		buscadorCliente.setVisible(true);
	}

	public void cargarCliente(Cliente cliente) {
		tfCodigoCliente.setText(cliente.getId()+"");
		tfNombeCliente.setText(cliente.getNombre());
		this.cliente = cliente;
	}
	private void guardarVenta() {
		Venta venta = new Venta();
		venta.setCliente(cliente);
		venta.setEstado(true);
		venta.setFecha(new Date());
		VentaDao.guardarVenta(venta);
	}
	
	private void modificarVenta() {
		Venta venta = new Venta();
		venta.setNumero(Integer.parseInt(tfNumeroVenta.getText()));
		venta.setCliente(cliente);
	
		VentaDao.modificarVenta(venta);
	}
	
	private void recuperVentaPorCodigo() {
		int codigo = Integer.parseInt(tfNumeroVenta.getText());
		venta = VentaDao.recuperarVentaPorCodigo(codigo);
		cliente = venta.getCliente();
		tfCodigoCliente.setText(cliente.getId()+"");
		tfNombeCliente.setText(cliente.getNombre());
	}
	
	private void anularVenta() {
		int codigo = Integer.parseInt(tfNumeroVenta.getText());
		VentaDao.anularVenta(codigo);
	}
}
