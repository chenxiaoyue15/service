package com.hzu.backstageservice.model;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public class Project {

    private Long id;

    private Integer type;  //1-上市公司 2-国企 3-私人公司

    private Integer price;  //价格，报酬

    private String name;    //项目名称

    private String images;  //图片地址

    private Integer recruitment;  //招聘人数

    private String degree;   //学历要求

    private String language;  //语言要求

    private Double rating;   //评级

    private String remarks;  //项目具体描述

    private String properties;  //属性

    private String tags;       //标签

    private Date createTime;   //创建时间

    private Integer cityId;   //城市

    private Integer areaId;   //区域

    private String address;   //详细地址

    private String firstImg;   //首个图片，展示在列表页

    private List<String> imageList = Lists.newArrayList();  //图片列表

    private List<MultipartFile> projectFiles;   //项目文件

    private Long userId;  //发布该项目的用户

    private boolean bookmarked;  //是否收藏

    private Integer state;     //1.上架 2-下架

    private List<Long> ids;    //通过搜索id进行查询

    private String sort = "time_desc";

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
        if (!Strings.isNullOrEmpty(images)) {
            List<String> list = Splitter.on(",").splitToList(images);
            if (!list.isEmpty()) {
                this.firstImg = list.get(0);
                this.imageList = list;
            }
        }
    }

    public Integer getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Integer recruitment) {
        this.recruitment = recruitment;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<MultipartFile> getProjectFiles() {
        return projectFiles;
    }

    public void setProjectFiles(List<MultipartFile> projectFiles) {
        this.projectFiles = projectFiles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", images='" + images + '\'' +
                ", recruitment=" + recruitment +
                ", degree='" + degree + '\'' +
                ", language='" + language + '\'' +
                ", rating=" + rating +
                ", remarks='" + remarks + '\'' +
                ", properties='" + properties + '\'' +
                ", tags='" + tags + '\'' +
                ", createTime=" + createTime +
                ", cityId=" + cityId +
                ", areaId=" + areaId +
                ", address='" + address + '\'' +
                ", firstImg='" + firstImg + '\'' +
                ", imageList=" + imageList +
                ", projectFiles=" + projectFiles +
                ", userId=" + userId +
                ", bookmarked=" + bookmarked +
                ", state=" + state +
                ", ids=" + ids +
                '}';
    }
}
