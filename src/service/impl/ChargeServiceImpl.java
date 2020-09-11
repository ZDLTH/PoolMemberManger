package service.impl;

import dao.ChargeDao;
import dao.impl.ChargeDaoImpl;
import pojo.VCharge;
import service.ChargeService;

import java.sql.SQLException;
import java.util.List;

public class ChargeServiceImpl implements ChargeService {
    ChargeDao chargeDao  = new ChargeDaoImpl();

    @Override
    public List<VCharge> queryAllChargesOfVCharge() throws SQLException {
        return chargeDao.queryAllChargesOfVCharge();
    }

    @Override
    public List<VCharge> queryChargesByCondition(List conditionName, List conditionValue) throws SQLException {
        StringBuilder condition = new StringBuilder("'1' = '1' ");
        for(int i = 0; i < conditionName.size(); i++){
            if(!"".equals(conditionValue.get(i))){
                String s = " and " + conditionName.get(i) + " = " + "'"+conditionValue.get(i) + "'";
                condition.append(s);
            }
        }


        return chargeDao.queryChargesByCondition(condition);


    }
}
