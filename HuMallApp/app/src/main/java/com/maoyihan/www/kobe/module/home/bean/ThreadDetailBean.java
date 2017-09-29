package com.maoyihan.www.kobe.module.home.bean;

import java.util.List;

/**
 * Created by MaoYiHan on 2017/9/29 0029
 */

public class ThreadDetailBean {

    /**
     * data : {"is_login":0,"tid":"20341416","pid":"","page":"1","nopic":"0","postAuthorPuid":0,"isrec":0,"time":"43分钟前","userImg":"http://i2.hoopchina.com.cn/user/header/20170415/149226231169335_small_3.jpg@60Q","author_puid":"17418724","username":"尚舞街区","fid":"3441","forum":{"fid":"3441","name":"英雄联盟","logo":"http://hupu-i1i5.img-cn-hangzhou.aliyuncs.com//blogfile/201707/31/BbsImg150148286586887_110x90.png","description":"虎扑英雄联盟区，做最好的英雄联盟讨论区！","unlights_enable":0},"visits":"19550","recommend_num":5,"via":"0","update_info":"","content":"我的妈呀！！！！<div><br /><\/div><div><center><img class=\"article-deposit-pic lazy\" data-w=\"400\" data-h=\"221.03703703704\" data_url= \"http://wx2.sinaimg.cn/mw690/4c82bd55gy1fk0iw5983fj20ir0adjsb.jpg\" src=\"https://bbsstaticoss.hoopchina.com.cn/aHR0cDovL3d4Mi5zaW5haW1nLmNuL213NjkwLzRjODJiZDU1Z3kxZmswaXc1OTgzZmoyMGlyMGFkanNiLmpwZw?x-oss-process=image/resize,w_400/format,webp/quality,Q_60\" /><\/center><br /><\/div>","title":"第二次抢龙！天选之子康帝","merge_title":"","praise":{"status":200,"tips":"注：您赞赏的虎扑币将全部折合成稿费发给作者，虎扑官方不收取任何费用。","list":[],"userinfo":{"header":"http://i2.hoopchina.com.cn/user/header/20170415/149226231169335_small_3.jpg@60Q","nickname":"尚舞街区","uid":"17418724"}},"show_post_praise":1,"is_recommend_filter":0,"totalPage":15,"replies":"298","check_reply_url":"https://bbs.mobileapi.hupu.com/1/7.1.8/threads/getCheckReplyH5","hits":39141}
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
         * is_login : 0
         * tid : 20341416
         * pid :
         * page : 1
         * nopic : 0
         * postAuthorPuid : 0
         * isrec : 0
         * time : 43分钟前
         * userImg : http://i2.hoopchina.com.cn/user/header/20170415/149226231169335_small_3.jpg@60Q
         * author_puid : 17418724
         * username : 尚舞街区
         * fid : 3441
         * forum : {"fid":"3441","name":"英雄联盟","logo":"http://hupu-i1i5.img-cn-hangzhou.aliyuncs.com//blogfile/201707/31/BbsImg150148286586887_110x90.png","description":"虎扑英雄联盟区，做最好的英雄联盟讨论区！","unlights_enable":0}
         * visits : 19550
         * recommend_num : 5
         * via : 0
         * update_info :
         * content : 我的妈呀！！！！<div><br /></div><div><center><img class="article-deposit-pic lazy" data-w="400" data-h="221.03703703704" data_url= "http://wx2.sinaimg.cn/mw690/4c82bd55gy1fk0iw5983fj20ir0adjsb.jpg" src="https://bbsstaticoss.hoopchina.com.cn/aHR0cDovL3d4Mi5zaW5haW1nLmNuL213NjkwLzRjODJiZDU1Z3kxZmswaXc1OTgzZmoyMGlyMGFkanNiLmpwZw?x-oss-process=image/resize,w_400/format,webp/quality,Q_60" /></center><br /></div>
         * title : 第二次抢龙！天选之子康帝
         * merge_title :
         * praise : {"status":200,"tips":"注：您赞赏的虎扑币将全部折合成稿费发给作者，虎扑官方不收取任何费用。","list":[],"userinfo":{"header":"http://i2.hoopchina.com.cn/user/header/20170415/149226231169335_small_3.jpg@60Q","nickname":"尚舞街区","uid":"17418724"}}
         * show_post_praise : 1
         * is_recommend_filter : 0
         * totalPage : 15
         * replies : 298
         * check_reply_url : https://bbs.mobileapi.hupu.com/1/7.1.8/threads/getCheckReplyH5
         * hits : 39141
         */

