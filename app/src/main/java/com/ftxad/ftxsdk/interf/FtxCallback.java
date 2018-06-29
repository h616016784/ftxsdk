package com.ftxad.ftxsdk.interf;

/**
 * Created by hanhuailong on 2018/6/27.
 */

public interface FtxCallback<T> {
    void ftxCallback(T t);
    void ftxFailed(String msg);
}
