import java.util.*;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
/**
 * A class where the main animations/story is. Dialogue
 * 
 * @author (Kenny Doan) 
 * @version (2.5.1)
 */
public class Extra
{
    public String name1, name2, name3;
    public static Clip clip;
    public Extra()
    {
        name1="";
        name2="";
        name3="";
    }

    public static void main(String[] args) throws Exception{
        Extra e= new Extra();
        e.printWitch();
        e.printAngryWitch();
    }

    public void printRock() throws Exception{
        println("----------____--------",5);
        println("--------/.    \\-------",5);
        println("------_/  .  . |------",5);
        println("---_/  .       .\\__---",5);
        println("-/_________________\\--",5);
    }

    public void printTown() throws Exception{
        println("--__________________--",5);
        println("-|  Welcome to      |-",5);
        println("-|       ...        |-",5);
        println("-|    {Camelot}     |-",5);
        println("-|__________________|-",5);
    }

    public void printShop() throws Exception{
        println("--__________________--",5);
        println("-|  Rignus Shop     |-",5);
        println("-|   -------------  |-",5);
        println("-| \"Need some Pot?\" |-",5);
        println("-|__________________|-",5);
    }

    public void printAngryRock() throws Exception{
        println("----------____--------",5);
        println("--------/.   #\\-------",5);
        println("------_/  .  . |------",5);
        println("---_/  .       .\\__---",5);
        println("-/_________________\\--",5);
    }

    public void printSlime() throws Exception{
        println("-----------___------",5);
        println("---------/ . . \\----",5);
        println("------_/       |----",5);
        println("--__/          \\----",5);
        println("/_____________/-----",5);
    }

    public void printAngrySlime() throws Exception{
        println("-----------___------",5);
        println("---------/ .\\/.\\----",5);
        println("------_/       |----",5);
        println("--__/          \\----",5);
        println("/_____________/-----",5);
    }

    public void printDeadSlime() throws Exception{
        println("-----------___------",5);
        println("---------/ X X \\----",5);
        println("------_/       |----",5);
        println("--__/          \\----",5);
        println("/_____________/-----",5);
    }

    public void printGoblin() throws Exception{
        println("---/\\______/\\-------",5);
        println("--/          \\------",5);
        println("-|    .    .  |-----",5);
        println("-|       >    |-----",5);
        println("--\\    __    /------",5);
        println("----\\______/--------",5);
    }

    public void printMadGoblin() throws Exception{
        println("---/\\______/\\-------",5);
        println("--/        # \\------",5);
        println("-|    .    .  |-----",5);
        println("-|       >    |-----",5);
        println("--\\    __    /------",5);
        println("----\\______/--------",5);
    }

    public void printAngryGoblin() throws Exception{
        println("---/\\______/\\-------",5);
        println("--/        # \\------",5);
        println("-|    .\\  /.  |-----",5);
        println("-|       >    |-----",5);
        println("--\\    __    /------",5);
        println("----\\______/--------",5);
    }

    public void printDeadGoblin() throws Exception{
        println("---/\\______/\\-------",5);
        println("--/          \\------",5);
        println("-|    X    X  |-----",5);
        println("-|       >    |-----",5);
        println("--\\    __    /------",5);
        println("----\\______/--------",5);
    }

    public void printWolf() throws Exception{
        println("---|\\--------/|-----",5);
        println("--\\|-\\^^||^^/-|/----",5);
        println("--\\\\\\        ///----",5);
        println("---\\\\ O    O //-----",5);
        println("---/\\\\\\    ///\\-----",5);
        println("--/---\\-QQ-/---\\----",5);
    }

    public void printAngryWolf() throws Exception{
        println("---|\\--------/|-----",5);
        println("--\\|-\\^^||^^/-|/----",5);
        println("--\\\\\\ /\\  /  ///----",5);
        println("---\\\\ o    o //------",5);
        println("---/\\\\\\ // ///\\-----",5);
        println("--/---\\-QQ-/---\\----",5);
    }

