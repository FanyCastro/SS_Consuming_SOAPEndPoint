package soap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.Matchers.contains;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComputerServiceTest {

	@Autowired
	private ComputerService computerService; 
	
	/**
	 * Function to test the operation that calls TodaysDilbert
	 * @throws Exception it manages error
	 */
	@Test
	public void checkCallTodaysDilbert() throws Exception {
		
		String value = computerService.callTodaysDilbert();
		assertThat(value).isNotEmpty();
		assertThat(value).isNotBlank();
	}
	
	
	/**
	 * Function to test the operation that calls DailyDilbert
	 * @throws Exception it manages errors
	 */
	@Test
	public void checkCallDailyDilbert() throws Exception {
		
		String value = computerService.callDailyDilbert("2017-02-10");
		assertThat(value).isNotEmpty();
		assertThat(value).isNotBlank();
	}
	
	
	/**
	 * Function to test the operation that calls DailyDilbert with an incorrect date
	 */
	@Test
	public void checkCallDailyDilbertWrongDate() {
		
		try {
			String value = computerService.callDailyDilbert("2018-19-19");
			fail();
			
		}catch (DatatypeConfigurationException dateError) {
			System.err.println(dateError.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * Function to test the operation that calls DailyDilbert with no date
	 */
	@Test
	public void checkCallDailyDilbertWithoutDate() {
	
		try {
			String value = computerService.callDailyDilbert(null);
			fail();
			
		}catch (DatatypeConfigurationException dateError) {
			System.err.println(dateError.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
