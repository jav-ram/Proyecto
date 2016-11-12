import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.*;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 */

/**
*Esta clase realiza los calculos del programa
*@author: Javier Andres Ramos Galvez 16230 
*@author: Rodrigo Stuardo Juarez Jui 16073 
*@author: Rodrigo Javier Albizures Lopez 16767
*@author: Jose Rodolfo Perez Garcia 16056
*@version: 11/2/2016
*/


public class Calculos {
	
	private BD mydb;
	private draw grafica;

	public Calculos() {
		mydb = new BD();
	}


	
	
	
	/**
	*Metodo que obtiene el entero de los gastos de la base de datos
	*@return El entero del gasto
	*/
	
	
 	public int getGasto(java.sql.Statement st,String s ,String tipo){
 		int n=0;
 		//* Hace la consulta
	 	try{
		 	s = "Select * from Dinero where Tipo = '"+ tipo +"';";
        	try {
        		ResultSet rs=st.executeQuery(s);
        		while (rs.next())
        	      {
        	        String id = rs.getString("CantDinero");
        	        n = Integer.parseInt(id)+n;
        	        // print the results
        	      }
        		
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 	} catch (Exception exc){
	 		exc.printStackTrace();
	 	}
 		
 		return n;
 	}
	
	/**
	*Metodo que hace una consulta a la base de datos
	*@return gasto de la base de datos
	*/
	
 	public double[] setDraw(){
 		
 		//* Inicializa statement para la consulta 
 		java.sql.Statement st = null;
 		String s=new String();
 		double[] gasto = new double[4];
 		
 		//* Crea el statement
	 	try {
			st = mydb.getCon().createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	 	
	 	//*Extrae la informacion
	 	gasto[0]=getGasto(st,s,"Ocio");
	 	gasto[1]=getGasto(st,s,"Estudios");
	 	gasto[2]=getGasto(st,s,"Servicios");
	 	gasto[3]=getGasto(st,s,"Comida");
	 	
	 	//*Crea grafica con los datos extraidos
	 	grafica = new draw(gasto[0],gasto[1],gasto[2],gasto[3]);
	 	
	 	//*Hace el total
	 	grafica.setTotal();
	 	
	 	//*Rescribe gasto con el porcentaje del gasto
	 	for (int i = 0; i<=3; i++){
	 		gasto[i]=grafica.getPorcentaje(gasto[i]);
	 	}
	 	
	 	//*termina la consulta
	 	try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	
	 	return gasto;
	 	
 	}
	
 	
 	/**
	*metodo que hace una constulta a la base de datos  sobre el monto de tipo string
	*@return String con el monto de la base de datos
	*/
	
	
 	public String getMonto(){
 		String monto=null;
 		java.sql.Statement st = null;
 		String s=new String();
 		
 		//* Crea el statement
	 	try {
			st = mydb.getCon().createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 		
 		//* Hace la consulta
	 	try{
		 	s = "Select * from Monto where idMonto = 1;";
        	try {
        		ResultSet rs=st.executeQuery(s);
        		while (rs.next())
        	      {
        	        String id = rs.getString("monto");
        	        monto= id;
        	        // print the results
        	      }
        		
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 	} catch (Exception exc){
	 		exc.printStackTrace();
	 	}
 		
 		return monto;
 	}
 	
 	/**
	*Metodo que actualiza los datos de la base de datos
	*/
	
 	public void updateMonto(int cant){
 		 
 		java.sql.Statement st = null;
 		String s=new String();
 		int monto = Integer.parseInt(getMonto());
 		monto -= cant;
 		//* Crea el statement
	 	try {
			st = mydb.getCon().createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 		
 		//* Hace la consulta
	 	PreparedStatement updateEXP;
		try {
			updateEXP = (PreparedStatement) mydb.getCon().prepareStatement("update Monto set monto = "+ monto +";");
			int updateEXP_done = updateEXP.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
 		
 	}
 	
	/**
	*Metodo que ingresa los gastos en la base de datos
	*/
	
	
	
 	public void setGasto(int cant,String nom, String tipo, int dia, String mes){
 		java.sql.Statement st = null;
	 	
 		updateMonto(cant);
 		
	 	try {
			st = mydb.getCon().createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	 	
	 	try{
	 		int monto;
	 		
	 		String s;
		 	s = "INSERT INTO Dinero VALUES (0,"+ cant +" , '" + nom +"', '"+ tipo +"',"+ dia +",'"+ mes +"');";
        	try {
				st.executeUpdate(s);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 	} catch (Exception exc){
	 		exc.printStackTrace();
	 	}
 	}
 	
 	
 	public void aniadirMonto(int cant){
		 
 		java.sql.Statement st = null;
 		String s=new String();
 		int monto = Integer.parseInt(getMonto());
 		monto += cant;
 		//* Crea el statement
	 	try {
			st = mydb.getCon().createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 		
 		//* Hace la consulta
	 	PreparedStatement updateEXP;
		try {
			updateEXP = (PreparedStatement) mydb.getCon().prepareStatement("update Monto set monto = "+ monto +";");
			int updateEXP_done = updateEXP.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
 		
 	}
 	
 	
 	

}