    public void printDeadWolf() throws Exception{        
        println("---|\\--------/|-----",5);
        println("--\\|-\\^^||^^/-|/----",5);
        println("--\\\\\\ _    _ ///----",5);
        println("---\\\\ X    X //-----",5);
        println("---/\\\\\\ // ///\\-----",5);
        println("--/---\\-QQ-/---\\----",5);
    }

    public void printSkeleton() throws Exception{
        println("--- _________-------",5);
        println("--/          \\------",5);
        println("-|    O    O  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_        _/------",5);
        println("----|__|_|_|--------",5);
    }

    public void printAngrySkeleton() throws Exception{
        println("--- _________-------",5);
        println("--/   \\   /   \\------",5);
        println("-|    O    O  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_        _/------",5);
        println("----|__|_|_|--------",5);
    }

    public void printDeadSkeleton() throws Exception{
        println("--- _________-------",5);
        println("--/          \\------",5);
        println("-|    X    X  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_        _/------",5);
        println("----|__|_|_|--------",5);
    }

    public void printOrc() throws Exception{
        println("---/\\______/\\-------",5);
        println("--/          \\------",5);
        println("-|    0    0  |-----",5);
        println("-|      **    |-----",5);
        println("--\\    ^---^ /------",5);
        println("----\\______/--------",5);
    }

    public void printAngryOrc() throws Exception{
        println("---/\\______/\\-------",5);
        println("--/    \\  /  \\------",5);
        println("-|    0    0  |-----",5);
        println("-|      **    |-----",5);
        println("--\\    ^---^ /------",5);
        println("----\\______/--------",5);
    }

    public void printDeadOrc() throws Exception{
        println("---/\\______/\\-------",5);
        println("--/          \\------",5);
        println("-|    X    X  |-----",5);
        println("-|      **    |-----",5);
        println("--\\    ^---^ /------",5);
        println("----\\______/--------",5);
    }

    public void printGhoul() throws Exception{
        println("--- _________-------",5);
        println("--/          \\------",5);
        println("-|    O    O  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_   ___  _/------",5);
        println("----|______|--------",5);
    }

    public void printAngryGhoul() throws Exception{
        println("--- _________-------",5);
        println("--/   \\   /   \\------",5);
        println("-|    O    O  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_   ___  _/------",5);
        println("----|______|--------",5);
    }

    public void printDeadGhoul() throws Exception{
        println("--- _________-------",5);
        println("--/          \\------",5);
        println("-|    X    X  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_   ___  _/------",5);
        println("----|______|--------",5);
    }

    public void printGolem() throws Exception{
        println("---,________,-------",5);
        println("--/   __  __ \\------",5);
        println("-|    \\/  \\/  |-----",5);
        println("-|            |-----",5);
        println("--\\    ||||  /------",5);
        println("----\\______/--------",5);
    }

    public void printAngryGolem() throws Exception{
        println("---,________,-------",5);
        println("--/ # __  __ \\------",5);
        println("-|    \\/  \\/  |-----",5);
        println("-|            |-----",5);
        println("--\\    ||||  /------",5);
        println("----\\______/--------",5);
    }

    public void printDeadGolem() throws Exception{
        println("---,________,-------",5);
        println("--/   __  __ \\------",5);
        println("-|    ~~  ~~  |-----",5);
        println("-|            |-----",5);
        println("--\\    ||||  /------",5);
        println("----\\______/--------",5);
    }

    public void printKnight() throws Exception{
        println("------________------",5);
        println("-----/    |   \\-----",5);
        println("----|   __+__ |-----",5);
        println("----|    . .  |-----",5);
        println("----|   . . . |-----",5);
        println("-----\\_______/------",5);
    }

    public void printAngryKnight() throws Exception{
        println("------________------",5);
        println("-----/ #  |   \\-----",5);
        println("----|   __+__ |-----",5);
        println("----|    . .  |-----",5);
        println("----|   . . . |-----",5);
        println("-----\\_______/------",5);
    }

    public void printDeadKnight() throws Exception{
        println("------________------",5);
        println("-----/    |   \\-----",5);
        println("----|   ==+== |-----",5);
        println("----|    . .  |-----",5);
        println("----|   . . . |-----",5);
        println("-----\\_______/------",5);
    }

