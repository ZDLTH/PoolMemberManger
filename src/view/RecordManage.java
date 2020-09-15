package view;

import pojo.*;
import service.*;
import service.impl.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.Year;
import java.util.*;
import java.util.List;

public class RecordManage extends JFrame{
    int width = 1200;
    int height = 800;

    JButton addConsumeButton = new JButton("添加消费记录");
    JButton queryConsumeButton = new JButton("查询消费记录");
    JButton queryChargeButton = new JButton("查询充值记录");

    JPanel centerPanel = new JPanel();
    CardLayout centerLayout = new CardLayout();

    /**
     * 用来放换页的按钮
     */
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));

    public RecordManage() {
        viewInit();
        buttonInit();
    }

    private void viewInit(){
        int x = Toolkit.getDefaultToolkit().getScreenSize().width;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((x - width) / 2,
                (y - height) / 2, width, height);
        setLayout(new BorderLayout(0,0));
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        setSize(width, height);
        centerInit();
        northInit();
    }

    void centerInit(){
        centerPanel.setLayout(centerLayout);
        centerPanel.add(new AddConsume(), "addConsume");
        centerPanel.add(new QueryConsume(), "queryConsume");
        centerPanel.add(new QueryCharge(), "queryCharge");
        centerLayout.show(centerPanel, "addRecord");
        this.add(centerPanel, BorderLayout.CENTER);
    }

    void northInit(){
        northPanel.setBackground(new Color(50,156,199));
        this.add(northPanel, BorderLayout.NORTH);
    }


    private void buttonInit() {
        addConsumeButtonInit();
        queryConsumeButtonInit();
        queryChargeButtonInit();
    }

    private void addConsumeButtonInit() {
        addConsumeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerLayout.show(centerPanel, "addConsume");
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
        addConsumeButton.setBackground(new Color(68,171,71));
        addConsumeButton.setForeground(Color.white);
        northPanel.add(addConsumeButton);

    }

    private void queryConsumeButtonInit() {
        queryConsumeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerLayout.show(centerPanel, "queryConsume");
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
        queryConsumeButton.setBackground(new Color(252,191,0));
        queryConsumeButton.setForeground(Color.white);
        northPanel.add(queryConsumeButton);
    }

    private void queryChargeButtonInit() {
        queryChargeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                centerLayout.show(centerPanel, "queryCharge");
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
        queryChargeButton.setBackground(new Color(255,63,51));
        queryChargeButton.setForeground(Color.white);

        northPanel.add(queryChargeButton);
    }


}

class AddConsume extends JPanel{

    GoodsService goodsService= new GoodsServiceImpl();
    DiscountService discountService = new DiscountServiceImpl();
    ConsumeService consumeService = new ConsumeServiceImpl();
    MemberService memberService = new MemberServiceImpl();

    List<Goods> goodsMsg = new ArrayList<>();
    List<Discount> discountsMsg = new ArrayList<>();
    JComboBox<String> discountComBox = new JComboBox<String>();
    JComboBox<String> goodsComboBox = new JComboBox<>();
    Map<String, Double> goodsPrice = new HashMap<>();
    Map<String, Float> discount = new HashMap<>();

    JLabel addConsume = new JLabel("添加订单", JLabel.CENTER);


    JPanel centerPanel = new JPanel(new GridLayout(3,1));

    JLabel consumeGoods = new JLabel("购买商品种类", JLabel.CENTER);
    List<JComboBox<String>> chooseGoods = new ArrayList<JComboBox<String>>();
    List<JTextField> chooseGoodsCount = new ArrayList<>();
    JButton addGoodsButton = new JButton();
    JButton reduceButton = new JButton();
    GridLayout orderGridLayout = new GridLayout(3,1,0,0);
    JPanel orderPanel = new JPanel(orderGridLayout);



    JRadioButton member = new JRadioButton("会员",true);
    JRadioButton tourist = new JRadioButton("游客");
    ButtonGroup group = new ButtonGroup();
    JLabel memberPhone = new JLabel("会员电话", JLabel.CENTER);
    JTextField inputMemberPhone = new JTextField(10);
    JPanel accountTypeChoosePanel = new JPanel(new GridLayout(2,1));
    JPanel memberPhonePanel = new JPanel();


    JLabel chooseDiscount = new JLabel();

