package com.example.haiterog.repository;

import com.example.haiterog.domain.Entity;

import java.util.ArrayList;
import java.util.Collection;

public class MemoryRepository<T extends Entity> extends AbstractRepository<T>
{
    @Override
    public void add(T o) throws RepositoryException
    {
        if (o == null)
        {
            throw new IllegalArgumentException();
        }
        if (find(o.getId()) != null)
        {
            throw new DuplicateRepository("Exista deja un element cu acest id!");
        }
        this.data.add(o);
    }

    @Override
    public void remove(int id) throws RepositoryException
    {
        if (find(id) == null)
        {
            throw new RepositoryException("Nu exista elementul cu id-ul cautat");
        }
        this.data.removeIf(e -> e.getId() == id);
    }

    @Override
    public void update(T o) throws RepositoryException
    {
        if (o == null)
        {
            throw new IllegalArgumentException();
        }
        if (find(o.getId()) == null)
        {
            throw new RepositoryException("Nu exista elementul cu id-ul cautat");
        }
        for(int i  = 0;i<this.data.size();i++)
        {
            if(data.get(i).getId() == o.getId())
                data.set(i, o);
        }
    }

    @Override
    public T find(int id)
    {
        for (T e : this.data)
        {
            if (e.getId() == id)
            {
                return e;
            }
        }
        return null;
    }

    @Override
    public Collection<T> getAll()
    {
        return new ArrayList<T>(this.data);
    }

    @Override
    public int getSize()
    {
        return this.data.size();
    }

    @Override
    public T getById(int id) throws RepositoryException{
        for(T elem:this.data)
        {
            if(elem.getId() == id)
            {
                return elem;
            }
        }
        throw new RepositoryException("Nu exista element cu acest id");
    }
}
