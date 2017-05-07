
/**
 * Organism is a super class that deals with basic character stats and methods.
 * :)
 * @author (Kenny Doan and Brian Tran) 
 * @version (1.0) missing health (double) has been added.
 */
public class Organism
{
    private String name;
    private int health, maxhealth, damage, normdamage, defense, normdefense, heal, normheal;
    public Organism(int h, int mh, int d, int dd, int hl, String name){
        health= h;
        maxhealth= mh;
        normdamage=d;
        damage=d;
        defense= dd;
        normdefense=dd;
        heal= hl;
        normheal = hl;
        this.name=name; 
    }

    //---------------GENERAL METHODS-----------------------------//

    public int getHealth(){
        return health;
    }

    public void setHealth(int amount){
        health=amount;
        if(health>maxhealth){
            health= maxhealth;
        }
        if(health<0){
            health=0;
        }
    }

    public int getMaxHealth(){
        return maxhealth;
    }

    public void setMaxHealth(int amount){
        maxhealth=amount;
    }

    public int getDamage(){
        return damage;
    }

    public void setDamage(int amount){
        damage= amount;
        if(damage<0){
            damage=0;
        }
    }

    public void updateDamage()
    {
        damage = normdamage;
    }

    public int getNormDamage()
    {
        return normdamage;
    }

    public void setNormDamage(int amount)
    {
        normdamage = amount;
    }

    public int getDefense(){
        return defense;
    }

    public void setNormDefense(int amount){
        normdefense= amount;
    }

    public int getNormDefense(){
        return normdefense;
    }

    public void setDefense(int amount){
        defense= amount;
    }

    public int getHeal(){
        return heal;
    }

    public void setHeal(int amount){
        heal=amount;
        if(heal<0){
            heal=0;
        }
    }

    public int getNormHeal(){
        return normheal;
    }

    public void setNormHeal(int amount){
        normheal=amount;
    }

    public String getName(){
        return name;
    }

    public void setName(String Name){
        name=Name;
    }

    /**
     * Updates stats back into original stats from any temporary buffs in battle.
     */
    public void updateStats()
    {
        damage = normdamage;
        defense = normdefense;
        heal = normheal;
    }

    public double missingHealthPercentage(){
        return (1-(double)health/maxhealth);
    }

    public double healthPercentage(){
        return (double)health/maxhealth;
    }

    public int missingHealth(){
        return maxhealth-health;
    }
}