    public void printWitch() throws Exception{
        println("--------____---------",5);
        println("-------/\\ / \\--------",5);
        println("------/__\\|__\\-------",5);
        println("--___{________}____--",5);
        println("-/_________________\\-",5);
        println("---------------------",5);
    }

    public void printAngryWitch() throws Exception{
        println("--------____---------",5);
        println("---!!!-/\\ / \\--------",5);
        println("------/__\\|__\\-------",5);
        println("--___{________}____--",5);
        println("-/_________________\\-",5);
        println("---------------------",5);
    }

    public void printDeadWitch() throws Exception{
        println("--------____---------",5);
        println("-------/\\ /#\\--------",5);
        println("------/__\\|__\\-------",5);
        println("--___{__X_____}____--",5);
        println("-/__________*______\\-",5);
        println("---------------------",5);
    }

    public void printbDeadWitch() throws Exception{
        println("------_______-------",5);
        println("-----/  #_^__\\------",5);
        println("----| x /-----|-----",5);
        println("----|  |------|-----",5);
        println("----|/- \\  _  |-----",5);
        println("-----\\__|    /------",5);
    }

    public void printbWitch() throws Exception{
        println("------_______-------",5);
        println("-----/   ____\\------",5);
        println("----|   /-----|-----",5);
        println("----|  |------|-----",5);
        println("----|   \\  __ |-----",5);
        println("-----\\__|    /------",5);
    }

    public void printbAngryWitch() throws Exception{
        println("------_______-------",5);
        println("-----/ # ____\\------",5);
        println("----|   /-----|-----",5);
        println("----|  |------|-----",5);
        println("----|   \\  ^  |-----",5);
        println("-----\\__|    /------",5);
    }


