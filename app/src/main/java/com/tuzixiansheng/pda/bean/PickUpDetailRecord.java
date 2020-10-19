package com.tuzixiansheng.pda.bean;

import java.util.List;

public class PickUpDetailRecord {

    /**
     * result : 查询成功
     * code : 0000
     * user_id : 10776
     * todaylist : []
     * yesterdaylist : [{"id":"2917","user_id":"10776","type":"verify","code":"5203148423","order_id":"131027","order_item_id":"36709","usetime":null,"expiretime":"1611264980","oper_type":null,"oper_id":"0","createtime":"1602624980","updatetime":"1602624980","goods_id":"2156","goods_sku_text":"750","goods_title":"精品西红柿","goods_image":"https://images.tuzixs.com/syfarm/upload/7b397968-c28e-4799-8512-f0ea104a414e.jpg","goods_num":1,"verify_num":0,"goods_price":5.82,"discount_fee":null,"actual_num":null,"actual_fee":null,"change_fee":null,"dispatch_status":"1","store_id":"5","transaction_id":"RP202010132024225942432","order_sn":"RO202010132024226185463","pay_type":null,"goods_sku_price_id":"1705","pay_fee":null,"refund_fee":null,"picktime":null,"sku_code":"11309","bar_code":null,"ext":null,"status_name":"待提货","ext_arr":null,"weight":null,"receiving_volume":null,"data_type":null},{"id":"2918","user_id":"10776","type":"verify","code":"4731784531","order_id":"131027","order_item_id":"290531","usetime":null,"expiretime":"1611264980","oper_type":null,"oper_id":"0","createtime":"1602624980","updatetime":"1602624980","goods_id":"1995","goods_sku_text":"1000","goods_title":"土豆","goods_image":"https://images.tuzixs.com/syfarm/upload/1f7d2f69-2b2d-47cd-a841-209e815a8b8a.jpg","goods_num":1,"verify_num":0,"goods_price":2.18,"discount_fee":null,"actual_num":null,"actual_fee":null,"change_fee":null,"dispatch_status":"1","store_id":"5","transaction_id":"RP202010132024225942432","order_sn":"RO202010132024226185463","pay_type":null,"goods_sku_price_id":"1580","pay_fee":null,"refund_fee":null,"picktime":null,"sku_code":"11224","bar_code":null,"ext":null,"status_name":"待提货","ext_arr":null,"weight":null,"receiving_volume":null,"data_type":null},{"id":"2919","user_id":"10776","type":"verify","code":"2074612467","order_id":"131027","order_item_id":"1061793","usetime":null,"expiretime":"1611264980","oper_type":null,"oper_id":"0","createtime":"1602624980","updatetime":"1602624980","goods_id":"17","goods_sku_text":"500","goods_title":"薄皮青椒","goods_image":"https://images.tuzixs.com/syfarm/upload/b1215543-a5fc-4bfd-a1f9-7dfb83f62ebc.jpg","goods_num":1,"verify_num":0,"goods_price":2.59,"discount_fee":null,"actual_num":null,"actual_fee":null,"change_fee":null,"dispatch_status":"1","store_id":"5","transaction_id":"RP202010132024225942432","order_sn":"RO202010132024226185463","pay_type":null,"goods_sku_price_id":"13","pay_fee":null,"refund_fee":null,"picktime":null,"sku_code":"11612","bar_code":null,"ext":null,"status_name":"待提货","ext_arr":null,"weight":null,"receiving_volume":null,"data_type":null},{"id":"2920","user_id":"10776","type":"verify","code":"8288186453","order_id":"131027","order_item_id":"1141499","usetime":null,"expiretime":"1611264980","oper_type":null,"oper_id":"0","createtime":"1602624980","updatetime":"1602624980","goods_id":"2443","goods_sku_text":"500","goods_title":"蒜苔","goods_image":"https://images.tuzixs.com/syfarm/upload/9354d708-bbca-4bbc-ae7e-2ab317286c3f.jpg","goods_num":1,"verify_num":0,"goods_price":2.89,"discount_fee":null,"actual_num":null,"actual_fee":null,"change_fee":null,"dispatch_status":"1","store_id":"5","transaction_id":"RP202010132024225942432","order_sn":"RO202010132024226185463","pay_type":null,"goods_sku_price_id":"1912","pay_fee":null,"refund_fee":null,"picktime":null,"sku_code":"11201","bar_code":null,"ext":null,"status_name":"待提货","ext_arr":null,"weight":null,"receiving_volume":null,"data_type":null}]
     */