    JButton addOrderButton = new JButton("添加");


    ImageIcon addBtnIcon = new ImageIcon(this.getClass().getClassLoader().getResource("img/addBtn.jpg"));
    ImageIcon reduceBtnIcon = new ImageIcon(this.getClass().getClassLoader().getResource("img/reduceBtn.jpg"));


    JLabel touristRemind = new JLabel("当前是游客",SwingConstants.CENTER);

    public AddConsume() {
        viewInit();
        buttonInit();

        group.add(member);
        group.add(tourist);

        try {
            goodsMsg = goodsService.queryAllGoods();
            discountsMsg = discountService.queryAllDiscount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Goods g : goodsMsg){
            goodsPrice.put(g.getName(), g.getPrice());
            goodsComboBox.addItem(g.getName());
        }
        for (Discount d : discountsMsg){
            discount.put(d.getName(), d.getDiscount());
            discountComBox.addItem(d.getName());
        }


    }

    private void viewInit() {
        this.setBackground(Color.white);

        this.setLayout(new BorderLayout());
        addConsume.setFont(new Font("微软雅黑", Font.BOLD, 20));
        this.add(addConsume, BorderLayout.NORTH);

        centerPanelInit();
    }

    void centerPanelInit(){

        chooseGoods.add(goodsComboBox);
        chooseGoodsCount.add(new JTextField(5));


        JPanel p = new JPanel();
        p.setBackground(Color.white);
        consumeGoods.setFont(new Font("微软雅黑", Font.BOLD, 15));
        p.add(consumeGoods);
        addGoodsButton.setPreferredSize(new Dimension(25,25));
        addGoodsButton.setIcon(addBtnIcon);
        p.add(addGoodsButton);

        reduceButton.setPreferredSize(new Dimension(25,25));
        reduceButton.setIcon(reduceBtnIcon);
        p.add(reduceButton);
        orderPanel.add(p);



        JPanel p1 = new JPanel();
        p1.setBackground(Color.white);

        p1.add(new JLabel("商品类型 "));
        p1.add(new JLabel(" 购买数量"));
        orderPanel.add(p1);

        JPanel p2 = new JPanel();

        p2.setBackground(Color.white);
        p2.add(chooseGoods.get(0));
        p2.add(chooseGoodsCount.get(0));
        orderPanel.add(p2);

        centerPanel.add(orderPanel);



        JPanel p3 = new JPanel();
        p3.setBackground(Color.white);

        p3.add(member);
        p3.add(tourist);
        accountTypeChoosePanel.add(p3);
        accountTypeChoosePanel.setBackground(Color.white);
        memberPhonePanel.setBackground(Color.white);
        memberPhonePanel.add(memberPhone);
        memberPhonePanel.add(inputMemberPhone);
        accountTypeChoosePanel.add(memberPhonePanel);
        centerPanel.add(accountTypeChoosePanel);


        member.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange() == ItemEvent.SELECTED){
                    accountTypeChoosePanel.add(memberPhonePanel);
                    accountTypeChoosePanel.remove(touristRemind);


                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    accountTypeChoosePanel.remove(memberPhonePanel);
                    accountTypeChoosePanel.add(touristRemind);
                }
                SwingUtilities.updateComponentTreeUI(AddConsume.this);
            }
        });


        JPanel p4 = new JPanel();
        p4.setBackground(Color.white);

        p4.add(new JLabel("选择折扣"));
        p4.add(discountComBox);
        p4.add(chooseDiscount);
        discountComBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object item = e.getItem();
                Float d = discount.get(item);
                if(d < 1){
                    d*=10;
                    chooseDiscount.setText(d+"折");
                }
                else {
                    chooseDiscount.setText("");
                }


            }
        });
        centerPanel.add(p4);



        this.add(centerPanel, BorderLayout.CENTER);



    }

    private void buttonInit() {
        addGoodsButtonInit();
        addOrderButtonInit();
        reduceButtonInit();

    }

    private void reduceButtonInit() {
        reduceButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(orderPanel.getComponentCount() > 3){
                    orderPanel.remove(orderPanel.getComponentCount()-1);
                    orderGridLayout = new GridLayout(orderGridLayout.getRows()-1, 1,0,0);
                    orderPanel.setLayout(orderGridLayout);
                }
                if(chooseGoods.size() > 1){
                    chooseGoods.remove(chooseGoods.size()-1);
                    chooseGoodsCount.remove(chooseGoodsCount.size()-1);
                }


                SwingUtilities.updateComponentTreeUI(AddConsume.this);
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


    private void addGoodsButtonInit() {
        addGoodsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(orderGridLayout.getRows() < 10){
                    orderGridLayout = new GridLayout(orderGridLayout.getRows()+1, 1,0,0);
                    orderPanel.setLayout(orderGridLayout);
                    JPanel p = new JPanel();
                    p.setBackground(Color.white);
                    chooseGoods.add(getNewGoodsComboBox());
                    chooseGoodsCount.add(new JTextField(5));
                    p.add(chooseGoods.get(chooseGoods.size()-1));
                    p.add(chooseGoodsCount.get(chooseGoodsCount.size()-1));
                    orderPanel.add(p);
                    SwingUtilities.updateComponentTreeUI(AddConsume.this);
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


    private void addOrderButtonInit() {

        addOrderButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                double price = 0;
                List<String> goodsCodeList = new ArrayList<>();
                List<Integer> goodsCountList = new ArrayList<>();

                try {
                    for (int i = 0; i < chooseGoods.size(); i++) {
                        Object goodsName = chooseGoods.get(i).getSelectedItem();
                        String goodsCount = chooseGoodsCount.get(i).getText();
                        Double p = goodsPrice.get(goodsName);
                        price += (p * Double.parseDouble(goodsCount));
                        Goods g = null;
                        g = goodsService.queryGoodsByGoodsName((String) goodsName);
                        goodsCodeList.add(g.getGoods_Code());
                        goodsCountList.add(Integer.parseInt(goodsCount));

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                Float d = discount.get(discountComBox.getSelectedItem())*10;
                double actual = (price * d.intValue())/10;
                int result = JOptionPane.showConfirmDialog(null, "总金额"+ price+"\n" + "应付金额"+actual,
                        "确认添加订单", JOptionPane.YES_NO_OPTION);


                    if(result == JOptionPane.YES_OPTION){
                        try {


                            String discount_code = discountService.queryDiscountByDiscountName((String) discountComBox.getSelectedItem()).getDiscount_Code();

                            if(member.isSelected()){

                                Member member = null;
                                member = memberService.queryMemberByPhone(inputMemberPhone.getText());
                                if (member == null){
                                    JOptionPane.showConfirmDialog(null,"会员不存在", "error",JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                String password = JOptionPane.showInputDialog(null, "输入密码", "验证密码", JOptionPane.INFORMATION_MESSAGE);


                                if (member.getPassword().equals(password)){
                                    consumeService.addSomeConsumeIsMember(member.getMember_Code(),goodsCodeList,discount_code, goodsCountList);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "密码错误");
                                    return;
                                }


                            }else {
                                consumeService.addSomeConsumeNotMember(goodsCodeList,discount_code, goodsCountList);
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            try {
                                consumeService.rollBack();
                            } catch (SQLException exc) {
                                exc.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null,
                                    ex.getLocalizedMessage().subSequence(ex.getLocalizedMessage().indexOf(':')+1, ex.getLocalizedMessage().indexOf('!')+1),
                                    "错误",  JOptionPane.ERROR_MESSAGE);
                            return;

                        }
                        JOptionPane.showMessageDialog(null, "添加成功！","订单状态", JOptionPane.OK_OPTION);


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


        JPanel p = new JPanel();
        p.setBackground(Color.white);
        addOrderButton.setForeground(Color.white);
        addOrderButton.setBackground(new Color(22,189,116));
        addOrderButton.setPreferredSize(new Dimension(180, 20));
        p.add(addOrderButton);
        this.add(p,BorderLayout.SOUTH);
    }



    JComboBox<String> getNewGoodsComboBox(){
        JComboBox<String> j = new JComboBox<>();
        for (Goods g : goodsMsg){
            j.addItem(g.getName());
        }
        return j;
    }

}

class QueryConsume extends JPanel{
    ConsumeService consumeService = new ConsumeServiceImpl();

    Panel tablePanel = new Panel(new BorderLayout());
    Object []title = {"姓名","手机号", "商品名", "折扣名","折扣力度", "数量", "金额", "交易时间"};
    private DefaultTableModel consumeTableModel =  new DefaultTableModel();
    private JTable consumeTable = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }
    };


    JPanel searchPanel = new JPanel();
    JComboBox<String> accountType = new JComboBox<>();
    JLabel phone = new JLabel("手机号", JLabel.CENTER);
    JTextField inputPhone = new JTextField(12);
    JButton searchButton = new JButton("查找");
    JTextField year = new JTextField(4);
    JTextField month = new JTextField(3);
    JTextField day = new JTextField(3);

    List<VConsume> allConsumes= null;
    Object[][] memberConsumes;
    Object[][] visitorConsumes;

    public QueryConsume(){
        viewInit();
        buttonInit();
    }

    private void viewInit() {
        this.setLayout(new BorderLayout(5,50));
        tablePanelInit();
        searchPanelInit();

    }

    private void searchPanelInit() {

        String []type = {"全部", "仅会员", "仅游客"};
        for (String s : type){
            accountType.addItem(s);
        }
        accountType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedItem = (String) accountType.getSelectedItem();
                String lastItem = (String) e.getItem();
                if(!selectedItem.equals(lastItem)){
                    switch (selectedItem){
                        case "全部": updateData(allConsumes);break;
                        case "仅会员": updateData(memberConsumes);break;
                        case "仅游客": updateData(visitorConsumes);break;
                        default:break;
                    }
                }
            }
        });

        searchPanel.setBackground(new Color(50,156,199));
        searchPanel.add(new JLabel("用户类型"));
        searchPanel.add(accountType);

        searchPanel.add(phone);
        searchPanel.add(inputPhone);


        searchPanel.add(year);
        searchPanel.add(new JLabel("年"));

        searchPanel.add(month);
        searchPanel.add(new JLabel("月"));

        searchPanel.add(day);
        searchPanel.add(new JLabel("日"));


        searchPanel.add(searchButton);

        this.add(searchPanel, BorderLayout.NORTH);
    }

    private void tablePanelInit() {

        try {
            allConsumes = consumeService.queryAllConusmesOfVConsume();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        updateData(allConsumes);
        consumeTable.setAutoCreateRowSorter(true);

        // 产生一个带滚动条的面板
        JScrollPane scrollPane = new JScrollPane(tablePanel);

        tablePanel.add(consumeTable.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(consumeTable, BorderLayout.CENTER);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void buttonInit() {
        searchButtonInit();
    }

    private void searchButtonInit() {
        searchButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String inputPhoneText = inputPhone.getText();
                String y = year.getText();
                String m = month.getText();
                String d = day.getText();

                List conditionName = new ArrayList();
                List conditionValue = new ArrayList();

                conditionName.add("phone");
                conditionName.add("to_char(vconsume.time,'yyyy')");
                conditionName.add("to_char(vconsume.time,'mm')");
                conditionName.add("to_char(vconsume.time,'dd')");

                conditionValue.add(inputPhoneText);
                conditionValue.add(y);
                if(!"".equals(m)&&Integer.parseInt(m) < 10){
                    m = "0".concat(m);
                }
                conditionValue.add(m);
                if(!"".equals(d) && Integer.parseInt(d) < 10){
                    d = "0".concat(d);
                }
                conditionValue.add(d);

                try {
                    allConsumes = consumeService.queryConsumesByCondition(conditionName, conditionValue);
                    updateData(allConsumes);
                    if(!"".equals(inputPhoneText)){
                        accountType.setSelectedItem("仅会员");
                    }else {
                        accountType.setSelectedItem("全部");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "查找失败");
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

    private Object[][] getDate(List<VConsume> consumes){

        int size = consumes.size();
        Object[][] data = new Object[size][8];
        visitorConsumes = new Object[size][8];
        memberConsumes = new Object[size][8];
        int vc = 0;
        int mc = 0;
        for(int i = 0; i < consumes.size(); i++){
            VConsume consume = consumes.get(i);
            String name = consume.getName();

            data[i][0] = name == null?"游客": name;

            data[i][1] = consume.getPhone();
            data[i][2] = consume.getGoodsName();
            data[i][3] = consume.getDiscountName();
            data[i][4] = consume.getDiscount();
            data[i][5] = consume.getCount();
            data[i][6] = consume.getPrice();
            data[i][7] = consume.getTime();

            if("游客".equals(data[i][0])){
                visitorConsumes[vc++] = data[i];
            }else{
                memberConsumes[mc++] = data[i];
            }

        }
        return data;
    }

    private void updateData(List<VConsume> consumes){

        consumeTableModel.getDataVector().removeAllElements();
        consumeTableModel.setDataVector(getDate(consumes),title);
        consumeTable.setModel(consumeTableModel);
    }

    private void updateData(Object[][] consumes){
        consumeTableModel.getDataVector().removeAllElements();
        consumeTableModel.setDataVector(consumes,title);
        consumeTable.setModel(consumeTableModel);
    }


}

class QueryCharge extends JPanel{
    ChargeService chargeService = new ChargeServiceImpl();

    Panel tablePanel = new Panel(new BorderLayout());
    Object []title = {"充值号", "姓名","手机号","金额","交易时间" };
    private DefaultTableModel chargeTableModel =  new DefaultTableModel();
    private JTable chargeTable = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }
    };


    JPanel searchPanel = new JPanel();
    JLabel phone = new JLabel("手机号", JLabel.CENTER);
    JTextField inputPhone = new JTextField(12);
    JButton searchButton = new JButton("查找");
    JTextField year = new JTextField(4);
    JTextField month = new JTextField(3);
    JTextField day = new JTextField(3);

    List<VCharge> allCharges= null;

    public QueryCharge(){
        viewInit();
        buttonInit();
    }

    private void viewInit() {
        this.setLayout(new BorderLayout(5,50));
        tablePanelInit();
        searchPanelInit();

    }

    private void searchPanelInit() {


        searchPanel.add(phone);
        searchPanel.add(inputPhone);


        searchPanel.add(year);
        searchPanel.add(new JLabel("年"));

        searchPanel.add(month);
        searchPanel.add(new JLabel("月"));

        searchPanel.add(day);
        searchPanel.add(new JLabel("日"));


        searchPanel.add(searchButton);

        searchPanel.setBackground(new Color(50,156,199));
        this.add(searchPanel, BorderLayout.NORTH);
    }

    private void tablePanelInit() {

        try {
            allCharges = chargeService.queryAllChargesOfVCharge();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        updateData(allCharges);
        chargeTable.setAutoCreateRowSorter(true);

        // 产生一个带滚动条的面板
        JScrollPane scrollPane = new JScrollPane(tablePanel);

        tablePanel.add(chargeTable.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(chargeTable, BorderLayout.CENTER);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void buttonInit() {
        searchButtonInit();
    }

    private void searchButtonInit() {
        searchButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String inputPhoneText = inputPhone.getText();
                String y = year.getText();
                String m = month.getText();
                String d = day.getText();

                List conditionName = new ArrayList();
                List conditionValue = new ArrayList();

                conditionName.add("phone");
                conditionName.add("to_char(time,'yyyy')");
                conditionName.add("to_char(time,'mm')");
                conditionName.add("to_char(time,'dd')");

                conditionValue.add(inputPhoneText);
                conditionValue.add(y);
                if(!"".equals(m)&&Integer.parseInt(m) < 10){
                    m = "0".concat(m);
                }
                conditionValue.add(m);
                if(!"".equals(d) && Integer.parseInt(d) < 10){
                    d = "0".concat(d);
                }
                conditionValue.add(d);

                try {
                    allCharges = chargeService.queryChargesByCondition(conditionName, conditionValue);
                    updateData(allCharges);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "查找失败");
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

    private Object[][] getDate(List<VCharge> charges){

        int size = charges.size();
        Object[][] data = new Object[size][title.length];

        int vc = 0;
        int mc = 0;
        for(int i = 0; i < charges.size(); i++){
            VCharge charge = charges.get(i);
            data[i][0] = charge.getChargeCode();
            data[i][1] = charge.getName();
            data[i][2] = charge.getPhone();
            data[i][3] = charge.getPrice();
            data[i][4] = charge.getTime();
        }
        return data;
    }

    private void updateData(List<VCharge> charges){

        chargeTableModel.getDataVector().removeAllElements();
        chargeTableModel.setDataVector(getDate(charges),title);
        chargeTable.setModel(chargeTableModel);
    }


}


