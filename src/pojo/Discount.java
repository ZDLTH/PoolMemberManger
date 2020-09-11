package pojo;

public class Discount {
    String discount_Code;
    String name;
    Float discount;

    public String getDiscount_Code() {
        return discount_Code;
    }

    public void setDiscount_Code(String discount_Code) {
        this.discount_Code = discount_Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {
        return "Discount{" +
                "discount_Code='" + discount_Code + '\'' +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                '}';
    }
}
