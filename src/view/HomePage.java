package view;

import controller.DiscountManageController;
import controller.GoodsManageController;
import controller.MemberManageController;
import controller.RecordManageController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HomePage extends JFrame{

    private int width = 1203;
    private int height = 677;


    ImageIcon bg = new ImageIcon("src/img/bg-homepage.jpg");

    int buttonDefaultWidth = (int) (width*0.2);
    int buttonDefaultHeight= (int) (height*0.05);

    //按钮之间的间隔

    private int buttonSpace = buttonDefaultHeight + 10;


    //标题

    JLabel title = new JLabel("游泳馆会员管理系统", JLabel.CENTER);
    JLabel bgLabel = new JLabel(bg);


    // 页面的按钮组件

    JButton member = new JButton("会员管理");
    JButton record = new JButton("记录管理");
    JButton goods = new JButton("商品管理");
    JButton discount = new JButton("折扣管理");



    /**
     * 构造函数
     */
    public HomePage(){
        viewInit();
        buttonInit();
        titleInit();
    }

    /**
     * 对主页面初始化
     */
    void viewInit(){

        bgLabel.setSize(width, height);
        this.add(bgLabel);
        setLayout(null);
        setSize(width, height);
        setVisible(true);
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //去除标题栏



    }

    void buttonInit(){
        memberButtonInit();
        recordButtonInit();
        goodsButtonInit();
        discountButtonInit();
    }

    /**
     * 对按钮相同的属性初始化
     * @param button 接收的按钮
     */
    void buttonSameInit(JButton button){
        button.setSize(buttonDefaultWidth, buttonDefaultHeight);
        Dimension size = button.getSize();
        button.setVisible(true);
        button.setLocation(width/2 - size.width/2,height/2 + 2*buttonSpace+25);
        bgLabel.add(button);
        button.setForeground(new Color(253,245,230));
    }

    private void memberButtonInit(){
        buttonSameInit(member);
        member.setBackground(new Color(251,114,153));
        member.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MemberManageController.show();
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

    private void recordButtonInit(){
        buttonSameInit(record);
        record.setBackground(new Color(0,161,214));
        record.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RecordManageController.show();
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

        record.setLocation(record.getLocation().x,record.getLocation().y + buttonSpace);
    }

    private void goodsButtonInit(){
        buttonSameInit(goods);
        goods.setBackground(new Color(30,111,255));
        goods.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GoodsManageController.show();
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
        goods.setLocation(goods.getLocation().x,goods.getLocation().y + 2*buttonSpace);
    }

    private void discountButtonInit(){
        buttonSameInit(discount);
        discount.setBackground(new Color(130,170,255));
        discount.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DiscountManageController.show();
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
        discount.setLocation(discount.getLocation().x,discount.getLocation().y + 3*buttonSpace);
    }

    private void titleInit(){
        title.setForeground(new Color(253,245,230));
        title.setFont(new Font("楷体", Font.BOLD, 40));
        title.setSize(width, height/4);
        title.setLocation(width/2 - title.getWidth()/2, height/8);
        bgLabel.add(title);
    }

}
