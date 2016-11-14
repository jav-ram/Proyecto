/**
*Clase de gastos de una persona
*@author: Javier Andres Ramos Galvez 16230 
*@author: Rodrigo Stuardo Juarez Jui 16073 
*@author: Rodrigo Javier Albizures Lopez 16767
*@author: Jose Rodolfo Perez Garcia 16056
*@version: 11/2/2016
*/



public class draw {
	
	public double total;
	public double ocio;
	public double servicios;
	public double comida;
	public double estudios;
	public double otros;
/**
*Constructor de la clase draw 
*/
	public draw(double ocio, double servicios, double comida, double estudios, double otros) {
		this.total = 0;
		this.ocio = ocio;
		this.servicios = servicios;
		this.comida = comida;
		this.estudios = estudios;
		this.otros= otros;
	}
/**
*metodo que saca el dinero total en double de todos los gastos
*/	
	public void setTotal(){
		total = ocio+ servicios+ comida + estudios + otros;
	}
/**
*metodo que regresa el total de los gastos
*/	
	public double getTotal(){
		return total;
	}
/**
*Metodo que regresa el porcentaje de dinero total
*@return Double de porcentaje
*/	
	public double getPorcentaje(double d){
		double porcentaje= (d/total)*100;
		return porcentaje;
	}
	
}
