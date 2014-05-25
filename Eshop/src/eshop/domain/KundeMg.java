package eshop.domain;

import java.io.*;
import java.util.*;

import eshop.domain.exceptions.ExcArtikelSchonDa;
import eshop.domain.exceptions.ExcKundeSchonDa;
import eshop.value.Artikel;
import eshop.value.Kunde;



public class KundeMg implements Serializable{
// alles, was der KundenMg so braucht:
	static final long serialVersionUID = 0L; // Kompatibilität zu späteren Versionen der class
	String dat; // die Sicherungsdatei
	List<Kunde> meineKunden = new ArrayList<Kunde>();


public KundeMg (String datei){
  // --- Initialisierung des KundenMg mit allen Daten aus datei_Kunde ---
	dat = datei+"_K.ser";		// <datei>_Buch.ser
	
	Object o  = null; 
	ObjectInputStream ois = null;
    try {
        ois = new ObjectInputStream( new FileInputStream(dat));
       	o = ois.readObject();	// readObject() legt Objekt des "richtigen" Typs an!
        if (o!=null) {
        	meineKunden = (List<Kunde>) o; // in „o“ steckt eine Kunden-Liste
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
public boolean sichernKunde(){
	ObjectOutputStream oos = null;
    try {
        oos = new ObjectOutputStream( new FileOutputStream(dat));	
        oos.writeObject(meineKunden);
        if (oos != null) {
        	oos.close();
        }
    }
    catch (Exception e) {
    	System.out.println(e);
    }
	return true; 
}

public boolean einfuegen (Kunde einK) throws ExcKundeSchonDa {
	if (! meineKunden.contains(einK)){
		meineKunden.add(einK);
		ObjectOutputStream oos = null;
	    try {
	        oos = new ObjectOutputStream( new FileOutputStream(dat));	
	        oos.writeObject(meineKunden);
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
	for (Kunde k : meineKunden){
		erg=erg+k+"\n";
	}
	return erg;
}

public boolean login(String benutzername, String passwort){
	boolean check = false;
	for(Kunde k : meineKunden){ 
		//ArrayListe durchlaufen und überprüfen ob Eingaben mit einem Kunden in der Liste
		//übereinstimmen
		if(passwort.equals(k.getPw()) && benutzername.equals(k.getkName())){
			check = true;
		}
	}
	return check;
	
}




} // class