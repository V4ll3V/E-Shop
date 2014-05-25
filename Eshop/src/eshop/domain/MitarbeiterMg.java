package eshop.domain;

import java.io.*;
import java.util.*;

import eshop.domain.exceptions.ExcArtikelSchonDa;
import eshop.domain.exceptions.ExcKundeSchonDa;
import eshop.value.Artikel;
import eshop.value.Mitarbeiter;



public class MitarbeiterMg implements Serializable{
// alles, was der KundenMg so braucht:
	static final long serialVersionUID = 0L; // Kompatibilität zu späteren Versionen der class
	String dat; // die Sicherungsdatei
	List<Mitarbeiter> meineMitarbeiter = new ArrayList<Mitarbeiter>();


public MitarbeiterMg (String datei){
  // --- Initialisierung des KundenMg mit allen Daten aus datei_Kunde ---
	dat = datei+"_M.ser";		// <datei>_Buch.ser
	
	Object o  = null; 
	ObjectInputStream ois = null;
    try {
        ois = new ObjectInputStream( new FileInputStream(dat));
       	o = ois.readObject();	// readObject() legt Objekt des "richtigen" Typs an!
        if (o!=null) {
        	meineMitarbeiter = (List<Mitarbeiter>) o; // in „o“ steckt eine Kunden-Liste
        }
        if (ois != null) {
        	ois.close();
        }
    }
    catch (Exception e) {
        System.out.println(e);
    }
}

// ganze Liste Speichern:
public boolean sichernMitarbeiter(){
	ObjectOutputStream oos = null;
    try {
        oos = new ObjectOutputStream( new FileOutputStream(dat));	
        oos.writeObject(meineMitarbeiter);
        if (oos != null) {
        	oos.close();
        }
    }
    catch (Exception e) {
    	System.out.println(e);
    }
	return true; 
}

public boolean einfuegen (Mitarbeiter einK) throws ExcKundeSchonDa {
	if (! meineMitarbeiter.contains(einK)){
		meineMitarbeiter.add(einK);
		ObjectOutputStream oos = null;
	    try {
	        oos = new ObjectOutputStream( new FileOutputStream(dat));	
	        oos.writeObject(meineMitarbeiter);
	        if (oos != null) {
	        	oos.close();
	        }
	    }
	    catch (Exception e) {
	    	System.out.println(e);
	    }
		return true;
	}
	else 
		throw new ExcKundeSchonDa("Kunde: "+einK+" schon da");
}

public String ausgebenAlle() {
	String erg = ""; 
	for (Mitarbeiter k : meineMitarbeiter){
		erg=erg+k+"\n";
	}
	return erg;
}

public boolean login(String benutzername, String passwort){
	boolean check = false;
	for(Mitarbeiter k : meineMitarbeiter){ 
		//ArrayListe durchlaufen und überprüfen ob Eingaben mit einem Kunden in der Liste
		//übereinstimmen
		if(passwort.equals(k.getPw()) && benutzername.equals(k.getMName())){
			check = true;
		}
	}
	return check;
	
}




} // class