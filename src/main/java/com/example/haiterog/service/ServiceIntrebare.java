package com.example.haiterog.service;

import com.example.haiterog.domain.*;
import com.example.haiterog.repository.*;

import java.util.Collection;
import java.util.Objects;

public class ServiceIntrebare
{
    private IRepository<Intrebare> repoIntrebare = new MemoryRepository<Intrebare>();

    SettingsProdus setari = SettingsProdus.getInstance();

    public ServiceIntrebare()
    {
        if (Objects.equals(setari.getRepoType(), "memory"))
        {
            this.repoIntrebare = new MemoryRepository<Intrebare>();
        }
        if(Objects.equals(setari.getRepoType(),"db")){
            this.repoIntrebare = new RepoIntrebareDB(setari.getRepoFile());
        }
    }

    public void addIntrebare(int id , String text,String raspunsA,String raspunsB,String raspunsC,String corect,int punctaj) throws RepositoryException
    {
        Intrebare i= new Intrebare(id,text,raspunsA,raspunsB,raspunsC,corect,punctaj);
        repoIntrebare.add(i);
    }

    public void removeIntrebare(int id) throws RepositoryException
    {
        repoIntrebare.remove(id);
    }

    public void updateIntrebare(int id , String text,String raspunsA,String raspunsB,String raspunsC,String corect,int punctaj) throws RepositoryException
    {
        Intrebare i= new Intrebare(id,text,raspunsA,raspunsB,raspunsC,corect,punctaj);
        repoIntrebare.update(i);
    }

    public Collection<Intrebare> getAllI() throws RepositoryException
    {
        return repoIntrebare.getAll();
    }

    public Intrebare getIntrebare(int id) throws RepositoryException
    {
        return this.repoIntrebare.getById(id);
    }
}