        private int is_login;
        private String tid;
        private String pid;
        private String page;
        private String nopic;
        private int postAuthorPuid;
        private int isrec;
        private String time;
        private String userImg;
        private String author_puid;
        private String username;
        private String fid;
        private ForumBean forum;
        private String visits;
        private int recommend_num;
        private String via;
        private String update_info;
        private String content;
        private String title;
        private String merge_title;
        private PraiseBean praise;
        private int show_post_praise;
        private int is_recommend_filter;
        private int totalPage;
        private String replies;
        private String check_reply_url;
        private int hits;

        public int getIs_login() {
            return is_login;
        }

        public void setIs_login(int is_login) {
            this.is_login = is_login;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getNopic() {
            return nopic;
        }

        public void setNopic(String nopic) {
            this.nopic = nopic;
        }

        public int getPostAuthorPuid() {
            return postAuthorPuid;
        }

        public void setPostAuthorPuid(int postAuthorPuid) {
            this.postAuthorPuid = postAuthorPuid;
        }

        public int getIsrec() {
            return isrec;
        }

        public void setIsrec(int isrec) {
            this.isrec = isrec;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getAuthor_puid() {
            return author_puid;
        }

        public void setAuthor_puid(String author_puid) {
            this.author_puid = author_puid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public ForumBean getForum() {
            return forum;
        }

        public void setForum(ForumBean forum) {
            this.forum = forum;
        }

        public String getVisits() {
            return visits;
        }

        public void setVisits(String visits) {
            this.visits = visits;
        }

        public int getRecommend_num() {
            return recommend_num;
        }

        public void setRecommend_num(int recommend_num) {
            this.recommend_num = recommend_num;
        }

        public String getVia() {
            return via;
        }

        public void setVia(String via) {
            this.via = via;
        }

        public String getUpdate_info() {
            return update_info;
        }

        public void setUpdate_info(String update_info) {
            this.update_info = update_info;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMerge_title() {
            return merge_title;
        }

        public void setMerge_title(String merge_title) {
            this.merge_title = merge_title;
        }

        public PraiseBean getPraise() {
            return praise;
        }

        public void setPraise(PraiseBean praise) {
            this.praise = praise;
        }

        public int getShow_post_praise() {
            return show_post_praise;
        }

        public void setShow_post_praise(int show_post_praise) {
            this.show_post_praise = show_post_praise;
        }

        public int getIs_recommend_filter() {
            return is_recommend_filter;
        }

        public void setIs_recommend_filter(int is_recommend_filter) {
            this.is_recommend_filter = is_recommend_filter;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public String getReplies() {
            return replies;
        }

        public void setReplies(String replies) {
            this.replies = replies;
        }

        public String getCheck_reply_url() {
            return check_reply_url;
        }

        public void setCheck_reply_url(String check_reply_url) {
            this.check_reply_url = check_reply_url;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public static class ForumBean {
            /**
             * fid : 3441
             * name : 英雄联盟
             * logo : http://hupu-i1i5.img-cn-hangzhou.aliyuncs.com//blogfile/201707/31/BbsImg150148286586887_110x90.png
             * description : 虎扑英雄联盟区，做最好的英雄联盟讨论区！
             * unlights_enable : 0
             */

            private String fid;
            private String name;
            private String logo;
            private String description;
            private int unlights_enable;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getUnlights_enable() {
                return unlights_enable;
            }

            public void setUnlights_enable(int unlights_enable) {
                this.unlights_enable = unlights_enable;
            }
        }

        public static class PraiseBean {
            /**
             * status : 200
             * tips : 注：您赞赏的虎扑币将全部折合成稿费发给作者，虎扑官方不收取任何费用。
             * list : []
             * userinfo : {"header":"http://i2.hoopchina.com.cn/user/header/20170415/149226231169335_small_3.jpg@60Q","nickname":"尚舞街区","uid":"17418724"}
             */

            private int status;
            private String tips;
            private UserinfoBean userinfo;
            private List<?> list;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public UserinfoBean getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserinfoBean userinfo) {
                this.userinfo = userinfo;
            }

            public List<?> getList() {
                return list;
            }

            public void setList(List<?> list) {
                this.list = list;
            }

            public static class UserinfoBean {
                /**
                 * header : http://i2.hoopchina.com.cn/user/header/20170415/149226231169335_small_3.jpg@60Q
                 * nickname : 尚舞街区
                 * uid : 17418724
                 */

                private String header;
                private String nickname;
                private String uid;

                public String getHeader() {
                    return header;
                }

                public void setHeader(String header) {
                    this.header = header;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }
            }
        }
    }
}
