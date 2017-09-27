package com.maoyihan.www.kobe.module.home.bean;

import java.util.List;

/**新闻详情
 * Created by MaoYiHan on 2017/9/27 0027
 */

public class NewsDetailBean {

    /**
     * data : {"news":{"nid":"2207244","lights":"77","replies":"15","title":"加索尔：我在首发更舒服，不过会听从教练组决定","type":"1","origin":"Twitter","img":"https://c2.hoopchina.com.cn/uploads/star/event/images/170927/thumbnail-e3564308a4ffb2b64c16fde6a69be8457b9189ba.png","addtime":"12分钟前","league":"1","summary":"暂无摘要！","origin_url":"https://twitter.com/15720615850djn1/status/912918875746037760","replyurl":"http://m.hupu.com/nba/news/2207244.html?utm_source=1&utm_medium=games&utm_content=nbanews&utm_campaign=games#top_reply","app_url":"","m_url":"","share":{"url":"http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26","wechat_moments":"加索尔：我在首发更舒服，不过会听从教练组决定","wechat":"加索尔：我在首发更舒服，不过会听从教练组决定","qzone":"加索尔：我在首发更舒服，不过会听从教练组决定 http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26","weibo":"加索尔：我在首发更舒服，不过会听从教练组决定 http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26 （分享自 @虎扑App）","qq":"加索尔：我在首发更舒服，不过会听从教练组决定","img":"https://c2.hoopchina.com.cn/uploads/star/event/images/170927/bmiddle-e3564308a4ffb2b64c16fde6a69be8457b9189ba.png","summary":"虎扑的这篇文章真不错，推荐看一下！"},"tags":[{"name":"马刺","url":"kanqiu://nba/nba/team/21"}]},"is_admin":0,"projectId":"1","version":"7.1.8","nopic":"0","ft":0,"client":"864587029303550","night":0,"uid":null,"cid":"6382813","leaguesEn":"nba","puid":"","user_name":"","header":"","is_login":0}
     * status : 0
     */

    private DataBean data;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * news : {"nid":"2207244","lights":"77","replies":"15","title":"加索尔：我在首发更舒服，不过会听从教练组决定","type":"1","origin":"Twitter","img":"https://c2.hoopchina.com.cn/uploads/star/event/images/170927/thumbnail-e3564308a4ffb2b64c16fde6a69be8457b9189ba.png","addtime":"12分钟前","league":"1","summary":"暂无摘要！","origin_url":"https://twitter.com/15720615850djn1/status/912918875746037760","replyurl":"http://m.hupu.com/nba/news/2207244.html?utm_source=1&utm_medium=games&utm_content=nbanews&utm_campaign=games#top_reply","app_url":"","m_url":"","share":{"url":"http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26","wechat_moments":"加索尔：我在首发更舒服，不过会听从教练组决定","wechat":"加索尔：我在首发更舒服，不过会听从教练组决定","qzone":"加索尔：我在首发更舒服，不过会听从教练组决定 http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26","weibo":"加索尔：我在首发更舒服，不过会听从教练组决定 http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26 （分享自 @虎扑App）","qq":"加索尔：我在首发更舒服，不过会听从教练组决定","img":"https://c2.hoopchina.com.cn/uploads/star/event/images/170927/bmiddle-e3564308a4ffb2b64c16fde6a69be8457b9189ba.png","summary":"虎扑的这篇文章真不错，推荐看一下！"},"tags":[{"name":"马刺","url":"kanqiu://nba/nba/team/21"}]}
         * is_admin : 0
         * projectId : 1
         * version : 7.1.8
         * nopic : 0
         * ft : 0
         * client : 864587029303550
         * night : 0
         * uid : null
         * cid : 6382813
         * leaguesEn : nba
         * puid :
         * user_name :
         * header :
         * is_login : 0
         */

        private NewsBean news;
        private int is_admin;
        private String projectId;
        private String version;
        private String nopic;
        private int ft;
        private String client;
        private int night;
        private Object uid;
        private String cid;
        private String leaguesEn;
        private String puid;
        private String user_name;
        private String header;
        private int is_login;

        public NewsBean getNews() {
            return news;
        }

        public void setNews(NewsBean news) {
            this.news = news;
        }

