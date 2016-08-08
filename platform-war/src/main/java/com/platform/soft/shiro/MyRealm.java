package com.platform.soft.shiro;

import com.platform.soft.api.backstage.ex.IMenuService;
import com.platform.soft.api.backstage.ex.IPermissionService;
import com.platform.soft.api.backstage.ex.IRoleService;
import com.platform.soft.api.backstage.ex.ISystemUserService;
import com.platform.soft.domain.backstage.ex.Permission;
import com.platform.soft.domain.backstage.ex.Menu;
import com.platform.soft.domain.backstage.ex.SystemUser;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by baixiaobin on 16/8/4.
 */
public class MyRealm  extends AuthorizingRealm {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISystemUserService systemUserService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    /**
     * 为当前登录的Subject授予角色和权限
     * 该方法的调用时机为需授权资源被访问时
     * 每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * 若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
     * 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Object roles = request.getSession().getAttribute("roleList");

        //赋角色
        if (null != roles) {
            List<Map<String, Object>> roleList = (List<Map<String, Object>>) roles;
            if (null != roleList && roleList.size() > 0) {
                for (int i = 0; i < roleList.size(); i++) {
                    Map<String, Object> map = roleList.get(i);

                    authorizationInfo.addRole(map.get("roleId").toString());
                }
            }
        }

        Object permissionObject = request.getSession().getAttribute("permissionList");
        //赋权限
        if (null != permissionObject) {
            List<Permission> permissionList = ( List<Permission> )permissionObject;
            for(Permission pp :permissionList){
                //System.out.println("permission:"+pp.getPermissionCode());
                authorizationInfo.addStringPermission(pp.getPermissionCode());
            }
        }
            return authorizationInfo;
    }


    /**
     * 验证当前登录的Subject
     * 该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        SystemUser user = systemUserService.login(token.getUsername(), new String(token.getPassword()));
        if(null != user){
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getSystemName(), user.getPassword(), user.getRealName());
            this.setSession("currentUser", user);
            getMenuRolePermission(user.getId());
            return authcInfo;
        }else{
            return null;
        }

    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }



    private void getMenuRolePermission(int userId){
        //用户角色列表
        List<Map<String,Object>> roleList = systemUserService.getRoleListByUserId(userId);
        //用户权限
        List<Permission> permissionList = permissionService.getPermissionByUserId(userId);
        List<Menu> menuList =  menuService.getMenuList(userId);
        this.setSession("roleList", roleList);
        this.setSession("permissionList", permissionList);
        this.setSession("menuList",menuList);

    }

}