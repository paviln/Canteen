package persistence;

import presentation.models.Category;
import presentation.models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements Dao<Category>
{
    private Connection connection = Database.getConnection();

    @Override
    public Object get(long id)
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
    public List<Category> getAll()
    {
        try
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tblCategory");

            List<Category> categories = new ArrayList<>();

            while (rs.next())
            {
                Category category = extractProduct(rs);
                categories.add(category);
            }

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

    }

    @Override
    public void update(Category category, String[] params)
    {

    }

    @Override
    public void delete(Category category)
    {

    }

    private Category extractProduct(ResultSet rs) throws SQLException
    {
        Category category = new Category();
        category.setName(rs.getString("fldName"));

        return category;
    }
}
