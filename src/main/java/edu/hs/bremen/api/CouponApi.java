package edu.hs.bremen.api;

import edu.hs.bremen.facade.IApiFacade;
import edu.hs.bremen.model.dto.CouponDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/coupon", produces = MediaType.APPLICATION_JSON_VALUE)
public class CouponApi {

    private IApiFacade apiFacade;

    @Autowired
    public CouponApi(IApiFacade apiFacade) {
        this.apiFacade = apiFacade;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity checkCouponCode(@RequestParam("user") String userUuid,
                                          @RequestParam("code") String couponCode) {
        final CouponDto couponDto = apiFacade.verifyCoupon(userUuid, couponCode);
        if (couponDto != null) {
            return ResponseEntity.ok(couponDto);
        }
        return ResponseEntity.badRequest().build();
    }
}