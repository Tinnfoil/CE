import java.util.*;
/**
 * Player object (More can be added).
 * //+ means things can be added
 * @author (Kenny Doan and Brian Tran) 
 * @version (CE 2.5) Update
 */
public class Player extends Organism
{
    private String name;
    //+
    private int mana, maxmana, crit, normcrit,level, sp, exp;
    // A status effect. If the player has guard, his defense is increased;
    //+
    private int manarate;

    //+
    private ArrayList<Weapon> weaponrack = new ArrayList<Weapon>();
    private ArrayList<Armor> armory = new ArrayList<Armor>();

    public Player(int h, int m, int mh, int mm, int d, int dd, int hl, int c, String Name){
        super(h,mh,d,dd,hl,Name);
        maxmana= mm;
        crit=c;
        normcrit=crit;
        level=0;
        sp=0; // Skill Point. Used for Stat ups
        manarate = 5;
        exp=0;
        addGear();
    }

    /**
     * Add gear as needed in here; Each gear requires to be initated then added to weaponrack.
     */
    public void addGear()
    {
        weaponrack.add(new Weapon("sword",0,0,0,0,true)); //one weapon should always be true by default
        weaponrack.get(0).setOwned(true);
        weaponrack.add(new Weapon("hatchet",2,1,3,-5,false));
        weaponrack.add(new Weapon("swordandboard",2,2,3,0,false));
        weaponrack.add(new Weapon("staff",1,1,2,0,false));
        weaponrack.add(new Weapon("dagger",3,2,0,3,false));

        armory.add(new Armor("tunic",20,10,2,4,0,false));//Armor is not on by default
        armory.add(new Armor("cloak",15,15,1,0,5,false));
        armory.add(new Armor("chestpiece",40,0,0,0,0,false));
        armory.add(new Armor("robe",10,30,1,4,0,false));
        armory.add(new Armor("cowl",10,10,2,-2,-5,false));
    }

    /**
     * heals player health by the stat heal with a little more. More heal player has. More they heal.
     * For Example: heal=5 --> possible heal of 5-10;
     * player health cannot exceed maxhealth
     */
    public void heal() throws InterruptedException{
        Random RN= new Random();
        int difference = RN.nextInt(5)+1;
        int total = difference+getHeal();
        print("You heal for "+total+" health", 10);
        if((getHealth()+getHeal()+difference)>getMaxHealth()){
            setHealth(getMaxHealth());
        }
        else{
            setHealth(getHealth()+total);
        }
    }

    /**
     * Adds manaRegen to mana amount when called. If mana is over the limit, mana will be set to max
     * limit.
     */
    public void regenMana()
    {
        if(mana < maxmana)
        {
            if(weaponrack.get(3).getEquipped()&&((double)mana/maxmana)<.5){
                mana+=(manarate/2);
            }
            else if(weaponrack.get(3).getEquipped()&&((double)mana/maxmana)>=.5){
                mana+=(manarate*2);
            }
            else{
                mana += manarate;
            }
        }
        if(mana > maxmana)
        {
            mana = maxmana;
        }
    }

    /**
     * calulates base damage player does. A small variation in the damage of the player
     * For Example: damage =10 --> possible return between 10-15;
     */
    public int damage() throws InterruptedException {
        Random RN= new Random();
        int total = RN.nextInt(5)+1+getDamage();
        return total;
    }

    //+
    //---------------GENERAL METHODS-----------------------------//
    //TO INCREASE STATS: EX.Player player= new Player(1,1,1,1,11,1,1); 
    //                      player.setHealth(player.getHealth()+10);
    public int getMana(){
        return mana;
    }

    public void setMana(int amount){
        mana= amount;
        if(mana>maxmana){
            mana= maxmana;
        }
        if(mana<0){
            mana=0;
        }
    }

    public int getMaxMana(){
        return maxmana;
    }

    public void setMaxMana(int amount){
        maxmana=amount;
    }

    public int getManaRegen(){
        return manarate;
    }

    public void setManaRegen(int amount){
        manarate=amount;
    }

    public int getCrit(){
        return crit;
    }

    public void setCrit(int amount){
        crit=amount;
    }

    public int getNormCrit(){
        return normcrit;
    }

    public void setNormCrit(int amount){
        normcrit=amount;
    }

    public int getExp(){
        return exp;
    }

    public void setExp(int amount){
        exp=amount;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int amount){
        level=amount;
    }

    public int getSP(){
        return sp;
    }

    public void setSP(int amount){
        sp=amount;
    }

    public ArrayList<Weapon> getWeaponRack(){
        return weaponrack;
    }

    public ArrayList<Armor> getArmory(){
        return armory;
    }

    /**
     * A method to reset player's stats back to it's normal state after a fight.
     */
    public void updateStats(){
        setDamage(getNormDamage());
        setHeal(getNormHeal());
        setDefense(getNormDefense());
        setCrit(normcrit);
    }

    //+
    //-----------WEAPONS-----------//
    public Weapon getWeapon(){
        for(int i=0;i<weaponrack.size();i++){
            if(weaponrack.get(i).getEquipped()==true){
                return weaponrack.get(i);
            }
        }
        return null;
    }

