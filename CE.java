import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
/**
 * A short game with the simple goal of beating the final boss in under 10 days.
 * This is a open source code and things are open to be changed
 * //+ means things can be added
 * @author (Kenny Doan and Brian Tran) 
 * @version (CE 2.5.1) Update
 * Burned Mechanic added for both monster/player(Cannot recover health);
 */
public class CE
{
    //+
    //These ints are gobal variables that can be used or changed at anytime.
    //For example: Number of Days passed, gold(player money),  
    public static int day, gold, dungeon, livingArmor, startingMana, healthPot, manaPot, statusPot, firePot, goodPot;
    public static String healthNum, manaNum, statusNum, fireNum, goodNum;

    //+
    //These booleans are used for status of the player in non-combat situation.
    //For example: If quests are availiable;
    public static boolean quest1, waterBought, mimi, sasha, mary, infight, playerLost, isGuarding, misGuarding, confused, cursed, DOT, mDOT, stun, sRevived, boosting,
    dodge, attackCancelled, guardCancelled, restCancelled, ballsy, defStance, shieldBash, hatchetPass, wildSlash, overflow, manaSurge, split, wrathPass, atkDown, atkUp, defUp, choosen, deathPrevention,
    attacked;    
    //
    //Check system
    //For things which happen in sequence only once up to 10 times.
    public static boolean firstCheck, secondCheck, thirdCheck, fourthCheck, fifthCheck, sixthCheck, seventhCheck, eigthCheck, ninthCheck, tenthCheck;

    //+
    //Timers for ablities/mechanics
    public static int guardTimer, guardAmount, mguardTimer, multiplier, defStanceTimer, trueBurst, amount, splitAmount, atksLeft, splitTimer, confuseTimer,curseTimer, DOTTimer, mDOTTimer, stunTimer,
    dodgeChance,dodgeTimer, attackCancelTimer, guardCancelTimer, restCancelTimer, boostingTimer,  atkDownTimer,
    atkUpTimer, defUpTimer, warCryCount, turnTimer;

    //+
    //Misc.
    public static int mDOTScale, DOTScale, boostingScale, wrathStacks, boostingTimes, atkDownScale, atkUpScale, defUpScale, lastTurnHp, damageDealt;

    public static int[] dung, currentDung;

    public static Player saveFile;

    public static Clip clip;

    //Boss
    public static boolean mDefStance, bossTimer;
    public static int timer;
    public static String bossName;
    Player player= new Player(100,100,100,100,10,0,5,5,"");
    Monster monster= new Monster(100,100,0,0,0,0,"",0);
    Monster boss = new Monster(100,100,0,0,0,0,"",0);
    Extra e= new Extra();
    /**
     * Main method to start the game. Where the beginning setup is made.Like cheats
     * For example: setting the day to day 5 right away, having extra gold
     */
    public static void main(String[] args) throws Exception{
        gold=400;
        day=0;
        dungeon=1;
        guardAmount=5;
        startingMana=0;
        waterBought=false;
        infight=false;
        playerLost=false;
        bossName="King";
        CE game= new CE();
        game.player.setMaxHealth(game.player.getMaxHealth()+50);
        game.player.setHealth(game.player.getMaxHealth());
        game.e.printTitle();
        game.start();
        game.town();
    }

    public void start() throws Exception{
        Scanner input= new Scanner(System.in);
        println("You see a faint light in the far distance.",10,500);
        println("Curious, you appoarch it and find a rock stuck between some boulders",10,500);
        println("Take it?",10,500);
        System.out.println("(1)Take it");
        System.out.println("(2)Ingore it and move on");
        String a= input.next();
        if(a.equals("1")){
            tutorial();
        }
        else{
            println("You decide to leave the strange rock alone",10,500);
            println("After walking aimlessly walking around, you stumble upon a large town",10,500);
            e.printTown();
        }
    }

    /**
     * One of the main part of the game. Consist of a while loop to give players the ability
     * to go to the same place again
     * Returns a value. Which represents a monster id.
     * 
     */
    public void town() throws Exception{
        Scanner input= new Scanner(System.in);
        String a="1";
        String b="1";
        while(playerLost==false){
            day++; //every call for this method will increase the day.
            System.out.println("{Day "+day+"}");
            System.out.println("Press 1 to continue");
            a=input.next(); //<--doesn't matter but it's used for pauses in the game.
            while(infight==false){
                System.out.println("Gold:"+gold+"");
                System.out.println("(1)Tavern");
                System.out.print("(2)Healer");
                if(player.healthPercentage()<.4){// helper indicators
                    System.out.println("{Low Health!"+player.getHealth()+"/"+player.getMaxHealth()+"}");
                }
                else{
                    System.out.println("");
                }
                System.out.println("(3)Blacksmith");
                System.out.print("(4)Player");
                if(player.getSP()>0){
                    System.out.println("{Skill Points Unused!}");
                }
                else{
                    System.out.println("");
                }
                System.out.println("(5)Dungeon");
                a=input.next();

                //After selecting a choice the switch method will chose one outcome.
                //more can be added
                //Tavern
                if(a.equals("1")){
                    b="1"; // use b for choices in the cases.
                    e.tavernTalk();
                    System.out.println("(1)Buy a drink [5 gold]");
                    System.out.println("(2)Talk to Billy");
                    System.out.println("(0)Go back");
                    b=input.next();
                    if(b.equals("1")){
                        println("Bartender:What kind?",20);
                        System.out.println("(1)Beer[5 gold]");
                        System.out.println("(2)Water[1 gold]");
                        System.out.println("(3)Cold Water[1 Gold]");
                        System.out.println("(0)Go back");
                        String c=input.next();
                        if(c.equals("1")&&canBuy(5)){
                            System.out.println("Bartender: Here you go.");
                            System.out.println("You drink a mug of beer.");
                        }
                        else if(c.equals("2")&&canBuy(1)){
                            System.out.println("Bartender: Here you go.");
                            System.out.println("You drink some water.");
                        }
                        else if(c.equals("3")){
                            if(!waterBought){
                                gold= gold-1;
                                System.out.println("Bartender: Here you go.");
                                System.out.println("You drink a cold glass of water");
                                e.tavernSecret();
                            }
                            println("You follow the bartender though a secret door in the back.",30,500);
                            if(!waterBought){
                                println("Bartender:Pick whatever one you like...",30,500);
                                println("He's harboring female slaves!",30,500);
                            }
                            System.out.println("GOLD:"+gold+"");
                            if(!mimi){System.out.println("(1)Mimi{50% chance to find more gold after fights(x1.2)}[120 Gold]|Age: 14|");}
                            if(!sasha){System.out.println("(2)Sasha{10% chance to attack per turn(damage based on your level:10+level)}[100 Gold]|Age:17|");}
                            if(!mary){System.out.println("(3)Mary{Heals you 15% of max health after every fight(Not through magic...)}[120 gold]|Age:19|");}
                            System.out.println("(0)Leave");
                            String d=input.next();
                            if(d.equals("1")&&!mimi&&canBuy(120)){
                                println("Mimi:I-I hope I can be useful to you...",30,500);
                                println("Mimi:I'm sorry, what was your name master?",30,500);
                                System.out.print("What will the slave call you?:");
                                String name= input.next();
                                e.setName1(name);
                                println("Mimi:I can't do much, b-but I can find things for you.",30,500);
                                println("Mimi:I promise I'll get things you will like "+e.getName1()+"!",30,500);
                                mimi=true;
                            }
                            else if(d.equals("2")&&!sasha&&canBuy(100)){
                                println("Sasha:... I know swordmanship... I don't know much other than that",30,500);
                                println("Sasha:Umm...?",30,500);
                                System.out.print("What will the slave call you?:");
                                String name= input.next();
                                e.setName2(name);
                                println("Sasha:I will do my best to serve you "+e.getName2()+"",30,500);
                                sasha=true;
                            }
                            else if(d.equals("3")&&!mary&&canBuy(120)){
                                println("She smiles and walks towards me",30,500);
                                println("Mary:I think you and me will get along nicely...",30,500);
                                println("Mary:May I ask for your name Master?...",30,500);
                                System.out.print("What will the slave call you?:");
                                String name= input.next();
                                e.setName3(name);
                                println("Mary:I hope I can please you "+e.getName3()+". One way or another...",30,500);
                                mary=true;
                            }
                            waterBought=true;
                        }
                    }
                    else if(b.equals("2"))
                    {
                        println("It seems Billy is away at this time",30);
                    }
                }
                //Healer
                else if(a.equals("2")){
                    boolean cont= true;
                    e.healerTalk();
                    while(cont){
                        System.out.println("GOLD:"+gold+"  Health:"+player.getHealth()+"/"+player.getMaxHealth()+"");
                        System.out.println("(1)Max heal[30 gold]");
                        System.out.println("(2)100 point heal[20 gold]");
                        System.out.println("(3)Health Potion{Heals 20+player's heal}[30 gold]|Max:5|");
                        System.out.println("(4)Mana Flask{Regenerates 30% of player's missing mana}[25 gold]|Max:5|");
                        System.out.println("(5)Ala's Tonic{Removes all debuffs}[15 gold]");
                        System.out.println("(0)Go back");
                        b=input.next();
                        if(b.equals("1")&&canBuy(30)&&player.healthPercentage()!=1.0){
                            player.setHealth(player.getMaxHealth());
                        }
                        else if(b.equals("2")&&canBuy(20)){
                            player.setHealth(player.getHealth()+100);
                        }    
                        else if(b.equals("3")&&canBuy(30)&&healthPot<5){
                            System.out.println("You buy a Health Potion");
                            healthPot++;
                        }
                        else if(b.equals("4")&&canBuy(25)&&manaPot<5){
                            System.out.println("You bought a Mana potion");
                            manaPot++;
                        }
                        else if(b.equals("5")&&canBuy(15)){
                            System.out.println("You bought Ala's tonic");
                            statusPot++;
                        }
                        else if(b.equals("0")){
                            cont=false;
                        }
                    }
                    System.out.println("Priestess: Come back next time!");
                }
                //Blacksmith
                else if(a.equals("3")){
                    e.smithTalk();
                    System.out.println("(1)Armors");
                    System.out.println("(2)Stackables");// Ex. defense upgrade is player defense+1;
                    System.out.println("(3)Weapons");
                    System.out.println("(0)Go back");
                    b= input.next();
                    if(b.equals("1")){
                        boolean cont=true;
                        while(cont){
                            System.out.println("GOLD:"+gold+"");
                            if(!player.getArmory().get(0).getOwned()){System.out.println("(1)Goblin Tunic[100 Gold]");}
                            if(!player.getArmory().get(1).getOwned()){System.out.println("(2)Dark Cloak[100 Gold]");}
                            if(!player.getArmory().get(2).getOwned()){System.out.println("(3)Gaia's Chestpiece[100 Gold]");}
                            if(!player.getArmory().get(3).getOwned()){System.out.println("(4)Ari's Robes[100 Gold]");}
                            if(!player.getArmory().get(4).getOwned()){System.out.println("(5)Magic Cowl[100 Gold]");}
                            System.out.println("(0)Go Back");
                            String c=input.next();
                            if(!c.equals("0")){
                                cont=printArmors(c);
                            }
                            else if(c.equals("0")){
                                cont=false;
                            }
                        }
                    }
                    else if(b.equals("2")){
                        boolean cont = true;
                        while(cont){
                            System.out.println("Gold:"+gold+"");
                            System.out.println("(1)Living Armor{+1 Health per turn during combat}[75 Gold]");
                            System.out.println("(2)Mana Ring{+1 Mana Regeneration per turn}[50 gold]");
                            System.out.println("(3)Reina's Pendent{+15 starting mana}[50 Gold]|Max:4|");
                            System.out.println("(4)Thirst of Ando{+2 damage -2 defense}[50 gold]");
                            System.out.println("(0)Go Back");
                            String c= input.next();
                            if(c.equals("1")&&canBuy(75)){
                                println("Living Armor +1!",20);
                                livingArmor++;
                            }
                            if(c.equals("2")&&canBuy(50)){
                                println("Mana Regen +1!",20);
                                player.setManaRegen(player.getManaRegen()+1);
                            }
                            if(c.equals("3")&&canBuy(50)&&startingMana<60){
                                println("Starting mana +15!",20);
                                startingMana+=15;
                            }
                            if(c.equals("4")&&canBuy(50)){
                                println("Gain +2 damage and lost -2 defense",20);
                                player.setNormDamage(player.getNormDamage()+2);
                                player.setNormDefense(player.getNormDefense()-2);
                                player.updateStats();
                            }
                            if(c.equals("0")){
                                cont=false;
                            }
                        }
                    }
                    else if(b.equals("3")){
                        boolean cont= true;
                        while(cont){
                            System.out.println("Gold:"+gold+"");
                            if(!player.getWeaponRack().get(1).getOwned()){System.out.println("(1)Hatchet[100 Gold]");}
                            if(!player.getWeaponRack().get(2).getOwned()){System.out.println("(2)Shield{Adds on sword}[100 Gold]");}
                            if(!player.getWeaponRack().get(3).getOwned()){System.out.println("(3)Magical Staff[100 Gold]");}
                            if(!player.getWeaponRack().get(4).getOwned()){System.out.println("(4)Cursed dagger[100 Gold]");}
                            if(!player.getWeaponRack().get(5).getOwned()){System.out.println("(5)Charge Saber[100 Gold]");}
                            System.out.println("(0)Go back");
                            String c= input.next();
                            if(!c.equals("0")){
                                cont=printWeapons(c);
                            }
                            else if(c.equals("0")){
                                cont=false;
                            }
                        }
                    }
                }
                //Player
                else if(a.equals("4")){
                    playerOptions();
                }
                else if(a.equals("5")){
                    if(dungeon==1){System.out.println("(1)Rocky Highlands 1");}
                    if(dungeon==2){System.out.println("(2)Ghastly Ruins 2");}
                    if(dungeon==3){System.out.println("(3)Devil's Lair 3");}
                    if(dungeon==4){System.out.println("(4)The Throne 4");}
                    b= input.next();
                    if(b.equals("1")&&dungeon==1){
                        if(!dungeon(1,4)){
                            playerLost=true;break; 
                        }
                        else{
                            infight=false;
                        }
                    }
                    else if(b.equals("2")&&dungeon==2){
                        if(!dungeon(2,5)){
                            playerLost=true;break;   
                        }
                        else{
                            infight=false;
                        }
                    }
                    else if(b.equals("3")&&dungeon==3){
                        if(!dungeon(3,5)){
                            playerLost=true;break;
                        }
                        else{
                            infight=false;
                        }
                    }
                    else if(b.equals("4")&&dungeon==4){
                        if(!dungeon(4,6)){
                            playerLost=true;break;    
                        }
                        else{
                            infight=false;
                        }
                    }
                }
            }
            System.out.println("");
            System.out.println("");
        }
    }

