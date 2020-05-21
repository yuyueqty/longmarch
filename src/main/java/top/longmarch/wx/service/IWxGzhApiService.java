package top.longmarch.wx.service;

import top.longmarch.wx.entity.GzhAccount;

public interface IWxGzhApiService {

    void tagAnalysis(GzhAccount gzhAccount, String lock);

    void tagRemove(GzhAccount gzhAccount, String lock);
}
