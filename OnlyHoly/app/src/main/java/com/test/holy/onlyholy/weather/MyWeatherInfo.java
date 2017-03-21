package com.test.holy.onlyholy.weather;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by houlin.jiang on 2017/3/8.
 */

public class MyWeatherInfo implements Serializable{
    private static final long serialVersionUID = 1L;


    public Address address = new Address();
    public Weather weather = new Weather();

    public static class Address implements Serializable{
        private static final long serialVersionUID = 1L;

        /**
         * location : {"lng":116.36255218154,"lat":40.051756144893}
         * formatted_address : 北京市海淀区后屯路
         * business : 西三旗,清河,小营
         * addressComponent : {"country":"中国","country_code":0,"province":"北京市","city":"北京市","district":"海淀区","adcode":"110108","street":"后屯路","street_number":"","direction":"","distance":""}
         * pois : [{"addr":"北京市海淀区小营东路15号","cp":" ","direction":"西北","distance":"261","name":"中关村东升科技园","poiType":"公司企业","point":{"x":116.36373794481,"y":40.050195900315},"tag":"公司企业;园区","tel":"","uid":"271f0b1ac2acc92c4151c0be","zip":""},{"addr":"西小口路66号中关村东升科技园C-6号","cp":" ","direction":"西北","distance":"259","name":"北京东升凯莱酒店","poiType":"酒店","point":{"x":116.3637199787,"y":40.050202804131},"tag":"酒店","tel":"","uid":"57861ea1ad40ff5715c90528","zip":""},{"addr":"后屯路（地铁8号线西小口站D口北侧20米）","cp":" ","direction":"东","distance":"289","name":"世华龙樾","poiType":"房地产","point":{"x":116.36000997694,"y":40.052191073912},"tag":"房地产;住宅区","tel":"","uid":"92eeef7a83d83e24d8131821","zip":""},{"addr":"海淀区八达岭高速小营桥东800米/林萃路向北到头左转权金城路口向北200米","cp":" ","direction":"东北","distance":"351","name":"专家国际花园","poiType":"房地产","point":{"x":116.36118675716,"y":40.049567650075},"tag":"房地产;住宅区","tel":"","uid":"3be122e19cf9ee2f1ef74d9d","zip":""},{"addr":"北京市海淀区西小口路","cp":" ","direction":"北","distance":"336","name":"北领地b-2","poiType":"房地产","point":{"x":116.36322591067,"y":40.049491707343},"tag":"房地产;写字楼","tel":"","uid":"fad8d128ac1a21324107c1c6","zip":""},{"addr":"北京市海淀区西小口路","cp":" ","direction":"东","distance":"394","name":"文龙家园","poiType":"房地产","point":{"x":116.35910268838,"y":40.0523843748},"tag":"房地产;住宅区","tel":"","uid":"ba62cf9d2a512f88a8f72985","zip":""},{"addr":"北京市海淀区西小口路66西门","cp":" ","direction":"北","distance":"496","name":"中关村东升科技园北领地","poiType":"公司企业","point":{"x":116.36373794481,"y":40.048449212128},"tag":"公司企业;园区","tel":"","uid":"15e1e85e068414df58109339","zip":""},{"addr":"北京市海淀区X026(西小口路)","cp":" ","direction":"东","distance":"601","name":"十一学校龙樾实验中学","poiType":"教育培训","point":{"x":116.35718929764,"y":40.052267013613},"tag":"教育培训;中学","tel":"","uid":"56f8a26eb6d05b5417b54b35","zip":""},{"addr":"北京市海淀区","cp":" ","direction":"东","distance":"983","name":"北京市公安局海淀分局西三旗派出所","poiType":"政府机构","point":{"x":116.35409912669,"y":40.053723658634},"tag":"政府机构;公检法机构","tel":"","uid":"7806c7864ea841dcf4e73bf6","zip":""}]
         * poiRegions : []
         * sematic_description : 中关村东升科技园西北261米
         * cityCode : 131
         */

        public LocationBean location = new LocationBean();
        public String formatted_address = "";
        public String business = "";
        public AddressComponentBean addressComponent = new AddressComponentBean();
        public String sematic_description = "";
        public int cityCode ;
        public List<PoisBean> pois = new ArrayList<PoisBean>();

        public static class LocationBean implements Serializable{
            private static final long serialVersionUID = 1L;
            /**
             * lng : 116.36255218154
             * lat : 40.051756144893
             */

            public double lng;
            public double lat;
        }

        public static class AddressComponentBean implements Serializable{
            private static final long serialVersionUID = 1L;
            /**
             * country : 中国
             * country_code : 0
             * province : 北京市
             * city : 北京市
             * district : 海淀区
             * adcode : 110108
             * street : 后屯路
             * street_number :
             * direction :
             * distance :
             */

