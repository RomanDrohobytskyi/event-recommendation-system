<!doctype html>
<html lang="en">
<head>
    <title>Events</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script>document.getElementsByTagName("html")[0].className += " js";</script>
    <link rel="stylesheet" href="../static/css/event-style.css">

    <link rel="shortcut icon" type="image/png" href="https://cdn.onlinewebfonts.com/svg/img_51582.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/photos.css">
    <link rel="stylesheet" href="/static/css/text.css">
    <link rel="stylesheet" href="/static/css/menu.css">

    <script type="text/javascript" src="/static/javascript/js.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/a39ccd76f8.js" crossorigin="anonymous"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <#import "parts/footer.ftlh" as footer>
    <#import "parts/menu.ftlh" as menu>
    <#import "parts/elements.ftlh" as elements>
    <#import "parts/event.ftlh" as event>
</head>
<body>
<@menu.customMenu menuElements slideMenuElements true/>

<div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Events creation</span>
    </div>
</div>

<div class="w3-content w3-container w3-padding-32" id="add-event">
    <div class="w3-center w3-row">
        <h3 class="w3-center">Create new event</h3>
        <form action="/events/creation/add" method="post">
            <p class="w3-center" style="color: red;">
                ${message!}
            </p>
            <br>
            <@elements.input id="title" name="title" type="text" placeholder="t e x t" value="test"/>
            <br><br>
            <@elements.input id="from" name="from" type="time" placeholder="f r o m ( H H : M M )" min="09:00" max="18:00"/>
            <br><br>
            <@elements.input id="to" name="to" type="time" placeholder="t o ( H H : M M )" min="09:00" max="18:00"/>
            <br><br>
            <@elements.input id="date" name="date" type="date" placeholder="d a t e"/>

            <br><br>
            <@elements.input id="country" name="country" type="text" placeholder="c o u n t r y" value="Poland"/>
            <br><br>
            <@elements.input id="city" name="city" type="text" placeholder="c i t y" value="Lublin"/>
            <br><br>
            <@elements.input id="address" name="address" type="text" placeholder="a d d r e s s" value="Test 1, 1"/>
            <br><br>
            <@elements.input id="zip" name="zipCode" type="text" placeholder="11-111" pattern="[0-9]{5}" value="11-111"/>
            <br><br>
            <select name="eventType">
                <#list eventTypes as type>
                    <option value="${type}">${type}</option>
                </#list>
            </select>
            <br><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn1 w3-button w3-padding-large" style="box-shadow:none;">
                Add
            </button>
        </form>
    </div>
</div>

<div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="creation">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity" title="Events">Events table</span>
    </div>
</div>

<div>
    <div class="w3-content w3-container w3-padding-32" id="choose-event">
        <div class="w3-center w3-row">
            <h3 class="w3-center">Choose event</h3>
            <form action="/events/creation" method="get" style="margin: 30px 10px 10px">
                <button type="submit" value="${'SPORT'}" name="eventType"
                        title="Sport" style="border: none;" class="fas fa-running">
                </button>
                <button type="submit" value="${'ART'}" name="eventType"
                        title="Art" style="border: none;" class="fas fa-palette">
                </button>
                <button type="submit" value="${'EDUCATION'}" name="eventType"
                        title="Education" style="border: none;" class="fas fa-graduation-cap">
                </button>
                <button type="submit" name="eventType"
                        title="Clear" style="border: none;" class="fas fa-eraser">
                </button>
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </form>
        </div>
    </div>
    <div>
        <@event.events events/>
    </div>
</div>

<div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Events created by ${user.username}</span>
    </div>
</div>

<div id="created-events" style="max-height:900px; min-height: 150px; overflow:auto;">
    <table style="text-align: center; width: 100%">
        <tr>
            <th>Title</th>
            <th>From</th>
            <th>To</th>
            <th>Date</th>
            <th>Day of week</th>
            <th>Created By</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <#if user.createdEvents?has_content>
            <#list user.createdEvents as event>
                <#if event.isActive()>
                    <tr id="event_${event.id}" style="height: 100px">
                        <td><b>${event.title}</b></td>
                        <td><b>${event.from}</b></td>
                        <td><b>${event.to}</b></td>
                        <td><b>${event.date}</b></td>
                        <td><b>${event.dayOfWeek.day}</b></td>
                        <td><b>${event.creator.email}</b></td>
                        <td>
                            <div>
                                <a href="/events/creation/edit/${event.id}">
                                    <i class="fa fa-pencil" aria-hidden="true" title="Edit event"></i>
                                </a>
                                <input type="hidden" value="${event}" name="event">
                            </div>
                        </td>
                        <td>
                            <div>
                                <a href="/events/creation/delete/${event.id}">
                                    <i class="fa fa-trash-o" aria-hidden="true" title="Delete event"></i>
                                </a>
                                <input type="hidden" value="${event}" name="event">
                            </div>
                        </td>
                    </tr>
                </#if>
            </#list>
        <#else>
            <p style="margin: 20px; font-weight: bold;">No created events yet</p>
        </#if>
    </table>
</div>

<div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="deleted">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Deleted events</span>
    </div>
</div>

<div id="created-events" style="max-height:900px; min-height: 150px; overflow:auto;">
    <table id="submittedEvents" style="text-align: center; width: 100%">
        <tr>
            <th>Title</th>
            <th>From</th>
            <th>To</th>
            <th>Date</th>
            <th>Day of week</th>
            <th>Created By</th>
            <th>Renew</th>
        </tr>
        <#if user.createdEvents?has_content>
            <#list user.createdEvents as event>
                <#if !event.isActive()>
                    <tr id="event_${event.id}" style="height: 100px">
                        <td><b>${event.title}</b></td>
                        <td><b>${event.from}</b></td>
                        <td><b>${event.to}</b></td>
                        <td><b>${event.date}</b></td>
                        <td><b>${event.dayOfWeek.day}</b></td>
                        <td><b>${event.creator.email}</b></td>
                        <td>
                            <div>
                                <a href="/events/creation/reenew/${event.id}">
                                    <i class="fa fa-pencil" aria-hidden="true" title="Renew event"></i>
                                </a>
                                <input type="hidden" value="${event}" name="event">
                            </div>
                        </td>
                    </tr>
                </#if>
            </#list>
        <#else>
            <p style="margin: 20px; font-weight: bold;">No deleted events yet</p>
        </#if>
    </table>
</div>


<script src="../static/javascript/util.js"></script>
<script src="../static/javascript/main.js"></script>

<div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="end">
</div>

<@footer.footer/>
</body>
</html>