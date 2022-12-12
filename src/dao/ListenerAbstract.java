package dao;

import java.util.ArrayList;

public abstract class ListenerAbstract implements Listener{
    
    abstract void setQuery(QueryBuilder b);
    @Override
    abstract public void update(ArrayList<String> data);
}