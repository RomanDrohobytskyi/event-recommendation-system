<#import "parts/user_events.ftlh" as events>
<#import "parts/main.ftlh" as main/>

<@main.main title="User events">
    <div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Actual ${user.username} events</span>
        </div>
    </div>

    <@events.table events=actualEvents/>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="out-of-date">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Out of date ${user.username} events</span>
        </div>
    </div>

    <@events.table events=outOfDateEvents/>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="end">
    </div>
</@main.main>