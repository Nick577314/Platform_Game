package com.mygdx.game;

public class Character {
  private String characterName;

  private int charHP;

  private int maxHP;

  private int attack;

  private int defense;

  private int experiencePoints;

  private int criticalHitRate;

  private int accuracy;

  private int speed;

  private Character Mage;

  public Character(
      String charName,
      int charHP,
      int maxHP,
      int attack,
      int defense,
      int experiencePoints,
      int critRate,
      int charAcc,
      int speed) {
    this.characterName = charName;
    this.charHP = charHP;
    this.maxHP = maxHP;
    this.attack = attack;
    this.defense = defense;
    this.experiencePoints = experiencePoints;
    this.criticalHitRate = critRate;
    this.accuracy = charAcc;
    this.speed = speed;
  }

  public Character createMage() {
    return new Character("Mage", 95, 95, 50, 25, 0, 5, 95, 30);
  }

  public Character createArcher() {
    return new Character("Archer", 90, 90, 40, 30, 0, 10, 95, 40);
  }

  public Character createWarrior() {
    return new Character("Warrior", 105, 105, 35, 40, 0, 5, 95, 35);
  }

  public Character createEnemy() {
    return new Character("Enemy", 120, 120, 25, 35, 0, 5, 95, 35);
  }

  public void setCharacterName(String newName) {
    characterName = newName;
  }

  public void setCurrentHP(int newHP) {
    charHP = newHP;
  }

  public void setMaxHP(int newMaxHP) {
    maxHP = newMaxHP;
  }

  public void setAttack(int newAttack) {
    attack = newAttack;
  }

  public void setDefense(int newDefense) {
    defense = newDefense;
  }

  public void setNewXP(int newXP) {
    experiencePoints = newXP;
  }

  public void setCritRate(int newCritRate) {
    criticalHitRate = newCritRate;
  }

  public void setAccuracy(int newAccuracy) {
    accuracy = newAccuracy;
  }

  public void setSpeed(int newSpeed) {
    speed = newSpeed;
  }

  public String getCharacterName() {
    return characterName;
  }

  public int getCharHP() {
    return charHP;
  }

  public int getMaxHp() {
    return maxHP;
  }

  public int getAttack() {
    return attack;
  }

  public int getDefense() {
    return defense;
  }

  public int getXP() {
    return experiencePoints;
  }

  public int getCritRate() {
    return criticalHitRate;
  }

  public int getAccuracy() {
    return accuracy;
  }

  public int getSpeed() {
    return speed;
  }
}
