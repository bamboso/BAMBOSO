/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proces;

/**
 *
 * @author Adrian
 */
import Scheduler.Scheduler;
public class Proces {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    PCB er=new PCB("p0");
    Scheduler z= new Scheduler();
    System.out.println(er.proceses);
    er.fork("p2");
    er.fork("p3");

    System.out.println(er.children);
    System.out.println(er.proceses);
    PCB a;
    //System.out.println(a);
         a=er.children.get(0);
     a.fork("p5");
     a.fork("p6");
     a.fork("p7");
 
      System.out.println(a.children);
      z.add_to_ready_list(er.getproces("p5"));
      z.add_to_ready_list(er.getproces("p2"));
      z.add_to_ready_list(er.getproces("p6"));
      z.add_to_ready_list(er.getproces("p7"));
      z.add_to_ready_list(er.getproces("p3"));
      er.showproceses();
      z.przelicz();
      
    //er.getproces("p3").exit();
//for(int i=0; i<3; i++){
//System.out.println("Kolejna komórka to: "+a.children.get(i).pid);
   // }
er.showproceses();
    }
}