    private String result;
    private String code;
    private String user_id;
    private List<TodayListBean> todaylist;
    private List<YesterdaylistBean> yesterdaylist;
    private List<EndListBean> endlist;

    public List<EndListBean> getEndlist() {
        return endlist;
    }

    public void setEndlist(List<EndListBean> endlist) {
        this.endlist = endlist;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<TodayListBean> getTodaylist() {
        return todaylist;
    }

    public void setTodaylist(List<TodayListBean> todaylist) {
        this.todaylist = todaylist;
    }

    public List<YesterdaylistBean> getYesterdaylist() {
        return yesterdaylist;
    }

    public void setYesterdaylist(List<YesterdaylistBean> yesterdaylist) {
        this.yesterdaylist = yesterdaylist;
    }

    public static class EndListBean {
        /**
         * id : 2917
         * user_id : 10776
         * type : verify
         * code : 5203148423
         * order_id : 131027
         * order_item_id : 36709
         * usetime : null
         * expiretime : 1611264980
         * oper_type : null
         * oper_id : 0
         * createtime : 1602624980
         * updatetime : 1602624980
         * goods_id : 2156
         * goods_sku_text : 750
         * goods_title : 精品西红柿
         * goods_image : https://images.tuzixs.com/syfarm/upload/7b397968-c28e-4799-8512-f0ea104a414e.jpg
         * goods_num : 1
         * verify_num : 0
         * goods_price : 5.82
         * discount_fee : null
         * actual_num : null
         * actual_fee : null
         * change_fee : null
         * dispatch_status : 1
         * store_id : 5
         * transaction_id : RP202010132024225942432
         * order_sn : RO202010132024226185463
         * pay_type : null
         * goods_sku_price_id : 1705
         * pay_fee : null
         * refund_fee : null
         * picktime : null
         * sku_code : 11309
         * bar_code : null
         * ext : null
         * status_name : 待提货
         * ext_arr : null
         * weight : null
         * receiving_volume : null
         * data_type : null
         */

        private String id;
        private String user_id;
        private String type;
        private String code;
        private String order_id;
        private String order_item_id;
        private Object usetime;
        private String expiretime;
        private Object oper_type;
        private String oper_id;
        private String createtime;
        private String updatetime;
        private String goods_id;
        private String goods_sku_text;
        private String goods_title;
        private String goods_image;
        private int goods_num;
        private int verify_num;
        private double goods_price;
        private Object discount_fee;
        private Object actual_num;
        private Object actual_fee;
        private Object change_fee;
        private String dispatch_status;
        private String store_id;
        private String transaction_id;
        private String order_sn;
        private Object pay_type;
        private String goods_sku_price_id;
        private Object pay_fee;
        private Object refund_fee;
        private Object picktime;
        private String sku_code;
        private Object bar_code;
        private Object ext;
        private String status_name;
        private Object ext_arr;
        private Object weight;
        private Object receiving_volume;
        private Object data_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_item_id() {
            return order_item_id;
        }

        public void setOrder_item_id(String order_item_id) {
            this.order_item_id = order_item_id;
        }

        public Object getUsetime() {
            return usetime;
        }

        public void setUsetime(Object usetime) {
            this.usetime = usetime;
        }

        public String getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(String expiretime) {
            this.expiretime = expiretime;
        }

        public Object getOper_type() {
            return oper_type;
        }

        public void setOper_type(Object oper_type) {
            this.oper_type = oper_type;
        }

        public String getOper_id() {
            return oper_id;
        }

        public void setOper_id(String oper_id) {
            this.oper_id = oper_id;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_sku_text() {
            return goods_sku_text;
        }

        public void setGoods_sku_text(String goods_sku_text) {
            this.goods_sku_text = goods_sku_text;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public int getVerify_num() {
            return verify_num;
        }

        public void setVerify_num(int verify_num) {
            this.verify_num = verify_num;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public Object getDiscount_fee() {
            return discount_fee;
        }

        public void setDiscount_fee(Object discount_fee) {
            this.discount_fee = discount_fee;
        }

        public Object getActual_num() {
            return actual_num;
        }

        public void setActual_num(Object actual_num) {
            this.actual_num = actual_num;
        }

        public Object getActual_fee() {
            return actual_fee;
        }

        public void setActual_fee(Object actual_fee) {
            this.actual_fee = actual_fee;
        }

        public Object getChange_fee() {
            return change_fee;
        }

        public void setChange_fee(Object change_fee) {
            this.change_fee = change_fee;
        }

        public String getDispatch_status() {
            return dispatch_status;
        }

        public void setDispatch_status(String dispatch_status) {
            this.dispatch_status = dispatch_status;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public Object getPay_type() {
            return pay_type;
        }

        public void setPay_type(Object pay_type) {
            this.pay_type = pay_type;
        }

        public String getGoods_sku_price_id() {
            return goods_sku_price_id;
        }

        public void setGoods_sku_price_id(String goods_sku_price_id) {
            this.goods_sku_price_id = goods_sku_price_id;
        }

        public Object getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(Object pay_fee) {
            this.pay_fee = pay_fee;
        }

        public Object getRefund_fee() {
            return refund_fee;
        }

        public void setRefund_fee(Object refund_fee) {
            this.refund_fee = refund_fee;
        }

        public Object getPicktime() {
            return picktime;
        }

        public void setPicktime(Object picktime) {
            this.picktime = picktime;
        }

        public String getSku_code() {
            return sku_code;
        }

        public void setSku_code(String sku_code) {
            this.sku_code = sku_code;
        }

        public Object getBar_code() {
            return bar_code;
        }

        public void setBar_code(Object bar_code) {
            this.bar_code = bar_code;
        }

        public Object getExt() {
            return ext;
        }

        public void setExt(Object ext) {
            this.ext = ext;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public Object getExt_arr() {
            return ext_arr;
        }

        public void setExt_arr(Object ext_arr) {
            this.ext_arr = ext_arr;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Object getReceiving_volume() {
            return receiving_volume;
        }

        public void setReceiving_volume(Object receiving_volume) {
            this.receiving_volume = receiving_volume;
        }

        public Object getData_type() {
            return data_type;
        }

        public void setData_type(Object data_type) {
            this.data_type = data_type;
        }
    }

    public static class TodayListBean {
        /**
         * id : 2917
         * user_id : 10776
         * type : verify
         * code : 5203148423
         * order_id : 131027
         * order_item_id : 36709
         * usetime : null
         * expiretime : 1611264980
         * oper_type : null
         * oper_id : 0
         * createtime : 1602624980
         * updatetime : 1602624980
         * goods_id : 2156
         * goods_sku_text : 750
         * goods_title : 精品西红柿
         * goods_image : https://images.tuzixs.com/syfarm/upload/7b397968-c28e-4799-8512-f0ea104a414e.jpg
         * goods_num : 1
         * verify_num : 0
         * goods_price : 5.82
         * discount_fee : null
         * actual_num : null
         * actual_fee : null
         * change_fee : null
         * dispatch_status : 1
         * store_id : 5
         * transaction_id : RP202010132024225942432
         * order_sn : RO202010132024226185463
         * pay_type : null
         * goods_sku_price_id : 1705
         * pay_fee : null
         * refund_fee : null
         * picktime : null
         * sku_code : 11309
         * bar_code : null
         * ext : null
         * status_name : 待提货
         * ext_arr : null
         * weight : null
         * receiving_volume : null
         * data_type : null
         */

        private String id;
        private String user_id;
        private String type;
        private String code;
        private String order_id;
        private String order_item_id;
        private Object usetime;
        private String expiretime;
        private Object oper_type;
        private String oper_id;
        private String createtime;
        private String updatetime;
        private String goods_id;
        private String goods_sku_text;
        private String goods_title;
        private String goods_image;
        private int goods_num;
        private int verify_num;
        private double goods_price;
        private Object discount_fee;
        private Object actual_num;
        private Object actual_fee;
        private Object change_fee;
        private String dispatch_status;
        private String store_id;
        private String transaction_id;
        private String order_sn;
        private Object pay_type;
        private String goods_sku_price_id;
        private Object pay_fee;
        private Object refund_fee;
        private Object picktime;
        private String sku_code;
        private Object bar_code;
        private Object ext;
        private String status_name;
        private Object ext_arr;
        private Object weight;
        private Object receiving_volume;
        private Object data_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_item_id() {
            return order_item_id;
        }

        public void setOrder_item_id(String order_item_id) {
            this.order_item_id = order_item_id;
        }

        public Object getUsetime() {
            return usetime;
        }

        public void setUsetime(Object usetime) {
            this.usetime = usetime;
        }

        public String getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(String expiretime) {
            this.expiretime = expiretime;
        }

        public Object getOper_type() {
            return oper_type;
        }

        public void setOper_type(Object oper_type) {
            this.oper_type = oper_type;
        }

        public String getOper_id() {
            return oper_id;
        }

        public void setOper_id(String oper_id) {
            this.oper_id = oper_id;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_sku_text() {
            return goods_sku_text;
        }

        public void setGoods_sku_text(String goods_sku_text) {
            this.goods_sku_text = goods_sku_text;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public int getVerify_num() {
            return verify_num;
        }

        public void setVerify_num(int verify_num) {
            this.verify_num = verify_num;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public Object getDiscount_fee() {
            return discount_fee;
        }

        public void setDiscount_fee(Object discount_fee) {
            this.discount_fee = discount_fee;
        }

        public Object getActual_num() {
            return actual_num;
        }

        public void setActual_num(Object actual_num) {
            this.actual_num = actual_num;
        }

        public Object getActual_fee() {
            return actual_fee;
        }

        public void setActual_fee(Object actual_fee) {
            this.actual_fee = actual_fee;
        }

        public Object getChange_fee() {
            return change_fee;
        }

        public void setChange_fee(Object change_fee) {
            this.change_fee = change_fee;
        }

        public String getDispatch_status() {
            return dispatch_status;
        }

        public void setDispatch_status(String dispatch_status) {
            this.dispatch_status = dispatch_status;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public Object getPay_type() {
            return pay_type;
        }

        public void setPay_type(Object pay_type) {
            this.pay_type = pay_type;
        }

        public String getGoods_sku_price_id() {
            return goods_sku_price_id;
        }

        public void setGoods_sku_price_id(String goods_sku_price_id) {
            this.goods_sku_price_id = goods_sku_price_id;
        }

        public Object getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(Object pay_fee) {
            this.pay_fee = pay_fee;
        }

        public Object getRefund_fee() {
            return refund_fee;
        }

        public void setRefund_fee(Object refund_fee) {
            this.refund_fee = refund_fee;
        }

        public Object getPicktime() {
            return picktime;
        }

        public void setPicktime(Object picktime) {
            this.picktime = picktime;
        }

        public String getSku_code() {
            return sku_code;
        }

        public void setSku_code(String sku_code) {
            this.sku_code = sku_code;
        }

        public Object getBar_code() {
            return bar_code;
        }

        public void setBar_code(Object bar_code) {
            this.bar_code = bar_code;
        }

        public Object getExt() {
            return ext;
        }

        public void setExt(Object ext) {
            this.ext = ext;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public Object getExt_arr() {
            return ext_arr;
        }

        public void setExt_arr(Object ext_arr) {
            this.ext_arr = ext_arr;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Object getReceiving_volume() {
            return receiving_volume;
        }

        public void setReceiving_volume(Object receiving_volume) {
            this.receiving_volume = receiving_volume;
        }

        public Object getData_type() {
            return data_type;
        }

        public void setData_type(Object data_type) {
            this.data_type = data_type;
        }
    }

    public static class YesterdaylistBean {
        /**
         * id : 2917
         * user_id : 10776
         * type : verify
         * code : 5203148423
         * order_id : 131027
         * order_item_id : 36709
         * usetime : null
         * expiretime : 1611264980
         * oper_type : null
         * oper_id : 0
         * createtime : 1602624980
         * updatetime : 1602624980
         * goods_id : 2156
         * goods_sku_text : 750
         * goods_title : 精品西红柿
         * goods_image : https://images.tuzixs.com/syfarm/upload/7b397968-c28e-4799-8512-f0ea104a414e.jpg
         * goods_num : 1
         * verify_num : 0
         * goods_price : 5.82
         * discount_fee : null
         * actual_num : null
         * actual_fee : null
         * change_fee : null
         * dispatch_status : 1
         * store_id : 5
         * transaction_id : RP202010132024225942432
         * order_sn : RO202010132024226185463
         * pay_type : null
         * goods_sku_price_id : 1705
         * pay_fee : null
         * refund_fee : null
         * picktime : null
         * sku_code : 11309
         * bar_code : null
         * ext : null
         * status_name : 待提货
         * ext_arr : null
         * weight : null
         * receiving_volume : null
         * data_type : null
         */

        private String id;
        private String user_id;
        private String type;
        private String code;
        private String order_id;
        private String order_item_id;
        private Object usetime;
        private String expiretime;
        private Object oper_type;
        private String oper_id;
        private String createtime;
        private String updatetime;
        private String goods_id;
        private String goods_sku_text;
        private String goods_title;
        private String goods_image;
        private int goods_num;
        private int verify_num;
        private double goods_price;
        private Object discount_fee;
        private Object actual_num;
        private Object actual_fee;
        private Object change_fee;
        private String dispatch_status;
        private String store_id;
        private String transaction_id;
        private String order_sn;
        private Object pay_type;
        private String goods_sku_price_id;
        private Object pay_fee;
        private Object refund_fee;
        private Object picktime;
        private String sku_code;
        private Object bar_code;
        private Object ext;
        private String status_name;
        private Object ext_arr;
        private Object weight;
        private Object receiving_volume;
        private Object data_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_item_id() {
            return order_item_id;
        }

        public void setOrder_item_id(String order_item_id) {
            this.order_item_id = order_item_id;
        }

        public Object getUsetime() {
            return usetime;
        }

        public void setUsetime(Object usetime) {
            this.usetime = usetime;
        }

        public String getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(String expiretime) {
            this.expiretime = expiretime;
        }

        public Object getOper_type() {
            return oper_type;
        }

        public void setOper_type(Object oper_type) {
            this.oper_type = oper_type;
        }

        public String getOper_id() {
            return oper_id;
        }

        public void setOper_id(String oper_id) {
            this.oper_id = oper_id;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_sku_text() {
            return goods_sku_text;
        }

        public void setGoods_sku_text(String goods_sku_text) {
            this.goods_sku_text = goods_sku_text;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public int getVerify_num() {
            return verify_num;
        }

        public void setVerify_num(int verify_num) {
            this.verify_num = verify_num;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public Object getDiscount_fee() {
            return discount_fee;
        }

        public void setDiscount_fee(Object discount_fee) {
            this.discount_fee = discount_fee;
        }

        public Object getActual_num() {
            return actual_num;
        }

        public void setActual_num(Object actual_num) {
            this.actual_num = actual_num;
        }

        public Object getActual_fee() {
            return actual_fee;
        }

        public void setActual_fee(Object actual_fee) {
            this.actual_fee = actual_fee;
        }

        public Object getChange_fee() {
            return change_fee;
        }

        public void setChange_fee(Object change_fee) {
            this.change_fee = change_fee;
        }

        public String getDispatch_status() {
            return dispatch_status;
        }

        public void setDispatch_status(String dispatch_status) {
            this.dispatch_status = dispatch_status;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public Object getPay_type() {
            return pay_type;
        }

        public void setPay_type(Object pay_type) {
            this.pay_type = pay_type;
        }

        public String getGoods_sku_price_id() {
            return goods_sku_price_id;
        }

        public void setGoods_sku_price_id(String goods_sku_price_id) {
            this.goods_sku_price_id = goods_sku_price_id;
        }

        public Object getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(Object pay_fee) {
            this.pay_fee = pay_fee;
        }

        public Object getRefund_fee() {
            return refund_fee;
        }

        public void setRefund_fee(Object refund_fee) {
            this.refund_fee = refund_fee;
        }

        public Object getPicktime() {
            return picktime;
        }

        public void setPicktime(Object picktime) {
            this.picktime = picktime;
        }

        public String getSku_code() {
            return sku_code;
        }

        public void setSku_code(String sku_code) {
            this.sku_code = sku_code;
        }

        public Object getBar_code() {
            return bar_code;
        }

        public void setBar_code(Object bar_code) {
            this.bar_code = bar_code;
        }

        public Object getExt() {
            return ext;
        }

        public void setExt(Object ext) {
            this.ext = ext;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public Object getExt_arr() {
            return ext_arr;
        }

        public void setExt_arr(Object ext_arr) {
            this.ext_arr = ext_arr;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Object getReceiving_volume() {
            return receiving_volume;
        }

        public void setReceiving_volume(Object receiving_volume) {
            this.receiving_volume = receiving_volume;
        }

        public Object getData_type() {
            return data_type;
        }

        public void setData_type(Object data_type) {
            this.data_type = data_type;
        }
    }
}
