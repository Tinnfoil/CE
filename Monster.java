
/**
 * The monster class that contains stats and buffs of certain types of monsters.
 * :)
 * @author (Kenny Doan and Brian Tran) 
 * @version (CE 2.5) Balance Update
 */
public class Monster extends Organism
{
    private String name;
    //AID --> Ability ID
    private int health, maxhealth, damage, defense, heal,id, dodge, AID, identification;

    //---------------------------Monster Type--------------------------//
    private boolean slime,goblin,skeleton,wolf;//weak monsters id 1+
    private boolean ghoul,golem,orc,crab; //medium monsters 11+
    private boolean knight, witch1, witch2, witch3, witch4; //hard monsters 101+
    private boolean boss, vanguard, goon, chicken, wraith; // ID 1001+ monsters(mini-bosses)
    //-------------------------------Other-----------------------------//
    private boolean buffed;
    /**
     * id or Monster Type parameter.
     * Monster IDs come in sets of thousands to indicate author.
     * Ex. Monster cheese= new Monster(100,100,5,3,1,Mouse,2); --> a goblin named Mouse
     * 1-Slime Type
     * 2-Goblin Type
     * 1001 - Chicken Type
     * 1002 - Wraith Type
     */
    public Monster(int h, int mh, int d, int dd, int hl,int doge,String Name, int id){
        super(h,mh,d,dd,hl,Name);
        AID=0;
        dodge=doge;
        if(id==1){
            slime=true;
        }
        else if(id==2){
            goblin=true;
        }
        else if(id==3){
            skeleton=true;
        }
        else if(id==4){
            wolf=true;
        }
        else if(id==11){
            ghoul=true;
        }
        else if(id == 12)
        {
            golem = true;
        }
        else if(id==13){
            orc=true;
        }
        else if(id==14)
        {   
            crab=true;
        }
        else if(id==101){
            knight=true;
        }
        else if(id==102)
        {
            witch1=true;
        }
        else if(id==103)
        {
            witch2=true;
        }
        else if(id==104)
        {
            witch3=true;
        }
        else if(id==105)
        {
            witch4=true;
        }
        else if(id == 1000)
        {
            chicken = true;
        }
        else if(id == 1001)
        {
            wraith = true;
        }
        else if(id==9999){
            boss=true;
        }
        else if(id==9001){
            vanguard=true;
        }
        else if(id==9002){
            goon= true;
        }
        buffed=false;
        identification = id;
    }

    /**
     * A method that will trigger solely stat buff in certain conditions. Call this method before start of turn
     * Ex. if(health<50&&Slime==true){
     *     damage= damage+5;
     *  }
     */
    public void buff(){
        if(slime==true&&health<40&&!buffed){
            setDamage(getDamage()+3);
            buffed=true;
        }
        else if(goblin == true && health < 30 && !buffed)
        {
            setDamage(getDamage()+4);
            setDefense(getDefense()+2);
            buffed = true;
        }
        else if(skeleton=true&& health<=0&&!buffed){
            setDamage(getDamage()+5);
            buffed=true;
        }
        else if(wolf=true&& healthPercentage()<=.2&&!buffed){
            setDodge(getDodge()-5);// Lol not a buff
            buffed=true;
        }
        else if(ghoul==true && (double)getHealth()/getMaxHealth()<=0.4&& !buffed){
            setDamage(getDamage()+8);
            buffed=true;
        }
        else if(orc=true&& healthPercentage()<=.5&&!buffed){
            setDefense(getDefense()+2);
            buffed=true;
        }
        else if(knight==true&& healthPercentage()<.2&&!buffed){
            setDodge(getDodge()+5);
            buffed=true;
        }
        else if(chicken == true && health < 4 && !buffed)
        {
            setDamage(getDamage()+100);
            setDefense(-4);
            setName("SUPER CHICKEN!!!");
        }
        else if(wraith == true && health > 30&&!buffed)
        {
            System.out.println("The wraith turns red and seems to go into a frenzy");
            buffed=true;
        }

    }

    /**
     * A return method for the purpose of checking what monster this is in the main method
     */
    public int getId(){
        return identification;
    }

    public int getDodge(){
        return dodge;
    }

    public void setDodge(int amount){
        dodge=amount;
    }

    public int getAID(){
        return AID;
    }

    public void setAID(int amount){
        AID=amount;
    }

    public boolean getBuff()
    {
        return buffed;
    }
    
    public void printStats(){
        System.out.println("Damage:"+getDamage()+" Defense:"+getDefense()+" Dodge:"+dodge+"");
    }
}