    public void printTitle() throws Exception{
        println("  ______  ______",5);
        println(" /  ____||  ____|",5);
        println(" |  |    | |____",5);
        println(" |  |    |  ____|",5);
        println(" |  |___ | |____",5);
        println(" \\______||______| The Awakening...",5);
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
     * Prints given String with a pause
     */
    public void print(String str, int delay,int pause) throws Exception{
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
    public void println(String str, int delay,int pause) throws Exception{
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            Thread.sleep(delay);
        }
        Thread.sleep(pause);
        System.out.println();
    }

    public void printBrab() throws Exception{
        println("---------------------",5);
        println("-------______--------",5);
        println("---V--/      \\--V----",5);
        println("----\\{   o.o  }/-----",5);
        println("------///-~-\\\\\\------",5);
        println("---------------------",5);
    }

    public void printCrab() throws Exception{
        println("--------____---------",5);
        println("-------{    }--------",5);
        println("---V--{      }--V----",5);
        println("----\\{   o.o  }/-----",5);
        println("------///-~-\\\\\\------",5);
        println("---------------------",5);
    }

    public void printShellCrab() throws Exception{
        println("--------____---------",5);
        println("-------{    }--------",5);
        println("------{      }-------",5);
        println("-----{   -.-  }------",5);
        println("------<<<-~->>>------",5);
        println("---------------------",5);
    }

    public void printAngryCrab() throws Exception{
        println("--------____---------",5);
        println("-------{    }--------",5);
        println("---V--{      }--V----",5);
        println("----\\{   >.<  }/-----",5);
        println("------///-~-\\\\\\------",5);
        println("---------------------",5);
    }

    public void printDeadCrab() throws Exception{
        println("--------____---------",5);
        println("-------{    }--------",5);
        println("---V--{      }--V----",5);
        println("----\\{   ~.~  }/-----",5);
        println("------///-~-\\\\\\------",5);
        println("---------------------",5);
    }

    public void healerTalk() throws Exception{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        a=5;
        if(a==1){println("{Priestess} Are you hurt? I can help you.",30,500,"Priestess 1");}
        else if(a==2){println("{Priestess} Need a potion?",30,500,"Priestess 2");} 
        else if(a==3){println("{Priestess}I recommend health potions.",30,500,"Priestess 3");} 
        else if(a==4){println("{Priestess} I give a discount for a full heal",30,500,"Priestess 4");} 
        else if(a==5){println("{Priestess} Is there anything I can help you with?",30,500,"Priestess 5");} 
    }

    public void smithTalk() throws Exception{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        if(a==1){println("Blacksmith:Armors, Weapons, I gottem all",30,500);}
        else if(a==2){println("Blacksmith:Needa upgrade? I can help, but for a price",30,500);}
        else if(a==3){println("Blacksmith:Things here cost alot, too bad for you",30,500);}
        else if(a==4){println("Blacksmith:I'll give you a discount of 0% off today.",30,500);}
        else if(a==5){println("Blacksmith:Buy sumthing, or get out of my shop.",30,500);}
    }

    public void tavernTalk() throws Exception{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        if(a==1){println("Bartender:Let me tell you a secret, these beers don't get you drunk",30,500);}
        else if(a==2){print("Bartender:Beer here is cheap...",30,500);println("So buy some. Or get something else...",30,500);}
        else if(a==3){println("Bartender:I only sell beer... haha...",30,500);}
        else if(a==4){println("Bartender:Need help? Don't ask me.",30,500);}
        else if(a==5){println("Bartender:Welcome to the tavern!",30,500);}
    }

    public void tavernSecret() throws Exception{
        println("Bartender:Hey, never thought you would be the kind of person who would be interested in this",30,500);
        println("Bartender:Follow me...",30,500);
    }

    public void setName1(String name){
        name1=name;
    }

    public void setName2(String name){
        name2=name;  
    }

    public void setName3(String name){
        name3=name;
    }

    public String getName1(){
        return name1;
    }

    public String getName2(){
        return name2;
    }

    public String getName3(){
        return name3;
    }

    public void slaveTalk() throws Exception{
        Random RN= new Random();
        int a=RN.nextInt(3)+1;
        if(!name1.equals("")){
            if(a==1){println("Mimi:You seem nice "+name1+". I'm glad.",30,500);}
            else if(a==2){println("Mimi:I'm sure I'll find something this time.",30,500);}
            else if(a==3){println("Mimi:I always dreamed of one day being able to fight like you.",30,500);}
        }
        if(!name2.equals("")){
            if(a==1){println("Sasha:Have you ever felt bad killing these monsters?",30,500);}
            else if(a==2){println("Sasha:I have much to learn. I don't feel that useful to you "+name2+"",30,500);}
            else if(a==3){println("Sasha:I'll try to not get in your way Master.",30,500);}
        }
        if(!name3.equals("")){
            if(a==1){println("Mary:You seem really quiet sometimes. I like that.",30,500);}
            else if(a==2){println("Mary:You never do anything to me "+name3+"...",30,500);}
            else if(a==3){println("Mary:I can't help you in your battles. Don't die "+name3+"",30,500);}
        }
    }

    public void mimiTalk1() throws Exception{
        Random RN= new Random();
        int a=RN.nextInt(3)+1;
        if(a==1){println("Mini:Master! I found some more gold!",30,500);}
        else if(a==2){print("Mini:I-I didn't find much, but I hope you like it "+name1+"",30,500);}
        else if(a==3){println("Mini:"+name1+"! There's some gold over here!",30,500);}
    }

    public void mimiTalk2() throws Exception{
        Random RN= new Random();
        int a=RN.nextInt()+1;
        if(a==1){println("Mini:I-I'm sorry "+name1+", but I couldn't find anything",30,500);}
        else if(a==2){print("Mini:I can't find anymore gold "+name1+"...",30,500);}
    }

    public void sashaTalk() throws Exception{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        if(a==1){println("Sasha:I got you!",30,500);}
        else if(a==2){print("Sasha:Master! There's an opening!",30,500);}
        else if(a==3){println("Sasha:Too slow!",30,500);}
        else if(a==4){println("Sasha:"+name2+"! Let me!",30,500);}
        else if(a==5){println("Sasha:Back off fiend!",30,500);}
    }

    public void maryTalk() throws Exception{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        if(a==1){print("Mary:You always getting hurt "+name3+".",30,500);print(" Let me help you...",30,500);}
        else if(a==2){print("Mary:How about we rest for a bit "+name3+"...",30,500);}
        else if(a==3){print("Mary:Your hurt Master.",30,500);println("Let me help you feel better...",30,500);}
        else if(a==4){println("Mary:You're not in a rush right now. Let's take a break.",30,500);}
        else if(a==5){print("Mary:"+name3+"! You're hurt!",30,500);println("Sit down. Let me take care of everything",30,500);}
    }
    
    /**
     * Prints a given String with a delay between letters.
     * Ex. print("hello",100); -->prints and finishes hello in 500 miliseconds
     */
    public static void println(String str, int delay, int pause, String name) throws Exception{
        try
        {
            File file = new File("C:\\Users\\yukioh99\\Desktop\\Project Sounds\\Downloads here\\Cut Files\\"+name+".wav");
            //Kenny's Directory// File file = new File("
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
}



);
        println("--------/.   #\\-------",5);
        println("------_/  .  . |------",5);
        println("---_/  .       .\\__---",5);
        println("-/_________________\\--",5);
    }

