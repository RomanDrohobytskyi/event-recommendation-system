<#macro events events>
    <div class="cd-schedule cd-schedule--loading margin-top-lg margin-bottom-lg js-cd-schedule">
        <@timeline/>
        <@eventsTable events/>
        <@eventDescription/>
    </div>
</#macro>

<#macro timeline>
  <div class="cd-schedule__timeline">
      <ul>
          <li><span>09:00</span></li>
          <li><span>09:30</span></li>
          <li><span>10:00</span></li>
          <li><span>10:30</span></li>
          <li><span>11:00</span></li>
          <li><span>11:30</span></li>
          <li><span>12:00</span></li>
          <li><span>12:30</span></li>
          <li><span>13:00</span></li>
          <li><span>13:30</span></li>
          <li><span>14:00</span></li>
          <li><span>14:30</span></li>
          <li><span>15:00</span></li>
          <li><span>15:30</span></li>
          <li><span>16:00</span></li>
          <li><span>16:30</span></li>
          <li><span>17:00</span></li>
          <li><span>17:30</span></li>
          <li><span>18:00</span></li>
      </ul>
  </div>
</#macro>

<#macro eventsTable events>
    <div class="cd-schedule__events">
        <ul>
            <#assign colorNumber = 1/>
            <#list events?keys as day>
                <li class="cd-schedule__group">
                    <div class="cd-schedule__top-info"><span>${day}</span></div>
                    <ul>
                        <#if events[day]?has_content>
                            <#list events[day] as event>
                                <#if event.isActive()>
                                    <li class="cd-schedule__event">
                                        <a data-start="${event.from}" data-end="${event.to}"
                                           data-content="event-abs-circuit" data-event="event-${colorNumber}" href="#0">
                                            <em class="cd-schedule__name">${event.title}</em>
                                            <#--<a href="/events/delete/${event.id}">
                                                <i class="fa fa-trash-o" aria-hidden="true"
                                                   title="Delete event"></i>
                                            </a>
                                            <input type="hidden" value="${event}" name="event">-->
                                        </a>
                                    </li>
                                    <#if colorNumber == 4>
                                        <#assign colorNumber = 1/>
                                    <#else>
                                        <#assign colorNumber = colorNumber + 1/>
                                    </#if>
                                </#if>
                            </#list>
                        </#if>
                    </ul>
                </li>
            </#list>
        </ul>
    </div>
</#macro>

<#macro dayEvents events dayName>
    <li class="cd-schedule__group">
        <div class="cd-schedule__top-info"><span>${dayName}</span></div>
        <#list events as event>
            <ul>
                <li class="cd-schedule__event">
                    <a data-start="${event.from}" data-end="${event.to}" data-content="event-abs-circuit"
                       data-event="event-1" href="#0">
                        <em class="cd-schedule__name">${event.title}</em>
                    </a>
                </li>
            </ul>
        </#list>
    </li>
</#macro>

<#macro eventDescription>
     <div class="cd-schedule-modal">
         <header class="cd-schedule-modal__header">
             <div class="cd-schedule-modal__content">
                 <span class="cd-schedule-modal__date"></span>
                 <h3 class="cd-schedule-modal__name"></h3>
             </div>
             <div class="cd-schedule-modal__header-bg"></div>
         </header>

         <div class="cd-schedule-modal__body">
             <div class="cd-schedule-modal__event-info"></div>
             <div class="cd-schedule-modal__body-bg"></div>
         </div>
         <a href="#0" class="cd-schedule-modal__close text-replace">Close</a>
     </div>

     <div class="cd-schedule__cover-layer"></div>
</#macro>

<#macro eventsWithRegistration events>
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
                <th>Rate</th>
                <th>Avg score</th>
            </tr>
            <#if events?has_content>
                <#list events as event>
                    <tr id="event_${event.id}" style="height: 100px;text-align: center;">
                        <td><b>${event.title}</b></td>
                        <td><b>${event.from}</b></td>
                        <td><b>${event.to}</b></td>
                        <td><b>${event.date}</b></td>
                        <td><b>${event.dayOfWeek.day}</b></td>
                        <td><b>${event.creator.email}</b></td>
                        <td>
                            <div>
                                <form action="/events/register" method="post" name="register">
                                    <button type="submit" class="btn-link">
                                        <i class="fa fa-check-circle"></i>
                                    </button>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <input type="hidden" value="${user.id}" name="user">
                                    <input type="hidden" value="${event.id}" name="event">
                                </form>
                            </div>
                        </td>
                        <td>
                            <div>
                                <form action="/events/rate/" method="post">
                                    <div class="rate">
                                        <input type="radio" id="star5_${event.id}" name="score" value="5" onclick="this.form.submit();"/>
                                        <label for="star5_${event.id}" title="text">5 stars</label>
                                        <input type="radio" id="star4_${event.id}" name="score" value="4" onclick="this.form.submit();"/>
                                        <label for="star4_${event.id}" title="text">4 stars</label>
                                        <input type="radio" id="star3_${event.id}" name="score" value="3" onclick="this.form.submit();"/>
                                        <label for="star3_${event.id}" title="text">3 stars</label>
                                        <input type="radio" id="star2_${event.id}" name="score" value="2" onclick="this.form.submit();"/>
                                        <label for="star2_${event.id}" title="text">2 stars</label>
                                        <input type="radio" id="star1_${event.id}" name="score" value="1" onclick="this.form.submit();"/>
                                        <label for="star1_${event.id}" title="text">1 star</label>

                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                        <input type="hidden" value="${user.id}" name="evaluator">
                                        <input type="hidden" value="${event.id}" name="evaluatedEvent">
                                    </div>
                                </form>
                            </div>
                        </td>
                        <td><b>${event.averageScore}</b></td>
                    </tr>
                </#list>
            <#else>
                <tr id="no-recommended-events" style="height: 100px; text-align: center;">
                    <td colspan="9">
                        <b><p style="margin: 20px; font-weight: bold;">No events for ${user.username}</p></b>
                    </td>
                </tr>
            </#if>
        </table>
    </div>
</#macro>