package dao.impl;

import dao.ConsumeDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import pojo.VConsume;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ConsumeDaoImpl implements ConsumeDao {

    private QueryRunner runner = null;
    Connection connection = null;


    public ConsumeDaoImpl(){
        runner = new QueryRunner();
    }


    @Override
    public void addConsumeIsMember(String member_code, String goods_code, String discount_code, Integer count, String time) throws SQLException {


        String sql = "call addConsume(?,?,?,?, to_date(? , 'yyyy-mm-dd hh24:mi:ss'))";
        runner.update(connection, sql ,member_code, goods_code,discount_code, count, time);

    }


    @Override
    public void addConsumeNotMember(String goods_code, String discount_code, Integer count, String time) throws SQLException {
        String sql = "call addConsumefortravel(?,?,?, to_date(? , 'yyyy-mm-dd hh24:mi:ss'))";
        runner.update(connection, sql , goods_code,discount_code, count, time);
    }

    @Override
    public List<VConsume> queryAllConusmesOfVConsume() throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from vconsume";
        List<VConsume> consumes = runner.query(connection, sql, new BeanListHandler<VConsume>(VConsume.class));
        DBUtils.close(null,null,connection);
        return consumes;
    }

    @Override
    public List<VConsume> queryConsumesByCondition(StringBuilder condition) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from vconsume where " + condition;
        List<VConsume> consumes = runner.query(connection, sql, new BeanListHandler<VConsume>(VConsume.class));
        DBUtils.close(null,null,connection);
        return consumes;
    }

    @Override
    public void chargeToMemberByMemberCode(String memberCode, Double price) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "call CHARGEForMember(?,?)";
        runner.update(connection,sql, memberCode, price);
        DBUtils.close(null,null,connection);
    }


    @Override
    public void startTrancstion() throws SQLException {
        connection = DBUtils.getConnection();
        connection.setAutoCommit(false);
    }


    @Override
    public void commit() throws SQLException {
        connection.commit();
        DBUtils.close(null,null,connection);
    }

    @Override
    public void rollBack() throws SQLException {
        connection.rollback();
        DBUtils.close(null,null,connection);
    }


}
