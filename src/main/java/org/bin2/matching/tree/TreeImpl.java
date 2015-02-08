package org.bin2.matching.tree;

import com.google.common.base.Preconditions;

import java.util.function.Function;

/**
 * Basic binary tree impl
 * inspired from http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
 *
 */
public class TreeImpl<K extends Index<K>,T> {
    //todo implement get by range
    private TreeSpec treeSpec;
    private Function<T, K> toIndex;
    private TreeNode<K,T> root;

    public TreeImpl(TreeSpec treeSpec, Function<T, K> toIndex) {
        this.treeSpec = treeSpec;
        this.toIndex = toIndex;
    }

    public void put(T value) {
        root = put(root, toIndex.apply(value), value);
        root.setColor(TreeNode.BLACK);
    }


    public boolean containsValue(T value) {
        return contains(toIndex.apply(value));
    }

    public T get(K key) {
        return get(root,key);
    }


    private T get(TreeNode<K, T> node, K key) {
        while (node != null) {
            int cmp = key.compareTo(node.getKey());
            if (cmp < 0) {
                node = node.getLeft();
            } else if (cmp > 0) {
                node = node.getRight();
            } else {
                return node.getValue();
            }
        }
        return null;
    }
    private TreeNode<K,T> put(TreeNode<K,T> node,K key, T value) {

        if (node == null) return new TreeNode<>(key, value, TreeNode.RED, 1);
        int comp = key.compareTo(node.getKey());
        if (comp==0) {
            node.setValue(value);
        } else if (comp > 0) {
            node.setRight(put(node.getRight(), key,value));
        } else {
            node.setLeft(put(node.getLeft(),key,value));
        }

        // fix-up any right-leaning links
        if (isRed(node.getRight()) && !isRed(node.getLeft())) node = rotateLeft(node);
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) node = rotateRight(node);
        if (isRed(node.getLeft()) && isRed(node.getRight())) flipColors(node);
        node.setSize(size(node.getLeft()) + size(node.getRight()) + 1);

