package com.jiangzz.entity;

public class JobInfo {
    private String name;
    private String group;

    public JobInfo(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public JobInfo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
