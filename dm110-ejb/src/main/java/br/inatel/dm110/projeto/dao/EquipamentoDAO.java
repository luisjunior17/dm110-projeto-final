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
		String status;
		List<Equipamento> listaEquipamento = em.createQuery(
											"FROM equipamento e WHERE e.ip=:ip",Equipamento.class)
											.setParameter("ip", ip)
											.getResultList();
				
		if(listaEquipamento.size() < 0){
			status = "{ \"status\": \"Não foi possível encontrar o equipamento!!\"}";	
		}else{
			
			status = "{ \"status\":\""+
					listaEquipamento.get(0).getStatus().trim()+"\"}";
		}
		
		return status;
		
	}
	
	public void adicionaEquipamento(Equipamento equipamento) {
		em.persist(equipamento);
	}
}