    /**
     * A class that creates a int[] or dungeon for a player based on dungeon type and size (Basically hardcoded dungeons)
     * Type 1: Shop in the middle. Golem as boss.
     * Type 2:
     */
    public boolean dungeon(int type, int size) throws Exception{
        Random RN=new Random();
        Scanner input = new Scanner(System.in);
        dung= new int[size];
        if(RN.nextInt(100)<=20){
            e.slaveTalk();
        }
        for(int i=0;i<dung.length;i++){
            if(type==1){
                if(i==dung.length/2){
                    dung[i]=777;
                }
                else if(i==dung.length-1){
                    dung[i]=12;
                }
                else{
                    dung[i]=RN.nextInt(4)+1;
                }
            }
            else if(type==2){
                if(i==dung.length/2){
                    dung[i]=777;
                }
                else if(i==dung.length-1){
                    dung[i]=101;
                }
                else if(i<2){
                    dung[i]=RN.nextInt(4)+1;
                }
                else{
                    dung[i]=RN.nextInt(4)+11;
                }
            }
            else if(type==3){
                if(i==dung.length/2){
                    dung[i]=777;
                }
                else if(i==dung.length-1){
                    dung[i]=102;
                }
                else if(i<1){
                    dung[i]=RN.nextInt(4)+1;
                }
                else{
                    dung[i]=RN.nextInt(4)+11;
                }
            }
            else if(type==4){
                if(i==dung.length/2){
                    dung[i]=777;
                }
                else if(i==dung.length-1){
                    dung[i]=9999;
                }
                else{
                    dung[i]=RN.nextInt(4)+11;
                }
            }
        }
        int pos=0;
        boolean dungeonWon=false;
        while(!playerLost||!dungeonWon){
            int current=dung[pos];
            dung[pos]=0;
            if(pos>0){
                dung[pos-1]=69;
            }
            if(current==777){
                println("You find a shop in the dungeon",20);
                e.printShop();
                boolean shopping=true;
                while(shopping){
                    printDungeon(dung);
                    System.out.println("Gold:"+gold+"");
                    System.out.println("(1)Rignus Shop");
                    System.out.print("(2)Player");
                    if(player.getSP()>0){
                        System.out.println("{Skill Points Unused!}");
                    }
                    else{
                        System.out.println("");
                    }
                    System.out.println("(0)Continue Dungeon");
                    String a= input.next();
                    if(a.equals("1")){
                        System.out.println("Rignus: Need a thing or two? I think I got what you want.");
                        System.out.println("(1)Health Potion{Heals 20+player's heal}[30 gold]|Max:5|");
                        System.out.println("(2)Mana Flask{Regenerates 30% of player's missing mana}[25 gold]|Max:5|");
                        System.out.println("(3)Fireblast Potion{Deals 10% of enemy's current health over three turns}[30 Gold]");
                        System.out.println("(4)Rignus's Good stuff{Grants immunity of damage for one turn}[30 Gold]");
                        String b= input.next();
                        if(b.equals("1")&&canBuy(30)&&healthPot<5){
                            println("You bought a Health Potion",20);
                            healthPot++;
                        }
                        else if(b.equals("2")&&canBuy(25)&&manaPot<5){
                            println("You bought a Mana Flask",20);
                            manaPot++;
                        }
                        else if(b.equals("3")&&canBuy(30)){
                            println("You bought a FireBlast Potion",20);
                            firePot++;
                        }
                        else if(b.equals("4")&&canBuy(30)){
                            println("You bought a RGS Potion",20);
                            goodPot++;
                        }
                    }
                    else if(a.equals("2")){
                        playerOptions();
                    }
                    else if(a.equals("0")){
                        shopping=false;
                    }
                }
                pos++;
                current=dung[pos];
                dung[pos]=0;
            }
            else{
                printDungeon(dung);
            }
            infight=true;
            fight(current);
            if(player.getHealth()<=0){
                return false;
            }
            else if(pos==dung.length-1){
                println("You return to the city.",30);
                this.dungeon++;//Increases what dungeon you are on
                return true;
            }
            else if(pos!=dung.length-1&&dung[pos+1]!=777&&!playerLost){
                playerOptions();
            }
            pos++;
        }
        return false;
    }

    public void printDungeon(int[] dungeon) throws Exception{
        System.out.println("Dungeon Position");
        for(int i=0;i<dungeon.length;i++){
            print("[",10);
            if(dungeon[i]==0){
                print("X",10);
            }
            else if(dungeon[i]==69){
                print("0",10);
            }
            else if(dungeon[i]==777){
                print("S",10);
            }
            else{
                print(" ",10);
            }
            print("]",10);
        }
        println("",10);
    }

    /**
     * //+
     * Fight method is method that puts the player into a fight with a monster with the monster id
     * Ex. id=1 -->Slime
     * The fight is conducted with a while loop. 
     * MECHANICS
     * 1.Attack- When the player attacks the monster. Able to crit and reduced by the monster's defense.
     * 2.Guard[G]-Increased stat defense + half of the player's level for a few turns
     * 3.Rest- Regenerates Health equal to the player's stats and regenerates mana equal to the player's mana regen.
     * 4.Confuse[Cf]- Random action for the next turn. Only player can be affected by this.
     * 5.Curse[C]- Player will be unable to regenerate mana
     * 6.Dodge- Chance the monster can evade player's attack.
     * 7.Stun- Renders monster unable to do anything for a turn.
     * 8.DOT[D]- Damage over time.
     * 9.Action Cancel- Unable to use a certain basic action. Only player affected.
     */
    public void fight(int id) throws Exception{
        Scanner input= new Scanner(System.in);
        Random RN= new Random();
        //--------MONSTER CREATION PHASE---------//
        initializeMonster(id);
        monster.printStats();
        //----------------------------------------//

        //--------------FIGHT PHASE--------------//
        isGuarding=false; misGuarding=false;
        guardTimer=0; mguardTimer=0;
        sRevived=false;
        deathPrevention=false;
        removeEffects(); dodgeTimer=0; dodge=false;
        mremoveEffects();
        wrathStacks=0;
        overflow=false;manaSurge=false;split=false;
        dodgeChance=0;
        timer=999;//Boss timer
        monster.setBurned(false);
        cursed=false; curseTimer=0;
        warCryCount=0;
        turnTimer = 0;
        player.setMana(startingMana);// can change for items that give extra starting mana
        while(infight==true){
            String a="1";
            //-----------DEBUG STATEMENTS-----------//

            //---------------------------------------//

            //-----------------------------PLAYER TURN-------------------------//
            choosen=false;
            int startingHealth=player.getHealth();
            while(!choosen){// while loop to loop the action menu until the player made a choice.
                healthbar(); //<--prints hp bars of monster and player

                printOptions();
                System.out.print("Action:");
                a = input.next();

                if((a.equals("1")||a.equals("2")||a.equals("3"))&&confused){ //confused mechanic
                    a=(RN.nextInt(3)+1)+"";
                }       

                //Exit CheatCode
                if(a.equals("p")){
                    player.setHealth(0);
                }
                //------------//

                if(a.equals("4")){
                    player.printStats();// Prints the player's stats
                    if(player.hatchet()){
                        System.out.print(" "+multiplier+" ");
                    }
                    if(player.cowl()){
                        System.out.print("Enemy's Defense:"+monster.getHealth()+" ");
                    }
                    System.out.println("");

                    printAbilities();//<--Since there is alot of abilities. Its better to be in a seperate method.
                    System.out.print("Action:");
                    String b=input.next();

                    choosen=useAbility(b);//<--Uses specified String to choose ability to use returns boolean to decide if that used up turn
                }
                else if(a.equals("5")&&boosting&&monster.getId()==1001)
                {
                    Random gen = new Random();
                    if(gen.nextInt(100)+1 <= 25)
                    {
                        println("The Wraith's pylon defends itself and zaps you!",35);
                        player.setHealth(player.getHealth()-2);
                        choosen = true;
                    }
                    else
                    {
                        removeBoosting();
                        boosting(0,0);
                        println("You destroyed the wraith's pylon! It seems that the wraith is powering down.",35);
                        choosen = true;
                    }

                }
                else{
                    //For conflicts with confuse and action cancelling. Also checks if the player can use an action or not.
                    if(!confused){
                        if(a.equals("1")&&attackCancelled||a.equals("2")&&guardCancelled||a.equals("3")&&restCancelled){
                            println("You are unable to use that action",15);
                        }
                        else{
                            choosen=true;
                        }
                    }
                    else{
                        choosen=true;
                    }

                }
            }

            //----------------------------------------------//
            //if choices are of the basic 3

            if(a.equals("1")){
                if(player.staff()&&split){
                    atksLeft=splitAmount;
                }
                if(mDefStance){
                    println(monster.getName()+" deflects you attack and your weapon",20);
                    cancelAttack(2);
                    mDefStance=false;
                }
                else{
                    attack();
                }
                if(player.dagger()){wrathStacks++;}
            }
            else if(a.equals("2"))
            {
                if((monster.getId()==9999||monster.getId()==13)&&monster.getAID()==2){monster.setAID(3);}
                if(player.cloak()){
                    println("You conceal yourself...",10);
                    dodge(4,30+player.getDefense());
                }
                else{
                    guard();
                }
                if(player.dagger()){wrathStacks++;}
            }
            else if(a.equals("3")){
                rest();
                if(player.dagger()){wrathStacks++;}
                if(player.cowl()){
                    if(trueBurst<10){trueBurst++;}
                }
            }

            //--------------------------------------------------------------//

            //------------STATUS EFFECTS------------//
            //Any player status timer must be one more than their intended duration due to the timer being
            //reduced by one before the monster phase occurs.
            if(attacked&&player.cowl()){
                trueBurst=0;
                attacked=false;
            }
            player.burnCheck();
            sashaPass();
            livingArmorHeal();//Living armor heal. An armor upgrade.
            monster.buff();
            skeletonCheck();
            dodgeCheck();
            stunCheck();
            if(!cursed){
                player.regenMana(); //regenerates mana every turn that happens
            }
            if(player.staff()){
                overFlowCheck();
            }
            if(player.dagger()){
                daggerCheck();
            }

            guardCheck();//Checks whether the player/monster is guarding. Prevents extra guarding
            confuseCheck();//Checks if the player is confused or not.
            curseCheck();//Checks if the player is curse.
            defStanceCheck();//Sword's/Sword&Board ability. 
            hatchetPass();//The passive buff from the hatchet weapon.
            mDOTCheck();//In case monster takes any extra damage not from attacks. 
            boostingCheck();//In the casa boosting is active for a monster;
            attackDownCheck();
            attackUpCheck();
            defenseUpCheck();
            cancelAttackCheck();
            cancelGuardCheck();
            cancelRestCheck();
            player.immuneCheck();
            goonCheck();
            lastTurnHp=player.getHealth();
            turnTimer++;
            if(deathCheck()){// HAS TO BE IN THE BOTTOM OF STATUS EFFECTS
                infight=false;
                break;
            }
            DOTCheck();//In case player is taking any extra damage not from attacks.
            //--------------------------------------//

            //-----------MONSTER TURN---------------//
            monster.immuneCheck();
            if(id>=102&&id<=105){
                witchPass(); //<--for witch transformations
            }
            if(!stun){
                //---MONSTER A.I.-----//
                int b=RN.nextInt(100)+1;
                if(mguardTimer>0){
                    b=100;
                }
                //--------------------//
                if(mCast(id)){//Monster attempts to cast an Ability. If not, it will use a basic action
                }
                else{
                    if(b>30){
                        mattack();
                    }
                    else if(b<=30){
                        mguard();
                    }
                }
                //-------------------------------------//
            }
            else{
                println(monster.getName()+" was stunned and did not do anything",30);
            }

            if(timer==10){
                println("Defeat "+monster.getName()+" under "+timer+" turns before "+monster.getName()+" can recover!",15);
            }

            if(deathCheck()){//checks if the specials killed the player
                infight=false;
                break;
            }

            //------------------------//
        }
        //------------------------------------------//

        //----------------AFTER FIGHT PHASE------------------//
        player.updateStats();//resets any temporary buffed damage in battle.
        if(player.getHealth()<=0&&monster.getHealth()<=0||monster.getHealth()<=0){
            if(player.getHealth()<=0){
                player.setHealth(1);
            }
            println("You are victorious.",50);
            int goldfound=0;
            if(player.tunic()){
                goldfound=(int)(goldFound(id)*1.3);
                gold= gold+goldfound;
            }
            else{
                goldfound=goldFound(id);
                gold= gold+goldfound;
            }
            if(mimi&&RN.nextInt(100)<=50){
                e.mimiTalk1();
                goldfound=(int)((double)goldfound*1.2);
            }
            else if(mimi){
                e.mimiTalk2();
            }
            levelCheck(player.getExp());
            println("You found "+goldfound+" gold! You now have "+gold+" gold.",33);

            if(mary){
                e.maryTalk();
                int heal=(int)((double)player.getMaxHealth()*.15);
                player.setHealth(player.getHealth()+heal);
                println("You regenerate "+heal+" health",20);
            }

        }
        else if(id==-1){
            playerLost=true;
        }
        else{
            print("You have died to "+monster.getName()+"", 50);
            println("...", 150);
            println("Game Over",50);
        }
        //---------------------------------------------------//

    }

