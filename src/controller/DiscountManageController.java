package controller;

import pojo.Discount;
import view.DiscountManage;

public class DiscountManageController {
    private static DiscountManage discountManage;

    public static void show(){
        discountManage = new DiscountManage();
        discountManage.setVisible(true);
    }
}
