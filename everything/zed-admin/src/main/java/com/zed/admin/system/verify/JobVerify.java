package com.zed.admin.system.verify;

import com.zed.admin.constant.AdminConstant;
import com.zed.admin.constant.AdminEnum;
import com.zed.admin.enums.JobEnum;
import com.zed.admin.system.pojo.dto.JobDTO;
import com.zed.admin.system.pojo.dto.JobVerifyDTO;
import com.zed.admin.system.pojo.dto.UserDTO;
import com.zed.admin.system.pojo.dto.UserVerifyDTO;
import com.zed.admin.system.service.JobService;
import com.zed.admin.system.service.UserService;
import com.zed.common.constant.StatusCode;
import com.zed.common.exception.VerifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * JobVerify
 *
 * @author zed
 * @date 2019/12/12 19:12
 */
@Slf4j
@Component
public class JobVerify {

    @Autowired
    private JobService jobService;

    public void verifyRepeat(JobDTO dto) {
        JobVerifyDTO verifyDTO = jobService.verifyRepeat(dto);
        // Class must not be null
        Assert.notNull(verifyDTO, StatusCode.VERIFY_410001.getMessage());
        if (verifyDTO.getCountName() > 0) {
            throw new VerifyException(AdminConstant.JOB_NAME_REPEAT);
        }

    }


}
