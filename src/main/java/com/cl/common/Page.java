package com.cl.common;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页
 * @param <T>
 */
public class Page<T>
        implements Serializable {
    /**
     * 当前页
     */
    private int currentIndex;
    /**
     * 每页条数
     */
    private int pageSize;
    /**
     * 总数量
     */
    private int totalNumber;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 下一页
     */
    private int nextIndex;
    /**
     * 上一页
     */
    private int preIndex;
    private List<T> Items = Collections.emptyList();

    public int getPageSize() {
        return this.pageSize;
    }

    public Page(int totalNumber, int currentIndex, int pageSize, List<T> items) {
        this.totalNumber = totalNumber;
        this.currentIndex = currentIndex;
        this.pageSize = pageSize;
        this.Items = items;
    }

    public Page() {
        this.totalNumber = 0;
        this.currentIndex = 1;
        this.pageSize = 10;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getTotalNumber() {
        return this.totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalPage() {
        int size = this.totalNumber / this.pageSize;
        if (this.totalNumber % this.pageSize != 0) {
            size += 1;
        }
        this.totalPage = size;

        return this.totalPage;
    }

    public int getNextIndex() {
        if (this.currentIndex >= getTotalPage()) {
            this.nextIndex = this.currentIndex;
        } else {
            this.nextIndex = (this.currentIndex + 1);
        }
        return this.nextIndex;
    }

    public int getPreIndex() {
        if (this.currentIndex <= 1) {
            this.preIndex = 0;
        } else {
            this.preIndex = (this.currentIndex - 1);
        }
        return this.preIndex;
    }

    public List<T> getItems() {
        return this.Items;
    }

    public void setItems(List<T> items) {
        this.Items = items;
    }

    /**
     * 偏移量，开始位置
     * @return
     */
    public int getStart() {
        return getPreIndex() * this.pageSize;
    }


}
