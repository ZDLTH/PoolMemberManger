package service;

import pojo.Goods;
import pojo.Member;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public interface GoodsService {

     List<Goods> queryAllGoods() throws SQLException;

    List<Goods> queryGoodsByCondition(String condition) throws SQLException;

    void updateCountByGoodsCode(Vector<String> deleteRows) throws SQLException;

    void updateGoods(Goods g) throws SQLException;

    String queryMaxGoodsCode() throws SQLException;

    void deleteGoodsByGoodsCode(Vector<String> deleteRows) throws SQLException;

    void addSomeGoods(Vector ag) throws SQLException;

    Goods queryGoodsByGoodsName(String goodsName) throws SQLException;
}