    //----------------------------MECHANICIS---------------------------------//

    /**
     * Decrements the guardTimer(how long the user has guard up) and decides when the player/monster loses their guarding defense buff.
     */
    public void guardCheck(){
        if(guardTimer>0&&isGuarding==true){
            guardTimer--;
        }
        if(mguardTimer>0&&misGuarding==true){
            mguardTimer--;
        }
        if(guardTimer==0&&isGuarding==true)
        {
            isGuarding=false;
            player.setDefense(player.getDefense()-guardAmount-(player.getLevel()/2));
        }
        if(mguardTimer==0&&misGuarding==true)
        {
            misGuarding=false;
            monster.setDefense(monster.getDefense()-5);
        }
    }

    /**
     * Sets guarding to true.
     */
    public void guard() throws Exception{
        isGuarding=true;
        println("You guard",20);
        if(guardTimer==0){
            player.setDefense(player.getDefense()+guardAmount+(player.getLevel()/2)); // 5 is placeholder
        }
        guardTimer = 4;
    }

    /**
     * Sets guard to true.
     */
    public void mguard() throws Exception{
        //Wraiths does not guard, but it can miss
        if(monster.getId()==1001)
        {
            println("The Wraith's beam narrowly misses you.",20);
            return;
        }

        misGuarding=true;
        println(monster.getName()+" guards",20);
        if(mguardTimer==0){
            monster.setDefense(monster.getDefense()+5); // 5 is placeholder
        }
        mguardTimer = 3;
    }

    public void confuseCheck(){
        if(confuseTimer>0){
            confuseTimer--;
        }
        if(confuseTimer==0){
            confused=false;
        }
    }

    /**
     * Method that sets confused to true. Used for making the player make a random action.
     */
    public void confuse(int amount){
        confused=true;
        confuseTimer=amount;
    }

    public void curseCheck()
    {
        if(curseTimer>0)
        {
            curseTimer--;
        }
        if(curseTimer==0)
        {
            cursed = false;
        }
    }

    /**
     * Sets curse to true. Used to inhibit mana regen for a set amount of turns
     */
    public void curse(int amount)
    {
        cursed = true;
        curseTimer = amount;
    }

    /**
     * DOT is any damage taken over time 
     * i.e. poison, bleeding, etc....
     */
    public void DOTCheck()
    {
        if(DOTTimer>0)
        {
            DOTTimer--;
            player.setHealth(player.getHealth() - DOTScale);
        }
        if(DOTTimer==0)
        {
            DOT = false;
        }
    }

    /**
     * amount -> effect duration;
     * scale -> how much damage per turn;
     */
    public void DOT(int amount, int scale)
    {
        DOT = true;
        DOTTimer = amount;
        DOTScale = scale;
    }

    public void mDOTCheck()
    {
        if(mDOTTimer>0)
        {
            mDOTTimer--;
            monster.setHealth(monster.getHealth() - mDOTScale);
        }
        if(mDOTTimer==0)
        {
            mDOT = false;
        }
    }

    public void mDOT(int amount, int scale)
    {
        mDOT = true;
        mDOTTimer = amount;
        mDOTScale = scale;
    }

    public void stunCheck()
    {
        if(stunTimer>0)
        {
            stunTimer--;
        }
        if(stunTimer==0)
        {
            stun = false;
        }
    }

    /**
     * A method that sets stun to true. Meant for cancelling monster turn for a set amount of turns
     */
    public void stun(int amount)
    {
        stun = true;
        stunTimer = amount;
    }

    public void dodgeCheck(){
        if(dodgeTimer>0){
            dodgeTimer--;
        }
        else{
            dodge=false;
            dodgeChance=0;
        }
    }

    public void dodge(int time, int chance){
        dodge=true;
        dodgeTimer=time;
        dodgeChance=chance;
    }

    public void skeletonCheck() throws Exception{
        if(monster.getId()==5&&monster.getHealth()<=0&&!sRevived){
            sRevived=true;
            monster.setHealth((int)((double)monster.getMaxHealth()*.5));
            System.out.println(monster.getName()+" rebuilds itself!");
            e.printAngrySkeleton();
        }
    }

    public void boostingCheck()
    {
        if(boosting = true&&boostingTimer > 0)
        {
            monster.setDamage(monster.getDamage() + boostingScale);
            monster.setDefense(monster.getDefense() + boostingScale);
            boostingTimes++;
            boostingTimer--;
        }
    }

    /**
     * Buff for monsters which build over time depending on the scaling. Amount is how long the buff
     * is applied;
     */
    public void boosting(int amount, int scale)
    {
        boostingScale = scale;
        boosting = true;
        boostingTimer = amount;
    }

    /**
     * In the case that a monster loses it's increased stats, call this.
     */
    public void removeBoosting()
    {
        monster.setDamage(monster.getDamage()-boostingTimes*boostingScale);
        monster.setDefense(monster.getDefense()-boostingTimes*boostingScale);
        boostingTimes = 0;
    }

    public void cancelAttack(int amount){
        attackCancelled=true;
        attackCancelTimer=amount;
    }

    public void cancelAttackCheck(){
        if(attackCancelTimer>0){
            attackCancelTimer--;
        }
        if(attackCancelTimer==0){
            attackCancelled=false;
        }
    }

    public void cancelGuard(int amount){
        guardCancelled=true;
        guardCancelTimer=amount;
    }

    public void cancelGuardCheck(){
        if(guardCancelTimer>0){
            guardCancelTimer--;
        }
        if(guardCancelTimer==0){
            guardCancelled=false;
        }
    }

    public void cancelRest(int amount){
        restCancelled=true;
        restCancelTimer=amount;
    }

    public void cancelRestCheck(){
        if(restCancelTimer>0){
            restCancelTimer--;
        }
        if(restCancelTimer==0){
            restCancelled=false;
        }
    }

    public void attackDown(int amount, int scale)
    {
        atkDown=true;
        atkDownTimer=amount;
        atkDownScale=scale;
        player.setDamage(player.getDamage()-scale);
    }

    public void attackDownCheck()
    {
        atkDownTimer=atkDownTimer-1;
        if(atkDownTimer==0&&atkDown==true)
        {
            atkDown=false;
            player.setDamage(player.getDamage()+atkDownScale);
        }
    }

    public void attackUp(int amount, int scale)
    {
        atkUp=true;
        atkUpTimer=amount;
        atkUpScale=scale;
        player.setDamage(player.getDamage()+scale);
    }

    public void attackUpCheck()
    {
        atkUpTimer--;
        if(atkUpTimer==0&&atkUp==true)
        {
            atkUp=false;
            player.setDamage(player.getDamage()-atkUpScale);
        }
    }

    public void defenseUp(int amount, int scale)
    {
        defUp=true;
        defUpTimer=amount;
        defUpScale=scale;
        player.setDefense(player.getDefense()+scale);
    }

    public void defenseUpCheck()
    {
        defUpTimer--;
        if(defUpTimer==0&&defUp==true)
        {
            defUp=false;
            player.setDefense(player.getDefense()-defUpScale);
        }
    }

    public void goonCheck() throws Exception{
        if(bossTimer&&monster.getHealth()<=0&&monster.getId()==9001){
            boss.setDefense(boss.getDefense()-1);
            println("You defeated "+monster.getName()+" before "+bossName+" was able to recover!{-1 defense}",25);
            println(""+bossName+": Incompetent underling...",20);
            monster= new Monster(100,100,0,0,0,0,"",0);
            monster = boss;
            monster.setAID(0);
            bossTimer=false;
        }
        if(bossTimer&&monster.getHealth()<=0&&monster.getId()==9002){
            boss.setDamage(boss.getDamage()-1);
            println("You defeated "+monster.getName()+" before "+bossName+" was able to recover!{-1 attack}",25);
            println(monster.getName()+": What a useless bonobo...",20);
            monster= new Monster(100,100,0,0,0,0,"",0);
            monster = boss;
            monster.setAID(0);
            bossTimer=false;
        }
        if(!bossTimer&&(monster.getId()==9001||monster.getId()==9002)){
            println(""+bossName+" was able to recover while you were distracted! {Healed 30 health}",25);
            println(""+bossName+": Well done, go and rest my underling.",20);
            monster = new Monster(100,100,0,0,0,0,"",0);
            monster = boss;
            monster.setAID(0);
            monster.setHealth(monster.getHealth()+30);
            bossTimer=false;
        }
        if(bossTimer&&timer>0){
            timer--;
            if(timer==0){
                bossTimer=false;
            }
        }
    }

    public void timer(int amount){
        timer=amount;
        bossTimer=true;
    }

    /**
     * returns true if the player is dead or <=0 health
     * else returns false
     */
    public boolean deathCheck(){
        if(deathPrevention == true&&player.getHealth()<=0)
        {
            deathPrevention = false;
            player.setHealth(1);
            return false;
        }
        if((player.getHealth()<=0&&monster.getHealth()<=0)||monster.getHealth()<=0){
            return true;
        }
        if(player.getHealth()<=0){
            return true;
        }
        return false;
    }
    //--------------------------------------------------------------------------//

    //-------------------------ARMOR/WEAPON EFFECTS-----------------------------//
    public void livingArmorHeal(){
        player.setHealth(player.getHealth()+livingArmor);
    }

    /**
     * Call for Fireball animation and effect. FIREBALL!!!
     */
    public void fireBlast() throws Exception{
        println("FIREBLAST!!",30);
        print("--",100);print("-\"",80);print("-_/",50);println("<*+#",40);//animation
        int damage=(int)(((double).1*monster.getHealth())/3);
        println(monster.getName()+" took "+damage+" this turn",30);
        mDOT(3,damage);
        useMana(40);
    }

    /**
     * Sword and S&B ability. For the purpose of adding extra damage equal to the player's defense.
     * (S&B ability 1) & (Sword ability 1)
     */
    public void defStance() throws Exception{
        defStance=true;
        println("You go into a Defensive Stance..",40);
        defStanceTimer=2;
    }

    public void defStanceCheck()
    {
        if(defStanceTimer>0)
        {
            defStanceTimer--;
        }
        if(defStanceTimer==0)
        {
            defStance = false;
        }
    }

    /**
     * S&B ability that stuns the enemy. 30% if not already stunned and 100% if already stunned.
     * (S&B ability 2)
     */
    public void shieldBash() throws Exception
    {
        Random gen = new Random();
        int damage =player.getDamage();
        if(stunTimer>0)
        {
            println("You stunned "+monster.getName()+" again!",30);
            shieldBash=true;
            attack();
            stun(2);
        }
        else if(gen.nextInt(100)+1 <= 40)
        {
            println("You stunned "+monster.getName()+"!",30);
            println("You dealt "+damage+" damage to "+monster.getName()+"",20);
            monster.setHealth(monster.getHealth()-damage);
            stun(2);
        }
    }

