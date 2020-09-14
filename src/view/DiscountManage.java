package view;

import pojo.Discount;
import service.DiscountService;
import service.impl.DiscountServiceImpl;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;


public class DiscountManage extends JFrame {
    int width = 1000;
    int height = 600;

    private DefaultTableModel discountTableModel =  new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int rowIndex, int colIndex){
            if(colIndex == 0){
                return false;
            }
            return true;
        }


    };


    Object[] title = {"折扣号", "名称", "折扣力度"};
    private JTable discountTable = new JTable();
    Panel tablePanel = new Panel(new BorderLayout());

    DiscountService discountService = new DiscountServiceImpl();

    private JPanel searchPanel = new JPanel();
    JComboBox searchCondition = new JComboBox();
    JTextField inputSearchCondition = new JTextField(10);
    JButton searchButton = new JButton("查询");
    JButton searchAllButton = new JButton("查询全部");


    private JPanel southPanel = new JPanel();
    JButton addButton = new JButton("添加");
    JButton sureAddButton = new JButton("确认添加");
    JButton clearButton = new JButton("删除");
    JButton updateButton = new JButton("确认修改");
    JButton cancelButton = new JButton("取消修改");

    //修改之前的数据

    Object[][] beforeData;

    int maxCode = 0;

    Vector addRows = new Vector();

    Map<String, Object> updateRows = new HashMap<>();


    public DiscountManage(){
        viewInit();
        buttonInit();
    }

    private void viewInit(){
        int x = Toolkit.getDefaultToolkit().getScreenSize().width;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((x - width) / 2,
                (y - height) / 2, width, height);
        this.setLayout(new BorderLayout(5,20));
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        setSize(width, height);
        discountTableInit();
        searchPanelInit();
        southPanelInit();
    }

    private void discountTableInit(){
        List<Discount> discount = null;
        try {
            discount = discountService.queryAllDiscount();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        updateData(discount);

        discountTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int firstRow = e.getFirstRow();
                if(firstRow >= 0 ){
                    String value = (String) discountTableModel.getValueAt(firstRow, 0);
                    updateRows.remove(value);
                    Vector v = new Vector();
                    for (int i = 0 ; i < title.length; i++){

                        v.add (discountTableModel.getValueAt(firstRow, i));
                    }
                    updateRows.put(value, v);
                }


            }
        });

        discountTable.setAutoCreateRowSorter(true);

        // 产生一个带滚动条的面板
        JScrollPane scrollPane = new JScrollPane(discountTable);

        // 将带滚动条的面板添加入窗口中
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        tablePanel.add(discountTable.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        this.add(tablePanel, BorderLayout.CENTER);
    }

    private void searchPanelInit(){

        searchPanel.add(new JLabel("请选择查询条件"));
        searchCondition.addItem("折扣号");
        searchCondition.addItem("折扣名称");
        searchPanel.add(searchCondition);
        inputSearchCondition.setSize(100, 50);
        searchPanel.add(inputSearchCondition);
        this.add(searchPanel, BorderLayout.NORTH);

    }

    private void southPanelInit(){
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void buttonInit() {
        searchButtonInit();
        searchAllButtonInit();
        addButtonInit();
        sureAddButtonInit();
        clearButtonInit();
        updateButtonInit();
        cancelButtonInit();
    }

    private void searchButtonInit(){
        searchButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedCondition = (String) searchCondition.getSelectedItem();
                String text = inputSearchCondition.getText();
                String condition = "";

                if("折扣号".equals(selectedCondition)){
                    condition = " discount_Code = "+ text;
                }
                else if("折扣名称".equals(selectedCondition)){
                    condition = " name like " + "'%" + text +"%'";
                }

                List<Discount> g = null;

                try {
                    g = discountService.queryDiscountByCondition(condition);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                updateData(g);

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


        searchPanel.add(searchButton);
    }

    private void searchAllButtonInit(){
        searchAllButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {


                List<Discount> g = null;

                try {
                    g = discountService.queryAllDiscount();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                updateData(g);

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


        searchPanel.add(searchAllButton);
    }

    private void addButtonInit(){
        addButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String discountCode = null;
                try {
                    discountCode = discountService.queryMaxDiscountCode();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                int i = Integer.parseInt(discountCode);
                addRows.add(discountTableModel.getRowCount());
                maxCode = Math.max(maxCode, i) + 1;

                discountTableModel.addRow(new Object[]{maxCode + "", "", 1.0});
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
        southPanel.add(addButton, BorderLayout.SOUTH);
    }

    private void sureAddButtonInit(){
        sureAddButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Vector ag = new Vector();
                for(Object k : addRows){
                    int c = (int) k;
                    Discount g = new Discount();
                    g.setDiscount_Code((String) discountTableModel.getValueAt(c, 0));
                    g.setName((String) discountTableModel.getValueAt(c, 1));
                    Float discount = Float.parseFloat(discountTableModel.getValueAt(c, 2) + "") ;

                    g.setDiscount(discount == null ? 0 : discount);

                    ag.add(g);
                }
                try {
                    discountService.addSomeDiscount(ag);
                    addRows.clear();
                    JOptionPane.showMessageDialog(null, "添加成功");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "添加失败");
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
        southPanel.add(sureAddButton, BorderLayout.SOUTH);
    }

    private void clearButtonInit(){
        clearButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] selectedRows = discountTable.getSelectedRows();
                Vector<String> deleteRows = new Vector<>();
                for (int i : selectedRows){
                    deleteRows.add((String) discountTableModel.getValueAt(i,0));
                }


                try {
                    discountService.deleteDiscountByDiscountCode(deleteRows);
                    updateData(discountService.queryAllDiscount());
                    JOptionPane.showMessageDialog(null, "删除成功");
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
        southPanel.add(clearButton, BorderLayout.SOUTH);
    }

    private void updateButtonInit(){
        updateButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Set<String> keySet = updateRows.keySet();

                for(String k : keySet){
                    Vector data = (Vector) updateRows.get(k);
                    Discount g = new Discount();
                    g.setDiscount_Code((String) data.get(0));
                    g.setName((String) data.get(1));
                    g.setDiscount(Float.parseFloat(data.get(2) + "" ));

                    try {
                        discountService.updateDiscount(g);
                        JOptionPane.showMessageDialog(null, "修改成功");
                        updateRows.clear();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "修改失败");
                        ex.printStackTrace();
                    }

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
        southPanel.add(updateButton, BorderLayout.SOUTH);
    }

    private void cancelButtonInit(){

        cancelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateRows.clear();
                discountTableModel.getDataVector().removeAllElements();
                discountTableModel.setDataVector(beforeData,title);
                discountTable.setModel(discountTableModel);
                maxCode = 0;
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

        southPanel.add(cancelButton);
    }

    private Object[][] getDate(List<Discount> discount){
        int size = discount.size();
        Object[][] data = new Object[size][4];

        for(int i = 0; i < discount.size(); i++){
            Discount iDiscount = discount.get(i);
            data[i][0] = iDiscount.getDiscount_Code();
            data[i][1] = iDiscount.getName();
            data[i][2] = iDiscount.getDiscount();
        }
        return data;
    }

    private void updateData(List<Discount> discount){

        discountTableModel.getDataVector().removeAllElements();
        beforeData = getDate(discount);
        discountTableModel.setDataVector(beforeData,title);
        discountTable.setModel(discountTableModel);
        maxCode = 0;
    }
}