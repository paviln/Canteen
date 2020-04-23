package presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import persistence.CategoryDao;
import persistence.ProductDao;
import presentation.models.Category;
import presentation.models.Product;

import java.util.List;

public class ManagerController
{
    @FXML
    private StackPane center;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private TableColumn<Product, String> fldName;

    @FXML
    private TableColumn<Product, String> fldCategory;

    @FXML
    private TableColumn<Product, String> fldPrice;

    @FXML
    private TableColumn<Product, String> fldCurrentStock;

    @FXML
    private TableColumn<Product, String> fldMinimumStock;

    @FXML
    private ListView<String> tblCategories;

    @FXML
    public void initialize()
    {
        fldName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        fldCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        fldPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        fldCurrentStock.setCellValueFactory(new PropertyValueFactory<Product, String>("currentStock"));
        fldMinimumStock.setCellValueFactory(new PropertyValueFactory<Product, String>("minimumStock"));
        showProducts();
    }

    @FXML
    private void showProducts()
    {
        // Test DAO methods
        ProductDao productDao = new ProductDao();

        List<Product> products = productDao.getAll();

        tblProduct.getItems().addAll(products);
    }

    @FXML
    private void showCategories()
    {
        tblCategories.setVisible(true);
        // Test DAO methods
        CategoryDao categoryDao = new CategoryDao();

        List<Category> categories = categoryDao.getAll();

        for (Category category : categories)
        {
            tblCategories.getItems().add(category.getName());
        }
    }
}
