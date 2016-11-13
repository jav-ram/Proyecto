import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
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

	private JFrame frmPresupuesto;
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
	int anio = Year.now().getValue();
	
	DefaultCategoryDataset datos= new DefaultCategoryDataset();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					graph window = new graph();
					window.frmPresupuesto.setVisible(true);
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
		
		
		frmPresupuesto = new JFrame();
		frmPresupuesto.setTitle("Organizacion de Presupuesto");
		frmPresupuesto.setBounds(100, 100, 598, 346);
		frmPresupuesto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPresupuesto.getContentPane().setLayout(null);
		
		
		JLabel lblIngresarGasto = new JLabel("Ingresar Gasto");
		lblIngresarGasto.setBounds(67, 25, 177, 16);
		frmPresupuesto.getContentPane().add(lblIngresarGasto);
		
		JLabel lblIngresarDinero = new JLabel("A\u00F1adir cantidad de Ingresos");
		lblIngresarDinero.setBounds(346, 25, 219, 16);
		frmPresupuesto.getContentPane().add(lblIngresarDinero);
		
		txtIngreso = new JTextField();
		txtIngreso.setBounds(364, 54, 116, 22);
		frmPresupuesto.getContentPane().add(txtIngreso);
		txtIngreso.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setToolTipText("Descripci\u00F3n del gasto");
		txtNombre.setBounds(133, 53, 168, 22);
		frmPresupuesto.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipoDeGasto = new JLabel("Tipo de Gasto:");
		lblTipoDeGasto.setBounds(23, 94, 128, 16);
		frmPresupuesto.getContentPane().add(lblTipoDeGasto);
		
		btnIngresarCantidad = new JButton("Ingresar Cantidad de Ingresos");
		btnIngresarCantidad.setBounds(331, 90, 213, 25);
		frmPresupuesto.getContentPane().add(btnIngresarCantidad);
		btnIngresarCantidad.addActionListener(new Listener());
		
		btnModificarGasto = new JButton("Modificar Gasto");
		btnModificarGasto.setBounds(346, 235, 168, 25);
		frmPresupuesto.getContentPane().add(btnModificarGasto);
		btnModificarGasto.addActionListener(new Listener());
		
		btnGraficar = new JButton("Graficar");
		btnGraficar.setBounds(364, 184, 97, 25);
		frmPresupuesto.getContentPane().add(btnGraficar);
		btnGraficar.addActionListener(new Listener());
		
		cmbTipo = new JComboBox();
		cmbTipo.setModel(new DefaultComboBoxModel(new String[] {"Ocio","Servicios","Comida","Estudios", "Otro"}));
		cmbTipo.setBounds(133, 91, 132, 22);
		frmPresupuesto.getContentPane().add(cmbTipo);
		
		JLabel lblCosto = new JLabel("Costo del gasto:");
		lblCosto.setToolTipText("Cantidad monetaria que gast\u00F3");
		lblCosto.setBounds(10, 128, 103, 16);
		frmPresupuesto.getContentPane().add(lblCosto);
		
		textCant = new JTextField();
		textCant.setBounds(136, 125, 165, 22);
		frmPresupuesto.getContentPane().add(textCant);
		textCant.setColumns(10);
		
		JLabel lblNombre = new JLabel("Gasto (Comentario):");
		lblNombre.setBounds(10, 57, 116, 16);
		frmPresupuesto.getContentPane().add(lblNombre);
		
		JLabel lblDia = new JLabel("Dia:");
		lblDia.setBounds(23, 173, 35, 16);
		frmPresupuesto.getContentPane().add(lblDia);
		
		cmbDia = new JComboBox();
		cmbDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Marzo")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Abril")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDia.setSelectedItem("30");
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Junio")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDia.setSelectedItem("30");
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Septiembre")){
					System.err.println("El valor ingresado no es un numero");
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDia.setSelectedItem("30");
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Noviembre")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDia.setSelectedItem("30");
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Febrero")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		if((anio%4==0) && (anio%100==0)){
			 			cmbDia.setSelectedItem("28");
			 		}
			 		if((anio%4==0) && (anio%100!=0)){
			 			cmbDia.setSelectedItem("29");
			 		}
				}
				if((cmbDia.getSelectedItem()=="30")&&(cmbMes.getSelectedItem()=="Febrero")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		if((anio%4==0) && (anio%100==0)){
			 			cmbDia.setSelectedItem("28");
			 		}
			 		if((anio%4==0) && (anio%100!=0)){
			 			cmbDia.setSelectedItem("29");
			 		}
			 		
				}
				if((cmbDia.getSelectedItem()=="29")&&(cmbMes.getSelectedItem()=="Febrero")){
					if(anio%4==0){
					}
					if((anio%4!=0) && (cmbMes.getSelectedItem()=="Febrero")){
						JOptionPane.showMessageDialog(frmPresupuesto,
					 			    "La fecha no es valida",
					 			    "Error",
					 			    JOptionPane.ERROR_MESSAGE);
					 		cmbDia.setSelectedItem("28");
					}
				}
			}
		});
		cmbDia.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",}));
		cmbDia.setBounds(70, 170, 56, 22);
		frmPresupuesto.getContentPane().add(cmbDia);
		
		JLabel lblMes = new JLabel("Mes:");
		lblMes.setBounds(138, 173, 35, 16);
		frmPresupuesto.getContentPane().add(lblMes);
		
		cmbMes = new JComboBox();
		cmbMes.setModel(new DefaultComboBoxModel(new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre",}));
		cmbMes.setBounds(185, 170, 89, 22);
		frmPresupuesto.getContentPane().add(cmbMes);
		cmbMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Marzo")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDia.setSelectedItem("30");
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Abril")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDia.setSelectedItem("30");
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Junio")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDia.setSelectedItem("30");
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Septiembre")){
					System.err.println("El valor ingresado no es un numero");
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDia.setSelectedItem("30");
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Noviembre")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDia.setSelectedItem("30");
				}
				if((cmbDia.getSelectedItem()=="31")&&(cmbMes.getSelectedItem()=="Febrero")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		if((anio%4==0) && (anio%100==0)){
			 			cmbDia.setSelectedItem("28");
			 		}
			 		if((anio%4==0) && (anio%100!=0)){
			 			cmbDia.setSelectedItem("29");
			 		}
				}
				if((cmbDia.getSelectedItem()=="30")&&(cmbMes.getSelectedItem()=="Febrero")){
			 		JOptionPane.showMessageDialog(frmPresupuesto,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		if((anio%4==0) && (anio%100==0)){
			 			cmbDia.setSelectedItem("28");
			 		}
			 		if((anio%4==0) && (anio%100!=0)){
			 			cmbDia.setSelectedItem("29");
			 		}
			 		
				}
				if((cmbDia.getSelectedItem()=="29")&&(cmbMes.getSelectedItem()=="Febrero")){
					if(anio%4==0){
					}
					if((anio%4!=0) && (anio%100==0) && (cmbMes.getSelectedItem()=="Febrero")){
						JOptionPane.showMessageDialog(frmPresupuesto,
					 			    "La fecha no es valida",
					 			    "Error",
					 			    JOptionPane.ERROR_MESSAGE);
					 		cmbDia.setSelectedItem("28");
					}
				}
			}
		});
		
		btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.setBounds(364, 135, 128, 25);
		frmPresupuesto.getContentPane().add(btnVerDatos);
		btnVerDatos.addActionListener(new Listener());
		
		JLabel lblPeriodicidad = new JLabel("Periodicidad:");
		lblPeriodicidad.setBounds(23, 217, 103, 16);
		frmPresupuesto.getContentPane().add(lblPeriodicidad);
		
		comboBoxTiempo = new JComboBox();
		comboBoxTiempo.setModel(new DefaultComboBoxModel(new String[] {"Ninguna","Diaria","Semanal","Mensual","Anual"}));
		comboBoxTiempo.setBounds(158, 214, 116, 22);
		frmPresupuesto.getContentPane().add(comboBoxTiempo);
		
		btnAniadirGasto = new JButton("A\u00F1adir Gasto");
		btnAniadirGasto.setBounds(94, 261, 132, 25);
		frmPresupuesto.getContentPane().add(btnAniadirGasto);
		btnAniadirGasto.addActionListener(new Listener());
		
		lblMonto = new JLabel("");
		lblMonto.setBounds(422, 261, 200, 50);
		frmPresupuesto.getContentPane().add(lblMonto);
		lblMonto.setText(""+operaciones.getMonto());
		
		JLabel lblSaldoTotal = new JLabel("Saldo Total:");
		lblSaldoTotal.setBounds(309, 279, 89, 14);
		frmPresupuesto.getContentPane().add(lblSaldoTotal);
		
		
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
					 		JOptionPane.showMessageDialog(frmPresupuesto,
					 			    "El valor ingresado no es un numero",
					 			    "Error",
					 			    JOptionPane.ERROR_MESSAGE);
					 	}
					 	catch(NumberFormatException e1){
					 		e1.printStackTrace();
					 		System.err.println("El valor ingresado no es un numero");
					 		JOptionPane.showMessageDialog(frmPresupuesto,
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
		    		 JOptionPane.showMessageDialog(frmPresupuesto,
		    				    "El valor ingresado no es un numero",
		    				    "Error",
		    				    JOptionPane.ERROR_MESSAGE);
		    	 }
		    	 catch(NumberFormatException e1){
		    		 e1.printStackTrace();
				 		System.err.println("El valor ingresado no es un numero");
				 		JOptionPane.showMessageDialog(frmPresupuesto,
				 			    "El valor ingresado no es un numero",
				 			    "Error",
				 			    JOptionPane.ERROR_MESSAGE);
				 	}
		        }
		
		}

	
	}
}	