            public String country = "";
            public int country_code;
            public String province = "";
            public String city;
            public String district = "";
            public String adcode = "";
            public String street = "";
            public String street_number = "";
            public String direction = "";
            public String distance = "";

        }

        public static class PoisBean implements Serializable{
            private static final long serialVersionUID = 1L;
            /**
             * addr : 北京市海淀区小营东路15号
             * cp :
             * direction : 西北
             * distance : 261
             * name : 中关村东升科技园
             * poiType : 公司企业
             * point : {"x":116.36373794481,"y":40.050195900315}
             * tag : 公司企业;园区
             * tel :
             * uid : 271f0b1ac2acc92c4151c0be
             * zip :
             */

            public String addr ="";
            public String cp = "";
            public String direction ="";
            public String distance ="";
            public String name = "";
            public String poiType = "";
            public PointBean point = new PointBean();
            public String tag = "";
            public String tel = "";
            public String uid = "";
            public String zip ="";

            public static class PointBean implements Serializable{
                private static final long serialVersionUID = 1L;

                /**
                 * x : 116.36373794481
                 * y : 40.050195900315
                 */

                public double x;
                public double y;
            }
        }
    }

    public static class Weather implements Serializable{
        private static final long serialVersionUID = 1L;

        /**
         * errNo : 0
         * data : {"weather":{"setting":{"city":"萧山"},"content":{"week":"周三 03月08日 ","city":"萧山","today":{"time":"周三 03月08日 (实时：15℃)","date":"03月08日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png","http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png"],"condition":"晴","wind":"东北风微风","temp":"15℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a0","1":"a0"},"pm25":"92","pollution":"10","pm25url":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pmdate":"2017-03-08"},"tomorrow":{"time":"周四","date":"03月09日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/5.png",""],"condition":"多云","wind":"南风微风","temp":"18 ~ 8℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a1","1":"a1"},"pm25":"92","pollution":"10","pm25url":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pmdate":"2017-03-08"},"thirdday":{"time":"周五","date":"03月10日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/3.png",""],"condition":"阴转小雨","wind":"东风微风","temp":"17 ~ 10℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a2","1":"a2"},"pm25":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pollution":"10","pm25url":"","pmdate":"2017-03-08"},"fourthday":{"time":"周六","date":"03月11日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/8.png",""],"condition":"小雨","wind":"东南风3-4级","temp":"15 ~ 11℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a7","1":"a7"}},"fifthday":{"time":"周日","date":"03月12日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/8.png",""],"condition":"小雨","wind":"北风微风","temp":"14 ~ 9℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a7","1":"a7"}},"linkseven":"http://www.weather.com.cn/weather/101210102.shtml#7d","source":{"name":"中国气象频道","url":"http://www.mywtv.cn/"},"cityname":"萧山","calendar":{"time":"1488957957869","lunar":"二月十一","festival":"妇女节","weatherSourceUrl":"http://www.weather.com.cn/weather/101210102.shtml#7d"},"currenttemp":"15℃","pslink":"//www.baidu.com/s?tn=baidutop10&rsv_idx=2&wd=%E8%90%A7%E5%B1%B1%E5%A4%A9%E6%B0%94%E9%A2%84%E6%8A%A5","weatherType":"aladdin","isauto":false,"ipcity":"北京","province":"北京"}}}
         */

        public String errNo = "";
        public DataBean data = new DataBean();

        public static class DataBean implements Serializable{
            private static final long serialVersionUID = 1L;
            /**
             * weather : {"setting":{"city":"萧山"},"content":{"week":"周三 03月08日 ","city":"萧山","today":{"time":"周三 03月08日 (实时：15℃)","date":"03月08日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png","http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png"],"condition":"晴","wind":"东北风微风","temp":"15℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a0","1":"a0"},"pm25":"92","pollution":"10","pm25url":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pmdate":"2017-03-08"},"tomorrow":{"time":"周四","date":"03月09日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/5.png",""],"condition":"多云","wind":"南风微风","temp":"18 ~ 8℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a1","1":"a1"},"pm25":"92","pollution":"10","pm25url":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pmdate":"2017-03-08"},"thirdday":{"time":"周五","date":"03月10日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/3.png",""],"condition":"阴转小雨","wind":"东风微风","temp":"17 ~ 10℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a2","1":"a2"},"pm25":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pollution":"10","pm25url":"","pmdate":"2017-03-08"},"fourthday":{"time":"周六","date":"03月11日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/8.png",""],"condition":"小雨","wind":"东南风3-4级","temp":"15 ~ 11℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a7","1":"a7"}},"fifthday":{"time":"周日","date":"03月12日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/8.png",""],"condition":"小雨","wind":"北风微风","temp":"14 ~ 9℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a7","1":"a7"}},"linkseven":"http://www.weather.com.cn/weather/101210102.shtml#7d","source":{"name":"中国气象频道","url":"http://www.mywtv.cn/"},"cityname":"萧山","calendar":{"time":"1488957957869","lunar":"二月十一","festival":"妇女节","weatherSourceUrl":"http://www.weather.com.cn/weather/101210102.shtml#7d"},"currenttemp":"15℃","pslink":"//www.baidu.com/s?tn=baidutop10&rsv_idx=2&wd=%E8%90%A7%E5%B1%B1%E5%A4%A9%E6%B0%94%E9%A2%84%E6%8A%A5","weatherType":"aladdin","isauto":false,"ipcity":"北京","province":"北京"}}
             */

