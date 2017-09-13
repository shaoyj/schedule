package com.syj.schedule.common.domain;

import com.syj.schedule.common.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PageView <T> extends BaseDomainZK {


    private List<T> result = Collections.emptyList();
    private int currentPage = 1;
    private int pageSize = 10;
    private long totalPages;
    private long totalSize;
    private String url;
    private List<String> orderBy;
    private String chartData;

    public PageView() {
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void setRecords(List<T> records) {
        this.result = records;
    }

    public List<T> getResult() {
        return this.result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > 0) {
            this.currentPage = currentPage;
        }

    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
        this.setTotalPages(this.totalSize % (long) this.pageSize == 0L ? this.totalSize / (long) this.pageSize : this.totalSize / (long) this.pageSize + 1L);
    }

    public String getChartData() {
        return this.chartData;
    }

    public void setChartData(String chartData) {
        this.chartData = chartData;
    }

    public List<String> getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(List<String> orderBy) {
        this.orderBy = orderBy;
    }

    public void makeRequestUrl(HttpServletRequest request) {
        this.url = WebUtils.getUrl(request);
    }

    public void makeRequestUrl(HttpServletRequest request, String basePath) {
        this.url = WebUtils.getUrl(request, basePath, true, (Map) null);
    }

    public void makeRequestUrl(HttpServletRequest request, String basePath, Map<String, String> parameter) {
        this.url = WebUtils.getUrl(request, basePath, true, parameter);
    }

    public static <T> PageView<T> newPage(int page, int pageSize) {
        PageView pageView = new PageView();
        pageView.setCurrentPage(page);
        pageView.setPageSize(pageSize);
        return pageView;
    }

}
