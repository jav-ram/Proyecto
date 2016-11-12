import java.time.Year;
public class Calendario {
	int anio = Year.now().getValue();
	String mes;
	String dia;
	public String getMes(){
		return mes;
	}
	public String getDia(){
		return dia;
	}
	public void setMes(String mes){
		this.mes=mes;
		
	}
	public void setDia(String dia){
		this.dia=dia;
	}
	public boolean AnioBiciesto(int anio){
		boolean a = false;
		if((anio%4)==0){
			if((anio%100)==0){
				a = false;
			}
			else{
				a = true;
			}
		}
		return a;
	}
	public boolean DiaNoValido(int anio, String mes, String dia){
		boolean a = true;
		if(mes.equals("febrero")&&(Integer.parseInt(dia)==29)){
			if(AnioBiciesto(anio)== true){
				a=true;
			}
			else{
				a=false;
			}
		}
		else if(mes.equals("febrero")&&(Integer.parseInt(dia)>29)){
			a = false;
		}
		else if(mes.equals("abril")&&(Integer.parseInt(dia)==31)){
			a = false;
		}
		else if(mes.equals("junio")&&(Integer.parseInt(dia)==31)){
			a = false;
		}
		else if(mes.equals("septiembre")&&(Integer.parseInt(dia)==31)){
			a = false;
		}
		else if(mes.equals("noviembre")&&(Integer.parseInt(dia)==31)){
			a = false;
		}
		else{
			a = true;
		}
		return a;
	}
	
}