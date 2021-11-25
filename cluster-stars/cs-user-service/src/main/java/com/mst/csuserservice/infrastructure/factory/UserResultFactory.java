package com.mst.csuserservice.infrastructure.factory;

import com.mst.csuserservice.application.DTO.UserDTO;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.controller.component.ResultVO;
import org.springframework.stereotype.Component;

/**
 * @author Molin
 * @date   2021-11-20 15:29
 * 基础设施创建用户返回结果工厂
 */
@Component
public class UserResultFactory {

    private UserResultFactory() {}

    /**
     * 注册回调成功创建返回结果
     * @param   userDTO  UserDTO
     * @return  ResultVO
     */
    public static ResultVO<UserDTO> newResultForRegister(final UserDTO userDTO) {

        if (Boolean.FALSE.equals(userDTO.getMsg())) {
            return ResultVO.validateFailed(UserConstant.REGISTER_FAILED);
        }
        return ResultVO.success(userDTO, UserConstant.REGISTER_SUCCEED);
    }

    /**
     * 登录回调成功创建返回结果
     * @param   userDTO  UserDTO
     * @return  ResultVO
     */
    public static ResultVO<UserDTO> newResultForLogin(final UserDTO userDTO) {

        if (Boolean.FALSE.equals(userDTO.getMsg())) {
            return ResultVO.validateFailed(UserConstant.WRONG_NAME_PWD);
        }
        return ResultVO.success(userDTO, UserConstant.LOGIN_SUCCEED);
    }
}
