package br.inatel.dm110.projeto.entities;

import java.io.Serializable;
import java.util.List;

public class ListaAux implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<String> ip;

	public List<String> getIp() {
		return ip;
	}

	public void setIp(List<String> ip) {
		this.ip = ip;
	}
	
}
