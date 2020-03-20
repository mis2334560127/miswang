package com.example.adminmanager.pojo;

public class JotterArticleWithBLOBs extends JotterArticle {
    private String articleContentHtml;

    private String articleContentMd;

    public String getArticleContentHtml() {
        return articleContentHtml;
    }

    public void setArticleContentHtml(String articleContentHtml) {
        this.articleContentHtml = articleContentHtml == null ? null : articleContentHtml.trim();
    }

    public String getArticleContentMd() {
        return articleContentMd;
    }

    public void setArticleContentMd(String articleContentMd) {
        this.articleContentMd = articleContentMd == null ? null : articleContentMd.trim();
    }
}