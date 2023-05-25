package com.bcajagn.brainbox.home;

import java.util.List;

public class DataModel {

    private List<String> chaildlist;
    private String itemText;

    String cover;
    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    private boolean isExpandable;

    public DataModel(List<String> itemList, String itemText) {
        this.chaildlist = itemList;
        this.itemText = itemText;
        isExpandable = false;

    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public List<String> getchaildlist() {
        return chaildlist;
    }

    public String getItemText() {
        return itemText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }



}
