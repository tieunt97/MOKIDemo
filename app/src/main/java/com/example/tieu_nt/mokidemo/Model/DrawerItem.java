package com.example.tieu_nt.mokidemo.Model;

/**
 * Created by tieu_nt on 3/12/2018.
 */

public class DrawerItem {
    private int icon;
    private String tenItem;

    public DrawerItem(int icon, String tenItem) {
        this.icon = icon;
        this.tenItem = tenItem;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTenItem() {
        return tenItem;
    }

    public void setTenItem(String tenItem) {
        this.tenItem = tenItem;
    }
}
