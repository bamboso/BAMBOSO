package proces;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import MemoryManagment.MemoryManagment;

public class PCB {

    MemoryManagment memory;
    public String pid;
    public String file;
    public int priority;
    public int uspri;
    public int A = 0, B = 0, C = 0, D = 0;
    public int commandCounter = 0;
    public int pri; //P
    public int nice; //P parametr nadawany przez uzytkownika
    public int cpu; //P wykorzystanie procesora
    public int pRA;  //P
    public int pRB;  //P
    public int pPC;  //P
    public boolean pZF;  //P
    public int state;//1-new, 2-ready,3-waiting,4-running,5-finished
    public PCB parent = null;
    public ArrayList<PCB> children = new ArrayList<PCB>();
    static public ArrayList<PCB> proceses = new ArrayList<PCB>();

    //struct files_struct files; /* list of open files */ 

    //struct mm_struct mm; /* address space of this process */


    //public ArrayList<Pipe> pipes = new ArrayList<>();

    // public IPC childPipe = null;


    public PCB(String PID, MemoryManagment memory) {
        this.pid = PID;
        this.memory = memory;
        proceses.add(this);
        priority = 8;
        state = 0;
        uspri = 0; //P
    }

    public void fork(String PID, String file) throws FileNotFoundException { //Tworzenie procesu
        boolean x = true;
        for (int i = 0; i < proceses.size(); i++) {
            if (proceses.get(i).pid == PID) {
                x = false;
            }
        }
        if (x) {
            PCB child = new PCB(PID, memory);
            child.parent = this;
            child.state = 1;
            child.file = file;
            children.add(child);
            uspri = nice; //P
            child.priority = 0; //P
            pRA = child.pRA;
            pRB = child.pRB;
            pPC = child.pPC;
            pZF = child.pZF;
            //proceses.add(child);
            child.memory.addProcess(child.pid, file);
            System.out.println("stworzono proces o ID:" + child.pid);
        } else {
            System.out.println("proces o podanym ID ju� istnieje");
        }
    }

    public void exit() { //usuwanie procesu
        this.state = 5;
        proceses.remove(this);
        if (parent == null) {
        } else {
            parent.kill(this);
        }
        this.parent = null;
    }

    public PCB getproces(String PID) { //wyszukiwanie procesu
        //PCB temp;
        int x = 0;
        for (int i = 1; i < proceses.size(); i++) {
            if (proceses.get(i).getpid().equals(PID)) {
                x = i;
                //PCB temp=proceses.get(i);
            }
        }
//        if (x > 0) {
//        } else {
//
////              System.out.println("nie odnaleziono procesu");
//
//        }
        return proceses.get(x);
    }

    void orphan() {
        if (this.parent == null) {
            this.parent = proceses.get(0);
        }
    }

    public void showproceses() {
        for (int i = 1; i < proceses.size(); i++) {
            System.out.print("proces PID:" + proceses.get(i).pid);
            System.out.print(" priorytet:" + proceses.get(i).priority);
            switch (proceses.get(i).getState()) {
                case 1:
                    System.out.println("nowy");
                case 2:
                    System.out.println("gotowy");
                case 3:
                    System.out.println("oczekujacy");
                case 4:
                    System.out.println("wykonywany");
                case 5:
                    System.out.println("skonczony");
            }
        }
    }

    public String working() {
        String s = "";
        for (int i = 1; i < proceses.size(); i++) {
            if (proceses.get(i).state == 4)
                s = proceses.get(i).pid;
        }
        return s;
    }

    public void kill(PCB a) {
        a = null;
    }

    public String getpid() {
        return this.pid;
    }

    void setpriority(int a) {
        priority = a;
    }

    public void setstate(int a) {
        this.state = a;
    }

    public int getState() {
        return this.state;
    }

}
