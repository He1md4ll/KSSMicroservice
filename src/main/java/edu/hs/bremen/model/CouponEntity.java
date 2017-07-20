package edu.hs.bremen.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class CouponEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String couponCode;

    @NotNull
    private Date validFrom;
    private Date validUntil;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer rate;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public static class CouponBuilder {
        private CouponEntity couponEntity = new CouponEntity();

        public CouponBuilder withCouponCode(String couponCode) {
            couponEntity.setCouponCode(couponCode);
            return this;
        }

        public CouponBuilder withValidFrom(Date validFrom) {
            couponEntity.setValidFrom(validFrom);
            return this;
        }

        public CouponBuilder withValidUntil(Date validUntil) {
            couponEntity.setValidUntil(validUntil);
            return this;
        }

        public CouponBuilder withRate(int rate) {
            couponEntity.setRate(rate);
            return this;
        }

        public CouponEntity build() {
            return couponEntity;
        }
    }
}