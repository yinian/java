package com.sourceforge.shiro.design_pattern.builder_design_pattern;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.subject.Subject;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
/**
 * @Auther: viagra
 * @Date: 2019/6/30 08:49
 * @Description: 这个主要是有关建造者设计模式，参考的类是 org.apache.shiro.subject.Subject.java
 */
public interface BuilderMain {



    /**
     * Returns this Subject's application-wide uniquely identifying principal, or {@code null} if this
     * Subject is anonymous because it doesn't yet have any associated account data (for example,
     * if they haven't logged in).
     * <p/>
     * The term <em>principal</em> is just a fancy security term for any identifying attribute(s) of an application
     * user, such as a username, or user id, or public key, or anything else you might use in your application to
     * identify a user.
     * <h4>Uniqueness</h4>
     * Although given names and family names (first/last) are technically considered principals as well,
     * Shiro expects the object returned from this method to be an identifying attribute unique across
     * your entire application.
     * <p/>
     * This implies that things like given names and family names are usually poor
     * candidates as return values since they are rarely guaranteed to be unique;  Things often used for this value:
     * <ul>
     * <li>A {@code long} RDBMS surrogate primary key</li>
     * <li>An application-unique username</li>
     * <li>A {@link java.util.UUID UUID}</li>
     * <li>An LDAP Unique ID</li>
     * </ul>
     * or any other similar suitable unique mechanism valuable to your application.
     * <p/>
     * Most implementations will simply return
     * <code>{@link #getPrincipals()}.{@link org.apache.shiro.subject.PrincipalCollection#getPrimaryPrincipal() getPrimaryPrincipal()}</code>
     *
     * @return this Subject's application-specific unique identity.
     * @see org.apache.shiro.subject.PrincipalCollection#getPrimaryPrincipal()
     */
    Object getPrincipal();

    /**
     * Returns this Subject's principals (identifying attributes) in the form of a {@code PrincipalCollection} or
     * {@code null} if this Subject is anonymous because it doesn't yet have any associated account data (for example,
     * if they haven't logged in).
     * <p/>
     * The word &quot;principals&quot; is nothing more than a fancy security term for identifying attributes associated
     * with a Subject, aka, application user.  For example, user id, a surname (family/last name), given (first) name,
     * social security number, nickname, username, etc, are all examples of a principal.
     *
     * @return all of this Subject's principals (identifying attributes).
     * @see #getPrincipal()
     * @see org.apache.shiro.subject.PrincipalCollection#getPrimaryPrincipal()
     */
    PrincipalCollection getPrincipals();

    /**
     * Returns {@code true} if this Subject is permitted to perform an action or access a resource summarized by the
     * specified permission string.
     * <p/>
     * This is an overloaded method for the corresponding type-safe {@link Permission Permission} variant.
     * Please see the class-level JavaDoc for more information on these String-based permission methods.
     *
     * @param permission the String representation of a Permission that is being checked.
     * @return true if this Subject is permitted, false otherwise.
     * @see #isPermitted(Permission permission)
     * @since 0.9
     */
    boolean isPermitted(String permission);

    /**
     * Returns {@code true} if this Subject is permitted to perform an action or access a resource summarized by the
     * specified permission.
     * <p/>
     * More specifically, this method determines if any {@code Permission}s associated
     * with the subject {@link Permission#implies(Permission) imply} the specified permission.
     *
     * @param permission the permission that is being checked.
     * @return true if this Subject is permitted, false otherwise.
     */
    boolean isPermitted(Permission permission);

    /**
     * Checks if this Subject implies the given permission strings and returns a boolean array indicating which
     * permissions are implied.
     * <p/>
     * This is an overloaded method for the corresponding type-safe {@link Permission Permission} variant.
     * Please see the class-level JavaDoc for more information on these String-based permission methods.
     *
     * @param permissions the String representations of the Permissions that are being checked.
     * @return a boolean array where indices correspond to the index of the
     *         permissions in the given list.  A true value at an index indicates this Subject is permitted for
     *         for the associated {@code Permission} string in the list.  A false value at an index
     *         indicates otherwise.
     * @since 0.9
     */
    boolean[] isPermitted(String... permissions);

