/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proces;

import java.util.ArrayList;
import java.util.LinkedList;
import Scheduler.Scheduler;
import Komunikacja_Miedzyprocesowa.Pipe;
import Komunikacja_Miedzyprocesowa.IPC;
/**
 *
 * @author Adrian
 */

public class PCB {
    public String pid;
    public int priority;
    public int uspri;
    public int A=0, B=0, C=0, D=0;
    public int commandCounter=0;
    public int pri; //P
    public int nice; //P parametr nadawany przez uzytkownika
    public int cpu; //P wykorzystanie procesora
    public int pRA;  //P
    public int pRB;  //P
    public int pPC;  //P
    public boolean pZF;  //P
    
    public int state;//1-new, 2-ready,3-waiting,4-running,5-finished
    public PCB parent=null;
    public ArrayList<PCB> children=new ArrayList<PCB>();
    static public ArrayList<PCB> proceses=new ArrayList<PCB>();
    //struct files_struct files; /* list of open files */ 
    //struct mm_struct mm; /* address space of this process */
    
    public ArrayList<Pipe> pipes = new ArrayList<>();
    public IPC childPipe = null;
    
    public PCB(String pid){
        this.pid=pid;
        proceses.add(this);
        priority=8;
        state=0;
	uspri = 0; //P
    }
     public void fork(String PID){ //Tworzenie procesu
         boolean x=true;
         for(int i=0;i<proceses.size();i++){
             if(proceses.get(i).pid==PID){
                 x=false;
             }
         }
         if(x){
         PCB child=new PCB(PID);
         child.parent=this;
         child.state=1;
         children.add(child);
         uspri = nice; //P
	child.priority = 0; //P
	pRA = child.pRA;
	pRB = child.pRB;
	pPC = child.pPC;
	pZF = child.pZF;
        //proceses.add(child);
        System.out.println("stworzono proces o ID:"+child.pid);
         }
         else{
            System.out.println("proces o podanym ID już istnieje"); 
         }
     }
     public void exit(){ //usuwanie procesu
     this.state=5;
     proceses.remove(this);
     parent.kill(this);
     this.parent=null;
     
     }
     public PCB getproces(String PID){ //wyszukiwanie procesu
         //PCB temp;
         int x=0;
       for(int i=0;i<proceses.size();i++){
          if(proceses.get(i).pid==PID){
              //PCB temp=proceses.get(i);   
             x=i;
             }
       }
          if(x>0){}
          else{
              System.out.println("nie odnaleziono procesu"); 
          }
           
       return proceses.get(x);
     }
     void orphan(){
         if(this.parent==null){
             this.parent=proceses.get(0);
         }
     }
     public void showproceses(){
        for(int i=1;i<proceses.size();i++){
            System.out.print("proces PID:"+proceses.get(i).pid);
            System.out.print(proceses.get(i).priority+" priorytet:");
            if(proceses.get(i).state==1){
                System.out.println("nowy");
            }
                        if(proceses.get(i).state==2){
                System.out.println("gotowy");
            }
                    if(proceses.get(i).state==3){
                System.out.println("oczekujący");
            }
                   if(proceses.get(i).state==4){
                System.out.println("wykonywany");
            }
                   if(proceses.get(i).state==5){
                System.out.println("skończony");
            }
       }
     }
     public void kill(PCB a){
         a=null;
     }
     public String getpid(){
         return pid;
     }
     void setpriority(int a){
         priority=a;
     }
     public void setstate(int a){
         state=a;
     }
}