    public void printSlime() throws InterruptedException{
        println("-----------___------",5);
        println("---------/ . . \\----",5);
        println("------_/       |----",5);
        println("--__/          \\----",5);
        println("/_____________/-----",5);
    }

    public void printAngrySlime() throws InterruptedException{
        println("-----------___------",5);
        println("---------/ .\\/.\\----",5);
        println("------_/       |----",5);
        println("--__/          \\----",5);
        println("/_____________/-----",5);
    }

    public void printDeadSlime() throws InterruptedException{
        println("-----------___------",5);
        println("---------/ X X \\----",5);
        println("------_/       |----",5);
        println("--__/          \\----",5);
        println("/_____________/-----",5);
    }

    public void printGoblin() throws InterruptedException{
        println("---/\\______/\\-------",5);
        println("--/          \\------",5);
        println("-|    .    .  |-----",5);
        println("-|       >    |-----",5);
        println("--\\    __    /------",5);
        println("----\\______/--------",5);
    }

    public void printMadGoblin() throws InterruptedException{
        println("---/\\______/\\-------",5);
        println("--/        # \\------",5);
        println("-|    .    .  |-----",5);
        println("-|       >    |-----",5);
        println("--\\    __    /------",5);
        println("----\\______/--------",5);
    }

    public void printAngryGoblin() throws InterruptedException{
        println("---/\\______/\\-------",5);
        println("--/        # \\------",5);
        println("-|    .\\  /.  |-----",5);
        println("-|       >    |-----",5);
        println("--\\    __    /------",5);
        println("----\\______/--------",5);
    }

    public void printDeadGoblin() throws InterruptedException{
        println("---/\\______/\\-------",5);
        println("--/          \\------",5);
        println("-|    X    X  |-----",5);
        println("-|       >    |-----",5);
        println("--\\    __    /------",5);
        println("----\\______/--------",5);
    }

    public void printWolf() throws InterruptedException{
        println("---|\\--------/|-----",5);
        println("--\\|-\\^^||^^/-|/----",5);
        println("--\\\\\\        ///----",5);
        println("---\\\\ O    O //-----",5);
        println("---/\\\\\\    ///\\-----",5);
        println("--/---\\-QQ-/---\\----",5);
    }

    public void printAngryWolf() throws InterruptedException{
        println("---|\\--------/|-----",5);
        println("--\\|-\\^^||^^/-|/----",5);
        println("--\\\\\\ /\\  /  ///----",5);
        println("---\\\\ o    o //------",5);
        println("---/\\\\\\ // ///\\-----",5);
        println("--/---\\-QQ-/---\\----",5);
    }

    public void printDeadWolf() throws InterruptedException{        
        println("---|\\--------/|-----",5);
        println("--\\|-\\^^||^^/-|/----",5);
        println("--\\\\\\ _    _ ///----",5);
        println("---\\\\ X    X //-----",5);
        println("---/\\\\\\ // ///\\-----",5);
        println("--/---\\-QQ-/---\\----",5);
    }

