package controller;

import view.RecordManage;

public class RecordManageController {
    public static RecordManage recordManage;

    public static void show(){
        recordManage = new RecordManage();
        recordManage.setVisible(true);
    }
}