    /**
     * Hatchet's rage passive. Increased damage the lower the player is.
     */
    public void hatchetPass()
    {
        multiplier=(int)(player.getNormDamage()+(((1.0-((double)player.getHealth()/player.getMaxHealth()))*(double)player.getNormDamage())/2));
        if(player.hatchet())
        {
            player.setDamage(multiplier);
        }
    }

    /**
     * Call for Wide Slash animation and effect. (Hatchet Ability 1)
     * 
     */
    public void wildSlash() throws Exception
    {
        println("WILD SLASH!",65);
        wildSlash=true;
        attack();
        Random gen = new Random();
        if(gen.nextInt(100)+1<player.getCrit())
        {
            mDOT(3,player.getDamage()/2);
            println(monster.getName()+" is also bleeding!!!",40);
        }

    }

    /**
     * Calls for war cry ability. Exchanges 40 mana for 20 crit chance with a 1% chance for additional
     * 10 crit; (Hatchet ability 2)
     */
    public void warCry() throws Exception
    {
        warCryCount++;
        println("Grand strength of the gods, come to me!!!", 40);
        player.setCrit(player.getCrit()+20);
        Random gen= new Random();
        if(gen.nextInt(100)+1<2)
        {
            println("The gods have chosen to favor you today.", 40);
            player.setCrit(player.getCrit()+10);
        }
    }

    public void overFlowCheck(){
        if(player.getMana()>=player.getMaxMana()){
            overflow=true;
        }
        if(split){
            splitTimer--;
        }
        if(splitTimer<=0){
            split=false;
            splitAmount=0;
        }
    }

    /**
     * Calls staff ability 1 which does 20% of the player's mana as damage
     */
    public void manaSurge() throws Exception
    {
        if(overflow){
            print("EXPL",50);print("OOOOO",30);println("SION!!!",10);
            int damage= player.getMana();
            player.setMana(startingMana);
            monster.setHealth(monster.getHealth()-damage);
            println("You dealt "+damage+" damage to "+monster.getName()+"",15);
            overflow=false;
        }
        else{
            println("You empower your next attack...",20);
            amount=player.getMana()/3;
            player.setMana(player.getMana()-amount);
            manaSurge=true;
        }
    }

    /**
     * Casts staff ability 2 which redirects damage taken for a turn toward the enemy
     */
    public void split() throws Exception
    {
        if(overflow){
            println("You create two clones to assist you",20);
            split=true;
            splitAmount=2;
            player.setMana(startingMana);
            overflow=false;
        }
        else{
            println("You create a clone to assist you",20);
            split=true;
            splitAmount=1;
            player.setMana(player.getMana()-60);
        }
        splitTimer=3;
    }

    public void daggerCheck() throws Exception{
        player.setMana(player.getMana()-2);
        player.setHealth(player.getHealth()-2-(wrathStacks/2));
    }

    public void fPact() throws Exception{
        int heal= player.getHeal()+25;
        player.setHealth(player.getHealth()+heal);
        println("You curse yourself and heal for "+heal+" health",15);
        curse(3);
    }

    public void wrath() throws Exception{
        print("FACE",50);print(" ",100);print("MY",50);print(" ",100);println("WRATH!!!",30);
        for(int i=0;i<wrathStacks;i++){
            wrathPass=true;
            attack();
            if(i==4){
                println("DIEEE",20);
            }
            if(i==7){
                println("MEET YOUR DOOM!!!",10);
            }
        }
        curse(3);
        wrathStacks=0;
    }

    public void removeEffects(){
        DOT=false; DOTTimer=0;
        confused=false; confuseTimer=0;
        cursed=false; curseTimer=0;   
        atkDown=false; atkDownTimer=0; atkDownScale=0;
        attackCancelled=false; attackCancelTimer=0;
        guardCancelled=false; guardCancelTimer=0;
        restCancelled=false; restCancelTimer=0;
        player.setBurned(false);player.burn(0);
    }

    public void mremoveEffects(){
        stun=false; stunTimer=0;
        mDOT=false; mDOTTimer=0;
        monster.setBurned(false);monster.burn(0);
    }
    //-------------------------------------------------------------------------//

    //-----------------------------ABILITIES-------------------------------------//
    /**
     * //+
     * Runs through the attack function of the player. Damage is calculated from the damage given by
     * player.damage() and is reduced by monster's defense. No damage will be dealt if defense is too high.
     * Also prints how much damage is done.
     */
    public void attack() throws Exception{
        Random gen = new Random();
        Random RN= new Random();
        int atk = player.damage();
        int def = monster.getDefense();
        int rn =RN.nextInt(100);
        if(player.cowl()){
            if(trueBurst>def){
                atk=(int)((double)atk*1.5);
            }
            def-=trueBurst;
            attacked=true;
        }
        if(wildSlash){
            atk=atk*2;
            wildSlash=false;
        }
        if(shieldBash){
            atk=atk*2;
            shieldBash=false;
        }
        if(rn<=player.getCrit()){
            if(player.cloak()&&dodge){  
                atk=atk*3;
                println("DOUBLE CRITICAL STRIKE!!!", 20);
            }
            else{
                atk=atk*2;
                print("CRITICAL", 40);
                println(" STRIKE!!!", 20);
            }
        }
        if(player.chestpiece()){
            atk+=(player.getHeal());
        }
        if(manaSurge&&atksLeft==0){
            atk+=amount; amount=0;
            manaSurge=false;
        }
        else if(manaSurge&&atksLeft>0){
            atk+=amount;
        }
        if(defStance&&isGuarding){//Sword's defStance ability.
            atk+=5;
        }
        if(player.dagger()&&wrathPass==true){
            wrathPass=false;
            atk=atk/2;
        }

        int inflicted = atk - def;

        if(inflicted <= 0)
        {
            inflicted = 0;
            println("You are too weak to hurt this monster!",15);
        }
        else
        {
            if(monster.getDodge()>=gen.nextInt(100)+1)
            {
                println(monster.getName()+" swiftly dodged your attack!!!",15);
            }
            else
            {
                monster.setHealth(monster.getHealth()-inflicted);
                damageDealt+=inflicted;
                print("You have inflicted " + inflicted + " points of damage to " +monster.getName(),7);
                if(player.robe()){
                    int amount=inflicted/5;
                    player.setMana(player.getMana()+(amount));
                    println(" "+amount+" Mana",5);
                }
                else{
                    System.out.println();
                }
            }
        }

        if(split&&atksLeft>0){
            atksLeft--;
            attack();
        }

    }

    /**
     * //+
     * Monster attacks like player only use its raw attack instead of the player exclusive damage().
     */
    public void mattack() throws Exception{
        Random RN= new Random();
        int atk = monster.getDamage();
        int def = player.getDefense();
        if(player.swordandboard()&&isGuarding){
            if(RN.nextInt(100)+1<=5){
                println("You reflect half "+monster.getName()+"'s attack!",25);
                atk=atk/2;
                monster.setHealth(monster.getHealth()-(atk/2));
            }
            if(RN.nextInt(100)+1<=5){
                println(monster.getName()+" was stunned after hitting you!",25);
                stun(1);
            }
        }
        int inflicted = atk-def;

        if(inflicted<=0)
        {
            println("*clink*",20);
        }
        else
        {
            if(RN.nextInt(100)+1>=dodgeChance){
                player.setHealth(player.getHealth()-inflicted);
                println(monster.getName() + " attacks dealing " + inflicted + " dmg",7);
            }
            else{
                println("You dodge "+monster.getName()+"'s attack!",10);
            }
        }

        //Wraith has chance of inflicting burn upon attack
        if(monster.getId()==1001)
        {
            if(RN.nextInt(100)+1<=5)
            {
                println("You get burned from attacking the Wraith.",15);
                DOT(3,3);
            }

        }
        if(monster.getId()==1001&&monster.getBuff())//in frenzy mode, wraith has 40% to attack twice.
        {
            if(inflicted<=0)
            {
                println("*clink*",20);
            }
            else
            {
                player.setHealth(player.getHealth()-inflicted);
                println(monster.getName() + " attacks again dealing " + inflicted + " dmg",7);
            }
        }
    }

    /**
     * The third basic action for a player. Regenerates health and mana equal to their respective stats.
     */
    public void rest() throws Exception{
        player.heal();
        if(!cursed){// Yes, even curse will prevent this.
            int regen= player.getManaRegen();
        }
        System.out.println();
    }

    public void initializeMonster(int id) throws Exception{
        if(id==1){
            monster= new Monster(140,140,6,2,0,0,"The Slime",1);
            System.out.println("You find a "+monster.getName().substring(3)+"");
            e.printSlime();//Animation
        }
        else if(id==2){
            monster= new Monster(150,150,4,3,0,7,"The Goblin",2);
            System.out.println("You encounter a "+monster.getName().substring(3)+"");
            e.printGoblin();
        }
        else if(id==3){
            monster= new Monster(120,120,5,3,0,0,"The Skeleton",3);
            System.out.println("You find a "+monster.getName().substring(3)+"");
            e.printSkeleton();
        }
        else if(id==4){
            monster=new Monster(130,130,4,3,2,10,"The Wolf",4);
            System.out.println("You encounter a "+monster.getName().substring(3)+"");
            e.printWolf();
        }
        else if(id==11){
            monster= new Monster(200,200,4,2,20,1,"The Ghoul",11);
            System.out.println("You find a lone "+monster.getName().substring(3)+"");
            e.printGhoul();
        }
        else if(id==12)
        {
            monster = new Monster(200,200,6,10,0,0,"The Golem",12);
            System.out.println("A stiff giant emerges from boulder nearby, best be ready to fight!");
            e.printGolem();
        }
        else if(id==13)
        {
            monster = new Monster(200,200,8,4,0,1,"The Orc",13);
            System.out.println("You find a single "+monster.getName().substring(3)+".");
            e.printOrc();
        }
        else if(id==14)
        {
            monster = new Monster(200,200,6,3,0,0,"The Giant Crab",14);
            System.out.println("You stubble upon a wandering "+monster.getName().substring(3)+".");
            e.printCrab();
        }
        else if(id==101){
            monster= new Monster(300,300,10,5,5,5,"The Knight",101);
            System.out.println("You find a lone "+monster.getName().substring(3)+"");
            e.printKnight();
        }
        else if(id==102)
        {
            Random gen = new Random();
            int temp = gen.nextInt(4)+1;
            if(temp ==1) 
            {
                monster = new Monster(300,300,10,4,0,7,"The Summer Witch",102);
            }
            else if(temp==2) 
            {
                monster = new Monster(300,300,5,7,0,5,"The Autumn Witch",103);
            }
            else if(temp==3)
            {
                monster = new Monster(300,300,5,4,0,10,"The Winter Witch",104);
            }
            else
            {
                monster = new Monster(300,300,5,4,20,5,"The Spring Witch",105);
            }
            println("You stumble upon a wandering "+monster.getName().substring(3)+" nearby.",20);
            e.printWitch();
        }
        else if(id==1001)
        {
            monster= new Monster(300,300,7,3,10,0,"The Wraith",1001);
            System.out.println("You stumble across a stray "+monster.getName().substring(3)+" in the ravaged plane.");
        }
        else if(id==9999){
            boss= new Monster(500,500,15,5,10,5,"King Joe",9999);
            monster=boss;
            println("King Joe says something cool!",50);
        }
        else if(id==-1){
            infight=false;
        }
    }