    public void printSkeleton() throws InterruptedException{
        println("--- _________-------",5);
        println("--/          \\------",5);
        println("-|    O    O  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_        _/------",5);
        println("----|__|_|_|--------",5);
    }

    public void printAngrySkeleton() throws InterruptedException{
        println("--- _________-------",5);
        println("--/   \\   /   \\------",5);
        println("-|    O    O  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_        _/------",5);
        println("----|__|_|_|--------",5);
    }

    public void printDeadSkeleton() throws InterruptedException{
        println("--- _________-------",5);
        println("--/          \\------",5);
        println("-|    X    X  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_        _/------",5);
        println("----|__|_|_|--------",5);
    }

    public void printOrc() throws InterruptedException{
        println("---/\\______/\\-------",5);
        println("--/          \\------",5);
        println("-|    0    0  |-----",5);
        println("-|      **    |-----",5);
        println("--\\    ^---^ /------",5);
        println("----\\______/--------",5);
    }

    public void printAngryOrc() throws InterruptedException{
        println("---/\\______/\\-------",5);
        println("--/    \\  /  \\------",5);
        println("-|    0    0  |-----",5);
        println("-|      **    |-----",5);
        println("--\\    ^---^ /------",5);
        println("----\\______/--------",5);
    }

    public void printDeadOrc() throws InterruptedException{
        println("---/\\______/\\-------",5);
        println("--/          \\------",5);
        println("-|    X    X  |-----",5);
        println("-|      **    |-----",5);
        println("--\\    ^---^ /------",5);
        println("----\\______/--------",5);
    }

    public void printGhoul() throws InterruptedException{
        println("--- _________-------",5);
        println("--/          \\------",5);
        println("-|    O    O  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_   ___  _/------",5);
        println("----|______|--------",5);
    }

    public void printAngryGhoul() throws InterruptedException{
        println("--- _________-------",5);
        println("--/   \\   /   \\------",5);
        println("-|    O    O  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_   ___  _/------",5);
        println("----|______|--------",5);
    }

    public void printDeadGhoul() throws InterruptedException{
        println("--- _________-------",5);
        println("--/          \\------",5);
        println("-|    X    X  |-----",5);
        println("-|      ..    |-----",5);
        println("--\\_   ___  _/------",5);
        println("----|______|--------",5);
    }

    public void printGolem() throws InterruptedException{
        println("---,________,-------",5);
        println("--/   __  __ \\------",5);
        println("-|    \\/  \\/  |-----",5);
        println("-|            |-----",5);
        println("--\\    ||||  /------",5);
        println("----\\______/--------",5);
    }

    public void printAngryGolem() throws InterruptedException{
        println("---,________,-------",5);
        println("--/ # __  __ \\------",5);
        println("-|    \\/  \\/  |-----",5);
        println("-|            |-----",5);
        println("--\\    ||||  /------",5);
        println("----\\______/--------",5);
    }

    public void printDeadGolem() throws InterruptedException{
        println("---,________,-------",5);
        println("--/   __  __ \\------",5);
        println("-|    ~~  ~~  |-----",5);
        println("-|            |-----",5);
        println("--\\    ||||  /------",5);
        println("----\\______/--------",5);
    }

    public void printKnight() throws InterruptedException{
        println("------________------",5);
        println("-----/    |   \\-----",5);
        println("----|   __+__ |-----",5);
        println("----|    . .  |-----",5);
        println("----|   . . . |-----",5);
        println("-----\\_______/------",5);
    }

    public void printAngryKnight() throws InterruptedException{
        println("------________------",5);
        println("-----/ #  |   \\-----",5);
        println("----|   __+__ |-----",5);
        println("----|    . .  |-----",5);
        println("----|   . . . |-----",5);
        println("-----\\_______/------",5);
    }

    public void printDeadKnight() throws InterruptedException{
        println("------________------",5);
        println("-----/    |   \\-----",5);
        println("----|   ==+== |-----",5);
        println("----|    . .  |-----",5);
        println("----|   . . . |-----",5);
        println("-----\\_______/------",5);
    }

