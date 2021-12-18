<#import "parts/event.ftlh" as event/>
<#import "parts/main.ftlh" as main/>

<@main.main title="Events">

    <div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="recommended">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Events based on Your preferences</span>
        </div>
    </div>

    <@event.eventsWithRegistration events=userPreferencesEvents/>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="all">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">All events</span>
        </div>
    </div>

    <@event.eventsWithRegistration events=otherEvents/>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="end">
    </div>
</@main.main>