package dao.impl;

import dao.ChargeDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.VCharge;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChargeDaoImpl implements ChargeDao {

    private QueryRunner runner = null;
    Connection connection = null;


    public ChargeDaoImpl(){
        runner = new QueryRunner();
    }


    @Override
    public List<VCharge> queryAllChargesOfVCharge() throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from vcharge";
        List<VCharge> charges = runner.query(connection, sql, new BeanListHandler<VCharge>(VCharge.class));
        DBUtils.close(null,null,connection);
        return charges;
    }

    @Override
    public List<VCharge> queryChargesByCondition(StringBuilder condition) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from vcharge where " + condition ;
        List<VCharge> charges = runner.query(connection, sql, new BeanListHandler<VCharge>(VCharge.class));
        DBUtils.close(null,null,connection);
        return charges;
    }
}
