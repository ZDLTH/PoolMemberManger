package service;

import pojo.Discount;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public interface DiscountService {

    List<Discount> queryAllDiscount() throws SQLException;

    List<Discount> queryDiscountByCondition(String condition) throws SQLException;

    void updateCountByDiscountCode(Vector<String> deleteRows) throws SQLException;

    void updateDiscount(Discount g) throws SQLException;

    String queryMaxDiscountCode() throws SQLException;

    void deleteDiscountByDiscountCode(Vector<String> deleteRows) throws SQLException;

    void addSomeDiscount(Vector ag) throws SQLException;

    Discount queryDiscountByDiscountName(String s) throws SQLException;
}
