package com.mst.csuserservice.controller;

import com.mst.csuserservice.application.DTO.UserDTO;
import com.mst.csuserservice.application.UserManager;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.controller.component.ResultVO;
import com.mst.csuserservice.domain.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Molin
 * @date   2021-11-19  23:30
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * 注册.
     * @param  user  user
     * @return ResultVO
     */
    @PostMapping("/register")
    public ResultVO<UserDTO> register(@RequestBody User user) {
        UserDTO register = userManager.register(user);
        if (Boolean.FALSE.equals(register.getMsg())) {
            return ResultVO.validateFailed(UserConstant.REGISTER_FAILED);
        }
        return ResultVO.success(register, UserConstant.REGISTER_SUCCEED);
    }

    /**
     * 登陆.
     * @param  user  user
     * @return ResultVO
     */
    @PostMapping("/login")
    public ResultVO<UserDTO> login(@RequestBody User user) {
        UserDTO login = userManager.login(user.getName(), user.getPassword());
        if (Boolean.FALSE.equals(login.getMsg())) {
            return ResultVO.validateFailed(UserConstant.WRONG_NAME_PWD);
        }
        return ResultVO.success(login, UserConstant.LOGIN_SUCCEED);
    }
}
