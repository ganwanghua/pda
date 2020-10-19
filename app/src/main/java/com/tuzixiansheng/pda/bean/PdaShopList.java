package com.tuzixiansheng.pda.bean;

import java.util.List;

public class PdaShopList {
    /**
     * code : 1
     * msg : 获取门店列表
     * time : 1602654412
     * data : [{"id":5,"name":"琥珀新天地","images":["https://images.tuzixs.com/syfarm/upload/a82f41a8-2324-4a5e-b84e-0ad5cb52f04c.jpg"],"realname":"丁黎","phone":"18956030056","province_name":"安徽省","city_name":"合肥市","area_name":"包河区","province_id":340000,"city_id":340100,"area_id":340111,"address":"安徽省合肥市包河区东流路868号琥珀新天地西苑8幢商105","latitude":"31.817063","longitude":"117.264359","store":"0","selfetch":"1","service_type":"radius","service_radius":1000,"service_province_ids":null,"service_city_ids":null,"service_area_ids":null,"openhours":"07:00 - 20:00","openweeks":"1,2,3,4,5,6,7","status":"1","createtime":1585889126,"updatetime":1602651881,"store_no":"TZ0004","store_desc":"TZ0004：琥珀新天地","UserId":2,"openweeks_arr":[1,2,3,4,5,6,7],"distance_text":"0m","image_first":"https://images.tuzixs.com/syfarm/upload/a82f41a8-2324-4a5e-b84e-0ad5cb52f04c.jpg"}]
     */

    private int code;
    private String msg;
    private String time;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * name : 琥珀新天地
         * images : ["https://images.tuzixs.com/syfarm/upload/a82f41a8-2324-4a5e-b84e-0ad5cb52f04c.jpg"]
         * realname : 丁黎
         * phone : 18956030056
         * province_name : 安徽省
         * city_name : 合肥市
         * area_name : 包河区
         * province_id : 340000
         * city_id : 340100
         * area_id : 340111
         * address : 安徽省合肥市包河区东流路868号琥珀新天地西苑8幢商105
         * latitude : 31.817063
         * longitude : 117.264359
         * store : 0
         * selfetch : 1
         * service_type : radius
         * service_radius : 1000
         * service_province_ids : null
         * service_city_ids : null
         * service_area_ids : null
         * openhours : 07:00 - 20:00
         * openweeks : 1,2,3,4,5,6,7
         * status : 1
         * createtime : 1585889126
         * updatetime : 1602651881
         * store_no : TZ0004
         * store_desc : TZ0004：琥珀新天地
         * UserId : 2
         * openweeks_arr : [1,2,3,4,5,6,7]
         * distance_text : 0m
         * image_first : https://images.tuzixs.com/syfarm/upload/a82f41a8-2324-4a5e-b84e-0ad5cb52f04c.jpg
         */

        private int id;
        private String name;
        private String realname;
        private String phone;
        private String province_name;
        private String city_name;
        private String area_name;
        private int province_id;
        private int city_id;
        private int area_id;
        private String address;
        private String latitude;
        private String longitude;
        private String store;
        private String selfetch;
        private String service_type;
        private int service_radius;
        private Object service_province_ids;
        private Object service_city_ids;
        private Object service_area_ids;
        private String openhours;
        private String openweeks;
        private String status;
        private int createtime;
        private int updatetime;
        private String store_no;
        private String store_desc;
        private int UserId;
        private String distance_text;
        private String image_first;
        private List<String> images;
        private List<Integer> openweeks_arr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public String getSelfetch() {
            return selfetch;
        }

        public void setSelfetch(String selfetch) {
            this.selfetch = selfetch;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public int getService_radius() {
            return service_radius;
        }

        public void setService_radius(int service_radius) {
            this.service_radius = service_radius;
        }

        public Object getService_province_ids() {
            return service_province_ids;
        }

        public void setService_province_ids(Object service_province_ids) {
            this.service_province_ids = service_province_ids;
        }

        public Object getService_city_ids() {
            return service_city_ids;
        }

        public void setService_city_ids(Object service_city_ids) {
            this.service_city_ids = service_city_ids;
        }

        public Object getService_area_ids() {
            return service_area_ids;
        }

        public void setService_area_ids(Object service_area_ids) {
            this.service_area_ids = service_area_ids;
        }

        public String getOpenhours() {
            return openhours;
        }

        public void setOpenhours(String openhours) {
            this.openhours = openhours;
        }

        public String getOpenweeks() {
            return openweeks;
        }

        public void setOpenweeks(String openweeks) {
            this.openweeks = openweeks;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public String getStore_no() {
            return store_no;
        }

        public void setStore_no(String store_no) {
            this.store_no = store_no;
        }

        public String getStore_desc() {
            return store_desc;
        }

        public void setStore_desc(String store_desc) {
            this.store_desc = store_desc;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getDistance_text() {
            return distance_text;
        }

        public void setDistance_text(String distance_text) {
            this.distance_text = distance_text;
        }

        public String getImage_first() {
            return image_first;
        }

        public void setImage_first(String image_first) {
            this.image_first = image_first;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<Integer> getOpenweeks_arr() {
            return openweeks_arr;
        }

        public void setOpenweeks_arr(List<Integer> openweeks_arr) {
            this.openweeks_arr = openweeks_arr;
        }
    }
}
