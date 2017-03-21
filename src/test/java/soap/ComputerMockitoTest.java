package soap;

// https://springframework.guru/mocking-unit-tests-mockito/

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import soap.wsdl.DailyDilbertResponse;
import soap.wsdl.TodaysDilbertResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(ComputerService.class)
public class ComputerMockitoTest {
	
	private static final Logger log = LoggerFactory.getLogger(ComputerService.class);

	@MockBean
    private ComputerClient computerClient;
	@MockBean 
	private TodaysDilbertResponse todaysDilbertResponse;
	@MockBean
	private DailyDilbertResponse dailyDilbertResponse; 
	
	@Autowired
	ComputerService computerService; 
	
    @Test
    public void testMockCreation() throws Exception{
        assertNotNull(computerClient);
    }
    
    
    /**
     * Function to test the operation that calls TodaysDilbert
     * @throws Exception to manage the error
     */
    @Test
    public void testTodaysDilbert () throws Exception {
    	
    	when(todaysDilbertResponse.getTodaysDilbertResult()).thenReturn("testing TODAYS dilbert ... ");
    	when (computerClient.getTodaysDilbert()).thenReturn(todaysDilbertResponse); 
    	
    	assertThat(computerClient.getTodaysDilbert()).isEqualTo(todaysDilbertResponse);
    	assertThat(computerClient.getTodaysDilbert().getTodaysDilbertResult()).isEqualTo("testing TODAYS dilbert ... ");
    	
    	log.info("TEST todaysDilbert {}: ", todaysDilbertResponse.getTodaysDilbertResult());
    	
    	// we verified that the getTodaysDilbert() method of the computerClient mock get’s invoked
    	computerService.callTodaysDilbert();
    	verify(computerClient, atLeast(1)).getTodaysDilbert();

    }
    
    /**
     * Function to test the operation that calls DailyDilbert
     * @throws Exception to manage the error
     */
    @Test
    public void testDailyDilbert () throws Exception {
  
    	when(dailyDilbertResponse.getDailyDilbertResult()).thenReturn("testing DAILY dilbert ... ");
    	GregorianCalendar c = GregorianCalendar.from((LocalDate.parse("2017-02-19")).atStartOfDay(ZoneId.systemDefault()));
		XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		when (computerClient.getDailyDilbert(xmlDate)).thenReturn(dailyDilbertResponse);  
		
    	assertThat(computerClient.getDailyDilbert(xmlDate)).isEqualTo(dailyDilbertResponse);
    	assertThat(computerClient.getDailyDilbert(xmlDate).getDailyDilbertResult()).isEqualTo("testing DAILY dilbert ... ");
    	
    	log.info("TEST dailyDilbert {}: ", dailyDilbertResponse.getDailyDilbertResult());
    	
    	// we verified that the getTodaysDilbert() method of the computerClient mock get’s invoked
    	computerService.callDailyDilbert("2017-02-19");
    	verify(computerClient, atLeast(1)).getDailyDilbert(xmlDate);
    	
    }
    
    
    /**
     * Function to test the operation that calls DailyDilbert with an incorrect date
     * @throws DateTimeParseException to manage the error
     * @throws DatatypeConfigurationException to manage the error
     */
    @Test(expected = DatatypeConfigurationException.class)
    public void testDailyDilbertErrorDate () throws DatatypeConfigurationException {
    	
    	String dateValue = "2017-04-19";
    	try {	
        	GregorianCalendar c = GregorianCalendar.from((LocalDate.parse(dateValue)).atStartOfDay(ZoneId.systemDefault()));
    		XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    		when (computerClient.getDailyDilbert(xmlDate)).thenThrow(DatatypeConfigurationException.class); 
    		
    		computerService.callDailyDilbert(dateValue);
    		//fail("Should throw an exception when the date is incorrect.");
    	}
    	catch (DatatypeConfigurationException e) {
    		log.info("TEST dailyDilbert date error {}: ", dateValue);
    		verify(computerClient, times(0)).getDailyDilbert(null);
    		throw e;
    	}
   	
    }  
    
}