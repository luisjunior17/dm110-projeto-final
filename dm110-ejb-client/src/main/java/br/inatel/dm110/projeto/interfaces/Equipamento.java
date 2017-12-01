package br.inatel.dm110.projeto.interfaces;

import java.util.List;
public interface Equipamento {

	void addEquipamento(List<String> ips);
	String getStatusEquipamento(String ip);
	
}
