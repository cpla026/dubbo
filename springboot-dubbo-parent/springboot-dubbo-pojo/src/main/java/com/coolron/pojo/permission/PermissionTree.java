package com.coolron.pojo.permission;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @Auther: xf
 * @Date: 2018/11/24 22:13
 * @Description:  权限树
 */
@Data
public class PermissionTree extends Permission{

    private static final long serialVersionUID = -2126764101730482230L;
    private Boolean checked = false;  // 回显是否被选中
    private List<PermissionTree> children = Lists.newArrayList();  // 子集

}