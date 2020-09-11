package view;

import dao.ConsumeDao;
import javafx.beans.binding.When;
import pojo.Member;
import service.ConsumeService;
import service.MemberService;
import service.impl.ConsumeServiceImpl;
import service.impl.MemberServiceImpl;

import javax.naming.Name;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;


public class MemberManage extends JFrame {

    int width = 780;
    int height = 480;

    JButton registButton = new JButton("注册会员");
    JButton cancelButton = new JButton("注销会员");
    JButton messageQueryButton = new JButton("信息查询");
    JButton messageUpdateButton = new JButton("信息修改");
    JButton chargeButton = new JButton("会员充值");


    /**
     * 用来放多个页面，实现点按钮换页
     */
    JPanel centerPanel = new JPanel();
    CardLayout centerLayout = new CardLayout();

    /**
     * 用来放换页的按钮
     */
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));

    public MemberManage(){
        viewInit();
        buttonInit();
    }

    private void viewInit(){

        int x = Toolkit.getDefaultToolkit().getScreenSize().width;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((x - width) / 2,
                (y - height) / 2, width, height);
        this.setLocationRelativeTo(null);


        setLayout(new BorderLayout(0,0));
        setSize(width, height);
        centerInit();
        northInit();
    }

    void centerInit(){
        centerPanel.setLayout(centerLayout);
        centerPanel.add(new Regist(), "regist");
        centerPanel.add(new Cancel(), "cancel");
        centerPanel.add(new Query(), "query");
        centerPanel.add(new Update(), "update");
        centerPanel.add(new Charge(), "charge");

        centerLayout.show(centerPanel, "regist");

        this.add(centerPanel, BorderLayout.CENTER);
    }

    void northInit(){
        northPanel.setBackground(new Color(107, 38, 38, 215));
        this.add(northPanel, BorderLayout.NORTH);
    }

    private void buttonInit() {
        registButtonInit();
        cancelButtonInit();
        messageQueryButtonInit();
        messageUpdateButtonInit();
        chargeButtonInit();
    }

    private void registButtonInit() {

        registButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerLayout.show(centerPanel, "regist");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        registButton.setBackground(new Color(251,114,153));
        registButton.setForeground(Color.white);
        northPanel.add(registButton);
    }

    private void cancelButtonInit() {
        cancelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerLayout.show(centerPanel,"cancel");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        cancelButton.setBackground(new Color(218, 101, 140));
        cancelButton.setForeground(Color.white);

        northPanel.add(cancelButton);
    }

    private void messageQueryButtonInit() {
        messageQueryButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerLayout.show(centerPanel, "query");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        messageQueryButton.setBackground(new Color(218, 109, 121));
        messageQueryButton.setForeground(Color.white);

        northPanel.add(messageQueryButton);
    }

    private void messageUpdateButtonInit() {
        messageUpdateButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerLayout.show(centerPanel, "update");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        messageUpdateButton.setBackground(new Color(201, 106, 106));
        messageUpdateButton.setForeground(Color.white);

        northPanel.add(messageUpdateButton);
    }

    private void chargeButtonInit() {
        chargeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerLayout.show(centerPanel, "charge");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        chargeButton.setBackground(new Color(163, 91, 91, 220));
        chargeButton.setForeground(Color.white);

        northPanel.add(chargeButton);
    }
}

class Regist extends JPanel{

    MemberService memberService = new MemberServiceImpl();

    JTextField inputName = new JTextField(12);
    JTextField inputPhone = new JTextField(12);
    JPasswordField inputPassword = new JPasswordField(12);
    JTextField inputMoney = new JTextField(12);
    JButton registButton = new JButton("注册");


    JLabel name = new JLabel(" 姓名 ", JLabel.CENTER);
    JLabel phone = new JLabel(" 电话 ", JLabel.CENTER);
    JLabel password = new JLabel(" 密码 ", JLabel.CENTER);



    Regist(){
        viewInit();
        buttonInit();
    }

    /**
     * 用来放输入框和提示框
     */
    JPanel top = new JPanel(new GridLayout(7,1,5,10));

    void viewInit(){
        this.setLayout(new BorderLayout());
        topInit();
    }

    void topInit(){
        top.add(new JLabel());
        top.setBackground(Color.white);

        nameInit();
        phoneInit();
        passwordInit();
        moneyInit();
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.add(registButton);
        top.add(p);
        this.add(top, BorderLayout.CENTER);


        top.add(new JLabel());

    }

    void nameInit(){
        JPanel p = new JPanel();

        p.setBackground(Color.white);

        name.setFont(new Font("微软雅黑",Font.BOLD,15));
        p.add(name);

        inputName.setColumns(15);
        p.add(inputName);

        top.add(p);
    }

