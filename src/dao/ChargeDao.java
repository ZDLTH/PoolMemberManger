package dao;

import pojo.VCharge;

import java.sql.SQLException;
import java.util.List;

public interface ChargeDao {
    List<VCharge> queryAllChargesOfVCharge() throws SQLException;

    List<VCharge> queryChargesByCondition(StringBuilder condition) throws SQLException;
}