            public WeatherBean weather = new WeatherBean();

            public static class WeatherBean implements Serializable{
                private static final long serialVersionUID = 1L;
                /**
                 * setting : {"city":"萧山"}
                 * content : {"week":"周三 03月08日 ","city":"萧山","today":{"time":"周三 03月08日 (实时：15℃)","date":"03月08日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png","http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png"],"condition":"晴","wind":"东北风微风","temp":"15℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a0","1":"a0"},"pm25":"92","pollution":"10","pm25url":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pmdate":"2017-03-08"},"tomorrow":{"time":"周四","date":"03月09日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/5.png",""],"condition":"多云","wind":"南风微风","temp":"18 ~ 8℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a1","1":"a1"},"pm25":"92","pollution":"10","pm25url":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pmdate":"2017-03-08"},"thirdday":{"time":"周五","date":"03月10日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/3.png",""],"condition":"阴转小雨","wind":"东风微风","temp":"17 ~ 10℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a2","1":"a2"},"pm25":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pollution":"10","pm25url":"","pmdate":"2017-03-08"},"fourthday":{"time":"周六","date":"03月11日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/8.png",""],"condition":"小雨","wind":"东南风3-4级","temp":"15 ~ 11℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a7","1":"a7"}},"fifthday":{"time":"周日","date":"03月12日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/8.png",""],"condition":"小雨","wind":"北风微风","temp":"14 ~ 9℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a7","1":"a7"}},"linkseven":"http://www.weather.com.cn/weather/101210102.shtml#7d","source":{"name":"中国气象频道","url":"http://www.mywtv.cn/"},"cityname":"萧山","calendar":{"time":"1488957957869","lunar":"二月十一","festival":"妇女节","weatherSourceUrl":"http://www.weather.com.cn/weather/101210102.shtml#7d"},"currenttemp":"15℃","pslink":"//www.baidu.com/s?tn=baidutop10&rsv_idx=2&wd=%E8%90%A7%E5%B1%B1%E5%A4%A9%E6%B0%94%E9%A2%84%E6%8A%A5","weatherType":"aladdin","isauto":false,"ipcity":"北京","province":"北京"}
                 */

                public SettingBean setting = new SettingBean();
                public ContentBean content = new ContentBean();

                public static class SettingBean implements Serializable{
                    private static final long serialVersionUID = 1L;
                    /**
                     * city : 萧山
                     */

                    public String city = "";
                }

                public static class ContentBean implements Serializable{
                    private static final long serialVersionUID = 1L;
                    /**
                     * week : 周三 03月08日
                     * city : 萧山
                     * today : {"time":"周三 03月08日 (实时：15℃)","date":"03月08日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png","http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png"],"condition":"晴","wind":"东北风微风","temp":"15℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a0","1":"a0"},"pm25":"92","pollution":"10","pm25url":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pmdate":"2017-03-08"}
                     * tomorrow : {"time":"周四","date":"03月09日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/5.png",""],"condition":"多云","wind":"南风微风","temp":"18 ~ 8℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a1","1":"a1"},"pm25":"92","pollution":"10","pm25url":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pmdate":"2017-03-08"}
                     * thirdday : {"time":"周五","date":"03月10日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/3.png",""],"condition":"阴转小雨","wind":"东风微风","temp":"17 ~ 10℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a2","1":"a2"},"pm25":"//www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2","pollution":"10","pm25url":"","pmdate":"2017-03-08"}
                     * fourthday : {"time":"周六","date":"03月11日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/8.png",""],"condition":"小雨","wind":"东南风3-4级","temp":"15 ~ 11℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a7","1":"a7"}}
                     * fifthday : {"time":"周日","date":"03月12日","img":["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/8.png",""],"condition":"小雨","wind":"北风微风","temp":"14 ~ 9℃","link":"http://www.weather.com.cn/weather/101210102.shtml","imgs":{"0":"a7","1":"a7"}}
                     * linkseven : http://www.weather.com.cn/weather/101210102.shtml#7d
                     * source : {"name":"中国气象频道","url":"http://www.mywtv.cn/"}
                     * cityname : 萧山
                     * calendar : {"time":"1488957957869","lunar":"二月十一","festival":"妇女节","weatherSourceUrl":"http://www.weather.com.cn/weather/101210102.shtml#7d"}
                     * currenttemp : 15℃
                     * pslink : //www.baidu.com/s?tn=baidutop10&rsv_idx=2&wd=%E8%90%A7%E5%B1%B1%E5%A4%A9%E6%B0%94%E9%A2%84%E6%8A%A5
                     * weatherType : aladdin
                     * isauto : false
                     * ipcity : 北京
                     * province : 北京
                     */

