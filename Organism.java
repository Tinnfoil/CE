
/**
 * Organism is a super class that deals with basic character stats and methods.
 * 
 * @author (Kenny Doan and Brian Tran) 
 * @version (2.1.0) missing health (double) has been added.
 */
public class Organism
{
    private String name;
    private int health, maxhealth, damage, normdamage, defense, normdefense, heal, normheal;
    private boolean immune, burned;
    private int immuneTimer, burnTimer;
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
        immune=false;immuneTimer=0;
        burned=false;burnTimer=0;
    }

    //---------------GENERAL METHODS-----------------------------//

    public int getHealth(){
        return health;
    }

    public void setHealth(int amount){
        if((amount<health)&&immune)
        {
            //Do nothing
        }
        else if((amount>health)&&burned){
            //do Nothing
        }
        else
        {
            health=amount;
            if(health>maxhealth){
                health= maxhealth;
            }
            if(health<0){
                health=0;
            }
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

    public void immune(int amount)
    {
        immune = true;
        immuneTimer = amount;
    }

    public void immuneCheck()
    {
        if(immuneTimer<=0)
        {
            immune = false;
        }
        else{
            immuneTimer--;
        }
    }

    public boolean getImmune()
    {
        return immune;
    }
    
    public int getImmuneTimer(){
        return immuneTimer;
    }

    public void setImmune(boolean temp)
    {
        immune = temp;
    }

    public void burn(int amount)
    {
        burned = true;
        burnTimer = amount;
    }

    public void burnCheck()
    {
        if(burnTimer<=0)
        {
            burned = false;
        }
        else{
            burnTimer--;
        }
    }

    public boolean getBurned()
    {
        return burned;
    }
    
    public int getBurnTimer(){
        return burnTimer;
    }

    public void setBurned(boolean temp)
    {
        burned = temp;
    }
}


