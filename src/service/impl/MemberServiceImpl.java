package service.impl;

import dao.MemberDao;
import dao.impl.MemberDaoImpl;
import pojo.Member;
import service.MemberService;

import java.sql.SQLException;

public class MemberServiceImpl implements MemberService {

    MemberDao memberDao = new MemberDaoImpl();

    @Override
    public void addMember(String name, String phone, Float balance, String password) throws SQLException {
        Member member = new Member();
        member.setName(name);
        member.setPhone(phone);
        member.setPassword(password);
        member.setBalance(balance);
        memberDao.addMember(member);
    }

    @Override
    public Member queryMemberByPhone(String phone) throws SQLException {
        return memberDao.queryMemberByPhone(phone);
    }

    @Override
    public void freezeMemberByMemberCode(String memberCode, float money) throws SQLException {
        memberDao.freezeMemberByMemberCode(memberCode, money);
    }

    @Override
    public void updateMemberNameAndPassordAndStatues(String memberCode, String name, String password, Integer status) throws SQLException {
        memberDao.updateMemberNameAndPassordAndStatues(memberCode, name, password, status);
    }
}
