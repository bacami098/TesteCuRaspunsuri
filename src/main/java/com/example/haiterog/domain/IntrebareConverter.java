package com.example.haiterog.domain;

public class IntrebareConverter implements EntityConverter<Intrebare>
{

    @Override
    public String toString(Intrebare o)
    {
        return o.getId() + "," + "," + o.getText()+ "," + o.getRaspunsA()+"," + o.getRaspunsB()+"," + o.getRaspunsC()+"," + o.getCorect()+ "," + o.getPunctaj();
    }

    @Override
    public Intrebare fromString(String line)
    {
        String[] tokens = line.split(",");
        return new Intrebare(Integer.parseInt(tokens[0]), tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]));
    }
}