        public int getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(int is_admin) {
            this.is_admin = is_admin;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getNopic() {
            return nopic;
        }

        public void setNopic(String nopic) {
            this.nopic = nopic;
        }

        public int getFt() {
            return ft;
        }

        public void setFt(int ft) {
            this.ft = ft;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public int getNight() {
            return night;
        }

        public void setNight(int night) {
            this.night = night;
        }

        public Object getUid() {
            return uid;
        }

        public void setUid(Object uid) {
            this.uid = uid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getLeaguesEn() {
            return leaguesEn;
        }

        public void setLeaguesEn(String leaguesEn) {
            this.leaguesEn = leaguesEn;
        }

        public String getPuid() {
            return puid;
        }

        public void setPuid(String puid) {
            this.puid = puid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public int getIs_login() {
            return is_login;
        }

        public void setIs_login(int is_login) {
            this.is_login = is_login;
        }

        public static class NewsBean {
            /**
             * nid : 2207244
             * lights : 77
             * replies : 15
             * title : 加索尔：我在首发更舒服，不过会听从教练组决定
             * type : 1
             * origin : Twitter
             * img : https://c2.hoopchina.com.cn/uploads/star/event/images/170927/thumbnail-e3564308a4ffb2b64c16fde6a69be8457b9189ba.png
             * addtime : 12分钟前
             * league : 1
             * summary : 暂无摘要！
             * origin_url : https://twitter.com/15720615850djn1/status/912918875746037760
             * replyurl : http://m.hupu.com/nba/news/2207244.html?utm_source=1&utm_medium=games&utm_content=nbanews&utm_campaign=games#top_reply
             * app_url :
             * m_url :
             * share : {"url":"http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26","wechat_moments":"加索尔：我在首发更舒服，不过会听从教练组决定","wechat":"加索尔：我在首发更舒服，不过会听从教练组决定","qzone":"加索尔：我在首发更舒服，不过会听从教练组决定 http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26","weibo":"加索尔：我在首发更舒服，不过会听从教练组决定 http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26 （分享自 @虎扑App）","qq":"加索尔：我在首发更舒服，不过会听从教练组决定","img":"https://c2.hoopchina.com.cn/uploads/star/event/images/170927/bmiddle-e3564308a4ffb2b64c16fde6a69be8457b9189ba.png","summary":"虎扑的这篇文章真不错，推荐看一下！"}
             * tags : [{"name":"马刺","url":"kanqiu://nba/nba/team/21"}]
             */

            private String nid;
            private String lights;
            private String replies;
            private String title;
            private String type;
            private String origin;
            private String img;
            private String addtime;
            private String league;
            private String summary;
            private String origin_url;
            private String replyurl;
            private String app_url;
            private String m_url;
            private ShareBean share;
            private List<TagsBean> tags;

            public String getNid() {
                return nid;
            }

            public void setNid(String nid) {
                this.nid = nid;
            }

            public String getLights() {
                return lights;
            }

            public void setLights(String lights) {
                this.lights = lights;
            }

            public String getReplies() {
                return replies;
            }

            public void setReplies(String replies) {
                this.replies = replies;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getLeague() {
                return league;
            }

            public void setLeague(String league) {
                this.league = league;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getOrigin_url() {
                return origin_url;
            }

            public void setOrigin_url(String origin_url) {
                this.origin_url = origin_url;
            }

            public String getReplyurl() {
                return replyurl;
            }

            public void setReplyurl(String replyurl) {
                this.replyurl = replyurl;
            }

            public String getApp_url() {
                return app_url;
            }

            public void setApp_url(String app_url) {
                this.app_url = app_url;
            }

            public String getM_url() {
                return m_url;
            }

            public void setM_url(String m_url) {
                this.m_url = m_url;
            }

            public ShareBean getShare() {
                return share;
            }

            public void setShare(ShareBean share) {
                this.share = share;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public static class ShareBean {
                /**
                 * url : http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26
                 * wechat_moments : 加索尔：我在首发更舒服，不过会听从教练组决定
                 * wechat : 加索尔：我在首发更舒服，不过会听从教练组决定
                 * qzone : 加索尔：我在首发更舒服，不过会听从教练组决定 http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26
                 * weibo : 加索尔：我在首发更舒服，不过会听从教练组决定 http://lite.hupu.com/s?u=nba/news/2207244&type=1&entrance=26 （分享自 @虎扑App）
                 * qq : 加索尔：我在首发更舒服，不过会听从教练组决定
                 * img : https://c2.hoopchina.com.cn/uploads/star/event/images/170927/bmiddle-e3564308a4ffb2b64c16fde6a69be8457b9189ba.png
                 * summary : 虎扑的这篇文章真不错，推荐看一下！
                 */

                private String url;
                private String wechat_moments;
                private String wechat;
                private String qzone;
                private String weibo;
                private String qq;
                private String img;
                private String summary;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getWechat_moments() {
                    return wechat_moments;
                }

                public void setWechat_moments(String wechat_moments) {
                    this.wechat_moments = wechat_moments;
                }

                public String getWechat() {
                    return wechat;
                }

                public void setWechat(String wechat) {
                    this.wechat = wechat;
                }

                public String getQzone() {
                    return qzone;
                }

                public void setQzone(String qzone) {
                    this.qzone = qzone;
                }

                public String getWeibo() {
                    return weibo;
                }

                public void setWeibo(String weibo) {
                    this.weibo = weibo;
                }

                public String getQq() {
                    return qq;
                }

                public void setQq(String qq) {
                    this.qq = qq;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getSummary() {
                    return summary;
                }

                public void setSummary(String summary) {
                    this.summary = summary;
                }
            }

            public static class TagsBean {
                /**
                 * name : 马刺
                 * url : kanqiu://nba/nba/team/21
                 */

                private String name;
                private String url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
