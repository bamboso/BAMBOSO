import fileSystem.FileSystem;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        FileSystem dupa = new FileSystem();
        String string;
        do {
            System.out.println("command: ");
            
           
            Scanner in = new Scanner(System.in);
            string = in.nextLine();
            String[] tab = string.split(" ");
           
           
            if(tab[0].equals("DEL"))   ///////////////////////////////////////////////////////////////////usuwanie pliku
            {
                if(tab.length==2){
                    dupa.deleteFile(tab[1]);
                    System.out.println("usuwanie pliku");
                }
                else
                {
                    System.out.println("nieprawidlowe wywolanie komendy");
                }
            }
           
            else if(tab[0].equals("DIR"))   ///////////////////////////////////////////////////////////////////pokaz katalog glowny
            {
                if(tab.length==1){
                    dupa.showMainCatalog();
                    System.out.println("wyswietlanie katalogu: ");
                }
                else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
            else if(tab[0].equals("COPY")&&tab[1].equals("NULL"))  ////////////////////////////////////////////tworzenie pustego pliku
            {
                if(tab.length==3){
                    dupa.createEmptyFile(tab[2]);
                    System.out.println("tworzenie pustego pliku");
                }
                else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
           
            else if(tab[0].equals("OVR"))  ///////////////////////////////////////////////////////////////////dopisywanie danych do pliku
            {
                if(tab.length>2)
                    {
                        String temp = "";
                        for(int i=2;i<tab.length;i++)
                        {
                            temp+=tab[i];
                            temp+=" ";
                        }
                    dupa.appendToFile(tab[1], temp);
                    System.out.println("dopisywanie danych do pliku");
                    }
                else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
           
            else if(tab[0].equals("CRF"))  ///////////////////////////////////////////////////////////////////tworzenie pliku z dopisaniem danych
            {
                if(tab.length>2)
                    {String temp = "";
                    for(int i=2;i<tab.length;i++)
                    {
                        temp+=tab[i];
                        temp+=" ";
                    }
                    dupa.createFileWithContent(tab[1], temp);
                    System.out.println("tworzenie pliku z dopisaniem danych");
                    }
                else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
           
            else if(tab[0].equals("OPF"))   ////////////////////////////////////////////////////////////////////otwieranie pliku
            {
                if(tab.length==2){
                    dupa.getFileContent(tab[1]);
                    System.out.println("otwieranie pliku");
                }
                else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
           
            else if(tab[0].equals("HELP"))   ////////////////////////////////////////////////////////////////////pomoc
            {
                if(tab.length==1) {
                   
                    System.out.println("DEL nazwa_pliku: usuwanie wskazanego pliku; ");
                    System.out.println("COPY NULL nazwa_pliku: tworzenie pustego pliku o wskazanej nazwie; ");
                    System.out.println("OVR nazwa_pliku: dopisanie danych do danego pliku; ");
                    System.out.println("CRF nazwa_pliku dane: tworzenie pliku z dopisaniem danych; ");
                    System.out.println("OPF nazwa_pliku: otwieranie wskazanego pliku; ");
                    System.out.println("DIR: wyswietlanie katalogu glownego; ");
                    System.out.println("MEMORY: sprawdzanie stanu pamieci; ");
                    System.out.println("SHV: sprawdzanie zawartosci wektora bitowego; ");
                    System.out.println("CHKDSK: sprawdzanie zawartości dysku; ");
                    System.out.println("TASKLIST: sprawdzanie listy procesow; ");
                    System.out.println("TASKKILL: nazwa_procesu: zabijanie procesu; ");
                    System.out.println("GO: kolejny krok wykonywanego procesu; ");
                    System.out.println("START nazwa_procesu grupa_procesu nazwa_pliku: stworzenie procesu; ");
                   
                }else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
           
            else if(tab[0].equals("MEMORY"))   //////////////////////////////////////////////////////////////////pamięć
            {
                if(tab.length==1) {
                   
                    //funkcja pokazująca stan pamięci
                    System.out.println("stan pamieci");
               
                   
                }
                else
                    System.out.println("nieprawidlowe wywolanie komendy");
            
            }
           
            else if(tab[0].equals("CHKDSK"))   //////////////////////////////////////////////////////////////////dysk
            {
                if(tab.length==1) {
                   
                    dupa.showDiskAndVector();
                    System.out.println("zawartość dysku");
                    
                   
                }else
                    System.out.println("nieprawidlowe wywolanie komendy");
            
            }
            else if(tab[0].equals("SHV"))   //////////////////////////////////////////////////////////////////dvector bitowy
            {
                if(tab.length==1) {
                   
                    dupa.showBitVector();
                    System.out.println("Wektor Bitowy: ");
                    
                   
                }else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
           
            else if(tab[0].equals("TASKLIST"))   ///////////////////////////////////////////////////////////////lista procesów
            {
                if(tab.length==1) {
                   
                    //funkcja pokazująca liste procesów
                    System.out.println("lista procesow");
                   
                }else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
           
            else if(tab[0].equals("TASKKILL"))   /////////////////////////////////////////////////////////////////zabijanie procesu
            {
                if(tab.length==2)
                    //tab[1] to nazwa procesu
                   
                    System.out.println("zabicie procesu");
                else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
           
            else if(tab[0].equals("GO"))   /////////////////////////////////////////////////////////////////////kolejny krok w procesie
            {
                if(tab.length==1) {
                   
                    //stan procesu po wykonaniu jednego kroku
                    System.out.println("kolejny krok w procesie");
               
                   
                }
                else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
           
           
           
            else if(tab[0].equals("START"))   //////////////////////////////////////////////////////////////////tworzenie procesu
            {
                if(tab.length==4) {
                    /*
                     tab[1] nazwa procesu
                     tab[2] grupa procesu
                     tab[3] nazwa pliku
                     */
                   
                    //tworzenie procesu
                    System.out.println("tworzenie procesu");
               
                }else
                    System.out.println("nieprawidlowe wywolanie komendy");
            }
            else
            {
                System.out.println("NIEPOPRAWNA KOMENDA ZIOMUS");
            }
           
        }while(!string.equals("exit"));
           
       
    }
 
}