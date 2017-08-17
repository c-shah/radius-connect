<#if loggedInToConnectedApp == true>
    <br>
    &nbsp; Radius Cloud : Currently Logged In
    <br>
    &nbsp; Oauth Configurtion
    <br>
        &nbsp;&nbsp; Id   : ${oauthConfigurationId}
    <br>
        &nbsp;&nbsp;Code : ${oauthConfigurationCode}

<#else>
    <br>
    &nbsp;Radius Cloud : <a href="${salesforceLoginURL}">Login</a> to Salesforce

</#if>


<br>
<br>
<br>
<br>
