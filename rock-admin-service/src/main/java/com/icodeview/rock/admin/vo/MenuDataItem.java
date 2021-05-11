package com.icodeview.rock.admin.vo;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Data
public class MenuDataItem {
    private Integer id;
    private String icon;
    private String name;
    private String key;
    private String path;
    private Integer parentId;
    private List<MenuDataItem> children;
    private Boolean hideChildrenInMenu;
    private Boolean hideInMenu;
    private List<String> parentKeys;

}
