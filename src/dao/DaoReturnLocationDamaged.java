package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoReturnLocationDamaged extends ListenerAbstract implements Listener{
    
    String query;
    QueryBuilder b= new QueryBuilder();
    @Override
    public void update(ArrayList<String> data) {
        // TODO Auto-generated method stub
        b.returnLocationDamaged(data);
        setQuery(b);
        DaoExecQuery exec = new DaoExecQuery(DaoConnection.getInstance().getBase(), query);
        try {
            exec.sendquerry();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    void setQuery(QueryBuilder b) {
        this.query=b.getQuery();
    }
}