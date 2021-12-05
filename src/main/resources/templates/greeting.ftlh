<#import "parts/main.ftlh" as main/>

<@main.main title="Welcome">
    <div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Event recommendation system</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="about">
        <h3 class="w3-center w3-black ">PERSONAL GOAL SETTING</h3>
        <p class="w3-center article"><em>Planning to Live Your Life Your Way</em></p>
        <p class="article-text">
            Many people feel as if they're adrift in the world.
            They work hard,but they don't seem to get anywhere worthwhile.
            A key reason that they feel this way is that they haven't spent enough time
            thinking about what they want from life,and haven't set themselves
            formal goals.After all,would you set out on a major journey with no
            real idea of your destination?Probably not!
        </p>

        <div class="w3-row">
            <div class="w3-col m6 w3-center w3-padding-large">
                <img id="myselfPhoto" src="/static/images/planingSticks.jpg"
                     class="w3-round w3-image w3-opacity w3-hover-opacity-off"
                     onclick="onClick(this); setMaxHeightAndWight('myselfPhoto');" alt="Planing stickers" width="100%">
                <p>
                    <b>
                        <i class="fa fa-dot-circle-o w3-margin-right"></i> Goals
                    </b>
                </p>
                <br>
                <p class="article-text">
                    You will also raise your...
                </p>
            </div>

            <div class="w3-col m6 w3-hide-small w3-padding-large">
                <h3 class="w3-center article">Why Set Goals?</h3>

                <p class="article-text">
                    Top-level athletes,successful businesspeople...
                </p>
            </div>
        </div>
    </div>

    <div class="w3-row w3-center w3-dark-grey w3-padding-16">
        <div class="w3-quarter w3-section">
            S.M.A.R.T goal
        </div>
        <div class="w3-quarter w3-section">
            Ten thousand hours rule
        </div>
        <div class="w3-quarter w3-section">
            Time management
        </div>
        <div class="w3-quarter w3-section">
            Time analyzer
        </div>
    </div>

    <div class="parallax big-img-greeting-small w3-display-container w3-opacity-min" id="submitted">
    </div>

</@main.main>