                    public String week= "";
                    public String city= "";
                    public TodayBean today = new TodayBean() ;
                    public TomorrowBean tomorrow = new TomorrowBean();
                    public ThirddayBean thirdday = new ThirddayBean();
                    public String linkseven = "";
                    public SourceBean source = new SourceBean();
                    public String cityname ="";
                    public CalendarBean calendar = new CalendarBean();
                    public String currenttemp = "";
                    public String pslink = "";
                    public String weatherType = "";
                    public boolean isauto;
                    public String ipcity = "";
                    public String province = "";

                    public static class TodayBean implements Serializable{
                        private static final long serialVersionUID = 1L;
                        /**
                         * time : 周三 03月08日 (实时：15℃)
                         * date : 03月08日
                         * img : ["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png","http://s1.bdstatic.com/r/www/aladdin/img/new_weath/bigicon/1.png"]
                         * condition : 晴
                         * wind : 东北风微风
                         * temp : 15℃
                         * link : http://www.weather.com.cn/weather/101210102.shtml
                         * imgs : {"0":"a0","1":"a0"}
                         * pm25 : 92
                         * pollution : 10
                         * pm25url : //www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2
                         * pmdate : 2017-03-08
                         */

                        public String time = "";
                        public String date = "";
                        public String condition = "";
                        public String wind = "";
                        public String temp = "";
                        public String link = "";
                        public String pm25 = "";
                        public String pollution = "";
                        public String pm25url = "";
                        public String pmdate = "";
                        public List<String> img = new ArrayList<>();
                    }

                    public static class TomorrowBean implements Serializable{
                        private static final long serialVersionUID = 1L;
                        /**
                         * time : 周四
                         * date : 03月09日
                         * img : ["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/5.png",""]
                         * condition : 多云
                         * wind : 南风微风
                         * temp : 18 ~ 8℃
                         * link : http://www.weather.com.cn/weather/101210102.shtml
                         * imgs : {"0":"a1","1":"a1"}
                         * pm25 : 92
                         * pollution : 10
                         * pm25url : //www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2
                         * pmdate : 2017-03-08
                         */

                        public String time  = "";
                        public String date = "";
                        public String condition = "";
                        public String wind = "";
                        public String temp = "";
                        public String link = "";
                        public String pm25 = "";
                        public String pollution = "";
                        public String pm25url = "";
                        public String pmdate = "";
                        public List<String> img = new ArrayList<>();


                    }

                    public static class ThirddayBean implements Serializable{
                        private static final long serialVersionUID = 1L;
                        /**
                         * time : 周五
                         * date : 03月10日
                         * img : ["http://s1.bdstatic.com/r/www/aladdin/img/new_weath/icon/3.png",""]
                         * condition : 阴转小雨
                         * wind : 东风微风
                         * temp : 17 ~ 10℃
                         * link : http://www.weather.com.cn/weather/101210102.shtml
                         * imgs : {"0":"a2","1":"a2"}
                         * pm25 : //www.baidu.com/s?wd=%E8%90%A7%E5%B1%B1%E7%A9%BA%E6%B0%94%E8%B4%A8%E9%87%8F%E6%8C%87%E6%95%B0&tn=baidutop10&rsv_idx=2
                         * pollution : 10
                         * pm25url :
                         * pmdate : 2017-03-08
                         */

                        public String time = "";
                        public String date = "";
                        public String condition = "";
                        public String wind = "";
                        public String temp = "";
                        public String link = "";
                        public String pm25  = "";
                        public String pollution = "";
                        public String pm25url = "";
                        public String pmdate = "";
                        public List<String> img = new ArrayList<>();
                    }

                    public static class SourceBean implements Serializable{
                        private static final long serialVersionUID = 1L;
                        /**
                         * name : 中国气象频道
                         * url : http://www.mywtv.cn/
                         */

                        public String name = "";
                        public String url = "";

                    }
                    public static class CalendarBean implements Serializable{
                        private static final long serialVersionUID = 1L;
                        /**
                         * time : 1488957957869
                         * lunar : 二月十一
                         * festival : 妇女节
                         * weatherSourceUrl : http://www.weather.com.cn/weather/101210102.shtml#7d
                         */

                        public String time = "";
                        public String lunar = "";
                        public String festival = "";
                        public String weatherSourceUrl = "";

                    }
                }
            }
        }
    }
}
