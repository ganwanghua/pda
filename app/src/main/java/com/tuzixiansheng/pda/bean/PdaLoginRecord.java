package com.tuzixiansheng.pda.bean;

public class PdaLoginRecord {

    /**
     * code : 1
     * msg : Logged in successful
     * time : 1602648006
     * data : {"userinfo":{"id":5353,"username":"","nickname":"恒","mobile":"18356025739","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK5clyqaJ3DMRqpb0NMt17ZFWWibRYjicQT5Ghic7l9wicq8czAreUxic2EQibciaOUCicmHzDaQZapefDhZQ/132","score":209,"token":"7a48cc28-6ab4-49e2-a93a-e25f7a6abebb","user_id":5353,"createtime":1602648006,"expiretime":1605240006,"expires_in":2592000}}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userinfo : {"id":5353,"username":"","nickname":"恒","mobile":"18356025739","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK5clyqaJ3DMRqpb0NMt17ZFWWibRYjicQT5Ghic7l9wicq8czAreUxic2EQibciaOUCicmHzDaQZapefDhZQ/132","score":209,"token":"7a48cc28-6ab4-49e2-a93a-e25f7a6abebb","user_id":5353,"createtime":1602648006,"expiretime":1605240006,"expires_in":2592000}
         */

        private UserinfoBean userinfo;
        private int is_store;

        public int getIs_store() {
            return is_store;
        }

        public void setIs_store(int is_store) {
            this.is_store = is_store;
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            /**
             * id : 5353
             * username :
             * nickname : 恒
             * mobile : 18356025739
             * avatar : https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK5clyqaJ3DMRqpb0NMt17ZFWWibRYjicQT5Ghic7l9wicq8czAreUxic2EQibciaOUCicmHzDaQZapefDhZQ/132
             * score : 209
             * token : 7a48cc28-6ab4-49e2-a93a-e25f7a6abebb
             * user_id : 5353
             * createtime : 1602648006
             * expiretime : 1605240006
             * expires_in : 2592000
             */

            private int id;
            private String username;
            private String nickname;
            private String mobile;
            private String avatar;
            private int score;
            private String token;
            private int user_id;
            private int createtime;
            private int expiretime;
            private int expires_in;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(int expiretime) {
                this.expiretime = expiretime;
            }

            public int getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(int expires_in) {
                this.expires_in = expires_in;
            }
        }
    }
}
