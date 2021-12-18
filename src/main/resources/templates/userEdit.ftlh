<#import "parts/main.ftlh" as main/>

<@main.main title="User settings">
    <div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">List of Users</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="add-message">
        <h3 class="w3-center">EDITOR</h3>
        <div class="w3-center w3-row">
            <form action="/user" method="post" style="margin: 30px 10px 10px">
                <p class="w3-center">User name: </p>
                <label>
                    <input type="text" name="username" value="${user.username}">
                </label>
                <p class="w3-center">First name: </p>
                <label>
                    <input type="text" name="firstName" value="${user.firstName}">
                </label>
                <p class="w3-center">Last name: </p>
                <label>
                    <input type="text" name="lastName" value="${user.lastName}">
                </label>
                <#list roles as role>
                    <br>
                    <div>
                        <label>
                            <input type="checkbox" name="${role}"
                                ${user.roles?seq_contains(role)?string("checked", "")}>
                            ${role}
                        </label>
                    </div>
                </#list>
                <br>
                <input type="hidden" value="${user.id}" name="userId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit" class="small-btn btn2 w3-button w3-padding-large">Save</button>
            </form>
        </div>
    </div>

    <div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
        </div>
    </div>
</@main.main>