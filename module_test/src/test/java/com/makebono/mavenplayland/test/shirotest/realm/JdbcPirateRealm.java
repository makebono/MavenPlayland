package com.makebono.mavenplayland.test.shirotest.realm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: JdbcPirateRealm 
 * @Description: JdbcPirateRealm 
 * @author makebono
 * @date 2018年2月8日 上午11:45:29 
 *  
 */
public class JdbcPirateRealm extends JdbcRealm {
    private static final Logger log = LoggerFactory.getLogger(JdbcPirateRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
        // permissionsLookupEnabled needs to be toggled true for getting permissions. Don't wanna change the logic so
        // set it true here. Also can be done in .ini config.
        this.setPermissionsLookupEnabled(true);

        // null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        final String username = (String) getAvailablePrincipal(principals);

        Connection conn = null;
        Set<String> roleNames = null;
        Set<String> permissions = null;
        try {
            conn = dataSource.getConnection();

            // Retrieve roles and permissions from database
            roleNames = getRoleNamesForUser(conn, username);
            if (permissionsLookupEnabled) {
                permissions = getPermissions(conn, username, roleNames);
            }

        }
        catch (final SQLException e) {
            final String message = "There was a SQL error while authorizing user [" + username + "]";
            if (log.isErrorEnabled()) {
                log.error(message, e);
            }

            // Rethrow any SQL errors as an authorization exception
            throw new AuthorizationException(message, e);
        }
        finally {
            JdbcUtils.closeConnection(conn);
        }

        final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected Set<String> getPermissions(final Connection conn, final String username,
            final Collection<String> roleNames) throws SQLException {
        final Set<String> result = super.getPermissions(conn, username, roleNames);
        System.out.println(result);
        return result;
    }

}
