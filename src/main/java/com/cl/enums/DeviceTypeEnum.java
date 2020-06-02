package com.cl.enums;

public enum DeviceTypeEnum {
    LIGHT(1,"智能电灯", "css/img/1.jpg"),
    AIR_CONDITIONING(2,"智能空调", "css/img/2.jpg")
    ;

    private Integer value;

    private String desc;

    private String img;

    DeviceTypeEnum(Integer value, String desc, String img) {
        this.value = value;
        this.desc = desc;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static DeviceTypeEnum getDevTypeStatus(Integer devType){
        for (DeviceTypeEnum deviceTypeEnum : DeviceTypeEnum.values()){
            if (deviceTypeEnum.getValue().equals(devType)){
                return deviceTypeEnum;
            }
        }
        throw new RuntimeException("没有找到相应的设备类型");
    }
}
