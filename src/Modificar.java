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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.Year;
import java.util.InputMismatchException;
import java.util.Vector;
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
	private JButton btnModificarGasto;
	private JButton btnEliminarGasto;
	int anio = Year.now().getValue();
	private Connection con = null;
	private Calculos operaciones;

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
		
		operaciones = new Calculos();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 399, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeGasto = new JLabel("Gasto a Modificar");
		lblNombreDeGasto.setBounds(29, 25, 110, 14);
		contentPane.add(lblNombreDeGasto);
		
		JLabel lblNombre = new JLabel("Descripci\u00F3n del gasto");
		lblNombre.setBounds(31, 50, 158, 14);
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
		textCantM.setBounds(155, 73, 162, 20);
		contentPane.add(textCantM);
		textCantM.setColumns(10);
		
		txtNombreM = new JTextField();
		txtNombreM.setBounds(156, 47, 161, 20);
		contentPane.add(txtNombreM);
		txtNombreM.setColumns(10);
		
		cmbDiaM = new JComboBox();
		cmbDiaM.setBounds(139, 130, 50, 20);
		contentPane.add(cmbDiaM);
		cmbDiaM.setModel(new DefaultComboBoxModel(new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",}));
		cmbDiaM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Inicia la programación defensiva contra fechas mal ingresadas en el comboBox de los días del mes
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
		//Finaliza la programación defensiva contra las fechas mal ingresadas para el comboBox que guarda los días
		cmbMesM = new JComboBox();
		cmbMesM.setBounds(224, 130, 93, 20);
		contentPane.add(cmbMesM);
		cmbMesM.setModel(new DefaultComboBoxModel(new String[] {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre",}));
		cmbMesM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Programación defensiva contra fechas mal ingresadas en el comboBox de meses
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
		//Finaliza programación defensiva contra las fechas no validas en el comboBox de Mes
		cmbTipoM = new JComboBox();
		cmbTipoM.setBounds(156, 102, 161, 20);
		contentPane.add(cmbTipoM);
		cmbTipoM.setModel(new DefaultComboBoxModel(new String[] {"Ocio","Servicios","Comida","Estudios"}));
		
		
		btnModificarGasto = new JButton("Modificar Gasto");
		btnModificarGasto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnModificarGasto.setBounds(29, 177, 161, 41);
		contentPane.add(btnModificarGasto);
		
		btnEliminarGasto = new JButton("Eliminar Gasto");
		btnEliminarGasto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminarGasto.setBounds(200, 177, 161, 41);
		contentPane.add(btnEliminarGasto);
		
		btnModificarGasto.addActionListener(new Listener());
		btnEliminarGasto.addActionListener(new Listener());
		
		Vector columnNames = new Vector();
		Vector data = new Vector();
		try {
            con = DriverManager.getConnection("jdbc:mysql://" + "localhost"
                    + ":3306/mydb", "root", "root");

            String sql = "select * from dinero";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }

            while (rs.next()) {
                Vector row = new Vector(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }

                data.addElement(row);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
		
		int x=0;
		while (x< data.size()){
			comboBoxModificar.addItem(data.elementAt(x));
			x++;	
		}
		
		
	}
	class Listener implements ActionListener{

		/* 
		 * Manda los datos a Calculos para modificar la base de datos
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btnModificarGasto){
				try{
					int id=comboBoxModificar.getSelectedIndex();
				 	Double.parseDouble(textCantM.getText());
				 	operaciones.updateGasto(Double.parseDouble(textCantM.getText()), txtNombreM.getText(), cmbTipoM.getSelectedItem().toString(), Integer.parseInt(cmbDiaM.getSelectedItem().toString()), cmbMesM.getSelectedItem().toString(), id);
				 	textCantM.setText("");
				 	txtNombreM.setText("");
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
			if (e.getSource()== btnEliminarGasto){
				int id= comboBoxModificar.getSelectedIndex();
				operaciones.eliminar(id);
			}
		}
		
	}
}
