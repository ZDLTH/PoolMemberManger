package dao;

import pojo.Goods;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public interface GoodsDao {


    java.util.List<Goods> queryAllGoods() throws SQLException;

    List<Goods> queryGoodsByCondition(String condition) throws SQLException;

    void updateCountByGoodsCode(String s) throws SQLException;

    void updateGoods(Goods g) throws SQLException;

    String queryMaxGoodsCode() throws SQLException;

    void deleteGoodsByGoodsCode(String s) throws SQLException;

    void addGoods(Goods g) throws SQLException;

    Goods queryGoodsByGoodsName(String goodsName) throws SQLException;
}
