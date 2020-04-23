package persistence;

import presentation.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao<Product>
{
    private Connection connection = Database.getConnection();

    @Override
    public Product get(long id)
    {
        try
        {
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
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tblProduct (fldName, fldCategory, fldPrice, fldCurrentStock, fldMinimumStock, fldSupplierId) VALUES (?, ?, ?, ?, ?, ?");

            ps.setString(1, product.getName());
            ps.setInt(2, product.getCategory());
            ps.setString(3, product.getPrice());
            ps.setInt(4, product.getCurrentStock());
            ps.setInt(5, product.getMinimumStock());
            ps.setInt(6, product.getSupplierId());

            ps.executeQuery();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product, String[] params)
    {

    }

    @Override
    public void delete(Product product)
    {

    }

    private Product extractProduct(ResultSet rs) throws SQLException
    {
        Product product = new Product();
        product.setId(rs.getInt("fldProductId"));
        product.setName(rs.getString("fldName"));
        product.setPrice(rs.getString("fldPrice"));
        product.setCurrentStock(rs.getInt("fldCurrentStock"));
        product.setMinimumStock(rs.getInt("fldMinimumStock"));
        return product;
    }
}