    public void printWitch() throws InterruptedException{
        println("------_______-------",5);
        println("-----/   ____\\------",5);
        println("----|   /-----|-----",5);
        println("----|  |------|-----",5);
        println("----|   \\  __ |-----",5);
        println("-----\\__|    /------",5);
    }

    public void printAngryWitch() throws InterruptedException{
        println("------_______-------",5);
        println("-----/ # ____\\------",5);
        println("----|   /-----|-----",5);
        println("----|  |------|-----",5);
        println("----|   \\  ^  |-----",5);
        println("-----\\__|    /------",5);
    }

    public void printDeadWitch() throws InterruptedException{
        println("------_______-------",5);
        println("-----/  #_^__\\------",5);
        println("----| x /-----|-----",5);
        println("----|  |------|-----",5);
        println("----|/- \\  _  |-----",5);
        println("-----\\__|    /------",5);
    }

    public void printTitle() throws InterruptedException{
        println("  ______  ______",5);
        println(" /  ____||  ____|",5);
        println(" |  |    | |____",5);
        println(" |  |    |  ____|",5);
        println(" |  |___ | |____",5);
        println(" \\______||______| The Awakening...",5);
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
     * Prints given String with a pause
     */
    public void print(String str, int delay,int pause) throws InterruptedException{
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            Thread.sleep(delay);
        }
        Thread.sleep(pause);
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

    /**
     * Prints given String but with a line added in the end
     */
    public void println(String str, int delay,int pause) throws InterruptedException{
        for(int i=0;i<str.length();i++){
            System.out.print(str.charAt(i));
            Thread.sleep(delay);
        }
        Thread.sleep(pause);
        System.out.println();
    }

    public void printBrab() throws InterruptedException{
        println("---------------------",5);
        println("-------______--------",5);
        println("---V--/      \\--V----",5);
        println("----\\{   o.o  }/-----",5);
        println("------///-~-\\\\\\------",5);
        println("---------------------",5);
    }

    public void printCrab() throws InterruptedException{
        println("--------____---------",5);
        println("-------{    }--------",5);
        println("---V--{      }--V----",5);
        println("----\\{   o.o  }/-----",5);
        println("------///-~-\\\\\\------",5);
        println("---------------------",5);
    }

    public void printShellCrab() throws InterruptedException{
        println("--------____---------",5);
        println("-------{    }--------",5);
        println("------{      }-------",5);
        println("-----{   -.-  }------",5);
        println("------<<<-~->>>------",5);
        println("---------------------",5);
    }

    public void printAngryCrab() throws InterruptedException{
        println("--------____---------",5);
        println("-------{    }--------",5);
        println("---V--{      }--V----",5);
        println("----\\{   >.<  }/-----",5);
        println("------///-~-\\\\\\------",5);
        println("---------------------",5);
    }

    public void printDeadCrab() throws InterruptedException{
        println("--------____---------",5);
        println("-------{    }--------",5);
        println("---V--{      }--V----",5);
        println("----\\{   ~.~  }/-----",5);
        println("------///-~-\\\\\\------",5);
        println("---------------------",5);
    }

    public void healerTalk() throws InterruptedException{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        if(a==1){println("Priestess:Are you hurt? I can help you.",30,500);}
        else if(a==2){println("Priestess:Need a potion or anything?",30,500);} 
        else if(a==3){println("Priestess:I recommend health potions",30,500);} 
        else if(a==4){println("Priestess:I give a discount for a full heal",30,500);} 
        else if(a==5){println("Priestess:Is there anything I can help you with?",30,500);} 
    }

    public void smithTalk() throws InterruptedException{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        if(a==1){println("Blacksmith:Armors, Weapons, I gottem all",30,500);}
        else if(a==2){println("Blacksmith:Needa upgrade? I can help, but for a price",30,500);}
        else if(a==3){println("Blacksmith:Things here cost alot, too bad for you",30,500);}
        else if(a==4){println("Blacksmith:I'll give you a discount of 0% off today.",30,500);}
        else if(a==5){println("Blacksmith:Buy sumthing, or get out of my shop.",30,500);}
    }

    public void tavernTalk() throws InterruptedException{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        if(a==1){println("Bartender:Let me tell you a secret, these beers don't get you drunk",30,500);}
        else if(a==2){print("Bartender:Beer here is cheap...",30,500);println("So buy some. Or get something else...",30,500);}
        else if(a==3){println("Bartender:I only sell beer... haha...",30,500);}
        else if(a==4){println("Bartender:Need help? Don't ask me.",30,500);}
        else if(a==5){println("Bartender:Welcome to the tavern!",30,500);}
    }

    public void tavernSecret() throws InterruptedException{
        println("Bartender:Hey, never thought you would be the kind of person who would be interested in this",30,500);
        println("Bartender:Follow me...",30,500);
    }

    public void setName1(String name){
        name1=name;
    }

    public void setName2(String name){
        name2=name;  
    }

    public void setName3(String name){
        name3=name;
    }

    public String getName1(){
        return name1;
    }

    public String getName2(){
        return name2;
    }

    public String getName3(){
        return name3;
    }

    public void slaveTalk() throws InterruptedException{
        Random RN= new Random();
        int a=RN.nextInt(3)+1;
        if(!name1.equals("")){
            if(a==1){println("Mimi:You seem nice "+name1+". I'm glad.",30,500);}
            else if(a==2){println("Mimi:I'm sure I'll find something this time.",30,500);}
            else if(a==3){println("Mimi:I always dreamed of one day being able to fight like you.",30,500);}
        }
        if(!name2.equals("")){
            if(a==1){println("Sasha:Have you ever felt bad killing these monsters?",30,500);}
            else if(a==2){println("Sasha:I have much to learn. I don't feel that useful to you "+name2+"",30,500);}
            else if(a==3){println("Sasha:I'll try to not get in your way Master.",30,500);}
        }
        if(!name3.equals("")){
            if(a==1){println("Mary:You seem really quiet sometimes. I like that.",30,500);}
            else if(a==2){println("Mary:You never do anything to me "+name3+"...",30,500);}
            else if(a==3){println("Mary:I can't help you in your battles. Don't die "+name3+"",30,500);}
        }
    }

    public void mimiTalk1() throws InterruptedException{
        Random RN= new Random();
        int a=RN.nextInt(3)+1;
        if(a==1){println("Mini:Master! I found some more gold!",30,500);}
        else if(a==2){print("Mini:I-I didn't find much, but I hope you like it "+name1+"",30,500);}
        else if(a==3){println("Mini:"+name1+"! There's some gold over here!",30,500);}
    }

    public void mimiTalk2() throws InterruptedException{
        Random RN= new Random();
        int a=RN.nextInt()+1;
        if(a==1){println("Mini:I-I'm sorry "+name1+", but I couldn't find anything",30,500);}
        else if(a==2){print("Mini:I can't find anymore gold "+name1+"...",30,500);}
    }

    public void sashaTalk() throws InterruptedException{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        if(a==1){println("Sasha:I got you!",30,500);}
        else if(a==2){print("Sasha:Master! There's an opening!",30,500);}
        else if(a==3){println("Sasha:Too slow!",30,500);}
        else if(a==4){println("Sasha:"+name2+"! Let me!",30,500);}
        else if(a==5){println("Sasha:Back off fiend!",30,500);}
    }

    public void maryTalk() throws InterruptedException{
        Random RN= new Random();
        int a=RN.nextInt(5)+1;
        if(a==1){print("Mary:You always getting hurt "+name3+".",30,500);print(" Let me help you...",30,500);}
        else if(a==2){print("Mary:How about we rest for a bit "+name3+"...",30,500);}
        else if(a==3){print("Mary:Your hurt Master.",30,500);println("Let me help you feel better...",30,500);}
        else if(a==4){println("Mary:You're not in a rush right now. Let's take a break.",30,500);}
        else if(a==5){print("Mary:"+name3+"! You're hurt!",30,500);println("Sit down. Let me take care of everything",30,500);}
    }
}

