package submarino;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun = false, 
		monochrome = true, 
		features = { "src/test/resources/" }, 
		glue = { "submarino/" }, 
		plugin = { "pretty", 
				"html:target/relariosimples", 
				"json:target/relatoriosimples.json",
				"com.cucumber.listener.ExtentcucumberFormatter:target/relatoriodetalhado/dashboard.html" }
		)
public class Runner {

}