    /**
     * Checks if this Subject implies the given Permissions and returns a boolean array indicating which permissions
     * are implied.
     * <p/>
     * More specifically, this method should determine if each {@code Permission} in
     * the array is {@link Permission#implies(Permission) implied} by permissions
     * already associated with the subject.
     * <p/>
     * This is primarily a performance-enhancing method to help reduce the number of
     * {@link #isPermitted} invocations over the wire in client/server systems.
     *
     * @param permissions the permissions that are being checked.
     * @return a boolean array where indices correspond to the index of the
     *         permissions in the given list.  A true value at an index indicates this Subject is permitted for
     *         for the associated {@code Permission} object in the list.  A false value at an index
     *         indicates otherwise.
     */
    boolean[] isPermitted(List<Permission> permissions);

    /**
     * Returns {@code true} if this Subject implies all of the specified permission strings, {@code false} otherwise.
     * <p/>
     * This is an overloaded method for the corresponding type-safe {@link org.apache.shiro.authz.Permission Permission}
     * variant.  Please see the class-level JavaDoc for more information on these String-based permission methods.
     *
     * @param permissions the String representations of the Permissions that are being checked.
     * @return true if this Subject has all of the specified permissions, false otherwise.
     * @see #isPermittedAll(Collection)
     * @since 0.9
     */
    boolean isPermittedAll(String... permissions);

    /**
     * Returns {@code true} if this Subject implies all of the specified permissions, {@code false} otherwise.
     * <p/>
     * More specifically, this method determines if all of the given {@code Permission}s are
     * {@link Permission#implies(Permission) implied by} permissions already associated with this Subject.
     *
     * @param permissions the permissions to check.
     * @return true if this Subject has all of the specified permissions, false otherwise.
     */
    boolean isPermittedAll(Collection<Permission> permissions);

    /**
     * Ensures this Subject implies the specified permission String.
     * <p/>
     * If this subject's existing associated permissions do not {@link Permission#implies(Permission)} imply}
     * the given permission, an {@link org.apache.shiro.authz.AuthorizationException} will be thrown.
     * <p/>
     * This is an overloaded method for the corresponding type-safe {@link Permission Permission} variant.
     * Please see the class-level JavaDoc for more information on these String-based permission methods.
     *
     * @param permission the String representation of the Permission to check.
     * @throws org.apache.shiro.authz.AuthorizationException
     *          if the user does not have the permission.
     * @since 0.9
     */
    void checkPermission(String permission) throws AuthorizationException;

    /**
     * Ensures this Subject {@link Permission#implies(Permission) implies} the specified {@code Permission}.
     * <p/>
     * If this subject's existing associated permissions do not {@link Permission#implies(Permission) imply}
     * the given permission, an {@link org.apache.shiro.authz.AuthorizationException} will be thrown.
     *
     * @param permission the Permission to check.
     * @throws org.apache.shiro.authz.AuthorizationException
     *          if this Subject does not have the permission.
     */
    void checkPermission(Permission permission) throws AuthorizationException;


    /**
     * Builder design pattern implementation for creating {@link Subject} instances
     * in a simplified way without requiring knowledge of Shiro's constructor techniques.
     * <p/>
     * <b>NOTE</b>: This is provided for framework development support only and should typically
     * never be used by application developers. {@code Subject} instances should generally be acquired
     * by using <code>SecurityUtils.{@ling SecurityUtils#getSubject() getSubject()}</code>
     * <h4>Usage</h4>
     * The simplest usage of this builder is to construct an anonymous, session-less {@code Subject} instance:
     * ubject subject = new Subject.{@link #Builder() Builder}().{@link #buildSubject() buildSubject()};</pre>
      * The default, no-arg {@code Subject.Builder()} constructor shown above will use the application's
      * currently accessible {@code SecurityManager} via
      * <code>SecurityUtils.{@link SecurityUtils#getSecurityManager() getSecurityManager()}</code>.  You may also
      * specify the exact {@code SecurityManager} instance to be used by the additional
      * <code>Subject.{@link #Builder(org.apache.shiro.mgt.SecurityManager) Builder(securityManager)}</code>
      * constructor if desired.
      * <p/>
     */
    public static class Builder{

