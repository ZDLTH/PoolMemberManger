package dao.impl;

import dao.DiscountDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Discount;
import utils.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DiscountDaoImpl implements DiscountDao {

    private QueryRunner runner;
    Connection connection = null;

    public DiscountDaoImpl() {
        this.runner = new QueryRunner();
    }

    @Override
    public List<Discount> queryAllDiscount() throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from discount";

        List<Discount> discountList = runner.query(connection, sql, new BeanListHandler<Discount>(Discount.class));
        DBUtils.close(null,null,connection);
        return discountList;
    }

    @Override
    public List<Discount> queryDiscountByCondition(String condition) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from discount where ' ' = ' ' and " + condition;

        List<Discount> discountList = runner.query(connection, sql, new BeanListHandler<Discount>(Discount.class));
        DBUtils.close(null,null,connection);
        return discountList;
    }

    @Override
    public void updateCountByDiscountCode(String s) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "update discount set count = 0 where discount_code = ?";

        runner.update(connection,sql, s);
        DBUtils.close(null,null,connection);
    }

    @Override
    public void updateDiscount(Discount g) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "update discount set discount_code  = ?, name = ?, discount = ? where discount_code = ?";

        runner.update(connection,sql, g.getDiscount_Code(), g.getName(),g.getDiscount(), g.getDiscount_Code());
        DBUtils.close(null,null,connection);
    }

    @Override
    public String queryMaxDiscountCode() throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select max(TO_NUMBER(discount_Code)) from discount  ";
        String r = null;
        r = runner.query(connection, sql, new ScalarHandler("max(TO_NUMBER(discount_Code))"))+ "";
        DBUtils.close(null,null,connection);
        return r;
    }

    @Override
    public void deleteDiscountByDiscountCode(String s) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "delete from discount where discount_code = ?";
        runner.update(connection,sql, s);
        DBUtils.close(null,null,connection);
    }

    @Override
    public void addDiscount(Discount g) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "insert into discount(discount_code, name, discount) values(?,?,?)";

        runner.update(connection,sql, g.getDiscount_Code(), g.getName(),g.getDiscount());
        DBUtils.close(null,null,connection);
    }

    @Override
    public Discount queryDiscountByDiscountName(String s) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from discount where name = ? ";

        Discount d = runner.query(connection, sql,  new BeanHandler<>(Discount.class), s);
        DBUtils.close(null,null,connection);
        return d;
    }

}
