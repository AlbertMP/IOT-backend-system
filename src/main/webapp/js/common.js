
var change = {
    deviceStatus:function (status){
        if (status == 1)
            return "在线"
        else
            return "离线"
    },
    deviceType:function (devType) {
        if (devType == 1) {
            return "智能电灯"
        } else if (devType == 2) {
            return "智能空调"
        }
        return "null";
    }
}
