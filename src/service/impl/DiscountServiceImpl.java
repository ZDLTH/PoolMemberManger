package service.impl;

import dao.DiscountDao;
import dao.impl.DiscountDaoImpl;
import pojo.Discount;
import service.DiscountService;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class DiscountServiceImpl implements DiscountService {

    DiscountDao discountDao = new DiscountDaoImpl();


    @Override
    public List<Discount> queryAllDiscount() throws SQLException {
        return discountDao.queryAllDiscount();
    }

    @Override
    public List<Discount> queryDiscountByCondition(String condition) throws SQLException {
        return discountDao.queryDiscountByCondition(condition) ;
    }

    @Override
    public void updateCountByDiscountCode(Vector<String> deleteRows) throws SQLException {
        for (String s : deleteRows){
            discountDao.updateCountByDiscountCode(s);
        }
    }

    @Override
    public void updateDiscount(Discount g) throws SQLException {
        discountDao.updateDiscount(g);
    }

    @Override
    public String queryMaxDiscountCode() throws SQLException {
        return discountDao.queryMaxDiscountCode();
    }

    @Override
    public void deleteDiscountByDiscountCode(Vector<String> deleteRows) throws SQLException {
        for (String s : deleteRows){
            discountDao.deleteDiscountByDiscountCode(s);
        }
    }

    @Override
    public void addSomeDiscount(Vector ag) throws SQLException {
        for(Object g: ag){
            discountDao.addDiscount((Discount)g);
        }
    }

    @Override
    public Discount queryDiscountByDiscountName(String s) throws SQLException {
        return discountDao.queryDiscountByDiscountName(s);
    }

}
