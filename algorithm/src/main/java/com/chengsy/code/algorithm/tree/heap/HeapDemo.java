package com.chengsy.code.algorithm.tree.heap;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomUtils;

/**
 * 堆
 * 1.堆中某个节点的值总是不大于或不小于其父节点的值；
 * 2.堆总是一棵完全二叉树。
 * 堆的定义：n个元素的序列{k1,k2,ki,…,kn}当且仅当满足下关系时，称之为堆。
 * (ki <= k2i,ki <= k2i+1)或者(ki >= k2i,ki >= k2i+1), (i = 1,2,3,4…n/2)
 *
 * @author chengsiyi
 * @date 2021/4/15 16:25
 */
public class HeapDemo<T extends Comparable<? super T>> {

    public static final Integer BIG_HEAP_TYPE = 1;

    public static final Integer SMALL_HEAP_TYPE = 2;

    public int left(int i) {
        return (i << 1) + 1;
    }

    public int right(int i) {
        return (i + 1) << 1;
    }

    public int parent(int i) {
        if (i == 0) {
            return -1;
        }
        return (i - 1) >> 1;
    }

    public void bigHeapify(T[] a, int i, int heapLength) {
        int l = left(i);
        int r = right(i);
        int largest = -1;

        //left < heapLength说明l在数组内，i非叶子结点；
        if (l < heapLength && a[i].compareTo(a[l]) < 0) {
            largest = l;
        } else {
            largest = i;
        }

        //right < heapLength说明r在数组内
        if (r < heapLength && a[i].compareTo(a[r]) < 0) {
            largest = r;
        }

        // 如果i处元素不是最大的，就把i处的元素与最大处元素交换，交换会使元素下降
        if (i != largest) {
            T temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            bigHeapify(a, largest, heapLength);
        }
    }

    public void smallHeapify(T[] a, int i, int heapLength) {
        int l = left(i);
        int r = right(i);
        int smallest = -1;
        /*
         * 下面两个if条件句用来找到三个元素中的最小元素的位置smallest；
         * l < heapLength说明l在数组内，i非叶子结点；
         */
        if (l < heapLength && a[i].compareTo(a[l]) > 0) {
            smallest = l;
        } else {
            smallest = i;
        }
        // r < heapLength说明r在数组内
        if (r < heapLength && a[smallest].compareTo(a[r]) > 0) {
            smallest = r;
        }
        // 如果i处元素不是最小的，就把i处的元素与最小处元素交换，交换会使元素下降
        if (i != smallest) {
            T temp = a[i];
            a[i] = a[smallest];
            a[smallest] = temp;
            // 交换元素后，以a[i]为根的树可能不在满足大根堆性质，于是递归调用该方法
            smallHeapify(a, smallest, heapLength);
        }
    }

    public void buildHeap(T[] a, int heapLength, Integer heapType) {
        // 从后往前看，lengthParent - 1处的元素是第一个有孩子节点的节点
        int lengthParent = parent(heapLength - 1);
        // 最初，parent(length)之后的所有元素都是叶子结点；
        // 因为大于length/2处元素的孩子节点如果存在，那么
        // 它们的数组下标值必定大于length，这与事实不符；
        // 在数组中，孩子元素必定在父亲元素的后面，从后往前

        if (BIG_HEAP_TYPE.equals(heapType)) {
            for (int i = lengthParent; i >= 0; i--) {
                bigHeapify(a, i, heapLength);
            }
        } else {
            for (int i = lengthParent; i >= 0; i--) {
                smallHeapify(a, i, heapLength);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[20];
        for (int i = 0; i < 20; i++) {
            a[i] = RandomUtils.nextInt(20,100);
        }
        System.out.println(JSON.toJSONString(a));
        HeapDemo demo = new HeapDemo();
        demo.buildHeap(a, 20, 2);
        System.out.println(JSON.toJSONString(a));

    }
}