        /**
         * Hold all contextual data via the Builder instance's method invocations to
         * be sent to the {@code SecurityManager} during the {@link #buildSubject} call.
         */
        private final SubjectContext subjectContext;
        /**
         * The SecurityManage to invoke during the {@link #buildSubject} call.
         */
        private final SecurityManager securityManager;
        /**
         * Constructs a new {@link Subject.Builder} instance, using the {@code SecurityManager} instance available
         * to the calling code as determined by a call to {@link org.apache.shiro.SecurityUtils#getSecurityManager()}
         * to build the {@code Subject} instance.
         */
        public Builder(){
            this(SecurityUtils.getSecurityManager());
        }

        public Builder(SecurityManager securityManager){
            if(securityManager == null){
                throw new NullPointerException("SecurityManager method argument cannot be null.");
            }
            this.securityManager = securityManager;
            this.subjectContext = newSubjectContextInstance();
            if (this.subjectContext == null) {
                throw new IllegalStateException("Subject instance returned from 'newSubjectContextInstance' " +
                        "cannot be null.");
            }

            this.subjectContext.setSecurityManager(securityManager);
        }

        /**
         * Returns the backing context used to build the {@code Subject} instance, available to subclasses
         * since the {@code context} class attribute is marked as {@code private}.
         *
         * @return the backing context used to build the {@code Subject} instance, available to subclasses.
         */
        protected SubjectContext getSubjectContext() {
            return this.subjectContext;
        }

        /**
         * Enables building a {@link Subject subject}} instance that owns the
         * {@link Session Session} with the specified {@code sessionId}.
         * <p/>
         * @param sessionId
         * @return
         */
        public Builder sessionId(Serializable sessionId){
            if (sessionId != null){
                this.subjectContext.setSessionId(sessionId);
            }
            return this;
        }

        /**
         * Ensures the {@code Subject} being built will reflect the specified host
         * name or IP originating location.
         * @param host
         * @return
         */
        public Builder host(String host){
            if (StringUtils.hasText(host)){
                this.subjectContext.setHost(host);
            }

            return this;
        }

