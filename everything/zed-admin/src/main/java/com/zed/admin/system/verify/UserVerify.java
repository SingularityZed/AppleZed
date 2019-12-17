package com.zed.admin.system.verify;

import com.zed.admin.common.constant.StatusCode;
import com.zed.admin.common.exception.VerifyException;
import com.zed.admin.system.constant.AdminEnum;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.dto.UserVerifyDTO;
import com.zed.admin.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * UserVerify
 *
 * @author zed
 * @date 2019/12/12 19:12
 */
@Slf4j
@Service
public class UserVerify {

    @Autowired
    private UserService userService;

    public void verifyRepeat(UserDTO dto) {
        UserVerifyDTO verifyDTO = userService.verifyRepeat(dto);
        // Class must not be null
        Assert.notNull(verifyDTO, StatusCode.VERIFY_410001.message());
        if (verifyDTO.getCountUsername() > 0) {
            throw new VerifyException(AdminEnum.USER_NAME_REPEAT);
        }

    }


}
