<#macro main title="Events recommendation system">
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>${title}</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" type="image/png" href="/static/images/application icon.png">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet"  type='text/css'>
        <link rel="stylesheet" href="/static/css/style.css">
        <link rel="stylesheet" href="/static/css/buttons.css">
        <link rel="stylesheet" href="/static/css/photos.css">
        <link rel="stylesheet" href="/static/css/menu.css">
        <link rel="stylesheet" href="/static/css/registration.css">
        <link rel="stylesheet" href="/static/css/login.css">
        <link rel="stylesheet" href="/static/css/event-style.css">
        <link rel="stylesheet" href="/static/css/new-menu.css">
        <link rel="stylesheet" href="/static/css/rating-stars.css"/>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>document.getElementsByTagName("html")[0].className += " js";</script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <#import "footer.ftlh" as footer>
        <#--<#import "image.ftlh" as paralax>-->
        <#import "menu.ftlh" as menu>
        <#import "elements.ftlh" as elements/>
        <#--<#import "user_events.ftlh" as user_events/>-->
    </head>
    <body>
        <@menu.customMenu menuElements slideMenuElements/>
        <#nested>
        <@footer.footer/>
    </body>
    </html>
</#macro>