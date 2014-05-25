package eshop.value;

import java.io.*;

public class Artikel {
// alles, was das Buch so braucht:
private int bNr;
private String titel;
private boolean verfuegbar = true; 
// ...


public Artikel (String t, int nr){
  bNr = nr;
  titel = t;  
}

public Artikel (){ 
}

// --- Dienste der Buch-Objekte ---

public String toString(){	
  // Standard-Methode von Object überschreiben
  // wird immer automatisch aufgerufen, wenn ein Buch-Objekt als "String" benutzt wird (zB in println(buch);)
  return ("Nr: " + bNr + " / Titel: " + titel + " / verfügbar: "+(verfuegbar?"+":"-"));
}

public boolean equals (Object o){ 
  // Standard-Methode von Object überschreiben (Vergleicht zwei Bücher auf gleiche Werte, nicht gleiche Adresse
	if ((o == null)||(! (o instanceof Artikel))) return false; 
	Artikel b = (Artikel) o;  // ich weiß, o ist ein Buch
	return ((this.bNr == b.bNr) && (this.titel.equals(b.titel)));
}

public int getbNr() {
	return bNr;
}

public String getTitel() {
	return titel;
}



public boolean leseDaten( BufferedReader f){
  try {
	titel= f.readLine();
	bNr  = Integer.parseInt( f.readLine());
	String v= f.readLine();
	if (v.equals("t")) 
		verfuegbar=true; 
	else 
		verfuegbar=false;
  }
  catch (Exception e){
	return false;
  }
  return true;
}

public boolean schreibeDaten( PrintWriter f){
  try {
    f.println(titel);
    f.println(bNr);
    if (verfuegbar) 
    	f.println("t");
    else 
    	f.println("f");
  }
  catch (Exception e){
	return false;
  }
  return true;
}


}

