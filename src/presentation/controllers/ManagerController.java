package presentation.controllers;

import domain.CategoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import persistence.ProductDao;
import presentation.models.Display;
import presentation.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ManagerController
{
    @FXML
    private StackPane center;

    @FXML
    private Button productsBtn, categoriesBtn, stockBtn;

    @FXML
    private VBox productsDisplay;

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
    private VBox categoriesDisplay;

    @FXML
    private ListView<String> tblCategories;

    @FXML
    private TextField categoryField;

    @FXML
    public void initialize()
    {
        fldName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        fldCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        fldPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        fldCurrentStock.setCellValueFactory(new PropertyValueFactory<Product, String>("currentStock"));
        fldMinimumStock.setCellValueFactory(new PropertyValueFactory<Product, String>("minimumStock"));

        tblProduct.setOnMouseClicked((MouseEvent event) ->
        {
            productsEdit();
            tblProduct.getSelectionModel().select(0);
        });

        tblCategories.setOnMouseClicked(event ->
        {
            categoryField.setText(tblCategories.getSelectionModel().getSelectedItem());
        });
    }

    /**
     * Handles menu button presses
     * @param e
     */
    @FXML
    private void menuHandler(ActionEvent e)
    {
        Button button = ((Button) e.getSource());

        switch (button.getText())
        {
            case "Products":
                changeDisplay(Display.Products);
                break;
            case "Categories":
                changeDisplay(Display.Categories);
                break;
        }
    }

    private void productsEdit()
    {
        if (tblProduct.getSelectionModel().getSelectedItem() != null)
        {
            Product selectedPerson = tblProduct.getSelectionModel().getSelectedItem();
        }
    }

    private void showProducts()
    {
        tblProduct.getItems().clear();
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getAll();
        tblProduct.getItems().addAll(products);
    }

    /**
     * Display contents of categories
     */
    private void showCategories()
    {
        tblCategories.getItems().clear();

        ArrayList<String> categories = CategoryService.getCategories();

        for (int i = 0; i < categories.size(); i++)
        {
            tblCategories.getItems().add(categories.get(0));
        }
    }

    /**
     * Changes the display according to the menu
     * @param display
     */
    private void changeDisplay(Display display)
    {
        productsBtn.getStyleClass().remove("current");
        categoriesBtn.getStyleClass().remove("current");
        productsDisplay.setVisible(false);
        categoriesDisplay.setVisible(false);

        switch (display)
        {
            case Products:
                showProducts();
                productsBtn.getStyleClass().add("current");
                productsDisplay.setVisible(true);
                break;
            case Categories:
                showCategories();
                categoriesBtn.getStyleClass().add("current");
                categoriesDisplay.setVisible(true);
        }
    }

    @FXML
    private void addProduct()
    {
        // Test DAO methods
        ProductDao productDao = new ProductDao();

        List<Product> products = productDao.getAll();

        tblProduct.getItems().addAll(products);
    }

    @FXML
    private void addCategory()
    {
        SelectionModel selectionModel = tblCategories.getSelectionModel();
        if (!selectionModel.isEmpty() || !categoryField.getText().isEmpty())
        {
            if (!tblCategories.getItems().contains(categoryField.getText()))
            {
                CategoryService.saveCategory(categoryField.getText());
                tblCategories.getItems().add(categoryField.getText());
            }
        }
    }

    @FXML
    private void renameCategory()
    {
        SelectionModel selectionModel = tblCategories.getSelectionModel();
        if (!selectionModel.isEmpty())
        {
            CategoryService.updateName(selectionModel.getSelectedItem().toString(), new String[]{categoryField.getText()});
            tblCategories.getItems().set(selectionModel.getSelectedIndex(), categoryField.getText());
        }
    }

    @FXML
    private void removeCategory()
    {
        SelectionModel selectionModel = tblCategories.getSelectionModel();
        if (!selectionModel.isEmpty())
        {
            CategoryService.deleteCategory(selectionModel.getSelectedItem().toString());
            tblCategories.getItems().remove(selectionModel.getSelectedIndex());
        }
        else
        {
            CategoryService.deleteCategory(categoryField.getText());
            tblCategories.getItems().remove(categoryField.getText());
        }
    }
}
