package com.example.weshare.databean;

/**
 * Created by Administrator on 2016/9/8.
 */
public class StartADBean {

    /**
     * error_code : 41
     * error_desc : 无数据
     * succeed : 0
     */

    private int error_code;
    private String error_desc;
    private int succeed;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public int getSucceed() {
        return succeed;
    }

    public void setSucceed(int succeed) {
        this.succeed = succeed;
    }
}
