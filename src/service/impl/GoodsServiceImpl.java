package service.impl;

import dao.GoodsDao;
import dao.impl.GoodsDaoImpl;
import pojo.Goods;
import service.GoodsService;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;


public class GoodsServiceImpl implements GoodsService {

    GoodsDao goodsDao = new GoodsDaoImpl();


    @Override
    public List<Goods> queryAllGoods() throws SQLException {
        return goodsDao.queryAllGoods();
    }

    @Override
    public List<Goods> queryGoodsByCondition(String condition) throws SQLException {
        return goodsDao.queryGoodsByCondition(condition) ;
    }

    @Override
    public void updateCountByGoodsCode(Vector<String> deleteRows) throws SQLException {
        for (String s : deleteRows){
            goodsDao.updateCountByGoodsCode(s);
        }
    }

    @Override
    public void updateGoods(Goods g) throws SQLException {
        goodsDao.updateGoods(g);
    }

    @Override
    public String queryMaxGoodsCode() throws SQLException {
        return goodsDao.queryMaxGoodsCode();
    }

    @Override
    public void deleteGoodsByGoodsCode(Vector<String> deleteRows) throws SQLException {
        for (String s : deleteRows){
            goodsDao.deleteGoodsByGoodsCode(s);
        }
    }

    @Override
    public void addSomeGoods(Vector ag) throws SQLException {
        for(Object g: ag){
            goodsDao.addGoods((Goods)g);
        }
    }

    @Override
    public Goods queryGoodsByGoodsName(String goodsName) throws SQLException {
        return goodsDao.queryGoodsByGoodsName(goodsName);
    }
}
