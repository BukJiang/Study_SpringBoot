package com.buk.swagger.web;

import com.buk.swagger.pojo.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * TODO: RESTFul接口
 * TODO: Swagger3
 *
 * @author BuK
 * @since 2020/08/17
 */
@Api(tags = "用户API-tags", value = "用户API-values")
@RestController
@RequestMapping("/user")
public class Swagger3Controller {

    /**
     * 创建线程安全的Map，模拟user信息的存储
     */
    private final static Map<Long, User> USER_MAP = Collections.synchronizedMap(new HashMap<>());

    /**
     * 创建用户信息
     * 通过@RequestBody注解用来绑定通过http请求中application/json类型上传的数据
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "创建用户信息-value", notes = "创建用户信息-notes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "200-success"),
            @ApiResponse(code = 400, message = "400-error")
    })
    @PostMapping("")
    public String post(@RequestBody User user) {
        if (USER_MAP.containsKey(user.getId())) {
            return "post-user-error, user is exist";
        }
        USER_MAP.put(user.getId(), user);
        return "post-user-success: " + user;
    }

    /**
     * 获取用户列表
     * 可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
     *
     * @return
     */
    @ApiOperation(value = "获取用户列表-value", notes = "获取用户列表-notes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "200-success"),
            @ApiResponse(code = 400, message = "400-error")
    })
    @GetMapping("")
    public List<User> get() {
        Collection<User> userValues = USER_MAP.values();
        return new ArrayList<>(userValues);
    }

    /**
     * 获取用户信息
     * url中的id可通过@PathVariable绑定到函数的参数中
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户信息-value", notes = "获取用户信息-notes")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "id-value", required = true)
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "200-success"),
            @ApiResponse(code = 400, message = "400-error")
    })
    @GetMapping("/{id}")
    public User getUser(@ApiParam("用户ID") @PathVariable Long id) {
        return USER_MAP.get(id);
    }

    /**
     * 更新用户信息
     *
     * @param id
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户信息-value", notes = "更新用户信息-notes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id-value", required = true),
            @ApiImplicitParam(name = "user", value = "user-value", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "200-success"),
            @ApiResponse(code = 400, message = "400-error")
    })
    @PutMapping("/{id}")
    public String put(@ApiParam("用户ID") @PathVariable Long id, @RequestBody User user) {
        User oldUser = USER_MAP.get(id);
        if (oldUser == null) {
            return "put-user-error, user is no-exist";
        }
        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());
        USER_MAP.put(id, oldUser);
        return "put-user-success: " + oldUser;
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "创建用户信息-value", notes = "创建用户信息-notes")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "id-value", required = true)
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "200-success"),
            @ApiResponse(code = 400, message = "400-error")
    })
    @DeleteMapping("/{id}")
    public String delete(@ApiParam("用户ID") @PathVariable Long id) {
        if (!USER_MAP.containsKey(id)) {
            return "delete-user-error, user is no-exist";
        }
        USER_MAP.remove(id);
        return "delete-user-success: " + id;
    }
}
