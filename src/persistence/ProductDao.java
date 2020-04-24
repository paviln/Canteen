package persistence;

import presentation.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao<Product>
{
    @Override
    public Product get(long id)
    {
        try
        {
            Connection connection = Database.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblProduct WHERE fldProductId=" + id);
            if (rs.next())
            {
                return extractProduct(rs);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAll()
    {
        try
        {
            Connection connection = Database.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblProduct");
            List<Product> products = new ArrayList<>();
            while (rs.next())
            {
                Product product = extractProduct(rs);
                products.add(product);
            }
            return products;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Product product)
    {
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tblProduct (fldName, fldCategory, fldPrice, fldCurrentStock, fldMinimumStock, fldSupplierId) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, product.getName());
            ps.setInt(2, product.getCategory());
            ps.setString(3, product.getPrice());
            ps.setInt(4, product.getCurrentStock());
            ps.setInt(5, product.getMinimumStock());
            ps.setInt(6, product.getSupplierId());
            ps.execute();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product)
    {
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE tblProduct SET fldName = ?, fldPrice = ?, fldCategory = ?, fldCurrentStock = ?, fldMinimumStock = ?, fldSupplierId = ? WHERE fldProductId = ? ");
            ps.setString(1, product.getName());
            ps.setString(2, product.getPrice());
            ps.setInt(3, product.getCategory());
            ps.setInt(4, product.getCurrentStock());
            ps.setInt(5, product.getMinimumStock());
            ps.setInt(6, product.getSupplierId());
            ps.setInt(7, product.getId());
            ps.execute();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product product)
    {
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tblProduct WHERE fldProductId=?");
            ps.setInt(1, product.getId());
            ps.execute();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Product extractProduct(ResultSet rs) throws SQLException
    {
        Product product = new Product();
        product.setId(rs.getInt("fldProductId"));
        product.setName(rs.getString("fldName"));
        product.setPrice(rs.getString("fldPrice"));
        product.setCurrentStock(rs.getInt("fldCurrentStock"));
        product.setMinimumStock(rs.getInt("fldMinimumStock"));
        product.setSupplierId(rs.getInt("fldSupplierId"));
        return product;
    }
}
