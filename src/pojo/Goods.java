package pojo;

public class Goods {
    String goods_Code;
    String name;
    Double price;
    Integer count;

    public String getGoods_Code() {
        return goods_Code;
    }

    public void setGoods_Code(String good_Code) {
        this.goods_Code = good_Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "good_Code='" + goods_Code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
