<#import "parts/main.ftlh" as main/>

<@main.main title="Preferences">
    <div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Event Management System</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="choose-preferences">
        <div class="w3-center w3-row">
            <form action="/userPreferences/save" method="post" style="margin: 30px 10px 10px">
                <#list tags?keys as eventType>
                    <#if eventType?has_content && tags[eventType]?has_content>
                        <br>
                        ${eventType}
                        <#list tags[eventType] as tag>
                            <div>
                                <label>
                                    <#if user.firstLogin>
                                        <input type="checkbox" name="${tag.name}">
                                        ${tag.description}
                                    <#else>
                                        <input type="checkbox" name="${tag.name}"
                                                ${user.tags?seq_contains(tag)?string("checked", "")}>
                                        ${tag.description}
                                    </#if>
                                </label>
                            </div>
                        </#list>
                    </#if>
                </#list>
                <br>
                <input type="hidden" value="${user.id}" name="user">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit" class="small-btn btn2 w3-button w3-padding-large">Save</button>
            </form>
        </div>
    </div>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="footer">
    </div>
</@main.main>