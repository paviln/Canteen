package persistence;

import presentation.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements Dao<Category>
{
    @Override
    public Category get(int id)
    {
        try
        {
            Connection connection = Database.getConnection();
            Statement stmt = Database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblProduct WHERE fldProductId=" + id);

            if (rs.next())
            {
                return extractCategory(rs);
            }

            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Category> getAll()
    {
        try
        {
            Connection connection = Database.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblCategory");

            List<Category> categories = new ArrayList<>();

            while (rs.next())
            {
                Category category = extractCategory(rs);
                categories.add(category);
            }

            connection.close();

            return categories;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void save(Category category)
    {
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tblCategory (fldName) VALUES (?)");
            ps.setString(1, category.getName());
            ps.execute();
            connection.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category category)
    {
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE tblCategory SET fldName = ? WHERE fldCategoryId = ?");
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.execute();
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category category)
    {
        try
        {
            Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tblCategory WHERE fldName=?");
            ps.setString(1, category.getName());
            ps.execute();
            connection.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Category extractCategory(ResultSet rs) throws SQLException
    {
        Category category = new Category();
        category.setId(rs.getInt("fldCategoryId"));
        category.setName(rs.getString("fldName"));
        return category;
    }
}
