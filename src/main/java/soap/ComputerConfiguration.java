package soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Configuring web service components
 * Spring WS uses Spring Framework’s OXM module which has the Jaxb2Marshaller to serialize and deserialize XML requests.
 */

@Configuration
public class ComputerConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() throws Exception {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("soap.wsdl");
		return marshaller;
	}
	
	@Bean
	public ComputerClient computerClient(Jaxb2Marshaller marshaller) throws Exception{
		ComputerClient client = new ComputerClient();
		client.setDefaultUri("http://www.gcomputer.net/webservices/dilbert.asmx");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
