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
		List<Equipamento> listaEquipamento = 
				 em.createQuery("FROM EQUIPAMENTO e WHERE e.ip=:ip",Equipamento.class)
				.setParameter("ip", ip)
				.getResultList();
				
		if(listaEquipamento.size() > 0)
				status = "{ \"status\":\""+listaEquipamento.get(0).getStatus().trim()+"\"}";
		
		return status;
		
	}
	
	public void insert(Equipamento equipamento) {
		em.persist(equipamento);
	}
}