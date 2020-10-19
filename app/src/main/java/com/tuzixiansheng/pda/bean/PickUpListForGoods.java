package com.tuzixiansheng.pda.bean;

import java.util.List;

public class PickUpListForGoods {

    /**
     * msg : 查询成功
     * code : 1
     * data : {"result":{"per_page":10,"total":153,"data":[{"item":[{"goods_original_price":3.7,"goods_price":3.18,"status_name":"11612","dispatch_status":1,"goods_id":17,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/b1215543-a5fc-4bfd-a1f9-7dfb83f62ebc.jpg","goods_title":"薄皮青椒　500g/份","goods_num":5,"id":1247664,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":13},{"goods_original_price":3.66,"goods_price":2.56,"status_name":"11209","dispatch_status":1,"goods_id":25,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/2e6d1dc6-9d31-482b-943a-e393d6b0a446.jpg","goods_title":"红心山芋　1000g/份","goods_num":2,"id":1245484,"goods_type":"normal","goods_sku_text":"1000","goods_sku_price_id":18},{"goods_original_price":3.69,"goods_price":2.58,"status_name":"11101","dispatch_status":1,"goods_id":34,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/3faa8de5-6871-4a5f-b774-66840161afcc.jpg","goods_title":"茼蒿　500g/份","goods_num":5,"id":1245968,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":27},{"goods_original_price":4.56,"goods_price":3.19,"status_name":"11902","dispatch_status":1,"goods_id":36,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/297aae72-2715-4dd7-9555-5b8baf19b698.jpg","goods_title":"千张　320g/份","goods_num":6,"id":1246975,"goods_type":"normal","goods_sku_text":"320","goods_sku_price_id":29},{"goods_original_price":1.41,"goods_price":0.99,"status_name":"11903","dispatch_status":1,"goods_id":37,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/4dcebd16-a81d-4086-be32-ba267c67f829.jpg","goods_title":"内酯豆腐　350g","goods_num":3,"id":1245876,"goods_type":"normal","goods_sku_text":"350g","goods_sku_price_id":30},{"goods_original_price":5.11,"goods_price":3.18,"status_name":"11905","dispatch_status":1,"goods_id":39,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/bfd31519-b1d3-4cd9-a2a8-879700a4a3d0.jpg","goods_title":"白干　500g/份","goods_num":1,"id":1245970,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":31},{"goods_original_price":3.99,"goods_price":2.79,"status_name":"11907","dispatch_status":1,"goods_id":41,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/f3aaf83f-f533-4a37-82f4-cb057281a8af.png","goods_title":"香辣干丝　350g/份","goods_num":2,"id":1246658,"goods_type":"normal","goods_sku_text":"350","goods_sku_price_id":33},{"goods_original_price":3.83,"goods_price":2.68,"status_name":"11908","dispatch_status":1,"goods_id":42,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/b2f1a74c-1c81-4701-bc20-68761efb82f9.jpg","goods_title":"老豆腐　500g","goods_num":2,"id":1245638,"goods_type":"normal","goods_sku_text":"500g/盒","goods_sku_price_id":34},{"goods_original_price":6.26,"goods_price":4.38,"status_name":"11910","dispatch_status":1,"goods_id":43,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/707883e0-5995-4f06-b942-1a986776d45c.jpg","goods_title":"脆皮豆腐　500g/份","goods_num":1,"id":1247299,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":35},{"goods_original_price":5.5,"goods_price":3.85,"status_name":"11915","dispatch_status":1,"goods_id":45,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/8aabf5b1-cf7c-41b0-895b-5cddc0f2632a.jpg","goods_title":"素鸡（散装袋装）　350g/份","goods_num":1,"id":1246028,"goods_type":"normal","goods_sku_text":"350","goods_sku_price_id":37}],"id":"","order_sn":""}],"last_page":15,"current_page":1},"total_num":153,"total_money":1774.58}
     */

