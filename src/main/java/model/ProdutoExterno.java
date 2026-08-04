package model;

public class ProdutoExterno {

    private String id;
    private String title;
    private String description;
    private String category;
    private double price;


    public ProdutoExterno(String id, String title, String description, String category, double price ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }


}
