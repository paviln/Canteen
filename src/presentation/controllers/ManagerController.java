package presentation.controllers;

import domain.CategoryService;
import domain.ProductService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import presentation.models.Display;
import presentation.models.Product;

import java.util.ArrayList;

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
    private TableColumn<Product, String> fldName, fldCategory, fldPrice, fldCurrentStock, fldMinimumStock, fldSupplier;

    @FXML
    private TextField productName, productCategory, productPrice, productCurrent, productMinimum, productSupplier;

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
        fldSupplier.setCellValueFactory(new PropertyValueFactory<Product, String>("supplierId"));

        showProducts();

        tblProduct.setOnMouseClicked((MouseEvent event) ->
        {
            productEdit();
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

    private Product productInput()
    {
        if (!productName.getText().isEmpty() && !productCategory.getText().isEmpty() && !productPrice.getText().isEmpty() && !productCurrent.getText().isEmpty() && !productMinimum.getText().isEmpty())
        {
            Product product = new Product();
            product.setName(productName.getText());
            product.setCategory(Integer.parseInt(productCategory.getText()));
            product.setPrice(productPrice.getText());
            product.setCurrentStock(Integer.parseInt(productCurrent.getText()));
            product.setMinimumStock(Integer.parseInt(productMinimum.getText()));
            product.setSupplierId(Integer.parseInt(productSupplier.getText()));

            return product;
        }
        return null;
    }

    private void productEdit()
    {
        if (tblProduct.getSelectionModel().getSelectedItem() != null)
        {
            Product selectedProduct = tblProduct.getSelectionModel().getSelectedItem();
            productName.setText(selectedProduct.getName());
            productCategory.setText(String.valueOf(selectedProduct.getCategory()));
            productPrice.setText(selectedProduct.getPrice());
            productCurrent.setText(String.valueOf(selectedProduct.getCurrentStock()));
            productMinimum.setText(String.valueOf(selectedProduct.getMinimumStock()));
            productSupplier.setText(String.valueOf(selectedProduct.getSupplierId()));
        }
    }

    @FXML
    private void addProduct()
    {
        Product product = productInput();
        ProductService.addProduct(product);
        showProducts();
    }

    @FXML
    private void updateProduct()
    {
        if (tblProduct.getSelectionModel().getSelectedItem() != null)
        {
            Product product = productInput();
            if (product != null)
            {
                product.setId(tblProduct.getSelectionModel().getSelectedItem().getId());
                ProductService.updateProduct(product);
            }
        }
    }

    @FXML
    private void removeProduct()
    {
        if (tblProduct.getSelectionModel().getSelectedItem() != null)
        {
            Product selectedProduct = tblProduct.getSelectionModel().getSelectedItem();
            ProductService.deleteProduct(selectedProduct);
            tblProduct.getItems().remove(selectedProduct);
        }
    }

    private void showProducts()
    {
        tblProduct.getItems().clear();
        tblProduct.getItems().addAll(ProductService.getProducts());
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
