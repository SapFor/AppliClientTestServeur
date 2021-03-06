package sapforclient.testclient;


import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import ClasseDonnee.*;



import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/*
* Created with Eclipse.
* Author : Caroline Chabert
* Class to call server and supply methods for controllers of IHM
*/

public class clientApp {
	
		public static PompierConcret moi;
		private static int idSession;
		private static ClientConfig config;
		private static Client client;
		private static WebResource service;
		//private List<?> abstractList;
		
		// Get server URI
		private static URI getBaseURI() {
			URI uri = UriBuilder.fromUri("http://localhost:8080/SAPFORServer/webresources/serveur/").build();
			return uri;
		}
		
		// First connection with access to the service object and initialize the fireman session
		public static void firstconnect(){
			
			moi = service.path("1/12345").accept(MediaType.APPLICATION_JSON).get(new GenericType<PompierConcret>(){}); 
		}
		
		// Get idSession by login and password
		public static String login(int idPompier,String mdp){
			config = new DefaultClientConfig();
			client = Client.create(config);
			service = client.resource(getBaseURI());
			String defPath = idPompier + "/" + mdp;
			moi = service.path(defPath).accept(MediaType.APPLICATION_JSON).get(new GenericType<PompierConcret>(){});
			idSession = moi.getIdSession();
			
			if (idSession == 999){ return "erreur"; }
			else { return "ok";}
		}
		
		// Get IdSession
		public static int getIdSession(){ return idSession;}
		
		// Deconnect the session
		public static String deconnexion(int idSession){
			String reponse;
			String nSession = "" + idSession;
			reponse = service.path(nSession).accept(MediaType.APPLICATION_JSON).get(new GenericType<String>(){});
			if(reponse.equals("OK")){
				return "Vous etes maintenant deconnecte!";
			}
			else { return "erreur de deconnexion!"; }
		}
		
		// Close the candidatures of a stage : associated to the "Candidater" button in the formation tab
		public static String cloturerCandidature(String nomStage,int jour, int mois, int annee){
			String reponse;
			String date = jour + "/" + mois + "/" + annee;
			reponse = service.path("directeur/" + nomStage + "/" + date).accept(MediaType.APPLICATION_JSON).get(new GenericType<String>(){});
			if(reponse == "OK"){
				return "date changée";
			}
			else { return "Problème lors du changement de date";}
		}
	
		// Get list of the director stages : to put into the director tab
		public static List<String> getListSessionDirecteur(){
			//WebResource service = connect();
			EncapsulationStage listSession = service.path("directeur/" + moi.getIdSession()).accept(MediaType.APPLICATION_JSON).get(new GenericType<EncapsulationStage>(){}); 
			
			List<StageConcret> stages=listSession.capsule;
			
			
			List<String> listSess = new ArrayList();
			Calendar dateStage;
			String nomUV;
			String date;
			String nomLieu;
			String ligneSess;
	    	
			Iterator<StageConcret> ite = stages.iterator();
	    	while(ite.hasNext()){
	    		StageConcret newLigne = ite.next();
	    						
				nomUV = newLigne.getUV();
				dateStage = newLigne.getDate();
				
				date = dateStage.get(Calendar.DAY_OF_MONTH) + "/" + (dateStage.get(Calendar.MONTH)+1) + "/" + dateStage.get(Calendar.YEAR);
				nomLieu = newLigne.getLieu();
	    		String ligneSess1 = nomUV + " " + date + " " + nomLieu;
	    		listSess.add(ligneSess1);
	    	}
	    	return listSess;
	    }
	 
	 
	  	// Get list of director candidates for a specific session : to put into the director tab
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
	 
	 	// Push a updated list of candidates for a specific session to the server : "Enregistrer" button in the director tab
		public static StageConcret postListGestionDirecteur(StageConcret session){
			StageConcret newSession = new StageConcret();
			
			
			return newSession;
		}
		
		public void pushInfoServer(){
			//WebResource service = connect();
			
			// Create a new list of candidates
			//Stage newListCandidats = postListGestionDirecteur(newSession);
			
			// Add a new product using the POST HTTP method. managed by the Jersey framework
			//service.path("directeur/idPompier").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(new GenericType<Pompiers>(){}, newListCandidats);
		}
	 
	

		public static void main(String[] args) {
			

			
			
			//List<String> mesCandidats = getListCandidatDirecteur(maSession);
			
			List<Integer> listeId=new ArrayList<Integer>();
			System.out.println(login(1,"12345"));
			listeId.add(idSession);
			System.out.println(idSession);
			
			System.out.println(login(2,"12345"));
			listeId.add(idSession);
			System.out.println(idSession);
		
			System.out.println(login(3,"12345"));
			listeId.add(idSession);
			System.out.println(idSession);
			
			System.out.println(login(5,"12345"));
			listeId.add(idSession);
			System.out.println(idSession);
			
			System.out.println(login(6,"12345"));
			listeId.add(idSession);
			System.out.println(idSession);
			
			for (int i=1; i<listeId.size();i++){
			String path="candidater/"+listeId.get(i)+"/FDF1broceliande15juin15";
			String reponse=service.path(path).accept(MediaType.APPLICATION_JSON).get(new GenericType<String>(){});
			System.out.println(reponse);
			}
			
			
			
			//List<String> maSession = getListSessionDirecteur();
			//System.out.println(maSession.toString());
			
			
			
			StageConcret domp = service.path("stage/FDF1broceliande15juin15").accept(MediaType.APPLICATION_JSON).get(new GenericType<StageConcret>(){});
			PompierConcret pomp = service.path("pompier/2").accept(MediaType.APPLICATION_JSON).get(new GenericType<PompierConcret>(){});
			
			System.out.println(pomp.getUV().toString());
			
			if (pomp.getAccepte()==null) {pomp.setAccepte(new ArrayList<String>());}
			System.out.println("Listeacceptepompier"+pomp.getAccepte().toString());
			
			
			List<String> candid=new ArrayList<String>();
			System.out.println("candidat"+candid.toString());
						
			List<String> acc=new ArrayList<String>();
			acc.add("5");
			acc.add("3");
			
			System.out.println("accepte"+acc.toString());
			
			List<String> att=new ArrayList<String>();
			att.add("2");
			
			List<String> ref=new ArrayList<String>();
			ref.add("6");
			
			domp.setCandidats(candid);
			domp.setAccepte(acc);
			domp.setAttente(att);
			domp.setRefuse(ref);
			
			String reponse=service.path("directeur/selection").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(new GenericType<String>(){},domp);			
			
			System.out.println(reponse);
			//StageConcret nouveau=service.path("stage/").accept(MediaType.APPLICATION_JSON).get(new GenericType<PompierConcret>(){});"stage/{nomStage}"
			
		}
}


