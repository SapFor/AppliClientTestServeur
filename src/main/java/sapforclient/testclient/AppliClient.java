package sapforclient.testclient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import ClasseDonnee.PompierConcret;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class AppliClient {
	
	public static PompierConcret moi;
	private static int idSession;
	private static WebResource service;
	private static Client client;
	private static ClientConfig config;
	
	
		
	
	public static String login(int idPompier,String mdp){
		String defPath=idPompier+"/"+mdp;
		moi = service.path(defPath)
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<PompierConcret>(){}); 
		
		idSession=moi.getIdSession();
		
		
		if (idSession==999){
			return "erreur";
		}
		else{
			return "ok";
		}
	}
	

	public static int getIdSession(){
		return idSession;
		
	}
	
	
	public static String deconnexion(int idSession){
		String reponse;
		String nSession=""+idSession;
		reponse=service.path(nSession)
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<String>(){});
		
		if(reponse.equals("OK")){
			return "Vous etes maintenant deconnecte!";
		}
		else{
			return "erreur de deconnexion!";
		}
		
	}
	
	
	public static String cloturerCandidature(String nomStage,int jour, int mois, int annee){
		String reponse;
		String date=jour+"."+mois+"."+annee;
		
		reponse=service.path("directeur/"+nomStage+"/"+date)
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<String>(){});
		
		if(reponse=="OK"){
			return "date changée";
		}
		else{	return "Problème lors du changement de date";}
		
	}
	
	
	
	private static URI getBaseURI() {
		URI uri =
		 UriBuilder.fromUri("http://localhost:8080/SAPFORServer/webresources").build();
		return uri;
	}
	
	public static void main(String [] args){
		
		config = new DefaultClientConfig();		
		client = Client.create(config);
		service = client.resource("http://localhost:8080/SAPFORServer/webresources/serveur/");
		
		List<Integer> listeId=new ArrayList<Integer>();
		
		System.out.println(login(1,"12345"));
		listeId.add(idSession);
		System.out.println(idSession);
		System.out.println(login(1,"12345"));
		System.out.println(idSession);
		System.out.println(login(2,"12345"));
		listeId.add(idSession);
		System.out.println(idSession);
		System.out.println(login(3,"12345"));
		listeId.add(idSession);
		System.out.println(idSession);
		System.out.println(login(4,"12345"));
		listeId.add(idSession);
		System.out.println(idSession);
		System.out.println(login(5,"12345"));
		listeId.add(idSession);
		System.out.println(idSession);
		for(int i=0;i<listeId.size();i++){
				System.out.println(deconnexion(listeId.get(i)));
		}
		
				
		
		
		
	}

}
