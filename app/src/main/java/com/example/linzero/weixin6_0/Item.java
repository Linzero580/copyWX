package com.example.linzero.weixin6_0;

/**
 * Created by Linzero on 2017/03/06.
 */

public class Item {
    private int iconId;
    private String iconName;

    public Item() {
    }

    public Item(int iconId, String iconName) {
        this.iconId=iconId;
        this.iconName=iconName;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
