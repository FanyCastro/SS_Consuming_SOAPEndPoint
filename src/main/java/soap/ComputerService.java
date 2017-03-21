package soap;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import soap.wsdl.DailyDilbertResponse;
import soap.wsdl.TodaysDilbertResponse;

/**
 * Service annotation is used in your service layer and annotates classes that perform service tasks 
 */
@Service
public class ComputerService {
	
	private static final Logger log = LoggerFactory.getLogger(ComputerService.class);
	
	@Autowired
	private ComputerClient computerClient;
	
	/**
	 * Function to call the getTodayDilbert and obtain the result
	 * @return string with a text that comes from the soap service
	 * @throws Exception to manage the possible errors
	 */
	public String callTodaysDilbert () throws Exception {
	
		log.info ("Calling TODAYS DILBERT");
		TodaysDilbertResponse responseToday = computerClient.getTodaysDilbert();
		log.info("Response: {}", responseToday.getTodaysDilbertResult());
		
		return responseToday.getTodaysDilbertResult();

	}
	
	/**
	 * Function to call the getDailyDilbert and obtain the result
	 * @param dateValue it is a datetime that is sent to the soap server
	 * @return string with a text that comes from the soap service
	 * @throws DatatypeConfigurationException to manage the possible errors
	 */
	public String callDailyDilbert (@RequestParam(value="dateValue", required=true, defaultValue="2017-02-10") String dateValue) throws DatatypeConfigurationException {
		
		GregorianCalendar c = GregorianCalendar.from((LocalDate.parse(dateValue)).atStartOfDay(ZoneId.systemDefault()));
		XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		log.info("Calling DAILY DILBERT with date {} ", dateValue);
		DailyDilbertResponse response = computerClient.getDailyDilbert(xmlDate);
		log.info("Response: {}", response.getDailyDilbertResult());
		
		return response.getDailyDilbertResult();
	}
}
