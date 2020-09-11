package service;

import pojo.VCharge;

import java.sql.SQLException;
import java.util.List;

public interface ChargeService {
    List<VCharge> queryAllChargesOfVCharge() throws SQLException;

    List<VCharge> queryChargesByCondition(List conditionName, List conditionValue) throws SQLException;
}