    private String msg;
    private String code;
    private DataBeanX data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * result : {"per_page":10,"total":153,"data":[{"item":[{"goods_original_price":3.7,"goods_price":3.18,"status_name":"11612","dispatch_status":1,"goods_id":17,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/b1215543-a5fc-4bfd-a1f9-7dfb83f62ebc.jpg","goods_title":"薄皮青椒　500g/份","goods_num":5,"id":1247664,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":13},{"goods_original_price":3.66,"goods_price":2.56,"status_name":"11209","dispatch_status":1,"goods_id":25,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/2e6d1dc6-9d31-482b-943a-e393d6b0a446.jpg","goods_title":"红心山芋　1000g/份","goods_num":2,"id":1245484,"goods_type":"normal","goods_sku_text":"1000","goods_sku_price_id":18},{"goods_original_price":3.69,"goods_price":2.58,"status_name":"11101","dispatch_status":1,"goods_id":34,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/3faa8de5-6871-4a5f-b774-66840161afcc.jpg","goods_title":"茼蒿　500g/份","goods_num":5,"id":1245968,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":27},{"goods_original_price":4.56,"goods_price":3.19,"status_name":"11902","dispatch_status":1,"goods_id":36,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/297aae72-2715-4dd7-9555-5b8baf19b698.jpg","goods_title":"千张　320g/份","goods_num":6,"id":1246975,"goods_type":"normal","goods_sku_text":"320","goods_sku_price_id":29},{"goods_original_price":1.41,"goods_price":0.99,"status_name":"11903","dispatch_status":1,"goods_id":37,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/4dcebd16-a81d-4086-be32-ba267c67f829.jpg","goods_title":"内酯豆腐　350g","goods_num":3,"id":1245876,"goods_type":"normal","goods_sku_text":"350g","goods_sku_price_id":30},{"goods_original_price":5.11,"goods_price":3.18,"status_name":"11905","dispatch_status":1,"goods_id":39,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/bfd31519-b1d3-4cd9-a2a8-879700a4a3d0.jpg","goods_title":"白干　500g/份","goods_num":1,"id":1245970,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":31},{"goods_original_price":3.99,"goods_price":2.79,"status_name":"11907","dispatch_status":1,"goods_id":41,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/f3aaf83f-f533-4a37-82f4-cb057281a8af.png","goods_title":"香辣干丝　350g/份","goods_num":2,"id":1246658,"goods_type":"normal","goods_sku_text":"350","goods_sku_price_id":33},{"goods_original_price":3.83,"goods_price":2.68,"status_name":"11908","dispatch_status":1,"goods_id":42,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/b2f1a74c-1c81-4701-bc20-68761efb82f9.jpg","goods_title":"老豆腐　500g","goods_num":2,"id":1245638,"goods_type":"normal","goods_sku_text":"500g/盒","goods_sku_price_id":34},{"goods_original_price":6.26,"goods_price":4.38,"status_name":"11910","dispatch_status":1,"goods_id":43,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/707883e0-5995-4f06-b942-1a986776d45c.jpg","goods_title":"脆皮豆腐　500g/份","goods_num":1,"id":1247299,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":35},{"goods_original_price":5.5,"goods_price":3.85,"status_name":"11915","dispatch_status":1,"goods_id":45,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/8aabf5b1-cf7c-41b0-895b-5cddc0f2632a.jpg","goods_title":"素鸡（散装袋装）　350g/份","goods_num":1,"id":1246028,"goods_type":"normal","goods_sku_text":"350","goods_sku_price_id":37}],"id":"","order_sn":""}],"last_page":15,"current_page":1}
         * total_num : 153
         * total_money : 1774.58
         */

        private ResultBean result;
        private int total_num;
        private double total_money;
        private String total_goods;

        public String getTotal_goods() {
            return total_goods;
        }

