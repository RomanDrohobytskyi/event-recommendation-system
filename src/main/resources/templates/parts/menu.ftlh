<#macro customMenu elements slideMenuElements withZIndex2 = false>
    <style>
        .fa{margin-right: 5px;}
    </style>

    <div class="w3-top" style= "${withZIndex2?then('z-index: 2;', '')}">
        <div class="w3-bar" id="myNavBar">
            <@customSlideMenu slideMenuElements/>
            <#list elements as element>
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

<#macro customSlideMenu slideMenuElements>
    <style>
        a{text-decoration: none;}
    </style>

    <a class="show-btn w3-bar-item w3-button" style="height: 38px;" href="javascript:void(0);"
       onclick="slideMenuToggleFunction()" title="Toggle Navigation Menu">
        <i class="fa fa-bars"></i>
    </a>
    <div id="slideMenu" class="middle w3-bar-item">
        <div class="sm-container">
            <div class="sm-menu" style="position:fixed;">
                <#list slideMenuElements as element>
                    <a href="${element.url!''}" class="${element.hrefCssClass!''}"><i class="${element.cssClass!''}"></i>${element.name!''}</a>
                </#list>
            </div>
            <#--<a href="/userProfile" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red">

                     <img alt="Qries" src="https://www.qries.com/images/banner_logo.png" width="15" height="10">

                </a>-->
        </div>
    </div>

    <script>
        $(".show-btn").click(function(){
            $(".sm-menu").fadeToggle("slow");
        });
    </script>
</#macro>

<#macro slideMenu>
    <style>
        a{text-decoration: none;}
    </style>

    <a class="show-btn w3-bar-item w3-button" style="height: 38px;" href="javascript:void(0);"
       onclick="slideMenuToggleFunction()" title="Toggle Navigation Menu">
        <i class="fa fa-bars"></i>
    </a>
    <div id="slideMenu" class="middle w3-bar-item">
        <div class="sm-container">
            <div class="sm-menu" style="position:fixed;">
                <a href="/aims" ><i class="fa fa-circle-o"></i>ALL AIMS</a>
                <a href="/main_aim" ><i class="fa fa-dot-circle-o"></i>S.M.A.R.T</a>
                <a href="/ten_thousand_hours_aim" ><i class="fa fa-dot-circle-o"></i>10k H</a>
                <a href="/main" ><i class="fa fa-book"></i>NOTES</a>
                <a href="/userAnalyzer/activity" ><i class="fa fa-circle-o"></i>USER ANALYZER</a>
                <a href="/photos" ><i class="fa fa-picture-o"></i>PHOTOS</a>
                <a href="/user" ><i class="fa fa-user-circle"></i>USERS</a>
            </div>
        </div>
    </div>

    <script>
        $(".show-btn").click(function(){
            $(".sm-menu").fadeToggle("slow");
        });
    </script>
</#macro>

<#macro loginMenu>
    <style>
        .fa{margin-right: 5px;}
    </style>

    <div class="w3-top">
        <div class="w3-bar" id="myNavBar">
            <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>HOME</a>
            <a href="#" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>UP</a>
            <a href="/login" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-in"></i></a>
        </div>

        <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
            <a href="/" class="w3-bar-item w3-button w3-right w3-hover-red"><i class="fa fa-home"></i></a>
            <a href="#" class="w3-bar-item w3-button w3-right w3-hover-red"><i class="fa fa-chevron-up"></i>UP</a>
            <a href="/login" class="w3-bar-item w3-button w3-right w3-hover-red"><i class="fa fa-sign-in"></i></a>
        </div>
    </div>
</#macro>
