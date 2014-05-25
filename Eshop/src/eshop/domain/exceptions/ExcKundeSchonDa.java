package eshop.domain.exceptions;

@SuppressWarnings("serial")
public class ExcKundeSchonDa extends Exception {

public ExcKundeSchonDa (String zusatzMsg){
  super ("Kunde schon da" + zusatzMsg);
}


}

