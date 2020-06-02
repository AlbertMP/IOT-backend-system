package com.cl.enums;

public enum DeviceStatusEnum {

    ONLINE(1,"在线"),
    OFFLINE(0,"离线")
    ;


    private Integer value;

    private String desc;

    DeviceStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
