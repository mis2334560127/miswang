package com.example.shopping.controller;

import com.example.shopping.pojo.AdminPermission;
import com.example.shopping.pojo.AdminRole;
import com.example.shopping.pojo.AdminRoleMenu;
import com.example.shopping.result.Result;
import com.example.shopping.result.ResultFactory;
import com.example.shopping.service.AdminPermissionService;
import com.example.shopping.service.AdminRoleMenuService;
import com.example.shopping.service.AdminRolePermissionService;
import com.example.shopping.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Evan
 * @date 2019/11
 */

@RestController
public class RoleController {
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminPermissionService adminPermissionService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;

    @GetMapping("/api/admin/role")
    public List<AdminRole> listRoles(){
        return adminRoleService.list();
    }

    @PutMapping("/api/admin/role/status")
    public Result updateRoleStatus(@RequestBody AdminRole requestRole) {
        AdminRole adminRole = adminRoleService.findById(requestRole.getId());
        adminRole.setEnabled(requestRole.isEnabled());
        adminRoleService.addOrUpdate(adminRole);
        String message = "用户" + adminRole.getNameZh() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PutMapping("/api/admin/role")
    public Result editRole(@RequestBody AdminRole requestRole) {
        adminRoleService.addOrUpdate(requestRole);
        adminRolePermissionService.savePermChanges(requestRole.getId(), requestRole.getPerms());
        String message = "修改角色信息成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @PostMapping("/api/admin/role")
    public Result addRole(@RequestBody AdminRole requestRole) {
        adminRoleService.addOrUpdate(requestRole);
        String message = "添加角色成功";
        return ResultFactory.buildSuccessResult(message);
    }

    @GetMapping("/api/admin/role/perm")
    public List<AdminPermission> listPerms() {
        return adminPermissionService.list();
    }

    @PutMapping("/api/admin/role/menu")
    public void updateRoleMenu(@RequestParam int rid, @RequestBody LinkedHashMap menusIds) {
        adminRoleMenuService.deleteAllByRid(rid);
        for (Object value : menusIds.values()) {
            for (int mid : (List<Integer>)value) {
                AdminRoleMenu rm = new AdminRoleMenu();
                rm.setRid(rid);
                rm.setMid(mid);
                adminRoleMenuService.save(rm);
            }
        }
    }
}
