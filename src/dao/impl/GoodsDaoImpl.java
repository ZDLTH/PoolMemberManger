package dao.impl;

import dao.GoodsDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Goods;

import utils.DBUtils;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    private QueryRunner runner;
    Connection connection = null;

    public GoodsDaoImpl() {
        this.runner = new QueryRunner();
    }

    @Override
    public List<Goods> queryAllGoods() throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from goods where name != '注销会员'";

        List<Goods> goodsList = runner.query(connection, sql, new BeanListHandler<Goods>(Goods.class));
        DBUtils.close(null,null,connection);
        return goodsList;
    }

    @Override
    public List<Goods> queryGoodsByCondition(String condition) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from goods where ' ' = ' ' and " + condition;

        List<Goods> goodsList = runner.query(connection, sql, new BeanListHandler<Goods>(Goods.class));
        DBUtils.close(null,null,connection);
        return goodsList;
    }

    @Override
    public void updateCountByGoodsCode(String s) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "update goods set count = 0 where goods_code = ?";

        runner.update(connection,sql, s);
        DBUtils.close(null,null,connection);
    }

    @Override
    public void updateGoods(Goods g) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "update goods set goods_code  = ?, name = ?, price = ?, count = ? where goods_code = ?";

        runner.update(connection,sql, g.getGoods_Code(), g.getName(),g.getPrice(), g.getCount(), g.getGoods_Code());
        DBUtils.close(null,null,connection);
    }

    @Override
    public String queryMaxGoodsCode() throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select max(TO_NUMBER(goods_Code)) from goods  ";
        String r = null;
        r = runner.query(connection, sql, new ScalarHandler("max(TO_NUMBER(goods_Code))"))+ "";
        DBUtils.close(null,null,connection);
        return r;
    }

    @Override
    public void deleteGoodsByGoodsCode(String s) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "delete from goods where goods_code = ?";
        runner.update(connection,sql, s);
        DBUtils.close(null,null,connection);
    }

    @Override
    public void addGoods(Goods g) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "insert into goods(goods_code, name, price,count) values(?,?,?,?)";

        runner.update(connection,sql, g.getGoods_Code(), g.getName(),g.getPrice(), g.getCount());
        DBUtils.close(null,null,connection);
    }

    @Override
    public Goods queryGoodsByGoodsName(String goodsName) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from goods where name = ? ";

        Goods g = runner.query(connection, sql,  new BeanHandler<>(Goods.class), goodsName);
        DBUtils.close(null,null,connection);
        return g;
    }

}
