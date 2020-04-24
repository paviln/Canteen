package domain;

import persistence.CategoryDao;
import presentation.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService
{
    public static void saveCategory(String name)
    {
        CategoryDao categoryDao = new CategoryDao();

        Category category = new Category(name);

        categoryDao.save(category);
    }

    public static void deleteCategory(String name)
    {
        CategoryDao categoryDao = new CategoryDao();

        Category category = new Category(name);

        categoryDao.delete(category);
    }

    public static ArrayList<String> getCategories()
    {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.getAll();

        ArrayList<String> names = new ArrayList<>();
        for (Category category : categories)
        {
            names.add(category.getName());
        }
        return names;
    }
    public static void updateName(String oldName, String[] params)
    {
        CategoryDao categoryDao = new CategoryDao();
        Category category = new Category(oldName);
        categoryDao.update(category);
    }
}
