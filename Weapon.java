
/**
 * Weapon Class represents a weapon which is a object that has it's respective stats.
 * :)
 * @author (Kenny Doan and Brian Tran) 
 * @version (CE 1.0)
 */
public class Weapon
{
    //Stats that goes with weapons. Adds onto the player's current stat
    private int damage, defense, heal, crit;
    //Name of the weapon
    private String name;
    //Boolean to represent if the the weapon is equiped.
    private boolean equipped, owned;

    /**
     * Constructor for objects of class Weapon
     */
    public Weapon(String n, int d, int dd, int hl, int c, boolean e)
    {
        name = n;
        damage = d;
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
    
    public int getDamage()
    {
        return damage;
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











