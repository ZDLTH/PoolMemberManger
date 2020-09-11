package dao.impl;

import dao.MemberDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.Member;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class MemberDaoImpl implements MemberDao {
    private QueryRunner runner = null;
    Connection connection = null;

    public MemberDaoImpl() {
        this.runner = new QueryRunner();
    }

    @Override
    public void addMember(Member member) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "call addMember(?,?,?,?)";
        runner.update(connection, sql , member.getName(), member.getPhone(),member.getBalance(), member.getPassword());
        DBUtils.close(null,null,connection);
    }

    @Override
    public Member queryMemberByPhone(String phone) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "select * from member where phone = ?";
        BeanHandler<Member> handler = new BeanHandler<>(Member.class);

        Member member = runner.query(connection, sql, handler, phone);
        DBUtils.close(null,null,connection);
        return member;
    }

    @Override
    public void freezeMemberByMemberCode(String memberCode, float money) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "call freezemember(?,?)";

        runner.update(connection, sql, memberCode, money);

        DBUtils.close(null,null,connection);
    }

    @Override
    public void updateMemberNameAndPassordAndStatues(String memberCode, String name, String password, Integer status) throws SQLException {
        connection = DBUtils.getConnection();
        String sql = "update member set name = ?, password = ?, status = ? where member_Code = ?";

        runner.update(connection, sql, name, password, status, memberCode);

        DBUtils.close(null,null,connection);
    }

}
