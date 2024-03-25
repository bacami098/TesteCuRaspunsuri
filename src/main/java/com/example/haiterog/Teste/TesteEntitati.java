package com.example.haiterog.Teste;

import com.example.haiterog.domain.Intrebare;
import org.junit.Test;

public class TesteEntitati
{
    @Test
    public void testIntrebare(){
        Intrebare i = new Intrebare(2,"Ce sunt eu?","om","caine","insecta","om",5);
        assert i.getId() == 2;
        assert "om".equals(i.getRaspunsA());
    }
}