package eshop.domain.exceptions;

@SuppressWarnings("serial")
public class ExcArtikelSchonDa extends Exception {

public ExcArtikelSchonDa (String zusatzMsg){
  super ("Buch schon da" + zusatzMsg);
}


}

