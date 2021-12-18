<#macro customMenu menuElements slideMenuElements='' withZIndex2 = false>

    <#if slideMenuElements?has_content>
        <@sideMenu slideMenuElements/>
    </#if>

    <style>
        .fa*{margin-right: 5px;}
    </style>

    <div class="w3-top" style= "${withZIndex2?then('z-index: 2;', '')}">
        <div class="w3-bar" id="myNavBar">
            <#list menuElements as element>
                <div id="${element.id}">
                    <a href="${element.url!''}" class="${element.hrefCssClass!''}" title="${element.description!''}">
                        <i class="${element.cssClass!''}"></i>
                        ${element.name!''}
                    </a>
                </div>
            </#list>
        </div>
    </div>
</#macro>

<#macro sideMenu elements>
    <div class="side-menu">
        <div class="toggle-btn fas fa-chevron-right"></div>
        <#list elements as element>
            <a href="${element.url!''}" class="${element.hrefCssClass!''}" title="${element.description!''}">
                ${element.name!''}
            </a>
        </#list>
    </div>

    <script>
        $(".toggle-btn").click(function(){
            $(".side-menu").toggleClass("active");
            $(this).toggleClass("fa-chevron-right fa-chevron-left");
        });
    </script>
</#macro>