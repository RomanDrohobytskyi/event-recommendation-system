<#macro table events>
<#--    <div style="max-height:900px; min-height: 150px; overflow:auto;">
        <table id="actual-events" style="text-align: center; width: 100%">
            <tr>
                <th>Title</th>
                <th>From</th>
                <th>To</th>
                <th>Date</th>
                <th>Day of week</th>
                <th>Created By</th>
                <th>Discard</th>
            </tr>
            <#if events?has_content>
                <#list events as event>
                    <tr id="event_${event.id}" style="height: 100px">
                        <td><b>${event.title}</b></td>
                        <td><b>${event.from}</b></td>
                        <td><b>${event.to}</b></td>
                        <td><b>${event.date}</b></td>
                        <td><b>${event.dayOfWeek.day}</b></td>
                        <td><b>${event.creator.email}</b></td>
                        <td>
                            <form action="/events/user/discard" method="post">
                                <button class="btn-link" type="submit">
                                    <i class="fa fa-ban" aria-hidden="true" title="Discard event registration"></i>
                                </button>
                                <input type="hidden" value="${event.id}" name="event">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            </form>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr>
                    <td colspan="7">
                        <p style="margin: 20px; font-weight: bold;">No events</p>
                    </td>
                </tr>
            </#if>
        </table>
    </div>-->
</#macro>