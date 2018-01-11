package Komunikacja_Miedzyprocesowa;


import java.util.Vector;

import proces.PCB;

/**
 * @author Olga Kryspin
 */
public class IPC {
    private Vector<Pipe> pipes; //wszystkie istniejące łącza w systemie

    private int open(Pipe pipe, char sign) // otwiera w trybie:.. 
    {
        if (sign == 'w') // ...zapisu
        {
            pipe.zapis();
            return 1;
        }
        if (sign == 'r') // ...odczytu 
        {
            pipe.odczyt();
            return 1;
        } else {
            return -1;
        }
    }

    private int close(Pipe pipe) {
        boolean wynik = pipes.removeElement(pipe); // zwraca true kiedy znajdzie element i usuwa pipe

        if (wynik == true) {
            return 1;
        } else {
            return -1;
        }
    }

    ////////////////udostępniane innym ////////////////////////////////////////////////////
    public int openpipe() {
        Pipe p = new Pipe();
        pipes.add(p);
        p.des = pipes.indexOf(p); //indeks w tablicy potoków 

        return p.des;
    }

    public int closepipe(int des) //inaczej niż w prezentacji, ale jeszcze niepewne
    {
        this.close(pipes.elementAt(des));
        pipes.remove(des);

        return 1;
    }
    /////////////////////////////////////////////////////////

    // write/
    //1. Sprawdzam, czy łącze już istanieje
    // - jeżeli tak to zapisuje do niego 
    // - jeżeli nie to tworzę: 
    //         - sprawdzam czy procesy są spokrewnione, jeżeli nie to błąd, jeżeli tak to tworzę łącze 
//  2. Tworzenie łącza 
//          - dodaje do pipes nowy PIPE 
//          - otwieram w trybie do zapisu (open 'w')
//          
    // jeżeli łącze istnieje to sprawdzam czy jest puste czy zajęte
    // jeżeli za mało miejsca to proces musi czekać (komunikat wpisuje w cześci ) 
    // 3. po utworzeniu łacza gdy zapisuje to ustawiam pole lock 
    //4. kończe zapis na końcu unlock
    //-ustawiam pola occupied bytes i free bytes i pn 
    //5. zwracam faktyczną liczbę zapisanych danych 
    public int write(int rozmiar, String komunikat, String pn, int des) {
        boolean exists = false;


        for (int i = 0; i < pipes.size(); i++) {
            if (pipes.get(i).pn.equals(pn)) {
                exists = true;
            }
        }

        if (exists == false) {
            System.out.println("Takie lacze nie istnieje!");
            return -1;
        }

        //sprawdzam czy procesy są spokrewnione 
        //... jeżeli tak to dalej jezeli nie to :
        //  System.out.println("Procesy nie sa spokrewnione!");
        //  return -1;

        if (pipes.get(des).lock == true) {
            System.out.println("Lacze jest zablokowane"); // tutaj musze użyć synchro 
        } else {
            (pipes.get(des)).lock();
        }
        (pipes.get(des)).zapis();
        (pipes.get(des)).pn = pn;

        int length = komunikat.length();
        if (length == pipes.get(des).buffer_lenght) //kiedy komunikat zajmuje caly bufor 
        {
            (pipes.get(des)).Dane = komunikat;
            (pipes.get(des)).occupied_bytes = length;
            (pipes.get(des)).free_bytes = (pipes.get(des)).buffer_lenght - length;
        }
        //(pipes.get(des)).Dane = komunikat;

        return (pipes.get(des)).occupied_bytes;

    }
    //___________________________________________________________
    //read: 
    // 1. sprawdzam czy łącze istnieje i czy nie jest puste 
    // 2. czytam określoną liczbę znaków 
    // 3. 
    //-ustawiam pola occupied bytes i free bytes i pn 
    //4. zwracam faktyczną liczbę odczytanych danych 

    String read(int count, String pn) {
        boolean exists = false;
        String bufor;

        for (int i = 0; i < pipes.size(); i++) {
            if (pipes.get(i).pn.equals(pn)) {
                exists = true;
            }
        }

        if (exists == false) {
            System.out.println("Takie lacze nie istnieje!");
            return "-1";
        }

        int des = 0;
        Pipe firstElement = pipes.firstElement();
        des = pipes.indexOf(firstElement);

        if (pipes.get(des).occupied_bytes == 0) {
            System.out.println("Potok jest pusty!");
            // tu synchro proces powinien czekać !

        }

        if (count >= pipes.get(des).occupied_bytes) {
            bufor = pipes.get(des).Dane.substring(0, count - 1);
            pipes.get(des).free_bytes += bufor.length();

            return bufor;
        } else if (count < pipes.get(des).occupied_bytes) {

            bufor = pipes.get(des).Dane.substring(0, count);
            //zwraca tylko czesc stringa 
            // String partData = pipes.get(des).Dane.
            pipes.get(des).free_bytes += bufor.length();
            return bufor;
        } else {
            System.out.println("funkcja read - blad!");
            return "-1";
        }

    }

}
