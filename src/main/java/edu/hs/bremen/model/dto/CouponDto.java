package edu.hs.bremen.model.dto;

import edu.hs.bremen.model.CouponEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CouponDto {
    @NotNull
    @NotEmpty
    private String couponCode;

    @NotNull
    private Date validFrom;
    private Date validUntil;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer rate;

    public CouponDto(String couponCode, Date validFrom, Date validUntil, Integer rate) {
        this.couponCode = couponCode;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.rate = rate;
    }

    public static CouponDto fromCouponEntity(final CouponEntity couponEntity) {
        if (couponEntity != null) {
            return new CouponDto(couponEntity.getCouponCode(), couponEntity.getValidFrom(),
                    couponEntity.getValidUntil(), couponEntity.getRate());
        } else {
            return null;
        }
    }

    public String getCouponCode() {
        return couponCode;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public Integer getRate() {
        return rate;
    }
}