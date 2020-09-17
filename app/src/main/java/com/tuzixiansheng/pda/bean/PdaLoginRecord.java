package com.tuzixiansheng.pda.bean;

import java.util.List;

public class PdaLoginRecord {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"shops":[{"id":"46bbb6a66c3147c7acc141ad684fd43d","name":"王笛测试002","address":null,"linker":null,"phone":null,"code":null,"distance":null}],"token":"eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjIwZDk2YTEyLTY5YTItNDgyZi04MWNlLTBkNzI2MzY5YWVkZiJ9.QJyeEE1KIgU-aBBBGyU6_L6WOHgF5sWzKS7e1HMw-ar8u4p6mfwm_sgY2qoYHFHJtHS7Wl00vdvqNWtmCfWRcQ"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shops : [{"id":"46bbb6a66c3147c7acc141ad684fd43d","name":"王笛测试002","address":null,"linker":null,"phone":null,"code":null,"distance":null}]
         * token : eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjIwZDk2YTEyLTY5YTItNDgyZi04MWNlLTBkNzI2MzY5YWVkZiJ9.QJyeEE1KIgU-aBBBGyU6_L6WOHgF5sWzKS7e1HMw-ar8u4p6mfwm_sgY2qoYHFHJtHS7Wl00vdvqNWtmCfWRcQ
         */

        private String token;
        private List<ShopsBean> shops;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public static class ShopsBean {
            /**
             * id : 46bbb6a66c3147c7acc141ad684fd43d
             * name : 王笛测试002
             * address : null
             * linker : null
             * phone : null
             * code : null
             * distance : null
             */

            private String id;
            private String name;
            private String address;
            private String linker;
            private String phone;
            private String code;
            private String distance;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLinker() {
                return linker;
            }

            public void setLinker(String linker) {
                this.linker = linker;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }
    }
}