        public void setTotal_goods(String total_goods) {
            this.total_goods = total_goods;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public int getTotal_num() {
            return total_num;
        }

        public void setTotal_num(int total_num) {
            this.total_num = total_num;
        }

        public double getTotal_money() {
            return total_money;
        }

        public void setTotal_money(double total_money) {
            this.total_money = total_money;
        }

        public static class ResultBean {
            /**
             * per_page : 10
             * total : 153
             * data : [{"item":[{"goods_original_price":3.7,"goods_price":3.18,"status_name":"11612","dispatch_status":1,"goods_id":17,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/b1215543-a5fc-4bfd-a1f9-7dfb83f62ebc.jpg","goods_title":"薄皮青椒　500g/份","goods_num":5,"id":1247664,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":13},{"goods_original_price":3.66,"goods_price":2.56,"status_name":"11209","dispatch_status":1,"goods_id":25,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/2e6d1dc6-9d31-482b-943a-e393d6b0a446.jpg","goods_title":"红心山芋　1000g/份","goods_num":2,"id":1245484,"goods_type":"normal","goods_sku_text":"1000","goods_sku_price_id":18},{"goods_original_price":3.69,"goods_price":2.58,"status_name":"11101","dispatch_status":1,"goods_id":34,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/3faa8de5-6871-4a5f-b774-66840161afcc.jpg","goods_title":"茼蒿　500g/份","goods_num":5,"id":1245968,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":27},{"goods_original_price":4.56,"goods_price":3.19,"status_name":"11902","dispatch_status":1,"goods_id":36,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/297aae72-2715-4dd7-9555-5b8baf19b698.jpg","goods_title":"千张　320g/份","goods_num":6,"id":1246975,"goods_type":"normal","goods_sku_text":"320","goods_sku_price_id":29},{"goods_original_price":1.41,"goods_price":0.99,"status_name":"11903","dispatch_status":1,"goods_id":37,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/4dcebd16-a81d-4086-be32-ba267c67f829.jpg","goods_title":"内酯豆腐　350g","goods_num":3,"id":1245876,"goods_type":"normal","goods_sku_text":"350g","goods_sku_price_id":30},{"goods_original_price":5.11,"goods_price":3.18,"status_name":"11905","dispatch_status":1,"goods_id":39,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/bfd31519-b1d3-4cd9-a2a8-879700a4a3d0.jpg","goods_title":"白干　500g/份","goods_num":1,"id":1245970,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":31},{"goods_original_price":3.99,"goods_price":2.79,"status_name":"11907","dispatch_status":1,"goods_id":41,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/f3aaf83f-f533-4a37-82f4-cb057281a8af.png","goods_title":"香辣干丝　350g/份","goods_num":2,"id":1246658,"goods_type":"normal","goods_sku_text":"350","goods_sku_price_id":33},{"goods_original_price":3.83,"goods_price":2.68,"status_name":"11908","dispatch_status":1,"goods_id":42,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/b2f1a74c-1c81-4701-bc20-68761efb82f9.jpg","goods_title":"老豆腐　500g","goods_num":2,"id":1245638,"goods_type":"normal","goods_sku_text":"500g/盒","goods_sku_price_id":34},{"goods_original_price":6.26,"goods_price":4.38,"status_name":"11910","dispatch_status":1,"goods_id":43,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/707883e0-5995-4f06-b942-1a986776d45c.jpg","goods_title":"脆皮豆腐　500g/份","goods_num":1,"id":1247299,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":35},{"goods_original_price":5.5,"goods_price":3.85,"status_name":"11915","dispatch_status":1,"goods_id":45,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/8aabf5b1-cf7c-41b0-895b-5cddc0f2632a.jpg","goods_title":"素鸡（散装袋装）　350g/份","goods_num":1,"id":1246028,"goods_type":"normal","goods_sku_text":"350","goods_sku_price_id":37}],"id":"","order_sn":""}]
             * last_page : 15
             * current_page : 1
             */

            private int per_page;
            private int total;
            private int last_page;
            private int current_page;
            private List<DataBean> data;

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * item : [{"goods_original_price":3.7,"goods_price":3.18,"status_name":"11612","dispatch_status":1,"goods_id":17,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/b1215543-a5fc-4bfd-a1f9-7dfb83f62ebc.jpg","goods_title":"薄皮青椒　500g/份","goods_num":5,"id":1247664,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":13},{"goods_original_price":3.66,"goods_price":2.56,"status_name":"11209","dispatch_status":1,"goods_id":25,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/2e6d1dc6-9d31-482b-943a-e393d6b0a446.jpg","goods_title":"红心山芋　1000g/份","goods_num":2,"id":1245484,"goods_type":"normal","goods_sku_text":"1000","goods_sku_price_id":18},{"goods_original_price":3.69,"goods_price":2.58,"status_name":"11101","dispatch_status":1,"goods_id":34,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/3faa8de5-6871-4a5f-b774-66840161afcc.jpg","goods_title":"茼蒿　500g/份","goods_num":5,"id":1245968,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":27},{"goods_original_price":4.56,"goods_price":3.19,"status_name":"11902","dispatch_status":1,"goods_id":36,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/297aae72-2715-4dd7-9555-5b8baf19b698.jpg","goods_title":"千张　320g/份","goods_num":6,"id":1246975,"goods_type":"normal","goods_sku_text":"320","goods_sku_price_id":29},{"goods_original_price":1.41,"goods_price":0.99,"status_name":"11903","dispatch_status":1,"goods_id":37,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/4dcebd16-a81d-4086-be32-ba267c67f829.jpg","goods_title":"内酯豆腐　350g","goods_num":3,"id":1245876,"goods_type":"normal","goods_sku_text":"350g","goods_sku_price_id":30},{"goods_original_price":5.11,"goods_price":3.18,"status_name":"11905","dispatch_status":1,"goods_id":39,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/bfd31519-b1d3-4cd9-a2a8-879700a4a3d0.jpg","goods_title":"白干　500g/份","goods_num":1,"id":1245970,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":31},{"goods_original_price":3.99,"goods_price":2.79,"status_name":"11907","dispatch_status":1,"goods_id":41,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/f3aaf83f-f533-4a37-82f4-cb057281a8af.png","goods_title":"香辣干丝　350g/份","goods_num":2,"id":1246658,"goods_type":"normal","goods_sku_text":"350","goods_sku_price_id":33},{"goods_original_price":3.83,"goods_price":2.68,"status_name":"11908","dispatch_status":1,"goods_id":42,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/b2f1a74c-1c81-4701-bc20-68761efb82f9.jpg","goods_title":"老豆腐　500g","goods_num":2,"id":1245638,"goods_type":"normal","goods_sku_text":"500g/盒","goods_sku_price_id":34},{"goods_original_price":6.26,"goods_price":4.38,"status_name":"11910","dispatch_status":1,"goods_id":43,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/707883e0-5995-4f06-b942-1a986776d45c.jpg","goods_title":"脆皮豆腐　500g/份","goods_num":1,"id":1247299,"goods_type":"normal","goods_sku_text":"500","goods_sku_price_id":35},{"goods_original_price":5.5,"goods_price":3.85,"status_name":"11915","dispatch_status":1,"goods_id":45,"discount_fee":0,"goods_image":"https://images.tuzixs.com/syfarm/upload/8aabf5b1-cf7c-41b0-895b-5cddc0f2632a.jpg","goods_title":"素鸡（散装袋装）　350g/份","goods_num":1,"id":1246028,"goods_type":"normal","goods_sku_text":"350","goods_sku_price_id":37}]
                 * id :
                 * order_sn :
                 */

                private String id;
                private String order_sn;
                private List<ItemBean> item;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getOrder_sn() {
                    return order_sn;
                }

                public void setOrder_sn(String order_sn) {
                    this.order_sn = order_sn;
                }

                public List<ItemBean> getItem() {
                    return item;
                }

                public void setItem(List<ItemBean> item) {
                    this.item = item;
                }

                public static class ItemBean {
                    /**
                     * goods_original_price : 3.7
                     * goods_price : 3.18
                     * status_name : 11612
                     * dispatch_status : 1
                     * goods_id : 17
                     * discount_fee : 0
                     * goods_image : https://images.tuzixs.com/syfarm/upload/b1215543-a5fc-4bfd-a1f9-7dfb83f62ebc.jpg
                     * goods_title : 薄皮青椒　500g/份
                     * goods_num : 5
                     * id : 1247664
                     * goods_type : normal
                     * goods_sku_text : 500
                     * goods_sku_price_id : 13
                     */

                    private double goods_original_price;
                    private double goods_price;
                    private String status_name;
                    private int dispatch_status;
                    private int goods_id;
                    private double discount_fee;
                    private String goods_image;
                    private String goods_title;
                    private int goods_num;
                    private int id;
                    private String goods_type;
                    private String goods_sku_text;
                    private int goods_sku_price_id;

                    public double getGoods_original_price() {
                        return goods_original_price;
                    }

                    public void setGoods_original_price(double goods_original_price) {
                        this.goods_original_price = goods_original_price;
                    }

                    public double getGoods_price() {
                        return goods_price;
                    }

                    public void setGoods_price(double goods_price) {
                        this.goods_price = goods_price;
                    }

                    public String getStatus_name() {
                        return status_name;
                    }

                    public void setStatus_name(String status_name) {
                        this.status_name = status_name;
                    }

                    public int getDispatch_status() {
                        return dispatch_status;
                    }

                    public void setDispatch_status(int dispatch_status) {
                        this.dispatch_status = dispatch_status;
                    }

                    public int getGoods_id() {
                        return goods_id;
                    }

                    public void setGoods_id(int goods_id) {
                        this.goods_id = goods_id;
                    }

                    public double getDiscount_fee() {
                        return discount_fee;
                    }

                    public void setDiscount_fee(double discount_fee) {
                        this.discount_fee = discount_fee;
                    }

                    public String getGoods_image() {
                        return goods_image;
                    }

                    public void setGoods_image(String goods_image) {
                        this.goods_image = goods_image;
                    }

                    public String getGoods_title() {
                        return goods_title;
                    }

                    public void setGoods_title(String goods_title) {
                        this.goods_title = goods_title;
                    }

                    public int getGoods_num() {
                        return goods_num;
                    }

                    public void setGoods_num(int goods_num) {
                        this.goods_num = goods_num;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getGoods_type() {
                        return goods_type;
                    }

                    public void setGoods_type(String goods_type) {
                        this.goods_type = goods_type;
                    }

                    public String getGoods_sku_text() {
                        return goods_sku_text;
                    }

                    public void setGoods_sku_text(String goods_sku_text) {
                        this.goods_sku_text = goods_sku_text;
                    }

                    public int getGoods_sku_price_id() {
                        return goods_sku_price_id;
                    }

                    public void setGoods_sku_price_id(int goods_sku_price_id) {
                        this.goods_sku_price_id = goods_sku_price_id;
                    }
                }
            }
        }
    }
}
