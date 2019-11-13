package com.lzy.util.vo;

import java.io.Serializable;

/**
 * @Description: 邮件信息
 * @Author: lzy
 * @date: 2019-11-13 10:27
 */
public class MailInfVo implements Serializable {
    private static final long serialVersionUID = -8515518775778874114L;
    /**
     * 发送邮箱
     */
    private String            mFrom;

    /**
     * 目的邮箱
     */
    private String            mTo;

    /**
     * 邮件标题
     */
    private String            mSubject;

    /**
     * 正文
     */
    private String            mText;

    /**
     * 附件地址
     */
    private String            mFilePath;

    /**
     * 文件名
     */
    private String            mFileName;



    public String getFrom() {
        return mFrom;
    }



    public void setFrom(String pFrom) {
        mFrom = pFrom;
    }



    public String getTo() {
        return mTo;
    }



    public void setTo(String pTo) {
        mTo = pTo;
    }



    public String getSubject() {
        return mSubject;
    }



    public void setSubject(String pSubject) {
        mSubject = pSubject;
    }



    public String getText() {
        return mText;
    }



    public void setText(String pText) {
        mText = pText;
    }



    public String getFilePath() {
        return mFilePath;
    }



    public void setFilePath(String pFilePath) {
        mFilePath = pFilePath;
    }



    public String getFileName() {
        return mFileName;
    }



    public void setFileName(String pFileName) {
        mFileName = pFileName;
    }



    @Override
    public String toString() {
        return "MailInfVo{" + "mFrom='" + mFrom + '\'' + ", mTo='" + mTo + '\'' + ", mSubject='" + mSubject + '\''
                + ", mText='" + mText + '\'' + ", mFilePath='" + mFilePath + '\'' + ", mFileName='" + mFileName + '\''
                + '}';
    }
}
