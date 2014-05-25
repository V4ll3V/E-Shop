package eshop.value;

import java.io.BufferedReader;
import java.io.Serializable;


public class Mitarbeiter implements Serializable{
private String password; 
private String MName;

public Mitarbeiter (String n, String pw){
  password = pw;
  MName = n; 

}
	
public Mitarbeiter(){

}

public String toString() {
		return "Mitarbeiter [kNr=" + password + ", kName=" + MName + "]";
}




// alles, was der Kunde so braucht:






public String getPw() {
	return password;
}




public void setkNr(String MNr) {
	this.password = MNr;
}




public String getMName() {
	return MName;
}






public boolean equals (Object k){ 
  // Standard-Methode von Object überschreiben (Vergleicht zwei Kunden auf gleiche Werte, nicht gleiche Adresse
  return (k!=null && (k instanceof Mitarbeiter) && password == ((Mitarbeiter) k).getPw());
}

}

