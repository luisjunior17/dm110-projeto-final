package br.inatel.dm110.projeto.rest;

import java.util.Set;
import java.util.HashSet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.inatel.dm110.projeto.impl.EquipamentoServiceImpl;

@ApplicationPath("/api")
public class RestApplication extends Application{
	
@Override
public Set<Class<?>> getClasses() {
	// TODO Auto-generated method stub
	Set<Class<?>> classes = new HashSet<>();
	classes.add(EquipamentoServiceImpl.class);
	return classes;
}
	
}
