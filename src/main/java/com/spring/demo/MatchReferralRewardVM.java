package com.spring.demo;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * @author Created by bonismo@hotmail.com on 2019/6/26 5:02 PM
 * @Description:
 * @Version: 1.0
 */
public class MatchReferralRewardVM implements Serializable {
    private String type;
    private Long userId;
    private String email;
    private String mobile;
    private Instant createdDate;
    private List<UpperLevelUser> upperLevels;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public List<UpperLevelUser> getUpperLevels() {
        return upperLevels;
    }

    public void setUpperLevels(List<UpperLevelUser> upperLevels) {
        this.upperLevels = upperLevels;
    }

    public static class UpperLevelUser {

        private Long upperUserId;
        private Integer upperRelation;
        private Double upperRewardRate;
        private Instant upperLevelExpiredDate;

        public Long getUpperUserId() {
            return upperUserId;
        }

        public void setUpperUserId(Long upperUserId) {
            this.upperUserId = upperUserId;
        }

        public Integer getUpperRelation() {
            return upperRelation;
        }

        public void setUpperRelation(Integer upperRelation) {
            this.upperRelation = upperRelation;
        }

        public Double getUpperRewardRate() {
            return upperRewardRate;
        }

        public void setUpperRewardRate(Double upperRewardRate) {
            this.upperRewardRate = upperRewardRate;
        }

        public Instant getUpperLevelExpiredDate() {
            return upperLevelExpiredDate;
        }

        public void setUpperLevelExpiredDate(Instant upperLevelExpiredDate) {
            this.upperLevelExpiredDate = upperLevelExpiredDate;
        }

        @Override
        public String toString() {
            return "UpperLevelUser{" +
                    "upperUserId=" + upperUserId +
                    ", upperRelation=" + upperRelation +
                    ", upperRewardRate=" + upperRewardRate +
                    ", upperLevelExpiredDate=" + upperLevelExpiredDate +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MatchReferralRewardVM{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", createdDate=" + createdDate +
                ", upperLevels=" + upperLevels +
                '}';
    }
}
