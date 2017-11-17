package com.freshman.Common;

/**
 * Created by tianxianglan on 2016/11/10.
 */
public class Response {
    private short status;
    private String errmsg;
    private Object object;

    public Response() {
    }

    public Response(short status, String errmsg, Object object) {
        this.status = status;
        this.errmsg = errmsg;
        this.object = object;
    }

    public Response(short status) {
        this.status = status;
    }

    public Response(Object object, short status) {
        this.object = object;
        this.status = status;
    }

    public Response(short status, String errmsg) {
        this.status = status;
        this.errmsg = errmsg;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
