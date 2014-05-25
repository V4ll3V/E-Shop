package eshop.ui.cui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

import eshop.domain.EshopVerw;
import eshop.value.*;



public class UIF {
// alles, was das UIF so braucht:
private  EshopVerw derEshop;  
private BufferedReader in=null; 

public UIF(String dieDaten){
	// in arg[0] kann später mal der Datei-Prefix stehen, jetzt aber noch ignoriert...
	derEshop = new EshopVerw (dieDaten);
	try {
	  in=new BufferedReader( new InputStreamReader( System.in));
	} 
	catch (Exception e) {
	  System.out.println("Fehler beim Lesen"); 
	  System.exit(1);  // wenn das nicht geht, dann aufhören.
	}
}

public static void main (String[] arg){				//PAULA hier gehts los!!!!
  UIF eshopUIF = new UIF ("dieEshopDatei");  // später dann arg[0] 
  // Hauptschleife
  eshopUIF.run();
}

private void gibLoginAus() {
	System.out.println("Register: 'R'");
	System.out.println("Login: 'L'");
	System.out.println("Für Mitarbeiter: 'M'");
	System.out.print("> "); // Prompt
	System.out.flush(); // ohne NL ausgeben
}
private void gibMenueAus(){
	System.out.println("Artikel finden: 'f' ");
	System.out.println("Alle Artikel ausgeben: 'a' ");
	System.out.println("Quit: 'q'");
	System.out.print("> ");
	System.out.flush();
	
}

  private void run(){
	// Variable für Eingaben von der Konsole
	String input = ""; 
	do{		// Endlosschleife für dauerhafte Darstellung
		boolean loginDone = false; //Variable zur Überprüfung, ob der Login funktioniert hat 
		do	 {
			gibLoginAus();
			
				try {
					input = in.readLine();
					loginDone = verarbeiteLogin(input);
					if(loginDone){
						do{
							gibMenueAus();
							input = in.readLine();
							verarbeiteEingabe(input);
						}while(!input.equals("q"));
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (!input.equals("q"));
			System.out.println("Und Tschuess!");
	}while(true);
  	}
  
  	private boolean verarbeiteLogin (String cmd) throws IOException {
  		boolean login = false;
  		String param = null; 

  		if (cmd.equals("R")) { //Register
  			// ---
  			// weitere notwendige Parameter einlesen:
  			boolean gKunde = false; //Variable zum bestimmen von Großkunden
  			String cmd2;
  			System.out.print("bitte Name eingeben:");
  			System.out.flush(); 
  			param = in.readLine();
  			System.out.print("bitte Passwort eingeben:");
  			System.out.flush();  
  			String param2 = in.readLine();
  			do{
	  			System.out.println("Sind sie ein Mitarbeiter? 'y' für ja, 'n' für nein : ");
	  			cmd2 = in.readLine();
	  		} while(!cmd2.equals("y") && !cmd2.equals("n"));
	  		if(cmd2.equals("y")){ 
	  			
	  			boolean ok = derEshop.einfuegenMitarbeiter (param, param2);
	  			System.out.println("Einfügen "+param+" "+param2+ (ok? " ok":" fail"));

	  			System.out.println("---------------------");
	  			if(ok){
	  				login = true; //Direkter Login nach erfolgreicher Registrierung 
	  			}
	  		}
	  		else{
  			
	  			do{
	  				System.out.print("Sind Sie ein Großkunde? 'y' für ja, 'n' für nein : ");
	  				cmd2 = in.readLine();
	  				
	  			} while(!cmd2.equals("y") && !cmd2.equals("n"));
		  		if(cmd2.equals("y")){ //Wenn ja dann Großkunde
		  			gKunde = true;
		  		}
		  		else if(cmd2.equals("n")) { //Wenn nein dann kein Großkunde
		  			gKunde = false;
		  		}	
	  			
		  			
	  			// ---
	  			// Aktion ausführen:
	  			boolean ok = derEshop.einfuegenKunde (param, param2, gKunde);
	  			System.out.println("Einfügen "+param+" "+param2+ (ok? " ok":" fail"));
	
	  			System.out.println("---------------------");
	  			if(ok){
	  				login = true; //Direkter Login nach erfolgreicher Registrierung 
	  			}
	  		}
  		}
  		else if(cmd.equals("L")){
  			System.out.print("Bitte geben Sie ihren Benutzernamen ein: ");
  			String benutzername = in.readLine();
  			System.out.print("Bitte geben Sie ihr Passwort ein: ");
  			String passwort = in.readLine();
  			if(derEshop.checkLogin(benutzername, passwort)){
  				System.out.println("Erfolgreich eingeloggt!");
  				login = true; //Bei erfolgreichem Login 
  				
  			}
  			else{
  				System.out.println("Passwort oder Benutzername Falsch!");
  				login = false; //Bei fehlerhaften Login
  			}
  			
  		}
  		
  		else if(cmd.equals("M")){
  			System.out.print("Bitte geben Sie ihren Benutzernamen ein: ");
  			String benutzername = in.readLine();
  			System.out.print("Bitte geben SIe ihr Passwort ein: ");
  			String passwort = in.readLine();
  			if(derEshop.checkMLogin(benutzername, passwort)){
  				System.out.println("Erfolgreich eingeloggt!");
  				login = true;//Bei erfolgreichem Login 
  			}
  			else{
  				System.out.println("Passwort oder Benutzername Falsch!");
  				login = false;//Bei fehlerhaften Login
  			}
  			
  		}
  		
  		else if (!cmd.equals("q")){
  			System.out.println("Error - falscher Eingabecode");
  		}
  	return login;
  }

private void verarbeiteEingabe (String cmd) throws IOException {
		boolean login = false;
		String param = null; 
		
		if (cmd.equals("f")) {
			// ---
			// weitere notwendige Parameter einlesen:
			System.out.print("bitte Artikelname eingeben:");
			System.out.flush();  // schreibt print-Text sofort, ohne 'newline'
			param = in.readLine();
			// ---
			// Aktion ausführen:
			List<Artikel> dieErgListe = derEshop.sucheNachArtikel (param);
			// ---
			// Ergebnis ausgegeben:
			// iterativ die Wert-Elemente aus der 'eineBuchM' rausholen 
			// und als String anzeigen (toString() von Buch automatisch verwenden):
			// [das UIF muss dabei nicht in die Implementierung von BuchM reinschauen!]
			System.out.println("--- TrefferMenge: ---");
			for (Artikel b : dieErgListe){
					System.out.println(b);
			}
			System.out.println("---------------------");
		}
		
		else if (cmd.equals("a")) {
			System.out.println(derEshop.ausgebenAlleArtikel());
		}
}		
}



