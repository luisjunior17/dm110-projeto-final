package br.inatel.dm110.projeto.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.inatel.dm110.projeto.entities.Equipamento;

@Stateless
public class EquipamentoDAO {
	@PersistenceContext(unitName = "equipamento")
	private EntityManager em;

	public String getStatus(String ip)
	{
		String status = "{ \"status\": \"Equipamento não encontrado\"}";
		List<Equipamento> equipmentList = 
				 em.createQuery("FROM EQUIPAMENTO e WHERE e.ip=:ip",Equipamento.class)
				.setParameter("ip", ip)
				.getResultList();
				
		if(equipmentList.size()>0)
				status = "{ \"status\":\""+equipmentList.get(0).getStatus().trim()+"\"}";
		
		return status;
		
	}
	
	public void insert(Equipamento equipamento) {
		em.persist(equipamento);
	}
}