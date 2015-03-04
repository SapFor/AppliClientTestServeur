package ClasseDonnee;

import java.util.List;

public class UV {
	
		
		private String nom;
		private String descr;
		private List<String> stage;
		
		
		public void setNom(String nom) {
			// TODO Auto-generated method stub
			
			this.nom=nom;
		}
		
		
		
		public void setDescr(String descr) {
			// TODO Auto-generated method stub
			
			this.descr=descr;
		}

		
		public void setStages(List<String> session) {
			// TODO Auto-generated method stub
			
			this.stage=session;
		}

		
		public String getNom() {
			// TODO Auto-generated method stub
			return nom;
		}

		
		public String getDescr() {
			// TODO Auto-generated method stub
			return descr;
		}

		
		public List<String> getStages() {
			// TODO Auto-generated method stub
			return stage;
		}

}
