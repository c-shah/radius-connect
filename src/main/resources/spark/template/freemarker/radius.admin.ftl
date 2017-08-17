<#if loggedInToConnectedApp == true>
    <br>
    &nbsp; Radius Cloud : Currently Logged In
    <br>
    &nbsp; Oauth Configurtion
    <br>
        &nbsp;&nbsp; Id   : ${oauthConfigurationId}
    <br>
        &nbsp;&nbsp; Code : ${oauthConfigurationCode}
    <br>
        &nbsp;&nbsp; Token : ${oauthConfigurationAccessToken}
    <br>
        &nbsp;&nbsp; Instance URL : ${oauthConfigurationInstanceURL}
    <br>
        &nbsp;&nbsp; Token Id : ${oauthConfigurationInstanceTokenId}
<#else>
    <br>
    &nbsp;Radius Cloud : <a href="${salesforceLoginURL}">Login</a> to Salesforce

</#if>


<br>
<br>
<br>
<br>
