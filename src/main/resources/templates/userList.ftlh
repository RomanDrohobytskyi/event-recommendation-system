<#import "parts/main.ftlh" as main/>

<@main.main title="Users">
    <!-- First Parallax Image -->
    <div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">List of Users</span>
        </div>
    </div>

    <!-- User ListContainer -->
    <div class="w3-content w3-container w3-padding-32" id="add-message">
        <h3 class="w3-center lucida-console" style="font-size:40px;">Users</h3>
        <div class="w3-center w3-row">
            <table align="center" width="100%" style="margin-top:30px">
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>User Name</th>
                    <th>Role</th>
                    <th>Remove</th>
                    <th>Edit</th>
                </tr>
                <tbody>
                    <#if activeUsers?has_content>
                        <tr>
                            <td colspan="7">
                                <h3 style="margin-top:15px;">Active users</h3>
                            </td>
                        </tr>
                        <#list activeUsers as user>
                            <tr style="height: 120px">
                                <td>
                                    ${user.email}
                                </td>
                                <td>
                                    ${user.firstName}
                                </td>
                                <td>
                                    ${user.lastName}
                                </td>
                                <td>
                                    ${user.username}
                                </td>
                                <td><#list user.roles as role>
                                        ${role}<#sep>,
                                    </#list>
                                </td>
                                <td>
                                    <div>
                                        <form action="/user/delete" method="post">
                                            <button type="submit" class="btn-link">
                                                <i class="fa fa-trash-o" aria-hidden="true"></i>
                                            </button>
                                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                            <input type="hidden" value="${user.id}" name="user">
                                        </form>
                                    </div>
                                </td>
                                <td>
                                    <a href="/user/${user.id}"> <button class="small-btn btn2 w3-button w3-padding-large">Edit</button></a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                    <#if deletedUsers?has_content>
                        <tr>
                            <td colspan="7">
                                <h3 style="margin-top:15px;">Deleted users</h3>
                            </td>
                        </tr>
                        <#list deletedUsers as user>
                            <tr style="height: 120px">
                                <td>
                                 <span class="not-active" title="Not active!">
                                     ${user.email}
                                 </span>
                                </td>
                                <td>
                                <span class="not-active" title="Not active!">
                                    ${user.firstName}
                                </span>
                                </td>
                                <td>
                                <span class="not-active" title="Not active!">
                                    ${user.lastName}
                                </span>
                                </td>
                                <td>
                                <span class="not-active" title="Not active!">
                                    ${user.username}
                                </span>
                                </td>
                                <td><#list user.roles as role>
                                        ${role}<#sep>,
                                    </#list>
                                </td>
                                <td>
                                    <div>
                                        <form action="/user/renew" method="post">
                                            <button type="submit" class="btn-link">
                                                <i class="fa fa-circle-o-notch" aria-hidden="true"></i>
                                            </button>
                                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                            <input type="hidden" value="${user.id}" name="user">
                                        </form>
                                    </div>
                                </td>
                                <td>
                                    <a href="/user/${user.id}"> <button class="small-btn btn2 w3-button w3-padding-large">Edit</button></a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                </tbody>
            </table>
        </div>
    </div>

    <div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
        </div>
    </div>
</@main.main>
