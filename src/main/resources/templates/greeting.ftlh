<#import "parts/main.ftlh" as main/>

<@main.main title="Events recommendation">
    <div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">
                Events recommendation system
            </span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="about">
        <h3 class="w3-center w3-black">SOCIAL EVENTS RECOMMENDATION SYSTEM</h3>
        <p class="w3-center article"><em>Find or create social events</em></p>
        <p class="article-text">
            The Events recommendation application is based on a recommendation algorithm
            that will recommend events based on Your preferences.
            Using this application You able to create events and manage them,
            find interested events, submit attendance, save, and edit personal data.
            This web application provides simple and user-friendly interface to make usage as easy as possible.
        </p>

        <div class="w3-row">
            <div class="w3-col m6 w3-center w3-padding-large">
                <img id="myselfPhoto" src="/static/images/events.jpg"
                     class="w3-round w3-image w3-opacity w3-hover-opacity-off" alt="Events">
                <p>
                    <b>
                        Events
                    </b>
                </p>
                <br>
                <p class="article-text">

                </p>
            </div>

            <div class="w3-col m6 w3-hide-small w3-padding-large">
                <h3 class="w3-center article">Events</h3>
                <p class="article-text">
                    Using the Events recommendation system You can create own Events
                    and share it for everyone and everyone can join your event.
                    <br>
                    <br>
                    The Events recommendation is a useful web application,
                    using it You can find and join all interested events,
                    meet new people, find some motivation,
                    spend you time with fun or make a new friendship.
                </p>
            </div>
        </div>

        <div class="w3-center">
            <p class="article-text">
                To start using Events recommendation web application You just need to
                <a href="/registration" style="text-decoration: none;">
                    <b> create account </b>
                    <i class="fa fa-user-plus"></i>
                </a>
                 and have fun.
            </p>
        </div>
    </div>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="footer">
    </div>

</@main.main>