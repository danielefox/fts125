package submarino;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BuscaSubmarino {
	String url;
	WebDriver driver;
	String nomePasta = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());

	// Funções e Metodos de Apoio

	// Tirar Print da Tela
	public void Print(String nomePrint) throws IOException {
		File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(foto, new File("C:\\Users\\tseiti\\eclipse-workspace\\Submarino\\target\\evidencias\\" + nomePasta
				+ "\\" + nomePrint + ".png"));

	}

	@Before
	public void Iniciar() {
		url = "https://www.submarino.com.br";
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\tseiti\\eclipse-workspace\\Submarino\\drivers\\chrome\\79\\chromedriver.exe");
		driver = new ChromeDriver();
		// dar tempo para requisiçoes/carregamento de tela
		driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
		// maximizar a janela para tirar print
		driver.manage().window().maximize();

	}

	@After
	public void Finaliazr() throws IOException {
		driver.quit();

	}

	
	@Given("^que acesso o site do Submarino$")
	public void que_acesso_o_site_do_Submarino() throws Throwable {
		driver.get(url);
		Print("Passo 1 Acessou o site do Submarino");
	}
	

	@When("^preencho o termo \"([^\"]*)\" e clico na lupa$")
	public void preencho_o_termo_e_clico_na_lupa(String termo) throws Throwable {
		driver.findElement(By.id("h_search-input")).clear();
		driver.findElement(By.id("h_search-input")).sendKeys(termo);
		Print("Passo 2 - Preencheu o Termo de Busca");
		driver.findElement(By.id("h_search-input")).sendKeys(Keys.ENTER);
		// driver.findElement(By.id("btnOk")).click();

	}

	@Then("^exibe a lista de produtos$")
	public void exibe_a_lista_de_produtos() throws Throwable {

		assertEquals("Smartphone com Ofertas Incríveis no Submarino.com", driver.getTitle());
		Print("Passo 3.p - exibiu a lista de produtos");
	}

	@Then("^exibe a mensagem de produto nao encontrado$")
	public void exibe_a_mensagem_de_produto_nao_encontrado() throws Throwable {
		Thread.sleep(10000);
		assertTrue(driver.findElement(By.cssSelector("span.TextUI-sc-12tokcy-0.CIZtP")).getText()
				.contains("Não encontramos nenhum resultado para"));
		// assertEquals("smartphone",
		// driver.findElement(By.cssSelector("ul.neemu-breadcrumb-container")).getText());
		Print("Passo 3.n - exibe a mensagem de erro");
	}

}