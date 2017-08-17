<#if loggedInToConnectedApp == true>

    Radius Cloud : Currently Logged In

    Oauth Configurtion
        Id   : ${oauthConfigurationId}
        Code : ${oauthConfigurationCode}

<#else>

    Radius Cloud : <a href="${salesforceLoginURL}">Login</a> to Salesforce

</#if>


<br>
<br>
<br>
<br>
