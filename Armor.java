
/**
 * Armor Class represents a armor which is a object that has it's respective stats.
 * 
 * @author (Kenny Doan and Brian Tran) 
 * @version (CE 2.1.0)
 */
public class Armor
{
    //Stats that goes with weapons. Adds onto the player's current stat
    private int health, mana, defense, heal, crit;
    //Name of the weapon
    private String name;
    //Boolean to represent if the the weapon is equiped.
    private boolean equipped, owned;

    /**
     * Constructor for objects of class Weapon
     */
    public Armor(String n, int h, int m, int dd, int hl, int c, boolean e)
    {
        name = n;
        health=h;
        mana=m;
        defense = dd;
        heal = hl;
        crit = c;
        equipped = e;
        owned = false;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getMana(){
        return mana;
    }

    public int getDefense()
    {
        return defense;
    }
    
    public int getHeal()
    {
        return heal;
    }
    
    public int getCrit()
    {
        return crit;
    }
    
    public boolean getOwned()
    {
        return owned;
    }
    
    public void setOwned(boolean a)
    {
        owned = a;
    }
    
    public boolean getEquipped()
    {
        return equipped;
    }
    
    public void equip()
    {
        equipped = true;
    }
    
    public void unEquip()
    {
        equipped = false;
    }
}












