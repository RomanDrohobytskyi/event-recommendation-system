<#import "parts/elements.ftlh" as elements/>
<#import "parts/main.ftlh" as main/>

<@main.main title="Login">
    <div class="parallax big-img-login-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">LOGIN</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="login">
        <div class="w3-center w3-row">
            <form method="get" action="/loginError">
                <p style="color: red">
                ${validatorError!}
                ${message!}
                </p>
            </form>

            <form method="post" action="/login">
                <p class="w3-center">Email: </p>

                <@elements.input id="login" name="username" type="email" placeholder="e m a i l . . ."
                    class="login-input" value="roman.drohobytskyi@gmail.com"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <p class="w3-center">Password: </p>

                <@elements.input id="password" name="password" type="password" placeholder="p a s s w o r d . . ."
                    class="login-input" value="111"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <br>
                <button type="submit" class="btn btn1 w3-button w3-padding-large">Sign In</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
            </form>
            <h5>Not registered yet? Create
                <a href="/registration" style="text-decoration: none;">
                    <b>account</b>
                    <i class="fa fa-user-plus"></i>
                </a>
                !
            </h5>
        </div>
    </div>

    <div class="parallax big-img-login-1 w3-display-container w3-opacity-min">
    </div>

</@main.main>