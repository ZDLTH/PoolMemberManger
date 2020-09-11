package dao;

import pojo.Member;

import java.sql.SQLException;

public interface MemberDao {

    void addMember(Member member) throws SQLException;

    Member queryMemberByPhone(String phone) throws SQLException;

    void freezeMemberByMemberCode(String memberCode, float money) throws SQLException;

    void updateMemberNameAndPassordAndStatues(String memberCode, String name, String password, Integer status) throws SQLException;
}