    /**
     * mCast is a method that attempts to get an monster to cast a special ability.
     * If it is successful in doing so, it will return true as well as casting it.
     * This is used in order to check if the monster will use an ability or not.
     * Since monsters shouldn't use a basic action and a special at teh same time.
     * @param id The id of the monster.
     */
    public boolean mCast(int id) throws Exception{
        Random RN= new Random();
        //-----------MONSTER SPECIALS----------//
        if(id==1&&monster.getAID()==0&&monster.getHealth()<=70&&RN.nextInt(100)<=10){//Slime
            println(monster.getName()+" confuses you!",40);
            confuse(3);
            monster.setAID(monster.getAID()+1);
            return true;
        }
        if(id==1&&monster.getAID()==1&&RN.nextInt(100)<=20){//Slime
            println(monster.getName()+" jumps on you and drains your mana!",30);
            useMana(20);
            monster.setAID(monster.getAID()+1);
            e.printAngrySlime();
            return true;
        }
        if(id==1&&monster.getAID()==2&&RN.nextInt(100)<=20){//Slime
            println(monster.getName()+" jumps on you and curses you {No mana regen}!",30);
            curse(2);
            monster.setAID(0);
            e.printAngrySlime();
            return true;
        }

        if(id==2&&monster.getAID()==0&&(double)monster.getHealth()/monster.getMaxHealth()<=.7&&RN.nextInt(100)<=20){//Goblin
            e.printMadGoblin();
            println(monster.getName()+" stabs you! {You are bleeding}",30);
            mDOT(monster.getDamage()/2,3);
            monster.setAID(RN.nextInt(2));
            return true;
        }
        if(id==2&&monster.getAID()==1&&RN.nextInt(100)<=10){//Goblin
            e.printAngryGoblin();
            println(monster.getName()+" enrages {+4 damage}!",30);
            monster.setDamage(monster.getDamage()+4);
            monster.setAID(RN.nextInt(2));
            return true;
        }

        if(id==3&&monster.getAID()==0&&monster.healthPercentage()<=.9){//Skeleton
            println(monster.getName()+" throws one of his broken bones at you! {You are bleeding}",20);
            DOT(monster.getDamage()/2,3);
            monster.setAID(1);
            return true;
        }
        if(id==3&&monster.getAID()==1&&RN.nextInt(100)<20){//Skeleton
            e.printAngrySkeleton();
            println(monster.getName()+" does a reckless swing!",25);
            int damage=(int)(monster.getDamage()*(1+monster.missingHealthPercentage()));
            player.setHealth(player.getHealth()-damage);
            println("You take "+damage+" damage!",20);
            monster.setAID(0);
            return true;
        }

        if(id==4&&monster.getAID()==0&&RN.nextInt(100)<=20){//Wolf
            e.printAngryWolf();
            println(monster.getName()+" bites off some of your armor!{-1 defense}",20);
            int damage=(int)(monster.getDamage()*1.5);
            println("You take "+damage+" damage.",25);
            player.setHealth(player.getHealth()-damage);
            monster.setAID(1);
            return true;
        }
        if(id==4&&monster.getAID()==1&&RN.nextInt(100)<34){//Wolf
            e.printAngryWolf();
            println(monster.getName()+" howls {+3 damage}!",25);
            monster.setDamage(monster.getDamage()+3);
            monster.setAID(0);
            return true;
        }

        if(id==11&&monster.getAID()==0&&(double)monster.getHealth()/monster.getMaxHealth()<=.9&&RN.nextInt(100)<=20){//Ghoul
            e.printAngryGhoul();
            int damage=(int)((double)monster.missingHealth()*.2);
            println(monster.getName()+" bites you and steals "+damage+" health!",30);
            monster.setHealth(monster.getHealth()+damage);
            player.setHealth(player.getHealth()-damage);
            monster.setAID(RN.nextInt(2)+1);
            return true;
        }
        if(id==11&&monster.getAID()==1&&RN.nextInt(100)<=10){//Ghoul
            e.printAngryGhoul();
            println(monster.getName()+" sratches you and you got cursed!",30);
            mattack();
            curse(2);
            monster.setAID(0);
            return true;
        }
        if(id==11&&monster.getAID()==2&&RN.nextInt(100)<=10){//Ghoul
            println(monster.getName()+" uses rapid recovery!",30);
            monster.setHealth(monster.getHealth()+(int)((double)monster.missingHealth()*0.2)+monster.getHeal());
            monster.setAID(0);
            return true;
        }

        if(id==12&&monster.healthPercentage()<0.75&&monster.getAID()==0)//Golem
        {
            println("Those rocks that you've been knocking off the being seem to allow it to move more easily.",35);
            monster.setDamage(monster.getDamage()+5);
            monster.setDefense(monster.getDefense()/2);
            monster.setAID(1);
            return true;
        }
        if(id==12&&monster.healthPercentage()<0.5&&monster.getAID()==1)//Golem
        {
            e.printAngryGolem();
            println("All the rocks have fallen off its body, now its time for the real fight!",35);
            monster.setDamage(monster.getDamage()+5);
            monster.setDefense(monster.getDefense()/2);
            monster.setAID(2);
            return true;
        }
        if(id==12&&monster.healthPercentage()<0.3)//Golem
        {
            Random gen = new Random();
            if(gen.nextInt(10)+1<=3)
            {
                e.printAngryGolem();
                println("A fiery beam fires from within the being.",35);
                player.setHealth(player.getHealth()+player.getDefense()-monster.getDamage());
            }
        }

        if(id==13&&monster.getAID()==0&&monster.healthPercentage()<=.9&&RN.nextInt(100)<=20){//Orc
            e.printAngryOrc();
            println(monster.getName()+" bashes you with his club and does "+monster.getDamage()+" damage!",20);
            player.setHealth(player.getHealth()-monster.getDamage());
            confuse(2);
            monster.setAID(1);
            return true;
        }
        if(id==13&&monster.getAID()==1&&RN.nextInt(100)<=30){//Orc
            println(monster.getName()+" charges up a large attack...",25);
            monster.setAID(2);
            return true;
        }
        if(id==13&&monster.getAID()==2){//Orc
            e.printAngryOrc();
            println(monster.getName()+" does a heavy swing {Bleeding}!",25);
            int damage= monster.getDamage()*2;
            println("You take "+damage+" damage.",15);
            player.setHealth(player.getHealth()-damage);
            DOT(2,monster.getDamage()/2);
            player.burn(2);
            monster.setAID(0);
            return true;
        }
        if(id==13&&monster.getAID()==3){
            println("You deflected "+monster.getName()+"'s attack{-1 defense}",20);
            monster.setDefense(monster.getDefense()-1);
            monster.setAID(0);
        }

        if(id==14&&monster.getAID()<40&&monster.getAID()%2==0)//Crab
        {
            if(RN.nextInt(100)+1<=25)
            {
                e.printShellCrab();
                println(monster.getName()+" retracts into its shell.",20);
                monster.setDefense(monster.getDefense()+10);
                monster.setAID(monster.getAID()+1);
                return true;
            }
        }
        if(id==14&&monster.getAID()%2!=0)//Crab
        {
            if(RN.nextInt(100)+1>60)
            {
                e.printAngryCrab();
                println(monster.getName()+" pops out from its shell, it seems to be angry...",20);
                monster.setDefense(monster.getDefense()-10);
                monster.setDamage(monster.getDamage()+1);
            }
            else
            {
                println(monster.getName()+" comes out of its shell.",20);
            }
            monster.setAID(monster.getAID()+1);
            return true;
        }

        if(id==101&&monster.getAID()==0&&(turnTimer>=5||monster.healthPercentage()<=.8)&&(!attackCancelled&&!guardCancelled&&!restCancelled)){//Knight
            println(monster.getName()+" curses you!",20);
            curse(3);
            monster.setAID(RN.nextInt(3)+2);
            return true;
        }
        if(id==101&&monster.getAID()==1&&monster.healthPercentage()<=.8&&(!attackCancelled&&!guardCancelled&&!restCancelled)){//Knight
            e.printAngryKnight();
            println(monster.getName()+" knocks you back and confuses you!",20);
            confuse(3);
            monster.setAID(RN.nextInt(3)+2);
            return true;
        }
        if(id==101&&monster.getAID()==2&&!confused&&RN.nextInt(100)<90){//Knight
            println(monster.getName()+" knocks off your weapon!",30);
            cancelAttack(2);
            monster.setAID(RN.nextInt(2));
            return true;
        }
        if(id==101&&monster.getAID()==3&&!confused&&RN.nextInt(100)<90){//Knight
            e.printAngryKnight();
            println(monster.getName()+" smashes your arm! Preventing your guard",30);
            cancelGuard(2);
            monster.setAID(RN.nextInt(2));
            return true;
        }
        if(id==101&&monster.getAID()==4&&!confused&&RN.nextInt(100)<90){//Knight
            println(monster.getName()+" burns you {Unable to heal}!",30);
            cancelRest(2);
            player.burn(2);
            DOT(3,monster.getDamage());
            monster.setAID(RN.nextInt(2));
            return true;
        }

        //Witch
        if(monster.getId()==102&&monster.healthPercentage()<1&&monster.getAID()<3)
        {
            println(monster.getName()+ " displays her searing flames.",25);
            int damage = (int)(monster.getDamage()*1.5-(double)player.getDefense());
            println("The searing flames inflicts " + damage+ "{Unable to heal}",25);
            player.setHealth(player.getHealth()-damage);
            player.burn(2);
            if(RN.nextInt(10)>4)
            {
                println("You escape the flames, but your pants are still on fire!!!{Burning}",25);
                DOT(4,monster.getDamage());
            }
            monster.setAID(monster.getAID()+1);
            if(monster.getAID()==3)
            {
                println("Her flames now seem to change...", 25);
                println("Her flames now burn of blue hue.",25);
            }
            return true;
        }
        if(monster.getId()==102&&monster.healthPercentage()<.5&&monster.getAID()<6&&monster.getAID()>3)
        {
            e.printAngryWitch();
            println(monster.getName()+" forms a blue fireball and fires it",25);
            int damage = (int)(monster.getDamage()*1.5-(double)player.getDefense());
            player.burn(1);
            println("The fireball chases you and inflicts "+damage+"{Unable to heal}",25);
            println("Her flames burn even brighter!",25);
            monster.setDamage(monster.getDamage()+2);
            return true;
        }
        if(monster.getId()==103&&monster.healthPercentage()<1&&monster.getAID()==0)
        {
            println("My leaves fall, covering the final blossoms...",25);
            monster.setDefense(monster.getDefense()+2);
            mattack();
            monster.setAID(1);
            return true;
        }
        if(monster.getId()==103&&monster.getAID()==1)
        {
            println("Listen to their anguish as loud as my own...",25);
            mattack();
            monster.setAID(2);
            return true;
        }
        if(monster.getId()==103&&monster.getAID()==2)
        {
            if(RN.nextInt(100)+1>50)
            {
                e.printAngryWitch();
                println("Wilt like they do for the greater evil!",25);
                println("CORRUPT!!!",25);
                int inflicted = monster.getDamage()*2-player.getDefense();
                player.setHealth(player.getHealth()-inflicted);
                println(monster.getName()+"'s spell inflicts "+inflicted+" damage.",25);
                monster.setAID(0);
            }
            else
            {
                println(monster.getName()+"remains idle...",25);
            }
            return true;
        }
        if(monster.getId()==104)
        {
            int temp = RN.nextInt(100)+1;
            if(0<temp&&temp<10)
            {
                e.printAngryWitch();
                println("Ah yes, I hear the common cold has struck pandemic this season.",25);
                println("{Reduced attack for a turn}",20);
                attackDown(1,3);
                return true;
            }
            else if(11<temp&&temp<30)
            {
                e.printAngryWitch();
                println("Do be mindful of those spikes atop your head!!!",25);
                int inflicted = (int)((double)monster.getDamage()*1.3)-player.getDefense();
                println("The witch's icicles rain from above dealing "+inflicted+" damage!",25);
                return true;
            }
            else if(31<temp&&temp<40)
            {
                e.printAngryWitch();
                println("It must be pretty cold to move a muscle, am I right?",25);
                cancelAttack(1);
                cancelGuard(1);
                println("You are frozen and can't move!",25);
                return true;
            }
        }
        if(monster.getId()==105&&monster.getAID()==1)
        {
            println("Oh dear, this one is persistent on growing.",25);
            println("The overgrown plants run rampant, preventing you from moving.",25);
            cancelAttack(1);
            monster.setAID(0);
            return true;
        }
        if(monster.getId()==105)
        {
            int temp = RN.nextInt(100)+1;
            if(0<temp&&temp<40)
            {
                println("My, what a lovely day to blossom.",25);
                monster.setHealth(monster.getHeal()+10);
                return true;
            }
            else if(40<temp&&temp<50)
            {
                e.printAngryWitch();
                println("It wouldn't hurt to grow a bit extra would it?",25);
                int heal=monster.getHeal()+3;
                monster.setHealth(monster.getHealth()+heal);
                println(monster.getName()+" heals for "+heal+" health!",20);
                monster.setAID(1);
                return true;
            }
        }

        if(id==1001&&monster.healthPercentage()<.7&&monster.getAID()==0)// deploys pylon which last indefinately until detroyed.
        {
            println(monster.getName()+" deploys a pylon!",25);
            boosting(999, 1);
            monster.setAID(1);
            return true;
        }
        if(id==1001&&monster.healthPercentage()<.2&&monster.getAID()==1)
        {
            if(boosting)
            {
                println("The wraith seems to be recieving even more power from its pylon!",35);
                boosting(999,5);
            }
            else
            {
                println(monster.getName()+" deploys a pylon!",35);
                boosting(999, 2);
                return true;
            }
            monster.setAID(2);
        }

        if(monster.getId()==9999&&(turnTimer>=5||monster.healthPercentage()<=.95)&&monster.getAID()==0){//boss
            println(monster.getName()+" hits you and cancels your guard!",35);
            cancelGuard(1);
            monster.setAID(1);
            return true;
        }
        if(monster.getId()==9999&&monster.getAID()==1){//boss
            if(RN.nextInt(2)==0){
                println(monster.getName()+" backs up...",35);
            }
            else{
                println(monster.getName()+" lifts up his sword...",35);
            }
            monster.setAID(2);
            return true;
        }
        if(monster.getId()==9999&&monster.getAID()==2){//boss
            if(RN.nextInt(2)==0){
                println(monster.getName()+" slashes you across the chest!{Bleeding}",30);
                DOT(2,monster.getDamage());
            }
            else{
                println(monster.getName()+" bashes you with the hilt and deals "+monster.getDamage()+" damage!{Confused}",30);
                player.setHealth(player.getHealth()-monster.getDamage());
                confuse(2);
            }
            monster.setAID(4);
            return true;
        }
        if(monster.getId()==9999&&monster.getAID()==3){//boss
            println("and deflected "+monster.getName()+"'s attack and lowered his defense!{-1 defense} ",20);// the and is suppose to act as if your guard did that :3
            monster.setDefense(monster.getDefense()-1);
            monster.setAID(4);
            return true;
        }
        if(monster.getId()==9999&&monster.getAID()==4&&RN.nextInt(100)<=30){//boss
            println(monster.getName()+" goes in a Defensive Stance",20);
            mDefStance=true;
            monster.setAID(5);
            return true;
        }
        if(monster.getId()==9999&&monster.getAID()==5&&RN.nextInt(100)<=40){//boss
            mDefStance=false;
            if(RN.nextInt(2)==0){
                println(monster.getName()+" curses you with his sword!{Cursed}",30);
                curse(3);
            }
            else{
                int damage=(int)((double)monster.getDamage()*1.5);
                player.setHealth(player.getHealth()-damage);
                println(monster.getName()+" throws a fireball at you and deals "+damage+" damage!{Unable to heal}",30);
                cancelRest(3);
            }
            if(RN.nextInt(2)==0){
                println(monster.getName()+": Come her soldier! Assist me!",30);
                boss = monster;
                monster = new Monster(100,100,7,2,0,0,"The Iron Vanguard",9001);
            }
            else{
                println(monster.getName()+": Henchman! Take care of this nusiance",30);
                boss = monster;
                monster = new Monster(100,100,10,0,0,0,"The Lowly Goon",9002);
            }
            timer(6);
            return true;
        }
        if(monster.getId()==9001&&monster.getAID()==0&&RN.nextInt(100)<=20){// Goon 1 Vanguard
            println(monster.getName()+" bashes you with his shield!{Confused}",30);
            if(player.swordandboard()){println(monster.getName()+": How do you like your own medicine?",20);}
            confuse(2);
            monster.setAID(monster.getAID()+1);
            return true;
        }
        if(monster.getId()==9001&&monster.getAID()==1&&RN.nextInt(100)<=20){// Goon 1 Vanguard
            println(monster.getName()+" thrusts you and deals "+monster.getDamage()+"",30);
            player.setHealth(player.getHealth()-monster.getDamage());
            monster.setAID(monster.getAID()+1);
            return true;
        }
        if(monster.getId()==9001&&monster.getAID()==2&&RN.nextInt(100)<=20){// Goon 1 Vanguard
            println(monster.getName()+" becomes cautious {+1 defense}",30);
            monster.setDefense(monster.getDefense()+1);
            monster.setAID(monster.getAID()+1);
            return true;
        }
        if(monster.getId()==9002&&monster.getAID()==0&&RN.nextInt(100)<=20){// Goon 2 Goon
            int damage= (int)(monster.getDamage()*1.2);
            println(monster.getName()+"does a reckless attack and deals "+damage+" damage!{-1 defense}",30);
            player.setHealth(player.getHealth()-damage);
            monster.setAID(monster.getAID()+1);
            return true;
        }
        if(monster.getId()==9002&&monster.getAID()==1&&RN.nextInt(100)<=20){// Goon 2 Goon
            println(monster.getName()+" gets angry {+1 attack}",30);
            monster.setDamage(monster.getDamage()+1);
            monster.setAID(monster.getAID()+1);
            return true;
        }
        if(monster.getId()==9002&&monster.getAID()==2&&RN.nextInt(100)<=20){// Goon 2 Goon
            println(monster.getName()+" becomes cautious {+10% dodge}",30);
            monster.setDodge(monster.getDodge()+10);
            monster.setAID(monster.getAID()+1);
            return true;
        }
        //-------------------------------------//
        return false;
    }

