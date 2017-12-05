package br.inatel.dm110.projeto.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/poller")
public interface EquipamentoService {
	
	@GET
	@Path("/start/{IP}/{Mask}")
	void start(@PathParam("IP") String ip,@PathParam("Mask") int mask);
	
	@GET
	@Path("/status/{IP}")
	@Produces(MediaType.APPLICATION_JSON)
	String checkStatus(@PathParam("IP") String ip);
	
}
