package controller;

import view.MemberManage;

public class MemberManageController {
    public static MemberManage memberManage = new MemberManage();

    public static void show(){
        memberManage.setVisible(true);
    }
}
