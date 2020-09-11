package dao;

import pojo.Discount;

import java.sql.SQLException;
import java.util.List;

public interface DiscountDao {

    java.util.List<Discount> queryAllDiscount() throws SQLException;

    List<Discount> queryDiscountByCondition(String condition) throws SQLException;

    void updateCountByDiscountCode(String s) throws SQLException;

    void updateDiscount(Discount g) throws SQLException;

    String queryMaxDiscountCode() throws SQLException;

    void deleteDiscountByDiscountCode(String s) throws SQLException;

    void addDiscount(Discount g) throws SQLException;

    Discount queryDiscountByDiscountName(String s) throws SQLException;
}
