package com.example.haiterog.Teste;

import org.junit.Test;
import com.example.haiterog.repository.RepositoryException;
import com.example.haiterog.service.ServiceIntrebare;

public class TesteService {
    @Test
    public void serviceIntrebare()
    {
        ServiceIntrebare serviceIntrebare = new ServiceIntrebare();
        boolean thrown = false;
        try
        {
            serviceIntrebare.addIntrebare(6,"Ce sunt eu?","om","caine","insecta","om",5);
            assert serviceIntrebare.getAllI().size() == 6;
        }
        catch (RepositoryException e)
        {
            thrown= true;
        }
        assert thrown == false;
        try
        {
            serviceIntrebare.removeIntrebare(6);
            assert serviceIntrebare.getAllI().size() == 5;
        }
        catch (RepositoryException e)
        {
            thrown = true;
        }
        assert thrown == false;
        try
        {
            serviceIntrebare.removeIntrebare(6);
        }
        catch (RepositoryException e)
        {
            thrown = true;
        }
        assert thrown ==true;
    }
}

