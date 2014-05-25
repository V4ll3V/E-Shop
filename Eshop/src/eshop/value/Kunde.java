package eshop.value;

import java.io.Serializable;

public class Kunde implements Serializable{

	
@Override
public String toString() {
		return "Kunde [kNr=" + password + ", kName=" + kName + "]";
}




// alles, was der Kunde so braucht:
private String password;  // eindeutig
private String kName;
private boolean gKunde;


public Kunde (String n, String pw, boolean gK){
  password = pw;
  kName = n; 
  gKunde = gK;
}


public String getPw() {
	return password;
}




public void setkNr(String kNr) {
	this.password = kNr;
}




public String getkName() {
	return kName;
}




public boolean equals (Object k){ 
  // Standard-Methode von Object überschreiben (Vergleicht zwei Kunden auf gleiche Werte, nicht gleiche Adresse
  return (k!=null && (k instanceof Kunde) && password == ((Kunde) k).getPw());
}

}

