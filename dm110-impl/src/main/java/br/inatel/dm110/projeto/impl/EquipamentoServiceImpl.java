package br.inatel.dm110.projeto.impl;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.dm110.projeto.api.EquipamentoService;
import br.inatel.dm110.projeto.interfaces.EquipamentoRemote;
import br.inatel.dm110.projeto.impl.NetworkIpGen;;

@RequestScoped
public class EquipamentoServiceImpl implements EquipamentoService {
	@EJB(lookup="java:app/dm110-ejb-0.1-SNAPSHOT/EquipamentoBean!br.inatel.dm110.projeto.interfaces.EquipamentoRemote")
	private EquipamentoRemote equipamentoBean;

	@Override
	public void start(String ip, int mask) {
		// TODO Auto-generated method stub
		
		String[] listaIP = NetworkIpGen.generateIps(ip, mask);
		ArrayList<String> ips = new ArrayList<>();
		
		for(int i = 0; i < listaIP.length; i++){
			
			String genIP = listaIP[i];
			ips.add(genIP);

		}
		
		if(ips.size() > 0){
			equipamentoBean.addEquipamento(ips);
		}
		
	}

	@Override
	public String checkStatus(String ip) {
		// TODO Auto-generated method stub
		return equipamentoBean.getStatusEquipamento(ip);
	}

	
	
}
