<#import "parts/elements.ftlh" as elements/>
<#import "parts/main.ftlh" as main/>

<@main.main title="User profile">
    <div class="parallax big-img-login-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">
                ${user.names}
            </span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="userProfile">
        <div class="w3-center w3-row">
            <form action="/userProfile/save" method="post" enctype="multipart/form-data">
                <p class="w3-center">Email: ${user.email}</p>
                <div class="w3-center" style="">
                    <#if user.avatar?has_content>
                        <img src="/img/${user.avatar}" style="width: 300px;height: 337px;">
                    <#else>
                        <p style="color: gray;">
                            <strong>No image</strong>
                        </p>
                    </#if>
                </div>
                <p class="w3-center">Change avatar: </p>
                <input type="file" name="avatar" placeholder="avatar . . ." />

                <p class="w3-center">First name</p>
                <@elements.input id="firstname" name="firstName" type="text" value="${user.firstName}" placeholder="f i r s t n a m e"/>
                <p class="w3-center">Last name</p>
                <@elements.input id="lastname" name="lastName" type="text" value="${user.lastName}" placeholder="l a s t n a m e"/>
                <p class="w3-center">Username</p>
                <@elements.input id="username" name="username" type="text" value="${user.username}" placeholder="u s e r n a m e"/>
                <#if user.isActive()>
                    <p class="w3-center"><b>Account is active</b></p>
                <#else>
                    <p class="w3-center" style="color: red;">Account is not active</p>
                </#if>
                <input type="hidden" value="${user.id}" name="userId">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn1 w3-button w3-padding-large">Save changes</button>
            </form>
        </div>
    </div>

    <div class="parallax small-img-login-1 w3-display-container w3-opacity-min" id="subscriptions">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">
                Subscriptions and notifications
            </span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="choose-subscriptions">
        <div class="w3-center w3-row">
            <form action="/userProfile/subscribe" method="post" style="margin: 30px 10px 10px">
                <#list subscriptions as subscription>
                    <div>
                        <label>
                            <input type="checkbox" name="${subscription}"
                                    ${userSubscriptions?seq_contains(subscription)?string("checked", "")}>
                            ${subscription}
                        </label>
                    </div>
                </#list>
                <br>
                <input type="hidden" value="${user.id}" name="user">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit" class="small-btn btn2 w3-button w3-padding-large">Save</button>
            </form>
        </div>
    </div>

    <div class="parallax small-img-login-1 w3-display-container w3-opacity-min" id="notifications">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">
                Notifications
            </span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="choose-subscriptions">
        <div class="w3-center w3-row">
            <#if user.notifications?has_content>
                <#list user.notifications as notification>
                    <div>
                        <p>${notification.creationDate!} | ${notification.title!}</p>
                        <br>
                        <p>${notification.content!}</p>
                    </div>
                </#list>
            <#else>
                <p>No notifications!</p>
            </#if>
        </div>
    </div>

    <div class="parallax small-img-login-1 w3-display-container w3-opacity-min" id="footer">
    </div>
</@main.main>