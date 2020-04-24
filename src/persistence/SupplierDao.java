package persistence;

import presentation.models.Product;
import presentation.models.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao implements Dao<Supplier>
{
    @Override
    public Object get(long id)
    {
        return null;
    }

    public List<Supplier> getAll()
    {
        try
        {
            Connection connection = Database.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblSupplier");

            List<Supplier> suppliers = new ArrayList<>();

            while (rs.next())
            {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt(1));
                supplier.setName(rs.getString(2));
                supplier.setPhone(rs.getString(3));
                supplier.setDeliveryTime(rs.getString(4));
                suppliers.add(supplier);
            }

            return suppliers;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void save(Supplier supplier)
    {

    }

    @Override
    public void update(Supplier supplier)
    {

    }

    @Override
    public void delete(Supplier supplier)
    {

    }
}
