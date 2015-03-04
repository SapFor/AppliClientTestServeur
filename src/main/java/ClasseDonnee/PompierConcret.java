package ClasseDonnee;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class PompierConcret {
		
	
		private int idSession;
		
		private int id;
		
		private String mdp;
		
		private boolean directeur;
		
		private String nom;
		
		private String prenom;
		
		private List<String> UV;
		
		private List<String> accepte;
		
		private List<String> attente;
		
		private List<String> enCours;
		
		private List<String> refuse;
		
		private List<String> gestion;
		
		public void setIdSession(int idSession){
			this.idSession=idSession;
		}
		
		public int getIdSession(){
			return idSession;
		}
		
		
		public void setId(int id) {
					
			this.id=id;
			
		}
		
		public int getId(){return id;}
		
		
		public void setMdp(String mdp) {
					
			this.mdp=mdp;
			
		}
		
		public String getMdp(){return mdp;}
		
		
		public void setDirecteur(String directeur) {
			
			if(directeur.equals("oui")){this.directeur=true;}
			else{this.directeur=false;}
			
		}
			
		
		public boolean getDirecteur(){return directeur;}
		

		
		public void setNom(String nom) {
			// TODO Auto-generated method stub
			this.nom=nom;
		}
		
		
		public String getNom(){return nom;}

		
		public void setPrenom(String prenom) {
			// TODO Auto-generated method stub
			this.prenom=prenom;
		}

		
		public String getPrenom(){return prenom;}
		
		
		public void setUV(List<String> UV) {
			
			this.UV=UV;
		}
		
		
		public List<String> getUV(){return UV;}
		
		
		public void setEnCours(List<String> enCours) {
			// TODO Auto-generated method stub
			
			this.enCours=enCours;
			
		}
		
		
		public List<String> getEnCours(){return enCours;}

		
		public void setAccepte(List<String> accepte) {
			// TODO Auto-generated method stub
			
			this.accepte=accepte;
			
		}
		
		
		public List<String> getAccepte(){return accepte;}

		
		public void setAttente(List<String> attente) {
			// TODO Auto-generated method stub
			
			this.attente=attente;
			
		}
		
		
		public List<String> getAttente(){return attente;}

		
		public void setRefuse(List<String> refuse) {
			// TODO Auto-generated method stub
			
			this.refuse=refuse;
			
		}
		
		
		public List<String> getRefuse(){return refuse;}

		
		public void setGestion(List<String> gestion) {
			// TODO Auto-generated method stub
			
			this.gestion=gestion;
			
		}
		
		
		public List<String> getGestion(){return gestion;}

			
		
		public boolean estDansListe (List<String> l){
			
			boolean trouve=false;
			int i=0;
			while (trouve==false && i<l.size()){
				if (this.id !=Integer.parseInt(l.get(i))){i++;}
				else {trouve=true;}
			}
			
			return trouve;		
		}




		
	}
