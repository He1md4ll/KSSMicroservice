package edu.hs.bremen.api;

import edu.hs.bremen.facade.IApiFacade;
import edu.hs.bremen.model.dto.CouponDto;
import edu.hs.bremen.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Api entry point for coupon
 * Api also triggers validation of user
 * Cross origin for separate deployment of frontend and backend
 */
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
        UserValidator.validateUserId(userUuid);
        final CouponDto couponDto = apiFacade.verifyCoupon(userUuid, couponCode);
        if (couponDto != null) {
            return ResponseEntity.ok(couponDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteCouponCode(@RequestParam("user") String userUuid) {
        UserValidator.validateUserId(userUuid);
        apiFacade.deleteCoupon(userUuid);
        return ResponseEntity.ok().build();
    }
}