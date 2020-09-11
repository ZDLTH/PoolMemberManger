package service;

import pojo.Member;

import java.sql.SQLException;

public interface MemberService {
    void addMember(String name, String phone, Float money, String password) throws SQLException;

    Member queryMemberByPhone(String phone) throws SQLException;

    void freezeMemberByMemberCode(String memberCode, float money) throws SQLException;

    void updateMemberNameAndPassordAndStatues(String memberCode, String name, String password, Integer status) throws SQLException;
}
