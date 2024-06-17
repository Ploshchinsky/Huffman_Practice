package org.example;

public class BinaryTreeNode implements Comparable<BinaryTreeNode> {
    private Character data;
    private int weight;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(Character data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public BinaryTreeNode(Character data, int weight, BinaryTreeNode left, BinaryTreeNode right) {
        this.data = data;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    public Character getData() {
        return data;
    }

    public void setData(Character data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public int compareTo(BinaryTreeNode o) {
        return o.weight - this.weight;
    }
}
