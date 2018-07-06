package com.ftxad.ftxsdk.bean.response;

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
         * price : 50
         * adStuffOffer : {"relId":106,"sId":3,"sName":"龙之圣痕","pName":"龙之圣痕,2018服新服开启","type":2,"url":"http://directpage.paojiao.com/lzshios_rrys_ws_002","icon":"https://sdk.fantasy.tv/adStuffFile/1/1/152568523567174.jpg","introduction":"开局一条龙，1级到1500级，装备宠物不用换,打装备，卖装备，交易所全线开通，一天净赚1000元,在线连麦，指挥国战，一天15000级","videoPath":"https://sdk.fantasy.tv/adStuffFile/1/1/1527300221357o_1ced2vqci1eg3rvfir0nme164t1a.mp4","imgPath":"https://sdk.fantasy.tv/,[{\"img\":\"adStuffFile/1/1/1527148617580o_1ce8id7or1a7frbmkkn1tj5mnr10.jpg\",\"id\":73},{\"img\":\"adStuffFile/1/1/1525685267525750x422.jpg\",\"id\":20}]","stopPath":"https://sdk.fantasy.tv/,[{\"img\":\"adStuffFile/1/1/1529489316129o_1cgealm831vf11741k661548156m10.png\",\"id\":\"107\"}]"}
         */

        private String adPriceSortId;
        private int orderId;
        private int price;
        private AdStuffOfferBean adStuffOffer;

        public String getAdPriceSortId() {
            return adPriceSortId;
        }

        public void setAdPriceSortId(String adPriceSortId) {
            this.adPriceSortId = adPriceSortId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public AdStuffOfferBean getAdStuffOffer() {
            return adStuffOffer;
        }

        public void setAdStuffOffer(AdStuffOfferBean adStuffOffer) {
            this.adStuffOffer = adStuffOffer;
        }

        public static class AdStuffOfferBean {
            /**
             * relId : 106
             * sId : 3
             * sName : 龙之圣痕
             * pName : 龙之圣痕,2018服新服开启
             * type : 2
             * url : http://directpage.paojiao.com/lzshios_rrys_ws_002
             * icon : https://sdk.fantasy.tv/adStuffFile/1/1/152568523567174.jpg
             * introduction : 开局一条龙，1级到1500级，装备宠物不用换,打装备，卖装备，交易所全线开通，一天净赚1000元,在线连麦，指挥国战，一天15000级
             * videoPath : https://sdk.fantasy.tv/adStuffFile/1/1/1527300221357o_1ced2vqci1eg3rvfir0nme164t1a.mp4
             * imgPath : https://sdk.fantasy.tv/,[{"img":"adStuffFile/1/1/1527148617580o_1ce8id7or1a7frbmkkn1tj5mnr10.jpg","id":73},{"img":"adStuffFile/1/1/1525685267525750x422.jpg","id":20}]
             * stopPath : https://sdk.fantasy.tv/,[{"img":"adStuffFile/1/1/1529489316129o_1cgealm831vf11741k661548156m10.png","id":"107"}]
             */

            private int relId;
            private int sId;
            private String sName;
            private String pName;
            private int type;
            private String url;
            private String icon;
            private String introduction;
            private String videoPath;
            private String imgPath;
            private String stopPath;

            public int getRelId() {
                return relId;
            }

            public void setRelId(int relId) {
                this.relId = relId;
            }

            public int getSId() {
                return sId;
            }

            public void setSId(int sId) {
                this.sId = sId;
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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getVideoPath() {
                return videoPath;
            }

            public void setVideoPath(String videoPath) {
                this.videoPath = videoPath;
            }

            public String getImgPath() {
                return imgPath;
            }

            public void setImgPath(String imgPath) {
                this.imgPath = imgPath;
            }

            public String getStopPath() {
                return stopPath;
            }

            public void setStopPath(String stopPath) {
                this.stopPath = stopPath;
            }
        }
    }
}