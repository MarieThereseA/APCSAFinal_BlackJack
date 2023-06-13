package com.example.apcsafinal_blackjack;

import java.util.ArrayList;

public class Player extends House{
  int bank;
  int wins;
  int losses;
  int games;
  int IDNum;
  private ArrayList<Chip> black;
  private ArrayList<Chip> green;
  private ArrayList<Chip> blue;
  private static final String BLUE = "\u001b[34;1m";
  private static final String GREEN = "\u001b[32;1m";
  private static final String WHITE = "\u001b[37;1m";
  private static final String RESET = "\u001B[0m";

  //Overloaded Constructor
  public Player(String name, int IDNum){
    super(name);
    this.IDNum = IDNum;
    bank = 500;
    wins = 0;
    losses = 0;
    games = 0;
    black = new ArrayList<Chip>();
    green = new ArrayList<Chip>();
    blue = new ArrayList<Chip>();

//    black.add(new Chip(100));
//    for (int i = 0; i < 8; i++){
//      green.add(new Chip(25));
//    }
//    for (int i = 0; i < 20; i++){
//      blue.add(new Chip(10));
//    }
  }

  //Overloaded Constructor
  public Player(String name){
    super(name);
  }

  //Overloaded Constructor
//  public Player(){
//    super();
//  }

  public void getInfo(String data){
    data = data.substring(data.indexOf("|") + 1);
    IDNum = Integer.parseInt(data.substring(data.indexOf("ID:") + 3, data.indexOf("|")));
    data = data.substring(data.indexOf("b:$") + 3);
    bank = Integer.parseInt(data.substring(0, data.indexOf("|")));

    data = data.substring(data.indexOf("blackC:") + 7);
    int blackChips = 0;
    blackChips = Integer.parseInt(data.substring(0, data.indexOf("|")));
    for (int i = 0; i < blackChips; i++){
      black.add(new Chip(100));
    }

    data = data.substring(data.indexOf("blueC:") + 6);
    int blueChips = 0;
    blueChips = Integer.parseInt(data.substring(0, data.indexOf("|")));
    for (int i = 0; i < blueChips; i++){
      blue.add(new Chip(10));
    }

    data = data.substring(data.indexOf("greenC:") + 7);
    int greenChips = 0;
    greenChips = Integer.parseInt(data.substring(0, data.indexOf("|")));
    for (int i = 0; i < greenChips; i++){
      green.add(new Chip(25));
    }

    data = data.substring(data.indexOf("gP:") + 3);
    games = Integer.parseInt(data.substring(0, data.indexOf("|")));

    data = data.substring(data.indexOf("gW:") + 3);
    wins = Integer.parseInt(data.substring(0, data.indexOf("|")));

    data = data.substring(data.indexOf("gL:") + 3);
    losses = Integer.parseInt(data.substring(0, data.indexOf(";")));

  }

  public int getBank(){
    return bank;
  }

  public int getID(){
    return IDNum;
  }


  public void addGame(){
    games++;
  }

  public void addWin(){
    wins++;
  }

  public void addLoss(){
    losses++;
  }

  @Override
  public void hit(Card card) {
    int val = getCurrentValue();
    addToHand(card);
    ArrayList<Card> hand = getCards();
    if (card.isAce()){
      boolean anotherAce = false;
      for (int  i = 0; i < getHandSize() - 1; i++){
        if (hand.get(i).isAce()){
          anotherAce = true;
        }
      }
      if(anotherAce){
        val++;
      }else {
        if ((val + 11) > 21){
          val++;
        }else {
          val += 11;
        }
      }
      setCurrentValue(val);
    }else {
      val += card.getValue();
      setCurrentValue(val);
    }
  }

  public void lose(int blackBet, int greenBet, int blueBet){
    int i = 0;
    while(i < blackBet){
      black.remove(0);
      i++;
    }

    i = 0;
    while(i < greenBet){
      green.remove(0);
      i++;
    }

    i = 0;
    while(i < blueBet){
      blue.remove(0);
      i++;
    }

    bank -= ((blackBet * 100) + (greenBet * 25) + (blueBet * 10));
    losses++;
    games++;
  }

  public void win(int blackBet, int greenBet, int blueBet){
    int i = 0;
    while(i < blackBet){
      black.add(new Chip(100));
      i++;
    }

    i = 0;
    while(i < greenBet){
      green.add(new Chip(25));
      i++;
    }

    i = 0;
    while(i < blueBet){
      blue.add(new Chip(10));
      i++;
    }

    bank += ((blackBet * 100) + (greenBet * 25) + (blueBet * 10));
    wins++;
    games++;
  }

  public String getChips(){
    String chips = WHITE + "Black ($100)" + RESET + ": " + black.size();
    chips+= GREEN + "\nGreen ($25)" + RESET + ": " + green.size();
    chips+= BLUE + "\nBlue ($10)" + RESET + ": " + blue.size();
    return chips;
  }

  public String toString(){
    String info = this.getName() + "'s Stats:";
    info+= "\nBank value: " + "$" + bank;
    info += "\nChips: \n" + getChips();
    info += "\nGames Played: " + games;
    info += "\nWins: " + wins;
    info += "\nLosses: " + losses;
    return info;
  }

  public int[] getMaxBet(){
    int[] max = new int[3];
    max[0] = black.size();
    max[1] = green.size();
    max[2] = blue.size();
    return max;
  }


}

