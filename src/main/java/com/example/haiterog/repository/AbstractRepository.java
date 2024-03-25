package com.example.haiterog.repository;

import com.example.haiterog.domain.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractRepository<T extends Entity> implements IRepository<T>
{
    protected List<T> data = new ArrayList<>();

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(data).iterator();
    }
}
