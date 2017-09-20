<br>
    Name : Chintan Shah
<br>
    Phone : 206 419 0241
<#if loggedInToConnectedApp == true>
  Add Contact to Salesforce ? <a href="/radius.connect.add.contact"> add contact </a>
<#else>

</#if>

<br>
<br>

queryParams ${queryParams}

<a href="javascript:void(0);" onclick="refresh();">

<script>
    function refresh() {
        parent.location.reload();
    }
</script>

<br>
<br>
<br>
<br>
