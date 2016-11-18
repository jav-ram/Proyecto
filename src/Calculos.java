import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
*@author: Rocio Loarca 16329
*@version: 11/2/2016
*/


public class Calculos {
	
	private BD mydb;
	private draw grafica;
	private Date fechaActual;
	private DateFormat formato;
	private Calendar fecha;

	/**
	 * Constructor de la clase.
	 */
	public Calculos() {
		mydb = new BD();
		fechaActual = new Date();
		formato = new SimpleDateFormat("dd/MM/yyyy");
		fecha = Calendar.getInstance();
	}


	
	
	
	/**
	*Metodo que obtiene el valor de los gastos de la base de datos de la tabla "dinero"
	*@return El entero del gasto
	*/
	
	
 	public double getGasto(java.sql.Statement st,String s ,String tipo){
 		double n=0;
 		//* Hace la consulta
	 	try{
		 	s = "Select * from Dinero where Tipo = '"+ tipo +"';";
        	try {
        		ResultSet rs=st.executeQuery(s);
        		while (rs.next())
        	      {
        	        String id = rs.getString("CantDinero");
        	        n = Double.parseDouble(id)+n;
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
 		double[] gasto = new double[5];
 		
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
	 	gasto[4]=getGasto(st,s,"Otros");
	 	
	 	//*Crea grafica con los datos extraidos
	 	grafica = new draw(gasto[0],gasto[1],gasto[2],gasto[3],gasto[4]);
	 	
	 	//*Hace el total
	 	grafica.setTotal();
	 	
	 	//*Rescribe gasto con el porcentaje del gasto
	 	for (int i = 0; i<=4; i++){
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
	*metodo que hace una constulta a la base de datos  sobre el monto de tipo string de la tabla "monto"
	*@return String con el monto de la base de datos
	*/
	
	
 	public double getMonto(){
 		double monto=0;
 		java.sql.Statement st = null;
 		String s=new String();
 		int mes = fecha.get(Calendar.MONTH)+1;
 		//* Crea el statement
	 	try {
			st = mydb.getCon().createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
 		
 		//* Hace la consulta
	 	try{
		 	s = "Select * from Monto where idMonto = "+mes+";";
        	try {
        		ResultSet rs=st.executeQuery(s);
        		while (rs.next())
        	      {
        	        String id = rs.getString("monto");
        	        monto= Double.parseDouble(id);
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
 	 * Metodo para modificar un gasto en especifico de la tabla "dinero"
 	 * @param cant
 	 * @param nom
 	 * @param tipo
 	 * @param dia
 	 * @param mes
 	 * @param id
 	 */
 	public void updateGasto(double cant, String nom, String tipo, int dia, String mes, int id){
 		java.sql.Statement st= null;
 		String s=new String();
 		try {
			st = mydb.getCon().createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

 		try {
 			s= "Select * from dinero where idDinero= '"+id+"';";
 			try{
 				ResultSet rs = st.executeQuery(s);
 				while (rs.next()){
 					String i = rs.getString("CantDinero");
 					double orig= Double.parseDouble(i);
 					updateMonto(cant-orig);
      	      }
 			}catch(SQLException e1){
 				e1.printStackTrace();
 			}
 		}catch(Exception exc){
 			exc.printStackTrace();
 		}
 		
 		//UPDATE table_name SET field1=new-value1, field2=new-value2
 		//		[WHERE Clause]
 		PreparedStatement updateExp;
 		try {
 			updateExp= (PreparedStatement) mydb.getCon().prepareStatement("update dinero SET CantDinero="+cant+", Nombre='"+nom+
 					"',Tipo='"+tipo+"', dia='"+dia+"', mes='"+ mes+"' where idDinero='"+id+"';");
 			int updateEXP_done= updateExp.executeUpdate();
 		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}/**
 	 * Metodo para convertir los meses en String
 	 * @param numMes
 	 * @return el mes
 	 */
 	public String getMonth(String numMes) {
 		if(numMes.equals("1")) {
 			return "Enero";
 		}
 		if(numMes.equals("2")) {
 			return "Febrero";}
 		if(numMes.equals("3")) {
 			return "Marzo";}
 		if(numMes.equals("4")) {
 			return "Abril";}
 		if(numMes.equals("5")) {
 			return "Mayo";}
 		if(numMes.equals("6")) {
 			return "Junio";}
 		if(numMes.equals("7")) {
 			return "Julio";}
 		if(numMes.equals("8")) {
 			return "Agosto";}
 		if(numMes.equals("9")) {
 			return "Septiembre";}
 		if(numMes.equals("10")) {
 			return "Octubre";}
 		if(numMes.equals("11")) {
 			return "Noviembre";}
 		if(numMes.equals("12")) {
 			return "Diciembre";}
 		return "";
 	}

 	/**
 	 * Metodo para consultar el dinero gastado de un mes en especifico
 	 * @param mes
 	 */
 	
 	
 	public double gastadoMes(String mes) {
 		double total = 0;
 		String consulta = "SELECT sum(CantDinero) as Total FROM `Dinero`where mes like \""+mes+"\"" ;
 		try{
 			double d= Double.valueOf(mes);
 	 		if (d==(int)d){
 	 			 consulta = "SELECT sum(CantDinero) as Total FROM `Dinero`where mes like \""+getMonth(mes)+"\"" ;
 	 		}
 		}
 		catch(Exception e){
 			
 		}
 		
 		System.out.println(mes);
 		//* Hace la consulta del mes que desea el usuario
	 	try{
		 	
	 		java.sql.Statement st = mydb.getCon().createStatement();
	 		ResultSet rs=st.executeQuery(consulta);
	 		while (rs.next())
	 		{
	 			total = rs.getDouble("Total");
	 			// print the results
	 		} 
	 		System.out.println(total);
	 		return total;
	 		}
         catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
	 	 catch (Exception exc){
	 		exc.printStackTrace();
	 	}
		return total;
 	}
 	
 	/**
 	 * Metodo para verificar el dinero gastado y el ingresado
 	 * @param mes
 	 * @return falso si el dinero es mayor al limite
 	 * @throws Exception si el dinero gastado es mayor al limite
 	 */
 	public boolean puedeGastar(String mes) throws Exception{
 		
 		
 		double limite = 0;
 		String consul = "SELECT SUM(monto) AS monto FROM `monto` where mes = " + mes; //mes actual?
 				
 		//* Hace la consulta
	 	try{
		 	double dineroGastado = gastadoMes(mes);
		 	java.sql.Statement st = mydb.getCon().createStatement();
		 	ResultSet cantLim = st.executeQuery(consul); //Cantidad limite ha gastar
		 	
		 	while (cantLim.next()) {
		 		limite = cantLim.getDouble("monto");
	        }

		 		
	 		if (dineroGastado>limite) //Si el dinero gastado es menor al l�mite .....
	 		{
	 			// print the results
	 			return false;
	 		} 
	 		}
         catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
	 	 catch (Exception exc){
	 		exc.printStackTrace();}
		
	 	return true;
 	}
 	
 	
 	
 	/**
 	 * Metodo que actualiza los datos de la base de datos "monto"
 	 * @param cant
 	 */
 	public void updateMonto(double cant){
 		
 		java.sql.Statement st = null;
 		String s=new String();
 		double monto = getMonto();
 		monto -= cant;
 		int mes = fecha.get(Calendar.MONTH)+1;
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
			updateEXP = (PreparedStatement) mydb.getCon().prepareStatement("update Monto set monto = "+ monto +" where idMonto = "+mes+";");
			int updateEXP_done = updateEXP.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
 		
 	}
 	
	
 	/**
 	 * Metodo que ingresa los gastos en la base de datos
 	 * @param cant
 	 * @param nom
 	 * @param tipo
 	 * @param dia
 	 * @param mes
 	 */
 	public void setGasto(double cant,String nom, String tipo, int dia, String mes){
 		java.sql.Statement st = null;
	 	
 		updateMonto(cant);
 		
	 	try {
			st = mydb.getCon().createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	 	
	 	try{
	 		double monto;
	 		
	 		String s;
		 	s = "INSERT INTO Dinero VALUES (CantDinero, Nombre, Tipo, dia, mes, Monto) VALUES ("+ cant +" , '" + nom +"', '"+ tipo +"',"+ dia +",'"+ mes +"','"+ getMonto() +"');";
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
 	
 	
 	/**
 	 * Metodo para a�adir dinero al monto total
 	 * @param cant
 	 */
 	public void aniadirMonto(double cant){
		 
 		java.sql.Statement st = null;
 		String s=new String();
 		double monto = getMonto();
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
 	
 	/**
 	 * Metodo para eliminar algun gasto de la tabla "dinero"
 	 * @param id
 	 */
 	public void eliminar(int id){
 		java.sql.Statement st= null;
 		String s= new String();
 		try {
 			st = mydb.getCon().createStatement();
 		}catch (SQLException e){
 			e.printStackTrace();
 		}
 		
 		try {
 			s= "Select * from dinero where idDinero= '"+id+"';";
 			try{
 				ResultSet rs = st.executeQuery(s);
 				while (rs.next()){
 					String i = rs.getString("CantDinero");
 					double orig= Double.parseDouble(i);
 					updateMonto(-orig);
      	      }
 			}catch(SQLException e1){
 				e1.printStackTrace();
 			}
 		}catch(Exception exc){
 			exc.printStackTrace();
 		}
 		PreparedStatement deleteEXP;
 		try {
 			deleteEXP= (PreparedStatement) mydb.getCon().prepareStatement("delete from dinero where idDinero= '"+(id)+"';");
 			int deleteEXP_done=  deleteEXP.executeUpdate();
 		}catch (SQLException e){
 			e.printStackTrace();
 		}
 	}
 	
 	
 	

}
