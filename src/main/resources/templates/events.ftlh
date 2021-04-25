<!DOCTYPE html>
<html lang="en">
<head>
    <title>Events</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script>document.getElementsByTagName("html")[0].className += " js";</script>
    <link rel="stylesheet" href="../static/css/event-style.css"/>

    <link rel="shortcut icon" type="image/png" href="https://cdn.onlinewebfonts.com/svg/img_51582.png"/>
    <link rel="stylesheet" href="/static/css/style.css"/>
    <link rel="stylesheet" href="/static/css/buttons.css"/>
    <link rel="stylesheet" href="/static/css/photos.css"/>
    <link rel="stylesheet" href="/static/css/text.css"/>
    <link rel="stylesheet" href="/static/css/menu.css"/>

    <script type="text/javascript" src="/static/javascript/js.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/a39ccd76f8.js" crossorigin="anonymous"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

    <#import "parts/footer.ftlh" as footer/>
    <#import "parts/image.ftlh" as paralax/>
    <#import "parts/menu.ftlh" as menu/>
    <#import "parts/elements.ftlh" as elements/>
    <#import "parts/event.ftlh" as event/>
</head>
<body>

<@menu.customMenu menuElements slideMenuElements true/>

<div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="recommended">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Events based on Your preferences</span>
    </div>
</div>

<div style="max-height:900px; min-height: 150px; overflow:auto;">
    <table id="events" style="text-align: center; width: 100%">
        <tr>
            <th>Title</th>
            <th>From</th>
            <th>To</th>
            <th>Date</th>
            <th>Day of week</th>
            <th>Creator</th>
            <th>Register</th>
        </tr>
        <#if userPreferencesEvents?has_content>
            <#list userPreferencesEvents as event>
                <tr id="event_${event.id}" style="height: 100px;text-align: center;">
                    <td><b>${event.title}</b></td>
                    <td><b>${event.from}</b></td>
                    <td><b>${event.to}</b></td>
                    <td><b>${event.date}</b></td>
                    <td><b>${event.dayOfWeek.day}</b></td>
                    <td><b>${event.creator.email}</b></td>
                    <td>
                        <div>
                            <a href="/main/register/${event.id}">
                                <i class="fa fa-check-circle" aria-hidden="true" title="Submit for an event"></i>
                            </a>
                            <input type="hidden" value="${event}" name="event">
                        </div>
                    </td>
                </tr>
            </#list>
        <#else>
            <tr id="no-recommended-events" style="height: 100px; text-align: center;">
                <td colspan="7">
                    <b><p style="margin: 20px; font-weight: bold;">No recommended events for ${user.username}</p></b>
                </td>
            </tr>
        </#if>
    </table>
</div>

<div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="all">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">All events</span>
    </div>
</div>

<div style="max-height:900px; min-height: 150px; overflow:auto;">
    <table id="events" style="text-align: center; width: 100%">
        <tr>
            <th>Title</th>
            <th>From</th>
            <th>To</th>
            <th>Date</th>
            <th>Day of week</th>
            <th>Creator</th>
            <th>Register</th>
        </tr>
        <#if otherEvents?has_content>
            <#list otherEvents as event>
                <tr id="event_${event.id}" style="height: 100px;text-align: center;">
                    <td><b>${event.title}</b></td>
                    <td><b>${event.from}</b></td>
                    <td><b>${event.to}</b></td>
                    <td><b>${event.date}</b></td>
                    <td><b>${event.dayOfWeek.day}</b></td>
                    <td><b>${event.creator.email}</b></td>
                    <td>
                        <div>
                            <a href="/events/register/${event.id}">
                                <i class="fa fa-check-circle" aria-hidden="true" title="Submit for an event"></i>
                            </a>
                            <input type="hidden" value="${event}" name="event"/>
                        </div>
                    </td>
                </tr>
            </#list>
        <#else>
            <tr id="no-events" style="height: 100px;text-align: center;">
                <td colspan="7">
                    <b style="margin: 20px; font-weight: bold;">No events yet</b>
                </td>
            </tr>
        </#if>
    </table>
</div>

<div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="end">
</div>

<@footer.footer/>
</body>
</html>