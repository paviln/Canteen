package persistence;

import presentation.models.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao implements Dao<Supplier>
{
    @Override
    public Supplier get(int id)
    {
        try
        {
            Connection connection = Database.getConnection();
            Statement stmt = Database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblSupplier WHERE fldSupplierId=" + id);

            if (rs.next())
            {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt(1));
                supplier.setName(rs.getString(2));
                supplier.setPhone(rs.getString(3));
                supplier.setDeliveryTime(rs.getString(4));
                return supplier;
            }

            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

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
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tblSupplier (fldName, fldPhone, fldDeliveryTime) VALUES (?, ?, ?)");
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getDeliveryTime());
            ps.execute();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Supplier supplier)
    {
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE tblSupplier SET fldName = ?, fldPhone = ?, fldDeliveryTime = ? WHERE fldSupplierId = ? ");
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getDeliveryTime());
            ps.setInt(4, supplier.getId());
            ps.execute();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Supplier supplier)
    {
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tblSupplier WHERE fldSupplierId=?");
            ps.setInt(1, supplier.getId());
            ps.execute();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
