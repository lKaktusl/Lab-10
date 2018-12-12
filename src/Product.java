import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product implements Serializable {

    enum Shop {
        Shopcountry,
        Bestmarket,
        Anyproduct,
        Allthings
    }

    private String shop;
    private String address;
    private String vendorCode;
    private String nameOfProduct;
    private int amountOfProducts;
    private double price;
    private float total;


    public Product(String shop, String address, String vendorCode, String nameOfProduct, int amountOfProducts, double price) {
        this.setShop(shop);
        this.setAddress(address);
        this.setVendorCode(vendorCode);
        this.setNameOfProduct(nameOfProduct);
        this.setAmountOfProducts(amountOfProducts);
        this.setPrice(price);
        this.setTotal(total);
    }

    public String getShop() {

        return shop;
    }

    public void setShop(String shop) {
        this.shop = "Неизвестный магазин";
        for(Shop variants: Shop.values()){
            if(shop.equalsIgnoreCase(variants.toString()))
                this.shop = variants.toString();
        }
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {
        Pattern exampleAdress = Pattern.compile("^([А-Яа-я]+\\-*)([А-Яа-я]+),([А-Яа-я]+\\-*)([А-Яа-я]+),\\d+.*$");//Ввод адреса в формате: город,улица,дом
        Matcher isExampleAdress = exampleAdress.matcher(address);
        if(isExampleAdress.matches()){
            this.address = address;
        }
        else {
            this.address = "Неизвестный адрес!";
        }
    }

    public String getVendorCode() {

        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {

        this.vendorCode = vendorCode;
    }

    public String getNameOfProduct() {

        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {

        this.nameOfProduct = nameOfProduct;
    }

    public int getAmountOfProducts() {
        return amountOfProducts;
    }

    public void setAmountOfProducts(int amountOfProducts) {
        if (amountOfProducts >= 0){
            this.amountOfProducts = amountOfProducts;
        }
        else {
            System.out.println("Количество товара не может быть отрицательным!");
        }
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
        if (price >= 0.01){
            this.price = price;
        }
        else {
            System.out.println("Введена неверная цена товара!");
        }
    }

    public float getTotal() {

        return total;
    }

    public void setTotal(float total) {

        this.total = this.amountOfProducts * (float)this.price;
    }

    @Override
    public String toString() {
        return
                "Магазин' - " + shop + '\'' +
                ", Адрес' - " + address + '\'' +
                ", Код товара' - " + vendorCode + '\'' +
                ", Название товара' - " + nameOfProduct + '\'' +
                ", Количество - " + amountOfProducts +
                ", Цена - " + price +
                ", Сумма - " + total + '\n';
    }
}