    /**
     * determines if the witch changes forms befor attacking
     */
    public void witchPass() throws Exception
    {
        Random gen = new Random();
        if(gen.nextInt(100)<=10)
        {
            int temp = gen.nextInt(4)+1;
            turnTimer=0;
            if(temp==1)
            {
                monster = new Monster(monster.getHealth(),300,10,4,0,7,"The Summer Witch",102);
            }
            else if(temp==2)
            {
                monster = new Monster(monster.getHealth(),300,5,7,0,5,"The Autumn Witch",103);
            }
            else if(temp==3)
            {
                monster = new Monster(monster.getHealth(),300,5,4,0,10,"The Winter Witch",104);
            }
            else if(temp==4)
            {
                monster = new Monster(monster.getHealth(),300,5,4,20,5,"The Spring Witch",105);
            }
            println("The Witch transformed to "+monster.getName()+"!",20);
        }
    }

    /**
     * Prints the abilities/ items availiable to the player.
     * Weapons only have 2 special actives*
     * Also executes the effects of the abilities.
     */
    public void printAbilities(){
        if(player.sword())
        {
            System.out.println("(1)Defensive Stance{For the next three turns, attacks will deal bonus damage equal to the defense}[30 Mana]");
        }
        else if(player.hatchet())
        {
            System.out.println("(1)Wild Slash{Attack dealing 200% dmg and chance to bleed}[40 Mana]");
            System.out.println("(2)War Cry{Chant the ancient words of the gods to gain 20% crit}[40 Mana]");;
        }
        else if(player.swordandboard())
        {
            System.out.println("(1)Defensive Stance{For the next three turns, attacks will deal bonus damage equal to the defense}[30 Mana]");
            System.out.println("(2)Shield Bash{Deals player's damage with a 40% to stun- Already stunned enemy will be stunned again}[20 Mana]");
        }
        else if(player.staff())
        {
            if(!overflow){
                System.out.println("(1)Mana Surge{Gain 30% of current mana as bonus damage}[30% Mana]");
                System.out.println("(2)Split{Create a clone. You can attack twice in a turn}[60 Mana]");
            }
            else{
                System.out.println("(1)Mana Explosion{Deal 100% of current mana as true damage}[100% Mana]");
                System.out.println("(2)Split{Create two clones. You can attack three times in a turn}[100% Mana]");
            }
        }
        else if(player.dagger())
        {
            System.out.println("(1)Forbidden Pact{Heal for 25+player's heal and curse yourself for 3 turns}[20 Mana]");
            System.out.println("(2)Wrath{For every wrath stack, attack(1/2 Damage) for the same amount of times curse yourself}[30 Mana]");
        }
        boolean cont=true;
        int number=3;
        while(cont){
            if(healthPot>0){System.out.println("("+number+")Health Potion{Heals 20 +"+player.getHeal()+"}["+healthPot+" left]");healthNum=number+"";number++;}
            if(manaPot>0){System.out.println("("+number+")Mana Flask{Regenerate 30% of missing Mana}["+manaPot+" left]");manaNum=number+"";number++;}
            if(statusPot>0){System.out.println("("+number+")Ala's Tonic{Removes all debuffs}["+statusPot+" left]");statusNum=number+"";number++;}
            if(firePot>0){System.out.println("("+number+")FireBlast Potion{Deals 10% enemy's health over three turns}["+firePot+" left]");fireNum=number+"";number++;}
            if(goodPot>0){System.out.println("("+number+")Rignus's Good stuff{Grants immunity for one turn}["+goodPot+" left]");goodNum=number+"";number++;}
            cont=false;
        }
        System.out.println("(0)Go back");
    }

    public boolean useAbility(String b) throws Exception{
        if(b.equals("1")&&player.hatchet()&&useMana(40)){
            wildSlash();
            return true;
        }
        else if(b.equals("2")&&player.hatchet()&&warCryCount<4&&useMana(40))
        {
            warCry();
            return true;
        }
        else if(b.equals("2")&&player.hatchet()&&warCryCount>4&&useMana(40))
        {
            println("You decide not to push your luck with the gods.");
        }
        else if(b.equals("1")&&(player.swordandboard()||player.sword())&&useMana(30))
        {
            defStance();
            return false;
        }
        else if(b.equals("2")&&player.swordandboard()&&useMana(20))
        {
            shieldBash();
            return true;
        }
        else if(b.equals("1")&&player.staff())
        {
            manaSurge();
            if(overflow){
                return true;
            }
            else{
                return false;
            }
        }
        else if(b.equals("2")&&player.staff()&&player.getMana()>=60)
        {
            split();
            return false;
        }
        else if(b.equals("1")&&player.dagger()&&useMana(20))
        {
            fPact();
            return true;
        }
        else if(b.equals("2")&&player.dagger()&&useMana(30))
        {
            wrath();
            return true;
        }
        else if(b.equals(healthNum)&&healthPot>0){
            healthPot--;
            int heal=player.getHeal()+20;
            println("You heal for "+heal+" health!",35);
            player.setHealth(player.getHealth()+heal);
            return false;
        }
        else if(b.equals(manaNum)&&manaPot>0){
            manaPot--;
            int regen=(int)(((double)player.getMaxMana()-(double)player.getMana())*0.3);
            println("You regenerate "+regen+" mana!",35);
            player.setMana(player.getMana()+regen);
            return false;
        }
        else if(b.equals(statusNum)&&statusPot>0){
            statusPot--;
            removeEffects();
            println("You remove your debuff",35);
            return false;
        }
        else if(b.equals(fireNum)&&firePot>0){
            fireBlast();
            return false;
        }
        else if(b.equals(goodNum)&&goodPot>0){
            player.immune(2);
            return false;
        }
        if(b.equals(manaNum))
        {
            System.out.println("{Cinthas} I am unable to consume this type of matter");
        }
        return false;
    }

    //-----------------------------------------------------------------------------//

    /**
     * Do not mess with this method. It will never be altered
     */
    public void healthbar(){
        int playerbars=(player.getHealth()*10)/player.getMaxHealth();
        if(player.dagger()){
            int loss=wrathStacks/2;
            System.out.print(loss);
        }
        else{System.out.print(" ");}
        System.out.print(" HP[");
        for(int i=0;i<10;i++){
            if(playerbars>0){
                System.out.print("/");
                playerbars--;
            }else{
                System.out.print("-");
            }
        }
        System.out.print("]");
        System.out.print(player.getHealth());
        int monsterbars=((monster.getHealth()*10)/monster.getMaxHealth());
        System.out.print("  MH[");
        for(int i=0;i<10;i++){
            if(monsterbars>0){
                System.out.print("/");
                monsterbars--;
            }else{
                System.out.print("-");
            }
        }
        System.out.print("]");
        System.out.print(monster.getHealth()+" ");
        if(misGuarding){
            System.out.print("Guarding ");
        }
        if(stun){
            System.out.print("Stunned ");
        }
        if(monster.getImmune()){
            System.out.print("Immune:"+monster.getImmuneTimer()+" ");
        }
        if(monster.getBurned()){
            System.out.print("Burned:"+monster.getBurnTimer()+" ");
        }
        if(mDOT){
            System.out.print("DOT:"+mDOTTimer+"-"+mDOTScale+" ");
        }
        if(mDefStance){
            System.out.print("Def Stance ");
        }
        System.out.println();
        int manabars=((player.getMana()*10)/player.getMaxMana());
        System.out.print("Mana[");

        for(int i=0;i<10;i++){
            if(manabars>0){
                System.out.print("/");
                manabars--;
            }else{
                System.out.print("-");
            }
        }
        System.out.print("]");
        System.out.print(player.getMana());

        //Status timer indicators
        System.out.print("   ");
        if(player.staff()&&overflow){
            System.out.print("Overflow ");
        }
        if(player.staff()&&manaSurge){
            System.out.print("Mana Surge ");
        }
        if(player.staff()&&split){
            System.out.print("Split:"+splitTimer+" ");
        }
        if(wrathStacks>0&&player.dagger()){
            System.out.print("Wrath:"+wrathStacks+" ");
        }
        if(player.cowl()){
            System.out.print("TrueBurst:"+trueBurst+" ");
        }
        if(isGuarding){
            System.out.print("Guard:"+guardTimer+" ");
        }
        if(confused){
            System.out.print("Confused:"+confuseTimer+" ");
        }
        if(cursed){
            System.out.print("Cursed:"+curseTimer+" ");
        }
        if(player.getImmune()&&player.getImmuneTimer()>=1){
            int amount=player.getImmuneTimer()-1;
            System.out.print("Immune:"+amount+" ");
        }
        if(player.getBurned()&&player.getBurnTimer()>=1){
            int amount=player.getBurnTimer()-1;
            System.out.print("Burned:"+amount+" ");
        }
        if(DOT){
            System.out.print("DOT:"+DOTTimer+"-"+DOTScale+" ");
        }
        if(atkUp){
            System.out.print("AtkUP:"+atkUpTimer+"-"+atkUpScale+"");
        }
        if(atkDown){
            System.out.print("AtkDown:"+atkDownTimer+"-"+atkDownScale+"");
        }
        if(defUp){
            System.out.print("DefUp:"+defUpTimer+"-"+defUpScale+"");
        }
        if(attackCancelled){
            System.out.print("Attack(X):"+attackCancelTimer+" ");
        }
        if(guardCancelled){
            System.out.print("Guard(X):"+guardCancelTimer+" ");
        }
        if(restCancelled){
            System.out.print("Rest(X):"+restCancelTimer+" ");
        }
        if(dodge&&dodgeTimer>1){
            int amount=dodgeTimer-1;
            System.out.print("Dodge%:"+amount+"-"+dodgeChance+" ");
        }
        System.out.println("");
        //
    }

