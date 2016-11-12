import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
*Esta clase grafica los datos de la base de datos
*@author: Javier Andres Ramos Galvez 16230 
*@author: Rodrigo Stuardo Juarez Jui 16073 
*@author: Rodrigo Javier Albizures Lopez 16767
*@author: Jose Rodolfo Perez Garcia 16056
*@version: 11/2/2016
*/


public class graph {

	private JFrame frame;
	private JTextField txtIngreso;
	private JTextField txtNombre;
	private JTextField textCant;
	private JLabel lblIngresarGasto;
	private JLabel lblIngresarDinero;
	private JLabel lblTipo;
	private JLabel lblCosto;
	private JLabel lblPeriodicidad;
	private JLabel lblDia;
	private JLabel lblMes;
	private JComboBox cmbDia;
	private JComboBox cmbMes;
	private JComboBox comboBoxTiempo;
	private JComboBox cmbTipo;
	private JButton btnVerDatos;
	private JButton btnGraficar;
	private JButton btnAniadirGasto;
	private JButton btnIngresarCantidad;
	private JButton btnModificarGasto;
	private Statement state;
	private JFreeChart Grafica;
	private Calculos operaciones;
	private JLabel lblMonto;
	
	DefaultCategoryDataset datos= new DefaultCategoryDataset();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					graph window = new graph();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public graph() {
		initialize();
		//*
		Grafica = ChartFactory.createBarChart("Gastos", "Categorias", "Gasto", datos, PlotOrientation.VERTICAL, true, true, false);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Crea la grafica
		operaciones = new Calculos();
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 598, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblIngresarGasto = new JLabel("Ingresar Gasto");
		lblIngresarGasto.setBounds(67, 25, 177, 16);
		frame.getContentPane().add(lblIngresarGasto);
		
		JLabel lblIngresarDinero = new JLabel("A\u00F1adir cantidad a Ingresos");
		lblIngresarDinero.setBounds(346, 25, 219, 16);
		frame.getContentPane().add(lblIngresarDinero);
		
		txtIngreso = new JTextField();
		txtIngreso.setBounds(364, 54, 116, 22);
		frame.getContentPane().add(txtIngreso);
		txtIngreso.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(185, 53, 116, 22);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipoDeGasto = new JLabel("Tipo de Gasto:");
		lblTipoDeGasto.setBounds(12, 94, 128, 16);
		frame.getContentPane().add(lblTipoDeGasto);
		
		btnIngresarCantidad = new JButton("Ingresar Cantidad");
		btnIngresarCantidad.setBounds(345, 90, 169, 25);
		frame.getContentPane().add(btnIngresarCantidad);
		btnIngresarCantidad.addActionListener(new Listener());
		
		btnModificarGasto = new JButton("Modificar Gasto");
		btnModificarGasto.setBounds(346, 235, 168, 25);
		frame.getContentPane().add(btnModificarGasto);
		btnModificarGasto.addActionListener(new Listener());
		
		btnGraficar = new JButton("Graficar");
		btnGraficar.setBounds(364, 184, 97, 25);
		frame.getContentPane().add(btnGraficar);
		btnGraficar.addActionListener(new Listener());
		
		cmbTipo = new JComboBox();
		cmbTipo.setModel(new DefaultComboBoxModel(new String[] {"Ocio","Servicios","Comida","Estudios"}));
		cmbTipo.setBounds(185, 90, 116, 22);
		frame.getContentPane().add(cmbTipo);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(54, 123, 72, 16);
		frame.getContentPane().add(lblCosto);
		
		textCant = new JTextField();
		textCant.setBounds(185, 122, 116, 22);
		frame.getContentPane().add(textCant);
		textCant.setColumns(10);
		
		JLabel lblNombre = new JLabel("Gasto (Comentario):");
		lblNombre.setBounds(12, 53, 155, 16);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblDia = new JLabel("Dia:");
		lblDia.setBounds(23, 173, 35, 16);
		frame.getContentPane().add(lblDia);
		
		cmbDia = new JComboBox();
		cmbDia.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",}));
		cmbDia.setBounds(70, 170, 56, 22);
		frame.getContentPane().add(cmbDia);
		
		JLabel lblMes = new JLabel("Mes:");
		lblMes.setBounds(138, 173, 35, 16);
		frame.getContentPane().add(lblMes);
		
		cmbMes = new JComboBox();
		cmbMes.setModel(new DefaultComboBoxModel(new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre",}));
		cmbMes.setBounds(185, 170, 89, 22);
		frame.getContentPane().add(cmbMes);
		
		btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.setBounds(364, 135, 128, 25);
		frame.getContentPane().add(btnVerDatos);
		btnVerDatos.addActionListener(new Listener());
		
		JLabel lblPeriodicidad = new JLabel("Periodicidad:");
		lblPeriodicidad.setBounds(23, 217, 103, 16);
		frame.getContentPane().add(lblPeriodicidad);
		
		comboBoxTiempo = new JComboBox();
		comboBoxTiempo.setModel(new DefaultComboBoxModel(new String[] {"Ninguna","Diaria","Semanal","Mensual","Anual"}));
		comboBoxTiempo.setBounds(158, 214, 116, 22);
		frame.getContentPane().add(comboBoxTiempo);
		
		btnAniadirGasto = new JButton("A\u00F1adir Gasto");
		btnAniadirGasto.setBounds(94, 261, 132, 25);
		frame.getContentPane().add(btnAniadirGasto);
		btnAniadirGasto.addActionListener(new Listener());
		
		lblMonto = new JLabel("");
		lblMonto.setBounds(422, 261, 200, 50);
		frame.getContentPane().add(lblMonto);
		lblMonto.setText(""+operaciones.getMonto());
		
		
	}
	/**
	*Listener del boton aniadirgasto
	*/
	
	class Listener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 if (e.getSource() == btnAniadirGasto) {
					 	try{
					 	Integer.parseInt(textCant.getText());
					 	operaciones.setGasto(Integer.parseInt(textCant.getText()), txtNombre.getText(), cmbTipo.getSelectedItem().toString(), Integer.parseInt(cmbDia.getSelectedItem().toString()), cmbMes.getSelectedItem().toString());
					 	textCant.setText("");
					 	txtNombre.setText("");
					 	lblMonto.setText(""+operaciones.getMonto());
					 	}
					 	catch(InputMismatchException e1){
					 		e1.printStackTrace();
					 		System.err.println("El valor ingresado no es un numero");
					 		JOptionPane.showMessageDialog(frame,
					 			    "El valor ingresado no es un numero",
					 			    "Error",
					 			    JOptionPane.ERROR_MESSAGE);
					 	}
					 	catch(NumberFormatException e1){
					 		e1.printStackTrace();
					 		System.err.println("El valor ingresado no es un numero");
					 		JOptionPane.showMessageDialog(frame,
					 			    "El valor ingresado no es un numero",
					 			    "Error",
					 			    JOptionPane.ERROR_MESSAGE);
					 	}
					 	
			 } 	
			 if (e.getSource() == btnGraficar) {
				 	JFrame grafica = new JFrame("");
				 	double[] n= new double[4];
				 	//Esta instruccion es para darle datos a la grafica
				 	n = operaciones.setDraw();
				 	datos.addValue(n[0],"Ocio","Ocio");
				 	datos.addValue(n[1],"Estudios","Estudios");
				 	datos.addValue(n[2],"Servicios","Servicios");
				 	datos.addValue(n[3],"Comida","Comida");
				 	
				 	ChartPanel Panel= new ChartPanel(Grafica);
				 	
				 	grafica.getContentPane().add(Panel);
				 	
					grafica.pack();
					grafica.setVisible(true);
					grafica.setBounds(100, 100, 598, 346);
					grafica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        }
		        
		     if (e.getSource() == btnVerDatos) {
		    	data data = new data();
		        data.setVisible(true);	
		        }
		        
		     if (e.getSource() == btnModificarGasto) {
		    	 Modificar mod = new Modificar();
		        mod.setVisible(true);
		        }
		        
		     if (e.getSource() == btnIngresarCantidad) {
		    	 try{
		    	 	operaciones.aniadirMonto(Integer.parseInt(txtIngreso.getText()));
		    	 	lblMonto.setText(""+operaciones.getMonto());
		    	 }
		    	 catch(InputMismatchException e1){
		    		 e1.printStackTrace();
		    		 System.err.println("El valor ingresado no es un numero");
		    		 JOptionPane.showMessageDialog(frame,
		    				    "El valor ingresado no es un numero",
		    				    "Error",
		    				    JOptionPane.ERROR_MESSAGE);
		    	 }
		    	 catch(NumberFormatException e1){
				 		e1.printStackTrace();
				 		System.err.println("El valor ingresado no es un numero");
				 		JOptionPane.showMessageDialog(frame,
				 			    "El valor ingresado no es un numero",
				 			    "Error",
				 			    JOptionPane.ERROR_MESSAGE);
				 	}
		        }
		
		}

	
	}
}	

