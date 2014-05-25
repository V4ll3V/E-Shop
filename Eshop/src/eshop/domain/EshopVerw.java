package eshop.domain;

import java.util.List;

import eshop.domain.exceptions.ExcArtikelSchonDa;
import eshop.domain.exceptions.ExcKundeSchonDa;
import eshop.value.*;



public class EshopVerw {
// alles, was die Bib so braucht:
private KundeMg    	meinKundeMg;
private ArtikelMg		meinArtikelMg;
private MitarbeiterMg    	meinMitarbeiterMg;

public EshopVerw (String datei){
  // --- Initialisierung der Bibliothek mit allen Daten aus dateiXXX ---
  // datei+"_K" ist die Datei der Kunden
  // datei+"_B" ist die Datei der Bücher
  // datei+"_A" ist die Datei der Autoren
  try{
	  meinKundeMg  = new KundeMg(datei);
	  meinArtikelMg = new ArtikelMg(datei);
	  meinMitarbeiterMg = new MitarbeiterMg(datei);
  }
  catch (Exception e){
	  System.out.println(e);
  }
}

// --- Dienste der BibVerw ---

public List<Artikel> sucheNachArtikel (String t) {
  // liefere die Buchmenge aller Bücher mit Titel=t zurück;
  // wenn Menge = leer, dann "null" zurück
  return meinArtikelMg.sucheT( t);	// einfach delegieren an meineBuecher
}


public String ausgebenAlleArtikel(){
	return meinArtikelMg.ausgebenAlle(); 
}
public boolean einfuegenBuch( String t, int i){
	try {
		meinArtikelMg.einfuegen(new Artikel(t, i));
	}
	catch (ExcArtikelSchonDa e){
		return false;
	}
	return true;
}

public boolean sichernBib(){
	boolean ok; 
	try{
		ok= meinArtikelMg.sichernArtikel();
	}
	catch (Exception e){
		System.out.println(e);
		ok=false;
	}
	return ok;
}

public boolean sichernKunden(){
	boolean ok; 
	try{
		ok= meinKundeMg.sichernKunde();
	}
	catch (Exception e){
		System.out.println(e);
		ok=false;
	}
	return ok;
}

public boolean einfuegenKunde( String t, String i, boolean gKunde){
	try {
		meinKundeMg.einfuegen(new Kunde(t, i, gKunde));
	}
	catch (ExcKundeSchonDa e){
		return false;
	}
	return true;
}

public boolean einfuegenMitarbeiter( String t, String i){
	try {
		meinMitarbeiterMg.einfuegen(new Mitarbeiter(t, i));
	}
	catch (ExcKundeSchonDa e){
		return false;
	}
	return true;
}

public String ausgebenAlleKunden(){
	return meinKundeMg.ausgebenAlle();
}

public boolean checkLogin(String benutzername, String passwort){ //Für Kunden
	
	return(meinKundeMg.login(benutzername, passwort));
}
public boolean checkMLogin(String benutzername, String passwort){ //Für Mitarbeiter
	
	return meinMitarbeiterMg.login(benutzername, passwort);
}

// weitere Funktionen der BibVerw, zB ausleihen etc.
// ...
}

