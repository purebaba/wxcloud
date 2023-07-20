package com.tencent.wxcloudrun.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created on 7/20/23 13:54
 *
 * @author hdk
 **/
@Entity
@Setter
@Getter
public class BindUserInfo {

    @Id
    private String id;
    private String userId;
    private String openId;
}
