package sapforclient.testclient;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import ClasseDonnee.*;

/*
* Created with Eclipse.
* Author : Caroline Chabert
* Class to call server and supply methods for controllers of IHM
*/

public class clientApp {
	
		private static PompierConcret moi;
		private static ClientConfig config;
		private static Client client; 
		private static WebResource service;
	
		//private List<?> abstractList;
		
		private static URI getBaseURI() {
			URI uri = UriBuilder.fromUri("http://localhost:8080/SAPFORServer/webresources/serveur/").build();
			return uri;
		}
		
		public static void firstconnect(){
			// Get access to the service object
			config = new DefaultClientConfig();
			client = Client.create(config);
			service = client.resource(getBaseURI());
			moi = service.path("1/12345").accept(MediaType.APPLICATION_JSON).get(new GenericType<PompierConcret>(){}); 
			System.out.println(moi.getIdSession());
		}
		
		public static WebResource connect(){
			// Get access to the service object
			config = new DefaultClientConfig();
			client = Client.create(config);
			service = client.resource(getBaseURI());
			return service;
		}
	
		public static List<String> getListSessionDirecteur(){
			//WebResource service = connect();
			List<StageConcret> listSession = service.path("directeur/" + moi.getIdSession()).accept(MediaType.APPLICATION_JSON).get(new GenericType<List<StageConcret>>(){}); 
			
			List<String> listSess = new ArrayList();
			Calendar dateStage;
			String nomUV;
			String date;
			String nomLieu;
			String ligneSess;
			Iterator<StageConcret> ite = listSession.iterator();
	    	while(ite.hasNext()){
	    		StageConcret newLigne = ite.next();
	    		System.out.println(newLigne.getUV());
	    		nomUV=newLigne.getUV();
	    		dateStage=newLigne.getDate();
	    		date=dateStage.get(Calendar.DAY_OF_MONTH)+"/"+dateStage.get(Calendar.MONTH)+1+"/"+dateStage.get(Calendar.YEAR);
	    		nomLieu=newLigne.getLieu();
	    		ligneSess =  nomUV+ " " + date+ " " +nomLieu; 
	    		listSess.add(ligneSess);
	    	}
	    	return listSess;
	    }
	 
	 
		public List<String> getListCandidatDirecteur(StageConcret session){
			//WebResource service = connect();
		 	List<String> listPompiers = new ArrayList();
		 	List<String> listIdCandidat = session.getCandidats();
		 	
	    	Iterator<String> ite = listIdCandidat.iterator();
	    	while(ite.hasNext()){
	    		String newLigne = ite.next();
	    		String path = "pompier/" + newLigne;
	    		
	    		PompierConcret pomp = service.path(path).accept(MediaType.APPLICATION_JSON).get(new GenericType<PompierConcret>(){}); 

	    		String namePomp = pomp.getNom() + " " + pomp.getPrenom() ;
	    		listPompiers.add(namePomp);
	    	}
	    	return listPompiers;
	    }
	 /*
		public static Stage postListGestionDirecteur(Stage session){
			Stage newSession = new Stage();
			
			
			return newSession;
		}
		
		public void pushInfoServer(){
			WebResource service = connect();
			// Create a new list of candidates
			//Stage newListCandidats = postListGestionDirecteur(newSession);
			
			// Add a new product using the POST HTTP method. managed by the Jersey framework
			//service.path("directeur/idPompier").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(new GenericType<Pompiers>(){}, newListCandidats);
		}
	 */


		public static void main(String[] args) {
			
			
			firstconnect();
			
			List<String> maSession = getListSessionDirecteur();
			for(int i=0;i<maSession.size();i++){
				System.out.println(maSession.get(i));
			}
			
			//List<String> mesCandidats = getListCandidatDirecteur(0);
		}
}


