package br.inatel.dm110.projeto.beans;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import br.inatel.dm110.projeto.dao.EquipamentoDAO;
import br.inatel.dm110.projeto.entities.*;
import br.inatel.dm110.projeto.interfaces.EquipamentoLocal;
import br.inatel.dm110.projeto.interfaces.EquipamentoRemote;

import java.util.List;
import java.util.stream.Collectors;



@Stateless
@Remote(EquipamentoRemote.class)
@Local(EquipamentoLocal.class)
public class EquipamentoBean implements EquipamentoLocal, EquipamentoRemote {

	@EJB
	private EquipamentoDAO equipamentoDAO;
	
	@Resource(lookup="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	@Resource(lookup="java:/jms/queue/equipmentControlQueue")
	private Queue queue;
	
	@Override
	public void addEquipamento(List<String> ips){
		
		ListHelper ipList = new ListHelper();
		ipList.setIps(ips);

		try (Connection connection = connectionFactory.createConnection();
				Session session = connection.createSession();
				MessageProducer producer = session.createProducer(queue);) {
			
			ObjectMessage objMessage = session.createObjectMessage(ipList);
			producer.send(objMessage);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String getStatusEquipamento(String ip) {
		
		return equipamentoDAO.getStatus(ip);
		
	}



}
