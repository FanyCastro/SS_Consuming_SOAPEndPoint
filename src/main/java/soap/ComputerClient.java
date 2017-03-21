package soap;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import soap.wsdl.DailyDilbert;
import soap.wsdl.DailyDilbertResponse;
import soap.wsdl.TodaysDilbert;
import soap.wsdl.TodaysDilbertResponse;

/**
 * This class creates a web service client extending the WebServiceGatewaySupport class and code your operations
 * The WebServiceTemplate supplied by the WebServiceGatewaySupport base class to do the actual SOAP exchange.
 */
public class ComputerClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(ComputerClient.class);

	/**
	 * Function to call the SOAP service DailyDilbert and get the response
	 * @param dateValue is the date we sent to the server in order to retrieve the information related to that date
	 * @return response which contains the response of the server
	 */
	public DailyDilbertResponse getDailyDilbert (XMLGregorianCalendar dateValue) {

		DailyDilbert request = new DailyDilbert();
		request.setADate(dateValue);

		log.info("Requesting daily dilbert for date {}", dateValue);

		DailyDilbertResponse response = (DailyDilbertResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.gcomputer.net/webservices/dilbert.asmx",
						request,
						new SoapActionCallback("http://gcomputer.net/webservices/DailyDilbert"));
		log.info("Response: {} ", response);
		return response;
	}
	
	/**
	 * Function to call the SOAP service TodaysDilbert and get the response
	 * @return response which contains the response of the server
	 */
	public TodaysDilbertResponse getTodaysDilbert () {
		
		TodaysDilbert request = new TodaysDilbert();

		log.info("Requesting today dilbert ");

		TodaysDilbertResponse response = (TodaysDilbertResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.gcomputer.net/webservices/dilbert.asmx",
						request,
						new SoapActionCallback("http://gcomputer.net/webservices/TodaysDilbert"));
		log.info("Response: {} ", response);
		return response;
		
	}
}
