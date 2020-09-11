package service;

import pojo.VConsume;

import java.sql.SQLException;
import java.util.List;

public interface ConsumeService {
    void addSomeConsumeIsMember(String member_code,List<String> goodsCodeList, String discount_code,List<Integer> goodsCountList ) throws SQLException;

    void addSomeConsumeNotMember(List<String> goodsCodeList, String discount_code,List<Integer> goodsCountList) throws SQLException;


    void rollBack() throws SQLException;

    List<VConsume> queryAllConusmesOfVConsume() throws SQLException;

    List<VConsume> queryConsumesByCondition(List conditionName, List conditionValue) throws SQLException;

    void chargeToMemberByMemberCode(String memberCode, Double price) throws SQLException;
}
