package br.inatel.dm110.projeto.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.inatel.dm110.projeto.beans.PingGenerator;
import br.inatel.dm110.projeto.dao.EquipamentoDAO;
import br.inatel.dm110.projeto.entities.Equipamento;
import br.inatel.dm110.projeto.entities.ListaAux;


@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType",
				propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination",
			propertyValue="java:/jms/queue/projetoDM110queue"),
		@ActivationConfigProperty(propertyName="maxSession",
			propertyValue="10"),
		
})
public class EquipamentoMDB implements MessageListener {

	@EJB
	private EquipamentoDAO equipamentoDAO;
	
	@Override
	public void onMessage(Message message) {
		try {
			if(message instanceof ObjectMessage){
				ObjectMessage objMessage = (ObjectMessage) message;
				Object object = objMessage.getObject();
				if(object instanceof ListaAux){
					
				ListaAux listaIP = (ListaAux) object;
				
				for (String ip : listaIP.getIp()) {
					String status = "Ativo";
					boolean resultPing = false;					
					
					Equipamento equipamento = new Equipamento();
					equipamento.setIp(ip);
					resultPing = PingGenerator.execPing(ip);
					
					if(!resultPing){
						status = "Inativo";
					}else{
						status = "Ativo";
					}					
										
					equipamento.setStatus(status);
					salvarIP(equipamento);
				}
				
				}
				else
				{
					System.out.println("ERRO - N�o h� lista de IP's na mensagem!!!");
				}
			
			}
			else{
				System.out.println("ERRO - Esta mensagem n�o � um objeto!!!");
			}
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
	}
	
	private void salvarIP(Equipamento equipamento) {
		equipamentoDAO.adicionaEquipamento(equipamento);
		
	}

}