        /**
         * Ensures the {@code Subject} being built will use the specified {@link Session} instance.  Note that it is
         * more common to use the {@link #sessionId sessionId} builder method rather than having to construct a
         * {@code Session} instance for this method.
         *
         * @param session the session to use as the {@code Subject}'s {@link Session}
         * @return this {@code Builder} instance for method chaining.
         */
        public Builder session(Session session) {
            if (session != null) {
                this.subjectContext.setSession(session);
            }
            return this;
        }
        /**
         * Ensures the {@code Subject} being built will reflect the specified principals (aka identity).
         * <p/>
         * For example, if your application's unique identifier for users is a {@code String} username, and you wanted
         * to create a {@code Subject} instance that reflected a user whose username is
         * '{@code jsmith}', and you knew the Realm that could acquire {@code jsmith}'s principals based on the username
         * was named &quot;{@code myRealm}&quot;, you might create the '{@code jsmith} {@code Subject} instance this
         * way:
         * <pre>
         * PrincipalCollection identity = new {@link org.apache.shiro.subject.SimplePrincipalCollection#SimplePrincipalCollection(Object, String) SimplePrincipalCollection}(&quot;jsmith&quot;, &quot;myRealm&quot;);
         * Subject jsmith = new Subject.Builder().principals(identity).buildSubject();</pre>
         * <p/>
         * Similarly, if your application's unique identifier for users is a {@code long} value (such as might be used
         * as a primary key in a relational database) and you were using a {@code JDBC}
         * {@code Realm} named, (unimaginatively) &quot;jdbcRealm&quot;, you might create the Subject
         * instance this way:
         * <pre>
         * long userId = //get user ID from somewhere
         * PrincipalCollection userIdentity = new {@link org.apache.shiro.subject.SimplePrincipalCollection#SimplePrincipalCollection(Object, String) SimplePrincipalCollection}(<em>userId</em>, &quot;jdbcRealm&quot;);
         * Subject user = new Subject.Builder().principals(identity).buildSubject();</pre>
         *
         * @param principals the principals to use as the {@code Subject}'s identity.
         * @return this {@code Builder} instance for method chaining.
         */
        public Builder principals(PrincipalCollection principals){
            if (principals != null && !principals.isEmpty()){
                this.subjectContext.setPrincipals(principals);
            }
            return this;
        }
        /**
         * Configures whether or not the created Subject instance can create a new {@code Session} if one does not
         * already exist.  If set to {@code false}, any application calls to
         * {@code subject.getSession()} or {@code subject.getSession(true))} will result in a SessionException.
         * <p/>
         * This setting is {@code true} by default, as most applications find value in sessions.
         *
         * @param enabled whether or not the created Subject instance can create a new {@code Session} if one does not
         *                already exist.
         * @return this {@code Builder} instance for method chaining.
         * @since 1.2
         */
        public Builder sessionCreationEnabled(boolean enabled){
            this.subjectContext.setSessionCreationEnabled(enabled);
            return this;
        }

        /**
         * Ensures the {@code Subject} being built will be considered
         * {@link org.apache.shiro.subject.Subject#isAuthenticated() authenticated}.  Per the
         * {@link org.apache.shiro.subject.Subject#isAuthenticated() isAuthenticated()} JavaDoc, be careful
         * when specifying {@code true} - you should know what you are doing and have a good reason for ignoring Shiro's
         * default authentication state mechanisms.
         *
         * @param authenticated whether or not the built {@code Subject} will be considered authenticated.
         * @return this {@code Builder} instance for method chaining.
         * @see org.apache.shiro.subject.Subject#isAuthenticated()
         */
        public Builder authenticated(boolean authenticated) {
            this.subjectContext.setAuthenticated(authenticated);
            return this;
        }
        /**
         * Creates a new {@code SubjectContext} instance to be used to populate with subject contextual data that
         * will then be sent to the {@code SecurityManager} to create a new {@code Subject} instance.
         *
         * @return a new {@code SubjectContext} instance
         */
        protected SubjectContext newSubjectContextInstance() {
            return new DefaultSubjectContext();
        }

        /**
         * Creates and returns a new {@code Subject} instance reflecting the cumulative state acquired by the
         * other methods in this class.
         * <p/>
         * This {@code Builder} instance will still retain the underlying state after this method is called - it
         * will not clear it; repeated calls to this method will return multiple {@link Subject} instances, all
         * reflecting the exact same state.  If a new (different) {@code Subject} is to be constructed, a new
         * {@code Builder} instance must be created.
         * <p/>
         * <b>Note</b> that the returned {@code Subject} instance is <b>not</b> automatically bound to the application
         * (thread) for further use.  That is,
         * {@link org.apache.shiro.SecurityUtils SecurityUtils}.{@link org.apache.shiro.SecurityUtils#getSubject() getSubject()}
         * will not automatically return the same instance as what is returned by the builder.  It is up to the
         * framework developer to bind the returned {@code Subject} for continued use if desired.
         *
         * @return a new {@code Subject} instance reflecting the cumulative state acquired by the
         *         other methods in this class.
         */
        public Subject buildSubject() {
            return this.securityManager.createSubject(this.subjectContext);
        }


    }


}