    void phoneInit(){
        JPanel p = new JPanel();
        p.setBackground(Color.white);

        phone.setFont(new Font("微软雅黑",Font.BOLD,15));
        p.add(phone);

        inputPhone.setColumns(15);
        p.add(inputPhone);

        top.add(p);
    }

    void passwordInit(){
        JPanel p = new JPanel();
        p.setBackground(Color.white);

        password.setFont(new Font("微软雅黑",Font.BOLD,15));
        p.add(password);

        inputPassword.setColumns(15);
        p.add(inputPassword);

        top.add(p);
    }

    void moneyInit(){

        JLabel money = new JLabel("初始金额", JLabel.CENTER);
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        money.setFont(new Font("微软雅黑",Font.BOLD,15));
        p.add(money);
        inputMoney.setColumns(15);
        p.add(inputMoney);

        top.add(p);

    }

    void buttonInit(){
        registButtonInit();
    }

    void registButtonInit() {

        registButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = inputName.getText();
                String phone = inputPhone.getText();
                String password = inputPassword.getText();
                String money = inputMoney.getText();


                try {
                    memberService.addMember(name, phone,Float.parseFloat(money), password);
                } catch (Exception ex) {
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(null,
                            ex.getLocalizedMessage().subSequence(ex.getLocalizedMessage().indexOf(':')+1, ex.getLocalizedMessage().indexOf('!')+1),
                            "错误",  JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null, "注册成功", "正确",  JOptionPane.INFORMATION_MESSAGE);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        registButton.setBackground(new Color(0,161,214));
        registButton.setPreferredSize(new Dimension(130,30));

    }

}

class Cancel extends JPanel{

    MemberService memberService = new MemberServiceImpl();

    JTextField inputPhone = new JTextField(15);
    JPasswordField inputPassword = new JPasswordField(15);
    JButton cancelButton = new JButton("注销");

    /**
     * 用来放输入框和提示框
     */
    JPanel top = new JPanel(new GridLayout(5,1,5,10));


    Cancel(){
        viewInit();
        buttonInit();
    }


    void viewInit(){
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        topInit();
    }

    void topInit(){
        top.setBackground(Color.white);
        top.add(new JLabel());
        phoneInit();
        passwordInit();
        top.add(new JLabel());

        JPanel p = new JPanel();
        cancelButton.setPreferredSize(new Dimension(130, 30));
        cancelButton.setBackground(new Color(124,212,242));
        p.add(cancelButton);
        p.setBackground(Color.white);

        top.add(p);

        this.add(top, BorderLayout.CENTER);
    }

    void phoneInit(){
        JLabel phone = new JLabel("电话", JLabel.CENTER);
        phone.setFont(new Font("微软雅黑", Font.BOLD, 15));

        JPanel p = new JPanel();
        p.setBackground(Color.white);

        p.add(phone);
        p.add(inputPhone);
        top.add(p);
    }

    void passwordInit(){
        JLabel password = new JLabel("密码", JLabel.CENTER);
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        password.setFont(new Font("微软雅黑", Font.BOLD, 15));

        p.add(password);
        p.add(inputPassword);
        top.add(p);
    }

    void buttonInit(){
        cancelButtonInit();
    }

