import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.InputMismatchException;
/**
*Esta clase se encarga de crear la GUI para el boton de modificar gasto
*@author: Javier Andres Ramos Galvez 16230 
*@author: Rodrigo Stuardo Juarez Jui 16073 
*@author: Rodrigo Javier Albizures Lopez 16767
*@author: Jose Rodolfo Perez Garcia 16056
*@version: 11/12/2016
*/
public class Modificar extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textCantM;
	private JTextField txtNombreM;
	private JComboBox comboBoxModificar;
	private JComboBox cmbDiaM;
	private JComboBox cmbMesM;
	private JComboBox cmbTipoM;
	private JComboBox comboBoxTiempoM;
	private JButton btnModificarGasto;
	int anio = Year.now().getValue();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar frame = new Modificar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Modificar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeGasto = new JLabel("Gasto a Modificar");
		lblNombreDeGasto.setBounds(29, 25, 110, 14);
		contentPane.add(lblNombreDeGasto);
		
		JLabel lblNombre = new JLabel("Gasto (Nuevo Comentario)");
		lblNombre.setBounds(10, 50, 158, 14);
		contentPane.add(lblNombre);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(99, 76, 46, 14);
		contentPane.add(lblMonto);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(109, 133, 20, 14);
		contentPane.add(lblDia);
		
		JLabel lblMes = new JLabel("Mes");
		lblMes.setBounds(200, 133, 28, 14);
		contentPane.add(lblMes);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(109, 105, 28, 14);
		contentPane.add(lblTipo);
		
		comboBoxModificar = new JComboBox();
		comboBoxModificar.setBounds(156, 22, 161, 20);
		contentPane.add(comboBoxModificar);
		
		textCantM = new JTextField();
		textCantM.setBounds(178, 73, 139, 20);
		contentPane.add(textCantM);
		textCantM.setColumns(10);
		
		txtNombreM = new JTextField();
		txtNombreM.setBounds(178, 47, 139, 20);
		contentPane.add(txtNombreM);
		txtNombreM.setColumns(10);
		
		cmbDiaM = new JComboBox();
		cmbDiaM.setBounds(139, 130, 50, 20);
		contentPane.add(cmbDiaM);
		cmbDiaM.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",}));
		cmbDiaM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Marzo")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Abril")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Junio")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Septiembre")){
					System.err.println("El valor ingresado no es un numero");
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Noviembre")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Febrero")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		if((anio%4==0) && (anio%100==0)){
			 			cmbDiaM.setSelectedItem("28");
			 		}
			 		if((anio%4==0) && (anio%100!=0)){
			 			cmbDiaM.setSelectedItem("29");
			 		}
				}
				if((cmbDiaM.getSelectedItem()=="30")&&(cmbMesM.getSelectedItem()=="Febrero")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		if((anio%4==0) && (anio%100==0)){
			 			cmbDiaM.setSelectedItem("28");
			 		}
			 		if((anio%4==0) && (anio%100!=0)){
			 			cmbDiaM.setSelectedItem("29");
			 		}
			 		
				}
				if((cmbDiaM.getSelectedItem()=="29")&&(cmbMesM.getSelectedItem()=="Febrero")){
					if(anio%4==0){
					}
					if((anio%4!=0) && (cmbMesM.getSelectedItem()=="Febrero")){
						JOptionPane.showMessageDialog(frame,
					 			    "La fecha no es valida",
					 			    "Error",
					 			    JOptionPane.ERROR_MESSAGE);
					 		cmbDiaM.setSelectedItem("28");
							cmbMesM.setSelectedItem("Febrero");
					}
				}
			}
		});
		
		cmbMesM = new JComboBox();
		cmbMesM.setBounds(224, 130, 93, 20);
		contentPane.add(cmbMesM);
		cmbMesM.setModel(new DefaultComboBoxModel(new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre",}));
		cmbMesM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Marzo")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Abril")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Junio")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Septiembre")){
					System.err.println("El valor ingresado no es un numero");
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Noviembre")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		cmbDiaM.setSelectedItem("30");
				}
				if((cmbDiaM.getSelectedItem()=="31")&&(cmbMesM.getSelectedItem()=="Febrero")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		if((anio%4==0) && (anio%100==0)){
			 			cmbDiaM.setSelectedItem("28");
			 		}
			 		if((anio%4==0) && (anio%100!=0)){
			 			cmbDiaM.setSelectedItem("29");
			 		}
				}
				if((cmbDiaM.getSelectedItem()=="30")&&(cmbMesM.getSelectedItem()=="Febrero")){
			 		JOptionPane.showMessageDialog(frame,
			 			    "La fecha no es valida",
			 			    "Error",
			 			    JOptionPane.ERROR_MESSAGE);
			 		if((anio%4==0) && (anio%100==0)){
			 			cmbDiaM.setSelectedItem("28");
			 		}
			 		if((anio%4==0) && (anio%100!=0)){
			 			cmbDiaM.setSelectedItem("29");
			 		}
				}
				if((cmbDiaM.getSelectedItem()=="29")&&(cmbMesM.getSelectedItem()=="Febrero")){
					if(anio%4==0){
					}
					if((anio%4!=0) && (cmbMesM.getSelectedItem()=="Febrero")){
						JOptionPane.showMessageDialog(frame,
					 			    "La fecha no es valida",
					 			    "Error",
					 			    JOptionPane.ERROR_MESSAGE);
					 		cmbDiaM.setSelectedItem("28");
					}
				}
			}
		});
		
		cmbTipoM = new JComboBox();
		cmbTipoM.setBounds(156, 102, 93, 20);
		contentPane.add(cmbTipoM);
		cmbTipoM.setModel(new DefaultComboBoxModel(new String[] {"Ocio","Servicios","Comida","Estudios"}));
		
		JLabel lblPeriodicidad = new JLabel("Periodicidad");
		lblPeriodicidad.setBounds(59, 164, 80, 14);
		contentPane.add(lblPeriodicidad);
		
		comboBoxTiempoM = new JComboBox();
		comboBoxTiempoM.setBounds(149, 161, 100, 20);
		contentPane.add(comboBoxTiempoM);
		comboBoxTiempoM.setModel(new DefaultComboBoxModel(new String[] {"Ninguna","Diaria","Semanal","Mensual","Anual"}));
		
		
		btnModificarGasto = new JButton("Modificar Gasto");
		btnModificarGasto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnModificarGasto.setBounds(121, 192, 161, 41);
		contentPane.add(btnModificarGasto);
		btnModificarGasto.addActionListener(new Listener());
	}
	class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btnModificarGasto){
				try{
				 	Integer.parseInt(textCantM.getText());
				 	
				 	textCantM.setText("");
				 	txtNombreM.setText("");
				 	comboBoxTiempoM.setSelectedItem("Niguna");
				 	
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
