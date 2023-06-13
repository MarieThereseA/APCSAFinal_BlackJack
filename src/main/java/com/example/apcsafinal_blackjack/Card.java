package com.example.apcsafinal_blackjack;

public class Card {
  private int value; // Value of the card during gameplay
  private String name; //Name of the card
  private String face; //Face of card

  public Card(String suit, String face){ //Constructor for face cards
    this.face = face;
    if (face.equals("Ace")){
      this.value = 0;
    }else {
      this.value = 10;
    }
    name = face + " of " + suit + "s";
  }

  //Overloaded Constructor
  public Card(String suit, int value){ //Constructor for numeric cards
    this.value = value;
    name = value + " of " + suit + "s";
  }

  //Overloaded Constructor
  public Card(){ //Constructor for Joker
    this.value = 0;
    name = "Joker";
  }

  //Getter & Setter Methods
  public String getName(){
    return name;
  }

  public int getValue(){
    return value;
  }

  public boolean isJoker(){
    return name.equals("Joker");
  }

  public boolean  isAce(){
    return name.contains("Ace");
  }

}