    public int goldFound(int id) throws Exception{
        Random RN = new Random();
        int goldfound=0;
        if(id==1){
            player.setExp(player.getExp()+75);goldfound+=RN.nextInt(50)+20; e.printDeadSlime();
        }
        if(id==2){
            player.setExp(player.getExp()+75);goldfound+=RN.nextInt(50)+20; e.printDeadGoblin();
        }
        if(id==3){
            player.setExp(player.getExp()+75);goldfound+=RN.nextInt(50)+20; e.printDeadSkeleton();
        }
        if(id==4){
            player.setExp(player.getExp()+75);goldfound+=RN.nextInt(60)+20; e.printDeadWolf();
        }
        if(id==11){
            player.setExp(player.getExp()+200);goldfound+=RN.nextInt(60)+50; e.printDeadGhoul();
        }
        if(id==12){
            player.setExp(player.getExp()+200);goldfound+=RN.nextInt(60)+50; e.printDeadGolem();
        }
        if(id==13){
            player.setExp(player.getExp()+200);goldfound+=RN.nextInt(60)+50; e.printDeadOrc();
        }
        if(id==14){
            player.setExp(player.getExp()+200);goldfound+=RN.nextInt(60)+50; e.printDeadOrc();
        }
        if(id==101){
            player.setExp(player.getExp()+400);goldfound+=RN.nextInt(80)+80; e.printDeadKnight();
        }
        if(id>=102&&id<=105){
            player.setExp(player.getExp()+400);goldfound+=RN.nextInt(80)+80; e.printDeadWitch();
        }
        if(id==1001){
            player.setExp(player.getExp()+400);goldfound+=RN.nextInt(100)+90;
        }
        return goldfound;
    }

    public void levelCheck(int exp) throws Exception{
        int lvl=player.getLevel();
        if(exp>=4000){
            player.setLevel(16); 
        }
        else if(exp>=3600){
            player.setLevel(15); 
        }
        else if(exp>=3200){
            player.setLevel(14); 
        }
        else if(exp>=2800){
            player.setLevel(13); 
        }
        else if(exp>=2400){
            player.setLevel(12); 
        }
        else if(exp>=2200){
            player.setLevel(11); 
        }
        else if(exp>=1800){
            player.setLevel(10); 
        }
        else if(exp>=1400){
            player.setLevel(9);
        }
        else if(exp>=1000){
            player.setLevel(8); 
        }
        else if(exp>=800){
            player.setLevel(7); 
        }
        else if(exp>=600){
            player.setLevel(6); 
        }
        else if(exp>=450){
            player.setLevel(5);
        }
        else if(exp>=300){
            player.setLevel(4); 
        }
        else if(exp>=200){
            player.setLevel(3); 
        }
        else if(exp>=100){
            player.setLevel(2);
        }
        else if(exp>=50){
            player.setLevel(1);
        }
        if(lvl!=player.getLevel()){
            int sp= (player.getLevel()-lvl);
            player.setSP(player.getSP()+sp);
            player.setMaxHealth(player.getMaxHealth()+(5*sp));
            println("You have leveled up! You are now level "+player.getLevel()+" and have "+player.getSP()+" Skill Points",30);
        }
    }

    //-------------------------MISC-----------------------------//

    public void printOptions() throws Exception{
        if(!confused){    
            if(!attackCancelled){System.out.println("(1)Attack");}
            else{System.out.println("(1)------");}
            if(guardCancelled){System.out.println("(2)-----");}
            else if(player.cloak()){
                System.out.println("(2)Conceal");
            }
            else{
                System.out.println("(2)Guard");
            }
            if(!restCancelled){
                System.out.println("(3)Rest");
            }
            else{System.out.println("(3)----");}
            System.out.println("(4)Other");
            if(monster.getId()==1001&&boosting)
            {
                System.out.println("(5)Attack pylon");
            }
        }
        else{
            System.out.println("(1)???");
            System.out.println("(2)???");
            System.out.println("(3)???");
            System.out.println("(4)Other");
        }
    }

    public void playerOptions() throws Exception{
        Scanner input= new Scanner(System.in);
        boolean done=true;
        while(done){
            System.out.println("(1)Characteristics");
            System.out.println("(2)Weapons");
            System.out.println("(3)Armors");
            System.out.println("(0)Exit");
            String b=input.next();
            if(b.equals("1")){
                boolean cont=true;
                player.updateStats();
                while(cont){
                    System.out.println("Damage:"+player.getNormDamage()+"  |Defense:"+player.getNormDefense()+"    |Heal:"+player.getNormHeal()+"");
                    System.out.println("Health:"+player.getHealth()+" |MaxHealth:"+player.getMaxHealth()+"|Crit Chance:"+player.getNormCrit()+"");
                    System.out.println("MaxMana:"+player.getMaxMana()+"|Mana Regen:"+player.getManaRegen()+" |Health Regen:"+livingArmor+"");
                    System.out.println("Level:"+player.getLevel()+" |Gold:"+gold+" |Skill Points:"+player.getSP()+"");
                    if(player.getSP()>0){
                        System.out.println("Skill Point usable");
                        System.out.println("(1)+1 Damage");
                        System.out.println("(2)+1 Defense[2 SP]");
                        System.out.println("(3)+1 Heal");
                        System.out.println("(4)+2 Crit");
                        System.out.println("(5)+10 Health");
                        System.out.println("(0)Go back");
                        String c=input.next();
                        if(c.equals("1")&&player.getSP()>=1){
                            println("Damage increased!",30);
                            player.setSP(player.getSP()-1);
                            player.setNormDamage(player.getNormDamage()+1);
                        }
                        else if(c.equals("2")&&player.getSP()>=2){
                            println("Defense increased!",30);
                            player.setSP(player.getSP()-2);
                            player.setNormDefense(player.getNormDefense()+1);
                        }
                        else if(c.equals("3")&&player.getSP()>=1){
                            println("Heal increased!",30);
                            player.setSP(player.getSP()-1);
                            player.setNormHeal(player.getNormHeal()+1);
                        }
                        else if(c.equals("4")&&player.getSP()>=1){
                            println("Critical Chance increased!",30);
                            player.setSP(player.getSP()-1);
                            player.setNormCrit(player.getNormCrit()+2);
                        }                        
                        else if(c.equals("5")&&player.getSP()>=1){
                            println("Health increased!",30);
                            player.setSP(player.getSP()-1);
                            player.setMaxHealth(player.getMaxHealth()+10);
                        }
                        else if(c.equals("0")){
                            cont=false;
                        }
                        player.updateStats();
                    }
                    cont=false;
                }    
            }
            else if(b.equals("2")){
                System.out.println("Current Weapon: "+player.getWeapon().getName()+"");
                if(player.getWeaponRack().get(1).getOwned()==true){System.out.println("(1)Hatchet");}
                if(player.getWeaponRack().get(2).getOwned()==true){System.out.println("(2)Sword and Shield");}
                if(player.getWeaponRack().get(3).getOwned()==true){System.out.println("(3)Msgical Staff");}
                if(player.getWeaponRack().get(4).getOwned()==true){System.out.println("(4)Cursed dagger");}
                String c=input.next();
                printWeapons(c);
                if(c.equals("1")&&player.getWeaponRack().get(1).getOwned()==true){
                    println("You have equipped the Hatchet",20);
                    player.setWeapon("hatchet");
                }
                else if(c.equals("2")&&player.getWeaponRack().get(2).getOwned()==true){
                    println("You have equipped the Sword and Shield",20);
                    player.setWeapon("swordandboard");
                }
                else if(c.equals("3")&&player.getWeaponRack().get(3).getOwned()==true){
                    println("You have equipped the Magical Staff",20);
                    player.setWeapon("staff");
                }
                else if(c.equals("4")&&player.getWeaponRack().get(4).getOwned()==true){
                    println("You have equipped the Cursed Dagger",20);
                    player.setWeapon("dagger");
                }
            }
            else if(b.equals("3")){
                if(player.getArmory().get(0).getOwned()==true){System.out.println("(1)Goblin Tunic");}
                if(player.getArmory().get(1).getOwned()==true){System.out.println("(2)Dark Cloak");}
                if(player.getArmory().get(2).getOwned()==true){System.out.println("(3)Gaia's Chestpiece");}
                if(player.getArmory().get(3).getOwned()==true){System.out.println("(4)Ari's Robes");}
                if(player.getArmory().get(4).getOwned()==true){System.out.println("(5)Magic Cowl");}
                String c=input.next();
                if(c.equals("1")&&player.getArmory().get(0).getOwned()==true){
                    println("You have equipped the Goblin Tunic",20);
                    player.printArmorStats("tunic");
                    player.setArmor("tunic");
                }
                else if(c.equals("2")&&player.getArmory().get(1).getOwned()==true){
                    println("You have equipped the Dark Cloak",20);
                    player.printArmorStats("cloak");
                    player.setArmor("cloak");
                }
                else if(c.equals("3")&&player.getArmory().get(2).getOwned()==true){
                    println("You have equipped Gaia's Chestpiece",20);
                    player.printArmorStats("chestpiece");
                    player.setArmor("chestpiece");
                }
                else if(c.equals("4")&&player.getArmory().get(3).getOwned()==true){
                    println("You have equipped Ari's Robes",20);
                    player.printArmorStats("robe");
                    player.setArmor("robe");
                }
                else if(c.equals("5")&&player.getArmory().get(4).getOwned()==true){
                    println("You have equipped the Magic Cowl",20);
                    player.printArmorStats("cowl");
                    player.setArmor("cowl");
                }
            }
            else if(b.equals("0")){
                done=false;
            }
        }
    }

