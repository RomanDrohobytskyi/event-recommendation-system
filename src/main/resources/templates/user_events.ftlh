<#import "parts/main.ftlh" as main/>
<#import "parts/event.ftlh" as event/>
<#--<#import "parts/user_events.ftlh" as events>-->

<@main.main title="User events">

    <script>
        $(document).ready(function() {
            document.getElementById('date').valueAsDate = new Date();
        });
    </script>

    <div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Actual ${user.username} events</span>
        </div>
    </div>

    <#--<@events.table events=actualEvents/>-->

    <div>
        <div class="w3-content w3-container w3-padding-32" id="choose-event">
            <div class="w3-center w3-row">
                <h3 class="w3-center">Choose event</h3>
                <form action="/events/user" method="get" style="margin: 30px 10px 10px">
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


    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="out-of-date">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Out of date ${user.username} events</span>
        </div>
    </div>

    <#--<@events.table events=outOfDateEvents/>-->

    <script src="../static/javascript/util.js"></script>
    <script src="../static/javascript/main.js"></script>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="end">
    </div>
</@main.main>