    /**
     * Sets the weapon to be true. While setting the other false (only one weapon can be active)
     * Precondition: name must match at least one of the if conditions otherwise all weapons will
     * be false
     * weaponrack should always contain something that is currently equipped.
     * *DO NOT WRITE* player.setWeapon(Sword); <-- names must be lowercase when calling.
     */
    public void setWeapon(String name){
        //Unequips current weapon and removes stat buffs.
        for(int i = 0; i < weaponrack.size(); i++)
        {
            if(weaponrack.get(i).getEquipped()==true)
            {
                Weapon a = weaponrack.get(i);
                setNormDamage(getNormDamage() - a.getDamage());
                setNormDefense(getNormDefense() - a.getDefense());
                setNormHeal(getNormHeal() - a.getHeal());
                setNormCrit(getNormCrit() - a.getCrit());
                weaponrack.get(i).unEquip();
            }
        }
        //equips weapon.
        for(int i = 0; i < weaponrack.size(); i++)
        {
            if(weaponrack.get(i).getName().equals(name)&&weaponrack.get(i).getOwned()==true)
            {
                weaponrack.get(i).equip();
                Weapon b = weaponrack.get(i);
                setNormDamage(getNormDamage() + b.getDamage());
                setNormDefense(getNormDefense() + b.getDefense());
                setNormHeal(getNormHeal() + b.getHeal());
                setNormCrit(getNormCrit() + b.getCrit());
            }
        }

    }

    public Armor getArmor(){
        for(int i=0;i<armory.size();i++){
            if(armory.get(i).getEquipped()==true){
                return armory.get(i);
            }
        }
        return null;
    }

    public void setArmor(String name){
        //Unequips current weapon and removes stat buffs.
        for(int i = 0; i < armory.size(); i++)
        {
            if(armory.get(i).getEquipped()==true)
            {
                Armor a = armory.get(i);
                setMaxHealth(getMaxHealth() - a.getHealth());
                setMaxMana(getMaxMana() - a.getMana());
                setNormDefense(getNormDefense() - a.getDefense());
                setNormHeal(getNormHeal() - a.getHeal());
                setNormCrit(getNormCrit() - a.getCrit());
                armory.get(i).unEquip();
            }
        }
        //equips weapon.
        for(int i = 0; i < armory.size(); i++)
        {
            if(armory.get(i).getName().equals(name)&&armory.get(i).getOwned()==true)
            {
                armory.get(i).equip();
                Armor b = armory.get(i);
                setMaxHealth(getMaxHealth() + b.getHealth());
                setMaxMana(getMaxMana() + b.getMana());
                setNormDefense(getNormDefense() + b.getDefense());
                setNormHeal(getNormHeal() + b.getHeal());
                setNormCrit(getNormCrit() + b.getCrit());
            }
        }

    }

    /**
     * When creating a weapon. Having a method like this will help set conditionals in the main code
     * For Example in the fight method in CE: 
     * if(sword){
     *     System.out.println("(1)Use Stab");
     *  }
     *  As weapons are added, simply increment i in .get(i) for each added weapon; the number should
     *  match its index in weaponrack.
     */
    public boolean sword(){
        return weaponrack.get(0).getEquipped();
    }

    public boolean hatchet(){
        return weaponrack.get(1).getEquipped();
    }

    public boolean swordandboard(){
        return weaponrack.get(2).getEquipped();
    }

    public boolean staff(){
        return weaponrack.get(3).getEquipped();
    }

    public boolean dagger(){
        return weaponrack.get(4).getEquipped();
    }

    public boolean tunic(){
        return armory.get(0).getEquipped();
    }

    public boolean cloak(){
        return armory.get(1).getEquipped();
    }

    public boolean chestpiece(){
        return armory.get(2).getEquipped();
    }

    public boolean robe(){
        return armory.get(3).getEquipped();
    }

    public boolean cowl(){
        return armory.get(4).getEquipped();
    }

    public void printStats(){
        System.out.println("Damage:"+getDamage()+" Defense:"+getDefense()+" Heal:"+getHeal()+" Crit:"+getCrit()+"");
    }

    public void printWeaponStats(String name){
        for(int i = 0; i < weaponrack.size(); i++)
        {
            if(weaponrack.get(i).getName()==name)
            {
                Weapon a= weaponrack.get(i);
                System.out.println("Damage:"+a.getDamage()+" Defense:"+a.getDefense()+" Heal:"+a.getHeal()+" Crit:"+a.getCrit()+"");
            }
        }
    }

    public void printArmorStats(String name){
        for(int i = 0; i < armory.size(); i++)
        {
            if(armory.get(i).getName()==name)
            {
                Armor a= armory.get(i);
                System.out.println("Extra Health:"+a.getHealth()+" Extra Mana:"+a.getMana()+" ");
                System.out.println("Defense:"+a.getDefense()+" Heal:"+a.getHeal()+" Crit:"+a.getCrit()+"");
            }
        }
    }

    /**
     * Prints a given String with a delay between letters.
     * Ex. print("hello",100); -->prints and finishes hello in 500 miliseconds
     */
    public void print(String str, int delay) throws InterruptedException{
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            Thread.sleep(delay);
        }
    }

    /**
     * Prints given String but with a line added in the end
     */
    public void println(String str, int delay) throws InterruptedException{
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            Thread.sleep(delay);
        }
        System.out.println();
    }
}


