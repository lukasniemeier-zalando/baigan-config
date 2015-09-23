package org.zalando.baigan.etcd.model;

public class KeyResultNode {
    private String action;
    private KeyNode node;

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public KeyNode getNode() {
        return node;
    }

    public void setNode(final KeyNode node) {
        this.node = node;
    }
}
