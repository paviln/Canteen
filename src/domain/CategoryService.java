package domain;

import persistence.CategoryDao;
import presentation.models.Category;

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
