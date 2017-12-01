package br.inatel.dm110.projeto.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.inatel.dm110.projeto.interfaces.Equipamento;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType",
				propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination",
			propertyValue="java:/jms/queue/dm110queue"),
		@ActivationConfigProperty(propertyName="maxSession",
			propertyValue="5"),
		
})
public class EquipamentoMDB implements MessageListener {

	@EJB
	private Equipamento inventory;
	
	@Override
	public void onMessage(Message message) {
		if(message instanceof TextMessage){
			TextMessage textMessage = (TextMessage) message;
			try {
				String text = textMessage.getText();
				System.out.println("### Iniciando processamento...");
				Thread.sleep(5000);
				System.out.println("### Processando mensagem: " + text);
				//inventory.createNewCustomer(name, email, endereco);
				Thread.sleep(5000);
				System.out.println("### Finalizando processamento...");
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
