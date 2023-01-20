package hilo;
import vista.FrmLogin;
import javax.swing.JFrame;

public class HiloCerrar extends Thread{
	
	//escribe la ventana que queires cerrar
	private JFrame Ventana;
	
	public HiloCerrar(JFrame ventana) {
		this.Ventana = ventana;
	}

	//obligatorio crear el método RUN
	public void run(){
		//Contador 10 -> 0
		
			for(int i = 15; i >= 0; i--){
			//muestra contador en la etiqueta
			//!!!Al pulsar Ctrl+ESPACIO no aparece la variable : *Esta mal escrita o no esta en global
				if(i<=5)
					FrmLogin.lblMensaje.setText("!Esta ventana se cerrara en "+i+"s!");
					System.out.print(i+"s");
		
			//Darle un intervalo de tiempo
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Error en la pausa del contador :" +e.getMessage());
			}
		}
		//al finalizar el contador se tiene que cerrar la ventana
		Ventana.dispose();
		}
	

}