        return node;
    }

    private int size(TreeNode<K, T> node) {
        return node == null ? 0 : node.size();
    }

    private boolean isRed(TreeNode<K, T> node) {
        if (node == null) return false;
        return node.isRed();
    }

    // is there a key-value pair with the given key?
    public boolean contains(K key) {
        return get(key) != null;
    }

    // return number of key-value pairs in this symbol table
    public int size() {
        return size(root);
    }

    // is this symbol table empty?
    public boolean isEmpty() {
        return root == null;
    }


    // make a left-leaning link lean to the right
    private TreeNode<K, T> rotateRight(TreeNode<K, T> h) {
        // assert (h != null) && isRed(h.left);
        TreeNode<K, T> x = h.getLeft();
        h.setLeft(x.getRight());
        x.setRight(h);
        x.setColor(x.getRight().getColor());
        x.setColor(TreeNode.RED);
        x.setSize(h.size());
        h.setSize(size(h.getLeft()) + size(h.getRight()) + 1);
        return x;
    }

    // make a right-leaning link lean to the left
    private TreeNode<K, T> rotateLeft(TreeNode<K, T> h) {
        // assert (h != null) && isRed(h.right);
        TreeNode<K, T> x = h.getRight();
        h.setRight(x.getLeft());
        x.setLeft(h);
        x.setColor(x.getLeft().getColor());
        x.getLeft().setColor(TreeNode.RED);
        x.setSize(h.size());
        h.setSize(size(h.getLeft()) + size(h.getRight()) + 1);
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(TreeNode<K, T> h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.setColor(!h.getColor());
        h.getLeft().setColor(!h.getLeft().getColor());
        h.getRight().setColor(!h.getRight().getColor());
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private TreeNode<K, T> moveRedLeft(TreeNode<K, T> h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.getRight().getLeft())) {
            h.setRight(rotateRight(h.getRight()));
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private TreeNode<K, T> moveRedRight(TreeNode<K, T> h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.getLeft().getLeft())) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private TreeNode<K, T> balance(TreeNode<K, T> h) {
        // assert (h != null);

        if (isRed(h.getRight())) h = rotateLeft(h);
        if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) h = rotateRight(h);
        if (isRed(h.getLeft()) && isRed(h.getRight())) flipColors(h);

        h.setSize(size(h.getLeft()) + size(h.getRight()) + 1);
        return h;
    }


    // delete the key-value pair with the given key
    public void remove(K key) {
        if (!contains(key)) {
            System.err.println("symbol table does not contain " + key);
            return;
        }

        // if both children of root are black, set root to red
        if (!isRed(root.getLeft()) && !isRed(root.getRight()))
            root.setColor(TreeNode.RED);

        root = delete(root, key);
        if (!isEmpty()) root.setColor(TreeNode.BLACK);
        // assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private TreeNode<K, T> delete(TreeNode<K, T> h, K key) {
        // assert get(h, key) != null;

        if (key.compareTo(h.getKey()) < 0) {
            if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
                h = moveRedLeft(h);
            h.setLeft(delete(h.getLeft(), key));
        } else {
            if (isRed(h.getLeft()))
                h = rotateRight(h);
            if (key.compareTo(h.getKey()) == 0 && (h.getRight() == null))
                return null;
            if (!isRed(h.getRight()) && !isRed(h.getRight().getLeft()))
                h = moveRedRight(h);
            if (key.compareTo(h.getKey()) == 0) {
                TreeNode<K, T> x = min(h.getRight());
                h.setKey(x.getKey());
                h.setValue(x.getValue());
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.setRight(deleteMin(h.getRight()));
            } else h.setRight(delete(h.getRight(), key));
        }
        return balance(h);
    }


    // delete the key-value pair with the minimum key rooted at h
    private TreeNode<K, T> deleteMin(TreeNode<K, T> h) {
        if (h.getLeft() == null)
            return null;

        if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
            h = moveRedLeft(h);

        h.setLeft(deleteMin(h.getLeft()));
        return balance(h);
    }

    // the smallest key in subtree rooted at x; null if no such key
    private TreeNode<K, T> min(TreeNode<K, T> x) {
        // assert x != null;
        if (x.getLeft() == null) return x;
        else return min(x.getLeft());
    }


    @Override
    public String toString() {
        return "TreeImpl{" +
                root +
                '}';
    }

    public static <K extends Index<K>, T> QuadtreeBuilder<K, T> newQuadtreeBuilder() {
        return new QuadtreeBuilder();
    }

    public static class QuadtreeBuilder<K extends Index<K>, T> {
        private double[] max;
        private double[] min;
        private CoordinateTransform<T> coordinateTransform;

        private QuadtreeBuilder() {

        }

        public TreeImpl<K, T> build() {
            Preconditions.checkNotNull(coordinateTransform, "coordinate transform is mandatory  ");
            Preconditions.checkNotNull(max, "should define the limite of the space coordinates by providing max values");
            Preconditions.checkNotNull(min, "should define the limite of the space coordinates by providing min values");
            Preconditions.checkArgument(max.length == min.length, "size of the max and min are differents");
            TreeSpec treeSpec = new TreeSpec(max, min);
            return new TreeImpl(treeSpec, IndexUtils.quadTreeIndex(treeSpec, coordinateTransform));
        }

        public QuadtreeBuilder<K, T> coordinateTransform(CoordinateTransform<T> transform) {
            this.coordinateTransform = transform;
            return this;
        }


        public QuadtreeBuilder<K, T> max(double[] max) {
            this.max = max;
            return this;
        }

        public QuadtreeBuilder<K, T> min(double[] min) {
            this.min = min;
            return this;
        }

    }


    public static <K extends Index<K>, T> CustomIndexBuilder<K, T> newCustomIndexBuilder() {
        return new CustomIndexBuilder();
    }

    public static class CustomIndexBuilder<K extends Index<K>, T> {
        private double[] max;
        private double[] min;
        private Function<T, K> function;

        private CustomIndexBuilder() {

        }

        public TreeImpl<K, T> build() {
            Preconditions.checkNotNull(function, "index function is mandatory  ");
            Preconditions.checkNotNull(max, "should define the limite of the space coordinates by providing max values");
            Preconditions.checkNotNull(min, "should define the limite of the space coordinates by providing min values");
            Preconditions.checkArgument(max.length == min.length, "size of the max and min are differents");
            TreeSpec treeSpec = new TreeSpec(max, min);
            return new TreeImpl(treeSpec, function);
        }

        public CustomIndexBuilder<K, T> indexFunction(Function<T, K> transform) {
            this.function = transform;
            return this;
        }


        public CustomIndexBuilder<K, T> max(double[] max) {
            this.max = max;
            return this;
        }

        public CustomIndexBuilder<K, T> min(double[] min) {
            this.min = min;
            return this;
        }

    }
}
