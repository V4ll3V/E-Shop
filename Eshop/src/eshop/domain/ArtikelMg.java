package eshop.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

import eshop.domain.exceptions.*;
import eshop.value.Artikel;
import eshop.value.Kunde;


public class ArtikelMg {
// alles, was der ArtikelMg so braucht:
	Map<String, Artikel> map = new HashMap<String, Artikel>(); // hier kommen die Artikel rein - Titel ist der Key
	String dat; // die Sicherungsdatei
	
	
public ArtikelMg (String datei) throws Exception{
	dat = datei+"_A.txt";		// <datei>_Buch.txt
	
	BufferedReader in;
	try {
	    in = new BufferedReader (new FileReader (dat));
	    boolean ok;
	    Artikel einArtikel;
	    do {
	    	einArtikel = new Artikel();
	    	ok = einArtikel.leseDaten(in);
	    	if (ok) 
	    		this.einfuegen( einArtikel);
	    } while (ok); 
	    in.close();
	}
	catch (Exception e) {
	    throw e;
	}
}

public boolean einfuegen (Artikel einA) throws ExcArtikelSchonDa {
	if (! map.containsValue(einA)){
		map.put(einA.getTitel(), einA);
		return true;
	}
	else 
		throw new ExcArtikelSchonDa("Buch: "+einA+" schon da");
}


// --- weitere Dienste der BuchMenge ---

public List<Artikel> sucheT (String t){
	Artikel b = /*(Buch)*/ map.get(t);
	List<Artikel> erg = new ArrayList<Artikel>(); 
	if (b!= null) 
		erg.add(b);
	return erg;
}

public boolean sichernArtikel() throws Exception{
	PrintWriter  out;
	try {
	    out = new PrintWriter( new FileOutputStream (dat));
	    for (Artikel b : map.values()){
		  b.schreibeDaten(out);
	    }
	    out.close();
	}
	catch (Exception e) {
	    throw e;
	}
	return true;
}

public String ausgebenAlle() {
	String erg = ""; 
	for (Artikel b : map.values()){
		erg=erg+b+"\n";
	}
	return erg;
}

} // class