package helpers;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

public class Helpers {
	private WebDriver driver;
	public Helpers(WebDriver driver) {
		this.driver = driver;
	}
	public void SleepSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	/*Implementaci�n de  ///implicit Wait///  :Sirve para esperar a que cargue una p�gina, la ventaja 
	es que cuando termina de cargar la pagina se sale del implicit wait y continua con la ejecuci�n del c�digo*/
	public void ImplicitlWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}