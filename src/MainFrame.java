import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JPanel Jpanel;
    private JLabel labelShop;
    private JTextField textShop;
    private JLabel labelAddress;
    private JTextField textAddress;
    private JLabel labelNameProduct;
    private JTextField textNameProduct;
    private JLabel labelVendorCode;
    private JTextField textVendorCode;
    private JLabel labelAmountOfProducts;
    private JSpinner spinnerAmountOfProducts;
    private JLabel labelPrice;
    private JLabel labelTotal;
    private JFormattedTextField formattedTextPrice;
    private JTextField textSumm;
    private JButton buttonAdd;
    private JButton buttonViewAll;
    private JPanel panelAdding;
    private JPanel panelViewInformation;
    private JButton buttonViewWithIndicatedName;
    private JLabel labelViewWithIndicatedroducts;
    private JLabel labelViewIndicatedProducts;
    private JButton buttonViewIndicatedProducts;
    private JTextField textViewIndicatedProducts;
    private JLabel labelViewShopsWithChipestProduct;
    private JButton buttonViewShopsWithChipestProduct;
    private JTextField textViewShopsWithChipestProduct;
    private JButton buttonDeleteProductWithIndicatedPrice;
    private JFormattedTextField formattedDeleteProductWithIndicatedPrice;
    private JPanel panelDeleteProductWithIndicatedPrice;
    private JLabel labelView;
    private JButton buttonLoad;
    private JButton buttonSave;
    private JTextField textViewTotalWithIndicatedName;
    private JLabel labelResult;
    private JPanel panelFiles;


    public MainFrame(){
        super("Учет товара в магазинах");
        initWindow();
        setSize(1000, 700);
        setResizable(false);
        ProductsArrayList products = new ProductsArrayList();
        FileControl files = new FileControl();
        files.createFiles();
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                products.addNewProductItem(new Product(textShop.getText(), textAddress.getText(), textNameProduct.getText(),
                        textVendorCode.getText(), (Integer)spinnerAmountOfProducts.getValue(), Double.parseDouble(formattedTextPrice.getText())));
                textShop.setText("");
                textAddress.setText("");
                textNameProduct.setText("");
                textVendorCode.setText("");
                spinnerAmountOfProducts.setValue(0);
                formattedTextPrice.setText("");
            }
        });
        buttonViewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelView.setText(products.showProductArrayList());
            }
        });
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                products.Load(files.getNameMainFile());
                labelResult.setText("Данные успешно загружены из файла.");
            }
        });
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                products.Save(files.getNameMainFile());
                labelResult.setText("Данные успешно сохранены в файл.");
            }
        });

        buttonViewIndicatedProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelView.setText(products.showIndicatedProducts(textViewIndicatedProducts.getText()));
            }
        });
        buttonViewWithIndicatedName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelView.setText(products.totalCostOfIndicatedProduct(textViewTotalWithIndicatedName.getText()));
            }
        });
        buttonDeleteProductWithIndicatedPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelResult.setText(products.deleteProductWithIndicatedPrice(Double.parseDouble(formattedDeleteProductWithIndicatedPrice.getText())));
            }
        });
        buttonViewShopsWithChipestProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelView.setText(products.shopsWithChipestProduct(textViewShopsWithChipestProduct.getText()));
            }
        });
    }

    private void initWindow(){
        setContentPane(Jpanel);
        setVisible(true);
        setResizable(true);

        new BorderLayout();
        pack();




        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new MainFrame();

    }

}
