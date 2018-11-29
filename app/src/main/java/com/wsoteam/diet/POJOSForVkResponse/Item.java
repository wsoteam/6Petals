
package com.wsoteam.diet.POJOSForVkResponse;

import java.util.List;
import com.squareup.moshi.Json;

public class Item {

    @Json(name = "id")
    private Integer id;
    @Json(name = "from_id")
    private Integer fromId;
    @Json(name = "owner_id")
    private Integer ownerId;
    @Json(name = "date")
    private Integer date;
    @Json(name = "marked_as_ads")
    private Integer markedAsAds;
    @Json(name = "post_type")
    private String postType;
    @Json(name = "text")
    private String text;
    @Json(name = "attachments")
    private List<Attachment> attachments = null;
    @Json(name = "post_source")
    private PostSource postSource;
    @Json(name = "comments")
    private Comments comments;
    @Json(name = "likes")
    private Likes likes;
    @Json(name = "reposts")
    private Reposts reposts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getMarkedAsAds() {
        return markedAsAds;
    }

    public void setMarkedAsAds(Integer markedAsAds) {
        this.markedAsAds = markedAsAds;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public PostSource getPostSource() {
        return postSource;
    }

    public void setPostSource(PostSource postSource) {
        this.postSource = postSource;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public void setReposts(Reposts reposts) {
        this.reposts = reposts;
    }

}
