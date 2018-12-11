package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageReservation {
	private WebDriver driver;
	private By tittleText;
	private By passengersDrop;
	private By fromDrop;
	private By toDrop;
	public PageReservation(WebDriver driver) {
		this.driver = driver;
		tittleText = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		passengersDrop = By.name("passCount");
		fromDrop = By.name("fromPort");
		toDrop = By.name("toPort");
	}
	public void AssertPage() {
		Assert.assertTrue(driver.findElement(tittleText).getText().contains("Use our Flight Finder to"));
	}
	
	/*Dropdowns o Listas desplegables por elementos visibles*/
	public void SelectPassengers(int cant) {
		/*Implementación de  ///Explicit Wait///  :Sirve para esperar a que cargue un elemento del DOM, la ventaja 
		es que no espera a que cargue toda la página para continuar con la ejecución del código*/
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(passengersDrop));
		Select selectPasajeros = new Select(cantidadPasajeros);
		selectPasajeros.selectByVisibleText(Integer.toString(cant));
	}
	
	/*Dropdowns o Listas desplegables por indice*/
	public void SelectFromPort(int index) {
		Select selectFrom = new Select(driver.findElement(fromDrop));
		selectFrom.selectByIndex(index);
	}
	
	/*Dropdowns o Listas desplegables por el value*/
	public void SelectToPort(String city) {
		Select selectTo = new Select(driver.findElement(toDrop));
		selectTo.selectByValue(city);
	}
}
