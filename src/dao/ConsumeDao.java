package dao;

import pojo.VConsume;

import java.sql.SQLException;
import java.util.List;

public interface ConsumeDao {
    void addConsumeIsMember(String member_code, String goods_code, String discount_code, Integer count, String time) throws SQLException;
    void startTrancstion() throws SQLException;
    void commit() throws SQLException;
    void rollBack() throws SQLException;

    void addConsumeNotMember(String goods_code, String discount_code, Integer count, String time) throws SQLException;

    List<VConsume> queryAllConusmesOfVConsume() throws SQLException;

    List<VConsume> queryConsumesByCondition(StringBuilder condition) throws SQLException;

    void chargeToMemberByMemberCode(String memberCode, Double price) throws SQLException;
}
