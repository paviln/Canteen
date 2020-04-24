package domain;

import persistence.CategoryDao;
import persistence.SupplierDao;
import presentation.models.Category;
import presentation.models.Supplier;

import java.util.List;

public class CategoryService
{
    private static boolean doesNotExist(Category category)
    {
        CategoryDao categoryDao = new CategoryDao();
        for (Category item : categoryDao.getAll())
        {
            if (item.equals(category))
            {
                return false;
            }
        }
        return true;
    }

    public static Category getCategory(int id)
    {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.get(id);
    }

    public static void saveCategory(Category category)
    {
        if (doesNotExist(category))
        {
            CategoryDao categoryDao = new CategoryDao();
            categoryDao.save(category);
        }
    }

    public static void deleteCategory(Category category)
    {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.delete(category);
    }

    public static List<Category> getCategories()
    {
        CategoryDao categoryDao = new CategoryDao();

        return categoryDao.getAll();
    }
    public static void updateName(Category category)
    {
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.update(category);
    }
}