    public boolean printWeapons(String c) throws Exception{
        Scanner input= new Scanner(System.in);
        if(c.equals("1")){
            System.out.println("Hatchet:");
            System.out.println("Passive: Gains damage as the player gets lower. x1 player base damage at 100% health and x1.5 base damage at 0% health.");
            System.out.println("Wild Slash: Attacks(can crit) the enemy dealing x2 the player's attack.[40 Mana]");
            System.out.println("            Can cause bleed based on player's crit chance. (x1.5 player's damage over 3 turns)");
            System.out.println("Warcry: Gain 20% critical chance for the rest of the fight. 1% chance to be 30% gain instead.[40 Mana]");
            System.out.println("        Takes up a turn");
            System.out.println("        Maximum cast of 3 times per battle.");
            player.printWeaponStats("hatchet");
            if(!player.getWeaponRack().get(1).getOwned()){
                System.out.println("(1)Buy");
                System.out.println("(2)Don't Buy");
                String d= input.next();
                if(d.equals("1")&&canBuy(100)){
                    println("You bought the Hatchet",20);
                    player.getWeaponRack().get(1).setOwned(true);
                    player.setWeapon("hatchet");
                    return false;
                }
            }
        }
        else if(c.equals("2")){//where to buy weapon
            System.out.println("Sword and Shield:");
            System.out.println("Passive: While guarding, player's attacks will have 5% to stun and a 5% chance to reflect when attacked");
            System.out.println("Defensive Stance: For the 2 turns, player will deal bonus damage equal to double their defense[30 Mana]");
            System.out.println("                  *Does not end the player's turn");
            System.out.println("Shield Bash: Deals player damage and has 40% chance to stun.[20 Mana]");
            System.out.println("             Stuns again if already stunned and deal double player damage");
            player.printWeaponStats("swordandboard");
            if(!player.getWeaponRack().get(2).getOwned()){
                System.out.println("(1)Buy");
                System.out.println("(2)Don't Buy");
                String d= input.next();
                if(d.equals("1")&&canBuy(100)&&!player.getWeaponRack().get(2).getOwned()){
                    println("You brought the Shield",20);
                    player.getWeaponRack().get(2).setOwned(true);
                    player.setWeapon("swordandboard");
                    return false;
                }
            }
        }
        else if(c.equals("3")&&!player.getWeaponRack().get(3).getOwned()){
            System.out.println("Magical Staff:");
            System.out.println("Passive: Overflow-Upon reaching max mana abilities will be upgraded. Mana will be reset to starting mana");
            System.out.println("         -2 mana per turn.");
            System.out.println("Mana surge: Next attack will consume and deal 30% of current mana as bonus damage[30% Mana]");
            System.out.println("            *does not take up turn");
            System.out.println("            Overflow-Deal 100% of max mana as true damage");
            System.out.println("Split: Create a clone. You will be able to attack twice for next three turns[60 Mana]");
            System.out.println("       *does not take up turn");                           
            System.out.println("       Overflow-Create an extra clone");                            
            player.printWeaponStats("staff");
            if(!player.getWeaponRack().get(3).getOwned()){
                System.out.println("(1)Buy");
                System.out.println("(2)Don't Buy");
                String d= input.next();
                if(d.equals("1")&&canBuy(100)){
                    println("You brought the Magical Staff",20);
                    player.getWeaponRack().get(3).setOwned(true);
                    player.setWeapon("staff");
                    return false;
                }
            }
        }
        else if(c.equals("4")){
            System.out.println("Cursed Dagger:");
            System.out.println("Passive: -2 mana and health regeneration, basic actions gives wrath stacks");
            System.out.println("         Lose health equal to half of wrath every turn.");
            System.out.println("Forbidden Pact:Heal 25+player's heal and curse yourself for 2 turns[20 Mana]");
            System.out.println("               *Does not stack on previous curses.");
            System.out.println("Wrath: For each wrath stack, attack(1/2 Damage) that many times. Curse youself for 2 turns[30 Mana] ");
            System.out.println("       *Can Crit");
            player.printWeaponStats("dagger"); 
            if(!player.getWeaponRack().get(4).getOwned()){
                System.out.println("(1)Buy");
                System.out.println("(2)Don't Buy");
                String d= input.next();
                if(d.equals("1")&&canBuy(100)&&!player.getWeaponRack().get(4).getOwned()){
                    println("You brought the Cursed Dagger",20);
                    player.getWeaponRack().get(4).setOwned(true);
                    player.setWeapon("dagger");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean printArmors(String c) throws Exception{
        Scanner input= new Scanner(System.in);
        if(c.equals("1")){//where to buy armor
            System.out.println("Goblin Tunic:");
            System.out.println("Gives 30% more gold found after in a battle");
            player.printArmorStats("tunic");
            if(!player.getArmory().get(0).getOwned()){
                System.out.println("(1)Buy");
                System.out.println("(2)Don't buy");
                String d= input.next();
                if(d.equals("1")&&canBuy(100)){
                    System.out.println("You bought the Goblin Tunic");
                    player.getArmory().get(0).setOwned(true);
                    player.setArmor("tunic");
                    return false;
                }
            }
        }
        if(c.equals("2")){
            System.out.println("Dark Cloak:");
            System.out.println("*Replaces guard with the basic action conceal.");
            System.out.println("Crits deal triple damage if player is concealed.");
            System.out.println("Player gains 30% +player's defense as dodge chance.");
            System.out.println("*Still considered a guard ability");
            player.printArmorStats("cloak");
            if(!player.getArmory().get(1).getOwned()){
                System.out.println("(1)Buy");
                System.out.println("(2)Don't buy");
                String d= input.next();
                if(d.equals("1")&&canBuy(100)){
                    System.out.println("You bought the Dark Cloak");
                    player.getArmory().get(1).setOwned(true);
                    player.setArmor("cloak");
                    return false;
                }
            }
        }
        if(c.equals("3")){
            System.out.println("Gaia'a Chestpiece:");
            System.out.println("Attacks deal bonus damage equal to half the player's heal");
            player.printArmorStats("chestpiece");
            if(!player.getArmory().get(2).getOwned()){
                System.out.println("(1)Buy");
                System.out.println("(2)Don't buy");
                String d= input.next();
                if(d.equals("1")&&canBuy(100)){
                    System.out.println("You bought the Gaia's Chestpiece");
                    player.getArmory().get(2).setOwned(true);
                    player.setArmor("chestpiece");
                    return false;
                }
            }
        }
        if(c.equals("4")){
            System.out.println("Ari's Robes:");
            System.out.println("Mana Sap-%20 of damage dealt to enemy will be converted to mana for player ");
            System.out.println("         Triggers only from attack");
            player.printArmorStats("robe");
            if(!player.getArmory().get(3).getOwned()){
                System.out.println("(1)Buy");
                System.out.println("(2)Don't buy");
                String d= input.next();
                if(d.equals("1")&&canBuy(100)){
                    System.out.println("You bought the Ari's Robes");            
                    player.getArmory().get(3).setOwned(true);
                    player.setArmor("robe");
                    return false;
                }
            }
        }
        if(c.equals("5")){
            System.out.println("Magic Cowl:");
            System.out.println("Using rest gives stacks of TrueBurst(Max: 10)");
            System.out.println("TrueBurst-Player's next attack will reduce enemy's defense by the amount of stacks for that turn only.");
            System.out.println("          Multiple attacks in one turn will still have defense reduced");
            System.out.println("          If monster's defense is equal or under 0, attacks deal x1.5 damage ");
            player.printArmorStats("cowl");
            if(!player.getArmory().get(4).getOwned()){
                System.out.println("(1)Buy");
                System.out.println("(2)Don't buy");
                String d= input.next();
                if(d.equals("1")&&canBuy(100)){
                    System.out.println("You bought the Magic Cowl");            
                    player.getArmory().get(4).setOwned(true);
                    player.setArmor("cowl");
                    return false;
                }
            }
        }
        return true;
    }

    public void tutorial() throws Exception{
        Scanner input= new Scanner(System.in);
        println("You decide to get the strange rock stuck between the neighboring ones",20,500);
        println("However, the rock jumps out of your hand and it begins to float in the air!",20,500);
        println("It begins to speak...",20,500);
        println("???:OH THANK THE HEAVENS YOU HAVE FREED ME FROM THAT CURSED PLACE",20,500);
        println("???:THOSE ROCKS WOULDN'T LET ME GO!",20,500);
        cont();
        println("The rock seems to have calmed down after looking around.",20,500);
        println("???:I gotta tell ya, sitting in one position for a thousand years can really make you stiff",20,500);
        println("???:Thanks for savin' me. Guessing from your looks, it seems like you are new here.",20,500);
        println("???:As a way to thank you, how bout I show you how to get started round here?",20,500);
        cont();
        println("???:By the way the name's Pepo, and you?",20,500);
        print("My name is... ",20);
        String name= input.next();
        player.setName(name);
        println("Pepo:"+player.getName()+"... What an odd name.",20,500);
        println("Pepo:Anywho, Let me show you how to fight. Let's take revenge on those rocks!!!",20,500);
        println("You pick a fight with a sationary rock",20,500);
        e.printRock();
        int i=0;
        monster= new Monster(999,999,0,0,0,0,"The Rock",0);
        boolean chosen=false;
        while(i<6){
            if(i==0){
                println("Pepo: Use that sword of yours and cut that rock like a samurai!",20,500);
                println("Pepo: Using attack will deal a certain amount of damage. It can crit and is reduced by how much defense the enemy has.",20,500);
            }
            if(i==1){
                println("Pepo:You usually will start a fight with zero mana. You will regenerate 5 per turn.",20,500);
                println("Pepo:If you have enough you can use a special ability!",20,500);
            }
            if(i==2){
                println("Pepo:Using rest can will double your mana regeneration and heal you too!",20,500);
            }
            if(i==3){
                println("Pepo:Betta watch it though! Your health matters too!",20,500);
                println("Pepo:Guess what happens when your health goes to zero...",20,500);
                println("Pepo:First you are disapointed of the fact that you failed at life...",20,500);
                println("     Then you die",10,500);
            }
            if(i==4){
                println("Pepo:Using guard will increase your defense by 5 and half of your level for three turns",20,500);
            }
            if(i==5){
                println("Pepo:Member' that these rocks are small fry and stronger ones will actually attack you",20,500);
                println("Pepo:The creepers round here can do terrible things to you.",20,500);
                println("*Confuse-Makes you do a random basic action(Actions 1-3)",20,200);
                println("*Curse-Unable to regenerate mana(There are exceptions)",20,200);
                println("*Action Cancel-Unable to use a certain basic action",20,200);
                println("*ETC.",20,200);
            }
            if(i==6){
                println("The rock cracks and then breaks in half.",20,500);
                e.printAngryRock();
                println("Pepo:Cy@",40,500);
            }
            healthbar();
            printOptions();
            System.out.print("Action:");
            String a= input.next();
            if(a.equals("1")){
                int damage= player.damage();
                if(defStance){damage=damage+(2*player.getDefense());}
                println("You hit "+monster.getName()+" dealing "+damage+" damage!",7);
                monster.setHealth(monster.getHealth()-damage);
                chosen=true;
            }
            else if(a.equals("2")){     
                println("You guard",15);
                chosen=true;
            }
            else if(a.equals("3")){
                player.heal();
                print(" and gain "+player.getManaRegen()+" mana\n",10);
                player.setMana(player.getMana()+player.getManaRegen());
                chosen= true;
            }
            else if(a.equals("4")){
                System.out.print("(1)Defensive Stance{For the next three turns, you gain bonus damage equal to you def}[30 Mana]");
                String b= input.next();
                if(b.equals("1")&&useMana(30)){
                    println("You go into a Defensive Stance!",20);
                    defStance=true;
                }
            }
            if(chosen=true){
                Thread.sleep(500);
                chosen=false;
                player.setMana(player.getMana()+5);
                i++;
            }
        }

        println("Pepo:I think you need to find yourself a town or something to take shelter in",20,500);
        println("Pepo:There was a safe haven in this place. I'll show you the way to it, if it's still there",20,500);
        println("...",20,500);
        println("After a couple hours of walking we find a town within the grasslands",20,500);
        println("I read a sign.",20,500);
        e.printTown();
        println("Pepo:Wowee..., this place is huge! This used to be a rag town",20,500);
        println("Pepo:So there are a couple of places you can go to here",20,500);
        println("Gold:"+gold+"",20);
        println("(1)Tavern-Misc. things",20,500);
        println("(2)Healer-Heal and to buy potions",20,500);
        println("(3)Blacksmith-Buy Armors/Weapons",20,500);
        println("(4)Player-Manage level/items",20,500);
        println("(5)Dungeon-Enter to fight monsters",20,500);
        println("Pepo:If you run a dungeon, you can't get out until you smash the boss's head in!",20,500);
        cont();
        println("Pepo:I think that should be good nough'. There more to tell you but there too much.",20,500);
        println("Pepo:Find out yourself "+player.getName()+", and... thanks for saving me",20,500);
        println("Looking a little lost, Pepo begins to fly away.",20,500);
    }

    public void cont(){
        Scanner input= new Scanner(System.in);
        System.out.print("Press 1 to continue");
        String a= input.next();
    }

    public boolean useMana(int amount) throws Exception{
        if(player.getMana()>=amount){
            player.setMana(player.getMana()-amount);
            return true;
        }
        else{
            println("You don't have enough mana!",20);
            return false;
        }
    }

    /**
     * Used to check of the player can buy an item.
     * will print "You don't have enough gold if they cannot"
     */
    public boolean canBuy(int gold){
        if(this.gold<gold){
            System.out.println("You dont have enough to buy this item");
            return false;
        }
        else{
            this.gold-=gold;
        }
        return true;
    }

    public void sashaPass() throws Exception{
        Random RN= new Random();
        if(sasha&&RN.nextInt(100)<=10){
            e.sashaTalk();
            int damage= 10+player.getLevel();
            monster.setHealth(monster.getHealth()-damage);
            println("Sasha attacks dealing "+damage+" damage!",20);
        }
    }

    /**
     * Prints a given String with a delay between letters.
     * Ex. print("hello",100); -->prints and finishes hello in 500 miliseconds
     */
    public void print(String str, int delay) throws Exception{
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            Thread.sleep(delay);
        }
    }

    /**
     * Prints a given String with a delay between letters.
     * Ex. print("hello",100); -->prints and finishes hello in 500 miliseconds
     */
    public void print(String str, int delay, int pause) throws Exception{
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            Thread.sleep(delay);
        }
        Thread.sleep(pause);
    }

    /**
     * Prints given String but with a line added in the end
     */
    public void println(String str, int delay) throws Exception{
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            Thread.sleep(delay);
        }
        System.out.println();
    }

    /**
     * Prints given String but with a line added in the end
     */
    public void println(String str, int delay, int pause) throws Exception{
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            Thread.sleep(delay);
        }
        System.out.println();
        Thread.sleep(pause);
    }

    /**
     * Prints a given String with a delay between letters.
     * Ex. print("hello",100); -->prints and finishes hello in 500 miliseconds
     */
    public static void println(String str, int delay, int pause, String name) throws Exception{
        try
        {
            File file = new File("E:\\hddfbcf\\"+name+".wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.setFramePosition(0);  // Must always rewind!
            clip.start();
            int total=0;
            for(int i=0;i<str.length();i++){
                System.out.print(str.charAt(i));
                Thread.sleep(delay);
                total=total+delay;
            }
            System.out.println();
            if((clip.getMicrosecondLength()/1000)>total){
                Thread.sleep((clip.getMicrosecondLength()/1000)-total);
            }
            Thread.sleep(pause);
        }
        catch(Exception e)
        {
            for(int i=0;i<str.length();i++){
                System.out.print(str.charAt(i));
                Thread.sleep(delay);
            }
            System.out.println();
            Thread.sleep(pause);
        }
    }

    public void endGame(){
        playerLost=true;
    }

    public void resetChecks()
    {
        firstCheck=false;
        secondCheck=false;
        thirdCheck=false;
        fourthCheck=false;
        fifthCheck=false;
        sixthCheck=false;
        seventhCheck=false;
        eigthCheck=false;
        ninthCheck=false;
        tenthCheck=false;
    }

}
