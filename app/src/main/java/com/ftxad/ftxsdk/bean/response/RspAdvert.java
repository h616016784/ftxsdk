package com.ftxad.ftxsdk.bean.response;

import java.util.List;

public class RspAdvert extends BaseResponse {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * adPriceSortId : 1_106
         * orderId : 1
         * price : 35
         * adStuff : {"id":3,"userId":1,"sName":"龙之圣痕","pName":"龙之圣痕,2018服新服开启","url":"http://directpage.paojiao.com/lzshios_rrys_ws_002","createTime":1525685373000,"updateTime":1525685373000}
         * relsMap : {"videoPath":[{"id":106,"sId":3,"type":2,"info":"https://sdk.fantasy.tv/adStuffFile/1/1/1527300221357o_1ced2vqci1eg3rvfir0nme164t1a.mp4"}],"coverStr":[{"id":20,"sId":3,"type":4,"info":"https://sdk.fantasy.tv/adStuffFile/1/1/1525685267525750x422.jpg"},{"id":73,"sId":3,"type":4,"info":"https://sdk.fantasy.tv/adStuffFile/1/1/1527148617580o_1ce8id7or1a7frbmkkn1tj5mnr10.jpg"}],"introduceStr":[{"id":52,"sId":3,"type":11,"info":"开局一条龙，1级到1500级，装备宠物不用换"},{"id":53,"sId":3,"type":11,"info":"打装备，卖装备，交易所全线开通，一天净赚1000元"},{"id":54,"sId":3,"type":11,"info":"在线连麦，指挥国战，一天15000级"}]}
         */

        private String adPriceSortId;
        private String orderId;
        private int price;
        private AdStuffBean adStuff;
        private RelsMapBean relsMap;

        public String getAdPriceSortId() {
            return adPriceSortId;
        }

        public void setAdPriceSortId(String adPriceSortId) {
            this.adPriceSortId = adPriceSortId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public AdStuffBean getAdStuff() {
            return adStuff;
        }

        public void setAdStuff(AdStuffBean adStuff) {
            this.adStuff = adStuff;
        }

        public RelsMapBean getRelsMap() {
            return relsMap;
        }

        public void setRelsMap(RelsMapBean relsMap) {
            this.relsMap = relsMap;
        }

        public static class AdStuffBean {
            /**
             * id : 3
             * userId : 1
             * sName : 龙之圣痕
             * pName : 龙之圣痕,2018服新服开启
             * url : http://directpage.paojiao.com/lzshios_rrys_ws_002
             * createTime : 1525685373000
             * updateTime : 1525685373000
             */

            private String id;
            private String userId;
            private String sName;
            private String pName;
            private String url;
            private long createTime;
            private long updateTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getSName() {
                return sName;
            }

            public void setSName(String sName) {
                this.sName = sName;
            }

            public String getPName() {
                return pName;
            }

            public void setPName(String pName) {
                this.pName = pName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }
        }

        public static class RelsMapBean {
            private List<VideoPathBean> videoPath;
            private List<CoverStrBean> coverStr;
            private List<IntroduceStrBean> introduceStr;

            public List<VideoPathBean> getVideoPath() {
                return videoPath;
            }

            public void setVideoPath(List<VideoPathBean> videoPath) {
                this.videoPath = videoPath;
            }

            public List<CoverStrBean> getCoverStr() {
                return coverStr;
            }

            public void setCoverStr(List<CoverStrBean> coverStr) {
                this.coverStr = coverStr;
            }

            public List<IntroduceStrBean> getIntroduceStr() {
                return introduceStr;
            }

            public void setIntroduceStr(List<IntroduceStrBean> introduceStr) {
                this.introduceStr = introduceStr;
            }

            public static class VideoPathBean {
                /**
                 * id : 106
                 * sId : 3
                 * type : 2
                 * info : https://sdk.fantasy.tv/adStuffFile/1/1/1527300221357o_1ced2vqci1eg3rvfir0nme164t1a.mp4
                 */

                private int id;
                private int sId;
                private int type;
                private String info;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSId() {
                    return sId;
                }

                public void setSId(int sId) {
                    this.sId = sId;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }

            public static class CoverStrBean {
                /**
                 * id : 20
                 * sId : 3
                 * type : 4
                 * info : https://sdk.fantasy.tv/adStuffFile/1/1/1525685267525750x422.jpg
                 */

                private int id;
                private int sId;
                private int type;
                private String info;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSId() {
                    return sId;
                }

                public void setSId(int sId) {
                    this.sId = sId;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }

            public static class IntroduceStrBean {
                /**
                 * id : 52
                 * sId : 3
                 * type : 11
                 * info : 开局一条龙，1级到1500级，装备宠物不用换
                 */

                private int id;
                private int sId;
                private int type;
                private String info;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSId() {
                    return sId;
                }

                public void setSId(int sId) {
                    this.sId = sId;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }
        }
    }
}