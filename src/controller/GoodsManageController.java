package controller;

import view.GoodsManage;
import view.MemberManage;

public class GoodsManageController {
    private static GoodsManage goodsMange;

    public static void show(){
        goodsMange = new GoodsManage();
        goodsMange.setVisible(true);
    }
}
