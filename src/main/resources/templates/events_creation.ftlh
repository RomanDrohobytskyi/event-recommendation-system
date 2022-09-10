<#import "parts/main.ftlh" as main/>
<#import "parts/event.ftlh" as event/>
<#import "parts/elements.ftlh" as elements/>

<@main.main title="Events creation">

    <script>
        $(document).ready(function() {
            document.getElementById('date').valueAsDate = new Date();
        });
    </script>

    <div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Events creation</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="add-event">
        <h3 class="w3-center">Create new event</h3>
        <form action="/events/creation/add" method="post">
            <div class="w3-row">
                <div class="w3-col m6 w3-center w3-padding-large">
                    <p class="w3-center" style="color: red;">
                        ${message!}
                    </p>
                    <br>
                    <@elements.input id="title" name="title" type="text" placeholder="t i t l e" value="test" additionalParams="required"/>
                    <br><br>
                    <@elements.input id="description" name="description" type="text" placeholder="d e s c r i p t i on" value="test description" additionalParams="required"/>
                    <br><br>
                    <@elements.input id="from" name="from" type="time" value="10:00" placeholder="f r o m ( H H : M M )" min="09:00" max="18:00" additionalParams="required"/>
                    <br><br>
                    <@elements.input id="to" name="to" type="time" value="11:00" placeholder="t o ( H H : M M )" min="09:00" max="18:00" additionalParams="required"/>
                    <br><br>
                    <@elements.input id="date" name="date" type="date" placeholder="d a t e" additionalParams="required"/>
                    <br><br>
                    <@elements.input id="country" name="country" type="text" placeholder="c o u n t r y" value="Poland"/>
                    <br><br>
                    <@elements.input id="city" name="city" type="text" placeholder="c i t y" value="Lublin"/>
                    <br><br>
                    <@elements.input id="address" name="address" type="text" placeholder="a d d r e s s" value="Test 1, 1"/>
                    <br><br>
                    <@elements.input id="zip" name="zipCode" type="text" placeholder="11-111" pattern="[0-9]{5}" value="11-111"/>
                    <br><br>
                    <select name="type">
                        <#list eventTypes as type>
                            <option value="${type}">${type}</option>
                        </#list>
                    </select>
                    <br><br>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn1 w3-button w3-padding-large" style="box-shadow:none;">
                        Add
                    </button>
                </div>

                <div class="w3-col m6 w3-hide-small w3-padding-large">
                    <h3 class="w3-center article">Choose tags</h3>
                    <div class="w3-center">
                        <#list tags?keys as eventType>
                            <#if eventType?has_content && tags[eventType]?has_content>
                                <br>
                                ${eventType}
                                <#list tags[eventType] as tag>
                                    <div>
                                        <label>
                                            <input type="checkbox" name="${tag.name}">
                                            ${tag.description}
                                        </label>
                                    </div>
                                </#list>
                            </#if>
                        </#list>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="created-events">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Actual events created by ${user.username}</span>
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
            <#if createdActiveEvents?has_content>
                <#list createdActiveEvents as event>
                    <tr id="event_${event.id}" style="height: 100px">
                        <td><b>${event.title}</b></td>
                        <td><b>${event.from}</b></td>
                        <td><b>${event.to}</b></td>
                        <td><b>${event.date}</b></td>
                        <td><b>${event.dayOfWeek.day}</b></td>
                        <td><b>${event.creator.email}</b></td>
                        <td>

                            <div id="edit-${event.id}">
                                <a href="/events/creation/edit/${event.id}">
                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                </a>
                                <input type="hidden" value="${event}" name="event">
                            </div>
                        </td>
                        <td>
                            <div id="delete-${event.id}">
                                <form action="/events/creation/delete" method="post">
                                    <button type="submit" class="btn-link">
                                        <i class="fa fa-trash"></i>
                                    </button>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <input type="hidden" value="${event.id}" name="event">
                                </form>
                            </div>
                        </td>
                    </tr>
                </#list>
            <#else>
                <p style="margin: 20px; font-weight: bold;">No created events yet</p>
            </#if>
        </table>
    </div>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="deleted">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Out of date / deleted events</span>
        </div>
    </div>

    <div id="deleted-created-events" style="max-height:900px; min-height: 150px; overflow:auto;">
        <table id="deleted-events" style="text-align: center; width: 100%">
            <tr>
                <th>Title</th>
                <th>From</th>
                <th>To</th>
                <th>Date</th>
                <th>Day of week</th>
                <th>Created By</th>
                <th>Renew</th>
            </tr>
            <#if deletedNotActiveEvents?has_content>
                <#list deletedNotActiveEvents as event>
                    <tr id="not_active_event_${event.id}" style="height: 100px">
                        <td><b>${event.title}</b></td>
                        <td><b>${event.from}</b></td>
                        <td><b>${event.to}</b></td>
                        <td><b>${event.date}</b></td>
                        <td><b>${event.dayOfWeek.day}</b></td>
                        <td><b>${event.creator.email}</b></td>
                        <td>
                            <div>
                                <form action="/events/creation/renew" method="post">
                                    <button type="submit" class="btn-link">
                                        <i class="fa fa-circle-o-notch" aria-hidden="true"></i>
                                    </button>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <input type="hidden" value="${event.id}" name="event">
                                </form>
                            </div>
                        </td>
                    </tr>
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
</@main.main>