    void cancelButtonInit(){
        cancelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String phone = inputPhone.getText();
                String password = inputPassword.getText();
                try {
                    Member member = memberService.queryMemberByPhone(phone);
                    if(member == null){
                        JOptionPane.showMessageDialog(null, "未找到该用户");
                        return;
                    }
                    if(!member.getPassword().equals(password)){
                        JOptionPane.showMessageDialog(null, "密码错误");
                        return;
                    }
                    String msg = "姓名：" + member.getName() + "\n" + "卡内余额：" + member.getBalance() + "\n" + "确认注销？";

                    int i = JOptionPane.showConfirmDialog(null, msg);

                    if (i == 0){
                        //确认注销
                        String money = JOptionPane.showInputDialog("输入退款金额");
                        memberService.freezeMemberByMemberCode(member.getMember_Code(), Float.parseFloat(money));
                        JOptionPane.showConfirmDialog(null, "注销成功", "成功", JOptionPane.OK_OPTION);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            ex.getLocalizedMessage().subSequence(ex.getLocalizedMessage().indexOf(':')+1, ex.getLocalizedMessage().indexOf('!')+1),
                            "错误",  JOptionPane.ERROR_MESSAGE);
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

}

class Query extends JPanel{

    MemberService memberService = new MemberServiceImpl();

    JLabel phone = new JLabel("手机号");
    JLabel memberMsg = new JLabel(" ", SwingConstants.CENTER);

    JTextField inputPhone  = new JTextField(15);

    JButton queryButton = new JButton("查询");
    JButton returnButton = new JButton("返回");

    /**
     * 查询前的界面
     */
    JPanel beforeQueryPanel = new JPanel(new GridLayout(4,1));
    JPanel afterQueryPanel = new JPanel(new GridLayout(4, 1));

    CardLayout cardLayout = new CardLayout();

    Query(){
        viewInit();
        buttonInit();
    }

    private void viewInit(){
        this.setLayout(cardLayout);
        beforeQueryPanelInit();
        afterQueryPanelInit();
    }

    void beforeQueryPanelInit(){

        beforeQueryPanel.setBackground(Color.WHITE);
        beforeQueryPanel.add(new JLabel()) ;


        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        phone.setFont(new Font("微软雅黑", Font.BOLD, 15));
        p.add(phone);
        p.add(inputPhone);

        beforeQueryPanel.add(p);

        JPanel p1 =  new JPanel();
        p1.setBackground(Color.WHITE);

        queryButton.setPreferredSize(new Dimension(130,30));
        queryButton.setBackground(new Color(108, 186, 216));
        p1.add(queryButton);
        beforeQueryPanel.add(p1);

        beforeQueryPanel.add(new JLabel()) ;

        this.add(beforeQueryPanel,"before");
        cardLayout.show(this, "before");
    }

    void afterQueryPanelInit(){
        afterQueryPanel.setBackground(Color.white);

        afterQueryPanel.add(new JLabel());
        memberMsg.setFont(new Font("微软雅黑", Font.BOLD, 15));
        afterQueryPanel.add(memberMsg);

        JPanel p =  new JPanel();
        p.setBackground(Color.white);

        returnButton.setPreferredSize(new Dimension(130,30));
        returnButton.setBackground(new Color(108, 186, 216));
        p.add(returnButton);
        afterQueryPanel.add(p);

        afterQueryPanel.add(new JLabel());

        this.add(afterQueryPanel,"after");
    }

    private void buttonInit() {
        queryButtonInit();
        returnButtonInit();
    }

    private void queryButtonInit(){
        queryButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String phone = inputPhone.getText();
                try {
                    Member member = memberService.queryMemberByPhone(phone);
                    if(member == null){
                        JOptionPane.showMessageDialog(null, "未找到该用户");
                        return;
                    }
                    String status = member.getStatus()==0?"已冻结":"正常使用";
                    String msg = "<html><body>"+"姓名：" + member.getName() + "<br><br>" + "卡内余额：" + member.getBalance() + "<br><br>" + "卡状态：" + status + "<body></html>";

                    memberMsg.setText(msg);
                    cardLayout.show(Query.this, "after");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void returnButtonInit(){
        returnButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                cardLayout.show(Query.this, "before");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


    }
}

class Update extends JPanel{
    MemberService memberService = new MemberServiceImpl();


    //当前正在修改的会员号
    String memberCode;

    JLabel phone = new JLabel("手机号");


    JTextField inputPhone  = new JTextField(12);

    JButton queryButton = new JButton("查找");


    JLabel uName = new JLabel("姓名");

    JLabel uPassword = new JLabel("密码");
    JLabel uStatues = new JLabel("状态");

    JTextField uInputName = new JTextField(15);
    JPasswordField uInputPassword = new JPasswordField(15);
    JComboBox uInputStatus = new JComboBox();

    JButton updateButton = new JButton("修改");

    JLabel remindMsg = new JLabel("当前没有正在修改的用户", SwingConstants.CENTER);

    /**
     * 查询前的界面
     */
    JPanel topPanel = new JPanel(new GridLayout(1,4));
    JPanel underPanel = new JPanel(new GridLayout(3, 1));

    JPanel updatePanel = new JPanel();



    Update(){
        viewInit();
        buttonInit();
    }

    private void viewInit(){
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(4,1));
       topPanelInit();
       underPanelInit();
       updatePanelInit();
       remindMsg.setFont(new Font("微软雅黑", Font.BOLD, 15));
        this.add(remindMsg);
    }

    void topPanelInit(){

        topPanel.setBackground(Color.white);

        topPanel.add(new JLabel());
        JPanel p = new JPanel();
        p.setBackground(Color.white);

        phone.setFont(new Font("微软雅黑",Font.BOLD, 15));
        p.add(phone);
        p.add(inputPhone);
        topPanel.add(p);

        JPanel p1 = new JPanel();
        p1.setBackground(Color.white);
        queryButton.setPreferredSize(new Dimension(130,20));
        p1.add(queryButton);
        topPanel.add(p1);

        topPanel.add(new JLabel());

        this.add(topPanel);
    }

    void underPanelInit(){
        underPanel.setBackground(Color.white);

        JPanel p = new JPanel();
        p.setBackground(Color.white);
        p.add(uName);
        uName.setFont(new Font("微软雅黑",Font.BOLD, 15));

        p.add(uInputName);
        underPanel.add(p);

        JPanel p1 = new JPanel();
        p1.setBackground(Color.white);
        p1.add(uPassword);
        uPassword.setFont(new Font("微软雅黑",Font.BOLD, 15));

        p1.add(uInputPassword);
        underPanel.add(p1);


        uInputStatus.addItem("冻结");
        uInputStatus.addItem("正常使用");

        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.add(uStatues);
        p2.add(uInputStatus);
        underPanel.add(p2);



    }


    private void buttonInit() {
        queryButtonInit();
        updateButtonInit();
    }

    private void updatePanelInit() {

        updatePanel.setBackground(Color.white);
        updateButton.setPreferredSize(new Dimension(130,30));

        updatePanel.add(updateButton);
    }

    private void queryButtonInit(){
        queryButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String phone = inputPhone.getText();
                Member member = null;
                try {
                    member = memberService.queryMemberByPhone(phone);

                    if(member == null){
                        JOptionPane.showMessageDialog(null, "未找到该用户");
                        return;
                    }

                    memberCode = member.getMember_Code();
                    uInputName.setText(member.getName());
                    uInputPassword.setText(member.getPassword());
                    uInputStatus.setSelectedIndex(member.getStatus());

                    Update.this.add(underPanel);
                    Update.this.add(updatePanel);
                    Update.this.remove(remindMsg);
                    Update.this.add(remindMsg);

                    remindMsg.setText("正在修改手机号为" +phone+"的用户");

                    SwingUtilities.updateComponentTreeUI(Update.this);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    private void updateButtonInit(){
        updateButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(memberCode == null || "".equals(memberCode)){
                    return;
                }
                String name = uInputName.getText();
                String password = uInputPassword.getText();
                String s = (String) uInputStatus.getSelectedItem();
                Integer status = "正常使用".equals(s)?1:0;
                try {
                    memberService.updateMemberNameAndPassordAndStatues(memberCode,name,password, status );
                    JOptionPane.showMessageDialog(null, "修改成功");
                } catch (SQLException ex) {

                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "修改失败");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }
}

class Charge extends JPanel{
    MemberService memberService = new MemberServiceImpl();
    ConsumeService consumeService = new ConsumeServiceImpl();

    //当前正在修改的会员号
    String memberCode;

    JLabel phone = new JLabel("手机号");


    JTextField inputPhone  = new JTextField(15);

    JButton chargeButton = new JButton("充值");


    JLabel chargePrice = new JLabel("充值金额");

    JTextField inputChargePrice = new JTextField(15);



    /**
     * 查询前的界面
     */

    public Charge(){
        viewInit();
        buttonInit();
    }

    private void viewInit(){

        this.setBackground(Color.white);
        this.setLayout(new GridLayout(5,1));
        this.add(new JLabel());
        JPanel p = new JPanel();
        p.setBackground(Color.white);

        phone.setFont(new Font("微软雅黑",Font.BOLD, 15));
        p.add(phone);

        p.add(inputPhone);
        this.add(p);

        JPanel p1= new JPanel();
        p1.setBackground(Color.white);
        chargePrice.setFont(new Font("微软雅黑",Font.BOLD, 15));
        p1.add(chargePrice);
        p1.add(inputChargePrice);
        this.add(p1);

        JPanel p2= new JPanel();
        p2.setBackground(Color.white);

        chargeButton.setPreferredSize(new Dimension(130, 25));
        p2.add(chargeButton);
        this.add(p2);

        this.add(new JLabel());


    }


    private void buttonInit() {
        chargeButtonInit();
    }


    private void chargeButtonInit(){
        chargeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String phone = inputPhone.getText();
                Member member = null;
                try {

                    member = memberService.queryMemberByPhone(phone);

                    if(member == null){
                        JOptionPane.showMessageDialog(null, "未找到该用户");
                        return;
                    }

                    memberCode = member.getMember_Code();
                    Double price = Double.parseDouble(inputChargePrice.getText()) ;
                    int s = JOptionPane.showConfirmDialog(null, "确认给该用户充值" + price + "元？", "警告", JOptionPane.YES_NO_OPTION);
                    if(s == 0){
                        consumeService.chargeToMemberByMemberCode(memberCode, price);
                        JOptionPane.showConfirmDialog(null, "充值成功", "充值状态", JOptionPane.YES_OPTION);
                    }


                } catch (SQLException ex) {
                    JOptionPane.showConfirmDialog(null,  ex.getLocalizedMessage().subSequence(ex.getLocalizedMessage().indexOf(':')+1, ex.getLocalizedMessage().indexOf('!')+1),
                            "错误", JOptionPane.YES_OPTION);

                    ex.printStackTrace();
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

}

