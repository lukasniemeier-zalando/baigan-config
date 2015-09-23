package org.zalando.baigan.etcd.model;

public class DirResultNode {
    private String action;
    private DirNode node;

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public DirNode getNode() {
        return node;
    }

    public void setNode(final DirNode node) {
        this.node = node;
    }
}
