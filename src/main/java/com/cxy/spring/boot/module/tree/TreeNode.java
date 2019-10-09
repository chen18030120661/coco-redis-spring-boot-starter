package com.cxy.spring.boot.module.tree;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode {

    private String id;

    private String parentId;

    private String name;

    private List<TreeNode> children;

    public TreeNode(String id, String name, String parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public TreeNode(String id, String name, TreeNode parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
    }
}