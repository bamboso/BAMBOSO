/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SynchronizacjaProcesów;

import proces.Proces;

public class SynchronizacjaProcesów {

    public String nazwa;

    public int proceszpamieci = 0;
    public int proceszkolejkiprocesow = 0;


    //public int suma = 0;
    public final Proces p;
    private final Lock lock;

    public SynchronizacjaProcesów(String nazwa, Proces p, Lock lock) {
        this.nazwa = nazwa;
        this.p = p;
        this.lock = lock;
    }

    /*@Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            wejsciedosekcjikrytycznej();
            System.out.println("tutaj " + this.nazwa);
            suma = sumaN();

            wyjsciezsekcjikrytycznej();
        }

    }
*/
    public boolean TestAndSet(Boolean lock) {
        if (lock == true) {
            System.out.println("siedzi w petli");
            return true;
        }
        System.out.println("wychodzi z petli");
        return false;
    }

    public void wejsciedosekcjikrytycznej() throws InterruptedException {
        System.out.println(nazwa + " weszlo do sekcji krytycznej");
        while (TestAndSet(lock.lock)) {
            //
            Thread.sleep(500);
            System.out.println("break");
            break;
        }
        lock.lock = true;
    }

    public void wyjsciezsekcjikrytycznej() {
        System.out.println(nazwa + " wyszlo z sekcji krytycznej");
        lock.lock = false;
    }

    public int sumaN(int suma) {
        //System.out.println(proceszkolejkiprocesow);
        suma = suma + 1;
        return suma;
    }

    public int sumaM(int suma) {
        suma = suma - 1;
        return suma;
    }

    //public int getSuma() {
    //return suma;
    //}

//    public static void main(String[] args) throws InterruptedException {
//
//        int suma = 0;
//
//        Lock lock = new Lock();
//        Proces jeden = new Proces();
//        Proces dwa = new Proces();
//
//        SynchronizacjaProcesów S1=new SynchronizacjaProcesów("S1",jeden,lock);
//        SynchronizacjaProcesów S2=new SynchronizacjaProcesów("S2",dwa,lock);
//
//
//
//        for (int i = 1; i <= 3; i++) {
//            S1.wejsciedosekcjikrytycznej();
//            suma = S1.sumaN(suma);
//            System.out.println("s "+suma);
//            S2.wejsciedosekcjikrytycznej();
//            S1.wyjsciezsekcjikrytycznej();
//            S2.wejsciedosekcjikrytycznej();
//            suma = S2.sumaN(suma);
//            System.out.println("s "+suma);
//            S2.wyjsciezsekcjikrytycznej();
//        }


//    }

}
