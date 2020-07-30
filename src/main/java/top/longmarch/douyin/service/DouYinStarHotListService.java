package top.longmarch.douyin.service;

import com.douyin.open.models.*;

public interface DouYinStarHotListService {

    StarDataStarDataInlineResponse200Data starHotListGet(Integer hotListType);

    StarAuthorStarAuthorInlineResponse200Data starAuthorScoreGet();

    StarAuthorStarAuthorInlineResponse2001Data starAuthorScoreV2Get(String uniqueId);

}
