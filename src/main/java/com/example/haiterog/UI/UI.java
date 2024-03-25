package com.example.haiterog.UI;

import com.example.haiterog.domain.Intrebare;
import com.example.haiterog.repository.RepositoryException;
import com.example.haiterog.service.ServiceIntrebare;

import java.util.Scanner;

public class UI
{
    private ServiceIntrebare serviceI = new ServiceIntrebare();

    public void afisareIntrebare()
    {
        try
        {
            for (Intrebare i: this.serviceI.getAllI())
            {
                System.out.println(i);
            }
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void addIntrebare()
    {
        try
        {
            int id;
            String text;
            String raspunsA;
            String raspunsB;
            String raspunsC;
            String corect;
            int punctaj;
            System.out.println("Id intrebare:");
            Scanner cin = new Scanner(System.in);
            id = cin.nextInt();
            System.out.println("Text:");
            text = cin.next();
            System.out.println("Raspuns A:");
            raspunsA = cin.next();
            System.out.println("Raspuns B:");
            raspunsB = cin.next();
            System.out.println("Raspuns C:");
            raspunsC = cin.next();
            System.out.println("Raspuns corect:");
            corect = cin.next();
            System.out.println("Punctaj:");
            punctaj = cin.nextInt();
            this.serviceI.addIntrebare(id,text,raspunsA,raspunsB,raspunsC,corect,punctaj);
            System.out.println("Intrebarea a fost adaugata");
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void removeIntrebare()
    {
        try
        {
            int id;
            System.out.println("Id intrebare:");
            Scanner cin = new Scanner(System.in);
            id = cin.nextInt();
            this.serviceI.removeIntrebare(id);
            System.out.println("Intrebarea a fost stearsa");
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateIntrebare()
    {
        try
        {
            int id;
            String text;
            String raspunsA;
            String raspunsB;
            String raspunsC;
            String corect;
            int punctaj;
            System.out.println("Id intrebare:");
            Scanner cin = new Scanner(System.in);
            id = cin.nextInt();
            System.out.println("Text:");
            text = cin.next();
            System.out.println("Raspuns A:");
            raspunsA = cin.next();
            System.out.println("Raspuns B:");
            raspunsB = cin.next();
            System.out.println("Raspuns C:");
            raspunsC = cin.next();
            System.out.println("Raspuns corect:");
            corect = cin.next();
            System.out.println("Punctaj:");
            punctaj = cin.nextInt();
            this.serviceI.updateIntrebare(id,text,raspunsA,raspunsB,raspunsC,corect,punctaj);
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void addStartI()
    {
        try
        {
            this.serviceI.addIntrebare(1,"Ce este albina?","fiinta","caine","insecta","insecta",10);
            this.serviceI.addIntrebare(2,"Ce sunt eu?","om","caine","insecta","om",5);
            this.serviceI.addIntrebare(3,"Ce este albina?","fiinta","caine","insecta","insecta",10);
            this.serviceI.addIntrebare(4,"Ce este albina?","fiinta","caine","insecta","insecta",10);
            this.serviceI.addIntrebare(5,"Ce este albina?","fiinta","caine","insecta","insecta",10);
            System.out.println("Cele 5 intrebari au fost adaugate");
        }
        catch (RepositoryException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void optiuni()
    {
        System.out.println("1.Adauga intrebare");
        System.out.println("2.Sterge intrebare");
        System.out.println("3.Modifica intrebare");
        System.out.println("4.Adauga 5 intrebari");
        System.out.println("5.Afiseaza intrebari");
        System.out.println("0.Inchide program");
    }

    public void menu()
    {
        boolean ok = true;
        while (ok)
        {
            optiuni();
            int opt;
            Scanner cin = new Scanner(System.in);
            System.out.println("Optiunea ta:");
            opt = cin.nextInt();
            switch (opt)
            {
                case 1:
                    addIntrebare();
                    break;
                case 2:
                    removeIntrebare();
                    break;
                case 3:
                    updateIntrebare();
                    break;
                case 4:
                    addStartI();
                    break;
                case 5:
                    afisareIntrebare();
                    break;
                case 0:
                    ok = false;
                    break;
            }
        }
    }
}
