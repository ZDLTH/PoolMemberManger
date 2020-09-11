package service.impl;

import dao.ConsumeDao;
import dao.impl.ConsumeDaoImpl;
import pojo.VConsume;
import service.ConsumeService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsumeServiceImpl implements ConsumeService {

    ConsumeDao consumeDao = new ConsumeDaoImpl();




    @Override
    public void addSomeConsumeIsMember(String member_code, List<String> goodsCodeList, String discount_code, List<Integer> goodsCountList) throws SQLException {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = s.format(date);
        consumeDao.startTrancstion();
        for(int i = 0; i < goodsCodeList.size(); i++){
            String goods_code = goodsCodeList.get(i);
            Integer count = goodsCountList.get(i);

            consumeDao.addConsumeIsMember(member_code,goods_code,discount_code, count, time);
        }
        consumeDao.commit();
    }

    @Override
    public void addSomeConsumeNotMember(List<String> goodsCodeList, String discount_code, List<Integer> goodsCountList) throws SQLException {
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = s.format(date);
        consumeDao.startTrancstion();
        for(int i = 0; i < goodsCodeList.size(); i++){
            String goods_code = goodsCodeList.get(i);
            Integer count = goodsCountList.get(i);

            consumeDao.addConsumeNotMember(goods_code,discount_code, count, time);
        }
        consumeDao.commit();
    }

    @Override
    public void rollBack() throws SQLException {
        consumeDao.rollBack();
    }

    @Override
    public List<VConsume> queryAllConusmesOfVConsume() throws SQLException {
        return consumeDao.queryAllConusmesOfVConsume();
    }

    @Override
    public List<VConsume> queryConsumesByCondition(List conditionName, List conditionValue) throws SQLException {
        StringBuilder condition = new StringBuilder("'1' = '1' ");
        for(int i = 0; i < conditionName.size(); i++){
            if(!"".equals(conditionValue.get(i))){
                String s = " and " + conditionName.get(i) + " = " + "'"+conditionValue.get(i) + "'";
                condition.append(s);
            }
        }


        return consumeDao.queryConsumesByCondition(condition);
    }

    @Override
    public void chargeToMemberByMemberCode(String memberCode, Double price) throws SQLException {
        consumeDao.chargeToMemberByMemberCode(memberCode, price);
    }

}
